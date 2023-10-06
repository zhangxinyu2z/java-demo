import org.apache.commons.net.smtp.SMTPClient;
import org.apache.commons.net.smtp.SMTPReply;
import org.xbill.DNS.*;

import java.io.IOException;

/**
 * <p>
 * 当通过互联网发送电子邮件时，发送方的邮件传输代理（MTA，Mail transfer agent）将会向DNS发送请求，
 * 查询每个收件人的邮箱域名的MX记录。这个请求将会返回可以接受发往该邮箱域名的邮件交换服务器的列表，
 * 以及它们的优先级。接下来，发送方的传输代理将会尝试和这些服务器建立SMTP连接
 * 原文链接：https://baike.baidu.com/item/MX%E8%AE%B0%E5%BD%95
 * <p>
 * 1.SMTP是工作在两种情况下：一是电子邮件从客户机传输到服务器；二是从某一个服务器传输到另一个
 * 　　服务器
 * 2.SMTP是个请求/响应协议，命令和响应都是基于ASCII文本，并以CR和LF符结束。响应包括一个表示返
 * 　　回状态的三位数字代码
 * 3.SMTP在TCP协议25号端口监听连接请求
 * 4.连接和发送过程
 * SMTP协议说复杂也不复杂（明明带有“简单”这个词嘛），说简单如果你懂得Sock。不过现在只是我们利用的就是第一条中说的，从客户机传输到服务器，当我们向一台服务器发送邮件时，邮件服务器会首先验证邮件发送地址是否真的存在于本服务器上。
 * 操作的步骤如下：
 * 连接服务器的25端口（如果没有邮件服务，连了也是白连）
 * 发送helo问候
 * 发送mail from命令，如果返回250表示正确可以，连接本服务器，否则则表示服务器需要发送人验证。
 * 发送rcpt to命令，如果返回250表示则Email存在
 * 发送quit命令，退出连接
 *
 * </p>
 */
public class CheckEmailOK {

    //"no-reply@domain.com";    info@domain.com
    public static final String SENDER_EMAIL = "no-reply@domain.com";
    //"domain.com";
    public static final String SENDER_EMAIL_SERVER = SENDER_EMAIL.split("@")[1];

    /**
     * @param email The recipient's email address, it need to be validate if it is real exists or doesn't exists.
     * @return True if email is real exists, false if not.
     */
    public boolean checkEmailMethod(String email) {
        if (!email.matches("[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+")) {
            System.err.println("Format error");
            return false;
        }

        String log = "";
        String host = "";
        String hostName = email.split("@")[1];
        Record[] result = null;
        SMTPClient client = new SMTPClient();
        //设置连接超时时间,有些服务器比较慢
        client.setConnectTimeout(8000);

        try {
            // 查找MX记录
            Lookup lookup = new Lookup(hostName, Type.MX);
            lookup.run();
            if (lookup.getResult() != Lookup.SUCCESSFUL) {
                log += "找不到MX记录\n";
                return false;
            } else {
                result = lookup.getAnswers();
            }
/*
			 if(result.length > 1) { // 优先级排序
	                List<Record> arrRecords = new ArrayList<Record>();
	                Collections.addAll(arrRecords, result);
	                Collections.sort(arrRecords, new Comparator<Record>() {

	                    public int compare(Record o1, Record o2) {
	                        return new CompareToBuilder().append(((MXRecord)o1).getPriority(), ((MXRecord)o2)
	                        .getPriority()).toComparison();
	                    }

	                });
	                host = ((MXRecord)arrRecords.get(0)).getTarget().toString();
	            }
 *
 */
            // 连接到邮箱服务器

            for (int i = 0; i < result.length; i++) {
                System.out.println(result[i].getAdditionalName().toString());
                System.out.println(((MXRecord)result[i]).getPriority());
            }
            int count = 0;
            //			String tempLog ="";
            for (int i = 0; i < result.length; i++) {
                log = "";
                host = result[i].getAdditionalName().toString();
                try {
                    //连接到接收邮箱地址的邮箱服务器
                    client.connect(host);
                } catch (Exception e) {        //捕捉连接超时的抛出的异常
                    count++;
                    //如果由MX得到的result服务器都连接不上，则认定email无效
                    if (count >= result.length) {
                        log += "Connect mail server timeout...\n";
                        return false;
                    }
                }

                //服务器通信不成功
                if (!SMTPReply.isPositiveCompletion(client.getReplyCode())) {
                    client.disconnect();
                    continue;
                } else {
                    log += "MX record about " + hostName + " exists.\n";
                    log += "Connection succeeded to " + host + "\n";
                    log += client.getReplyString();

                    // HELO <$SENDER_EMAIL_SERVER>   //domain.com
                    try {
                        //这一步可能会出现空指针异常
                        client.login(SENDER_EMAIL_SERVER);
                    } catch (Exception e) {
                        return false;
                    }
                    log += ">HELO " + SENDER_EMAIL_SERVER + "\n";
                    log += "=" + client.getReplyString();

                    client.setSender(SENDER_EMAIL);
                    //为解决hotmail有的MX可能出现=550 OU-001 (SNT004-MC1F43) Unfortunately, messages from 116.246.2.245
                    // weren't sent.
                    if (client.getReplyCode() != 250) {
                        client.disconnect();
                        //把client.login 和client.setSender放在循环体内，这样所有的如果某mx不行就换其他mx，但这样会对无效的邮箱进行所有mx遍历，耗时
                        continue;
                    }
                    log += ">MAIL FROM: <" + SENDER_EMAIL + ">\n";
                    log += "=" + client.getReplyString();
                    // RCPT TO: <$email>
                    try {
                        client.addRecipient(email);
                    } catch (Exception e) {
                        return false;
                    }
                    log += ">RCPT TO: <" + email + ">\n";
                    log += "=" + client.getReplyString();

                    //最后从收件邮箱服务器返回true，说明服务器中能够找到此收件地址，邮箱有效
                    if (250 == client.getReplyCode()) {
                        return true;
                    }
                    client.disconnect();

                }
            }
            //			log+=tempLog;
            //			log += ">MAIL FROM: <"+SENDER_EMAIL+">\n";
            //			log += "=" + client.getReplyString();
            //
            //			// RCPT TO: <$email>
            //			try{
            //				client.addRecipient(email);
            //			}catch(Exception e){
            //				return false;
            //			}
            //			log += ">RCPT TO: <" + email + ">\n";
            //			log += "=" + client.getReplyString();
            //
            //			//最后从收件邮箱服务器返回true，说明服务器中能够找到此收件地址，邮箱有效
            //			if (250 == client.getReplyCode()) {
            //				return true;
            //			}
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                client.disconnect();
            } catch (IOException e) {
            }
            // print log
            System.out.println(log);
        }
        return false;
    }

    /**
     * This method is more accurate than checkEmailMethod(String email);
     *
     * @param email The recipient's email address, it need to be validate if it is real exists or doesn't exists.
     * @return True if email is real exists, false if not.
     */
    public boolean checkEmail(String email) {
        if (email.split("@")[1].equals("qq.com")) {
            if (checkEmailMethod(email) && checkEmailMethod(email) && checkEmailMethod(email)) {
                return true;
            } else {
                return false;
            }
        }
        return checkEmailMethod(email);
    }

    public static void main(String[] args) {

        CheckEmailOK ce = new CheckEmailOK();
        if (ce.checkEmail("2455921928@qq.com")) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }

}