import com.google.gson.Gson;
import com.sun.deploy.net.HttpResponse;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.*;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

/**
 * @author zhangxinyu
 * @since 2022/9/21 13:56
 */
public class SimpleDemoTest {

    @Test
    public void aSimpleExample() throws IOException {
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        formparams.add(new BasicNameValuePair("time", "1663741083314"));
        formparams.add(new BasicNameValuePair("token", "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJwc3NfaWQiOiJQUzAwMDAwMDAwMDAwMDAxNDY5NCIsInVzZXJfbmFtZSI6Iis4Ni0xNTIzNzM4NjI3MSIsInNjb3BlIjpbImFsbCJdLCJleHAiOjE2NjM4Mjc0ODMsImxvZ2luIjoiU0MxNTAwODY0MiIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJqdGkiOiJkM2IzN2UyOS1kMDRjLTQxNDctODdiZS1jYzY4MzExZDZlYzkiLCJjbGllbnRfaWQiOiJzc28ifQ.Ah0xLWkINWyWHSD3BO6zOA6hZ6sm8D_sbSUW2CG4eE2VlC-E9ZuswIHuSsHwTrUBJUWfyB1pz0wvh2nuoUy4ytMN3pge4gF-5yLUXHKyA_Bsfhk4Uak8c5cmD0vounkVgUCn1FrdowP891V7a6ZkVZ-NRAMmEQpVh9ovXkYec-E"));
        HttpEntity reqEntity = new UrlEncodedFormEntity(formparams, "utf-8");

        RequestConfig requestConfig = RequestConfig.custom()
            .setConnectTimeout(5000)//一、连接超时：connectionTimeout-->指的是连接一个url的连接等待时间, 和服务器建立连接的timeout
            .setSocketTimeout(5000)// 二、读取数据超时：SocketTimeout-->指的是连接上一个url，获取response的返回等待时间,从服务器读取数据的timeout
            .setConnectionRequestTimeout(5000) // 从连接池获取连接的timeout
            .build();

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost("http://micro.tradechina.com/blade-account-manager/account/queryAccountInfo.json");
        post.setHeader("Content-type", "application/json");
        post.setEntity(reqEntity);
        post.setConfig(requestConfig);
        HttpResponse response = client.execute(post);

        if (response.getStatusLine().getStatusCode() == 200) {
            HttpEntity resEntity = response.getEntity();
            String message = EntityUtils.toString(resEntity, "utf-8");
            System.out.println(message);
        } else {
            System.out.println("请求失败");
        }

    }

    @Test
    public void example2() {
        Gson gson = new Gson();
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        Map<Object, Object> baseRequest = new HashMap<>();

        baseRequest.put("time",System.currentTimeMillis());
        baseRequest.put("token", "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9"
            + ".eyJwc3NfaWQiOiJQUzAwMDAwMDAwMDAwMDAxNDY5NCIsInVzZXJfbmFtZSI6Iis4Ni0xNTIzNzM4NjI3MSIsInNjb3BlIjpbImFsbCJdLCJleHAiOjE2NjM4Mjc0ODMsImxvZ2luIjoiU0MxNTAwODY0MiIsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJqdGkiOiJkM2IzN2UyOS1kMDRjLTQxNDctODdiZS1jYzY4MzExZDZlYzkiLCJjbGllbnRfaWQiOiJzc28ifQ.Ah0xLWkINWyWHSD3BO6zOA6hZ6sm8D_sbSUW2CG4eE2VlC-E9ZuswIHuSsHwTrUBJUWfyB1pz0wvh2nuoUy4ytMN3pge4gF-5yLUXHKyA_Bsfhk4Uak8c5cmD0vounkVgUCn1FrdowP891V7a6ZkVZ-NRAMmEQpVh9ovXkYec-E");

//        baseRequest.setAppVersion(appBehaviorRequest.getAppVersion());
//        baseRequest.setDevice(appBehaviorRequest.getDevice());
        //第一步：创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //第二步：创建httpPost对象
        try {
            String url = "http://micro.tradechina.com/blade-account-manager/account/queryAccountInfo.json";
            HttpPost httpPost = new HttpPost(url);
            //第三步：给httpPost设置JSON格式的参数
            StringEntity requestEntity = new StringEntity(gson.toJson(baseRequest), "utf-8");
            requestEntity.setContentEncoding("UTF-8");
            httpPost.setHeader("Content-type", "application/json");
            httpPost.setEntity(requestEntity);
            //第四步：发送HttpPost请求，获取返回值
            String jsonStr = httpClient.execute(httpPost, responseHandler);
            System.out.println(String.format("获取用户信息，请求接口【%s】,"
                + "参数【%s】，返回结果：%s", url, gson.toJson(baseRequest), jsonStr));
            Map map = gson.fromJson(jsonStr, Map.class);
        } catch (Exception e) {
//            LOGGER.error(String.format("获取用户信息，请求接口【%s】,参数【%s】", url, gson.toJson(baseRequest)));
//            LOGGER.error(e.getMessage(), e);

        }
    }
}
