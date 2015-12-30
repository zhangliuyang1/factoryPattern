package weixin;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author zhangliuyang
 * @email zhangliuyang@nggirl.com.cn
 * @date 2015/11/18  11:25
 */
public class CommonUtil {
    public static Log log = LogFactory.getLog(CommonUtil.class);

    /**
     * 获取接口访问凭证
     * @param appid 凭证
     * @param appsecret 密钥
     * @return
     */
    public static String getAcessToken(String appid,String appsecret){
        //凭证获取接口
        String token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
        String requestUrl = token_url.replace("APPID",appid).replace("APPSECRET",appsecret);

        JSONObject jsonObject = httpRequest(requestUrl,"get",null);
        String accessToken = null;
        if (null != jsonObject){
            try {
                accessToken = jsonObject.getString("accessToken");
            }catch (JSONException e){
                //获取失败
                log.error("获取token失败 errc   ode:{} errmsg:{}");
            }


        }
        return accessToken;
    }

    /**
     * 调用微信JS接口的临时票据
     * @param accessToken 接口访问凭证
     * @return
     */
    public static String getJsApiTicket(String accessToken){
        String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
        String requestUrl = url.replace("ACCESSTOKEN",accessToken);

        //发起请求
        JSONObject jsonObject = httpRequest(requestUrl,"get",null);
        String ticket = null;
        if (null != jsonObject){
            try {
                ticket = jsonObject.getString("ticket");
            }catch (JSONException e){
                log.error("取token失败 errcode:{} errmsg:{}");
            }

        }
        return ticket;
    }



    /**
     *发起https请求并获取结果
     * @param requestUrl
     * @param requestMethod
     * @param outputStr
     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
     */
    public static JSONObject httpRequest(String requestUrl,String requestMethod,String outputStr){
        JSONObject jsonObject = null;
        StringBuffer buffer = new StringBuffer();
        try {
            //创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = {new MyX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL","SunJSSE");
            sslContext.init(null,tm,new java.security.SecureRandom());
            //从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);

            HttpsURLConnection httpURLConnection = (HttpsURLConnection)url.openConnection();
            httpURLConnection.setSSLSocketFactory(ssf);

            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setUseCaches(false);
            //设置请求方式（get、post）
            httpURLConnection.setRequestMethod(requestMethod);

            if ("GET".equalsIgnoreCase(requestMethod)){
                httpURLConnection.connect();
            }
            if (null != outputStr){
                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();

            }
            //将返回的输入流转成字符串
            InputStream inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str=bufferedReader.readLine())!=null){
                buffer.append(str);
            }

            bufferedReader.close();
            inputStreamReader.close();

            //释放资源
            inputStream.close();
            inputStream = null;
            httpURLConnection.disconnect();
            jsonObject = JSONObject.fromObject(buffer.toString());

        }catch (ConnectException c){
            log.error("hWeixin server connection timed out.");
        }catch (Exception e){
            System.out.println("https request error:{}");
            log.error("https request error:{}",e);
        }

        return jsonObject;
    }
}
