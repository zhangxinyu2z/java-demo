import org.junit.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Arnoer
 * @since 2022/10/13 15:34
 */
public class DemoSocket {
    private final String BLANK = " ";    //空格符
    private final String CRLF = "\r\n";  //回车换行符

    @Test
    public void server() {
        start();
    }

    public void start() {
        System.out.println("服务启动。。。");
        try (ServerSocket server = new ServerSocket(8899)) {
            while (true) {
                Socket client = server.accept();
                InputStream in = new BufferedInputStream(client.getInputStream());       //获取HTTP请求报文
                byte[] b = new byte[5 * 1024];  //这里因为网络流读取不是以 -1 来判断结束，处理比较复杂，
                int len = in.read(b);         //所以我使用一个大点的字节数组，直接读取整个报文
                //不对请求报文进行处理，直接响应一个固定的数据。
                OutputStream out = new BufferedOutputStream(client.getOutputStream());   //发送HTTP响应报文

                Charset UTF_8 = StandardCharsets.UTF_8;  //使用 UTF-8 作为字符集
                String json = "{\"name\":\"龙林夕\", \"words\":\"I love you yesterday and today\"}";
                byte[] responseBody = json.getBytes(UTF_8);

                StringBuilder header = new StringBuilder();
                byte[] responseHeader =
                    header.append("HTTP/1.0").append(BLANK).append(200).append(BLANK).append("OK").append(CRLF)  // 响应头部
                        .append("Server:" + "CrazyDragon").append(CRLF).append("Date:").append(BLANK)
                        .append(this.getDate()).append(CRLF).append("Content-Type:").append(BLANK)
                        .append("application/json").append(CRLF).append("Content-Length:").append(BLANK)
                        .append(responseBody.length).append(CRLF).append(CRLF).toString().getBytes(UTF_8);

                //发送响应报文
                out.write(responseHeader);
                out.write(responseBody);
                out.flush();  //刷新输出流。
                //打印请求报文
                System.out.println(new String(b, 0, len, UTF_8));

                //把报文写入文件，并且使用追加的方式，否则请求图片的报文会覆盖我的请求参数的报文
                try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("D:/DragonFile/DBC/msg.txt", true), UTF_8))) {
                    writer.write(new String(b, 0, len, UTF_8));
                }

                //关闭客户端连接
                client.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("服务结束！");
    }

    //获取时间
    private String getDate() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss 'GMT'", Locale.US);
        format.setTimeZone(TimeZone.getTimeZone("GMT")); // 设置时区为GMT
        return format.format(date);
    }
}
