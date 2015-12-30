package weixin;

import net.sf.json.JSONObject;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 实现签名逻辑
 * @author zhangliuyang
 * @email zhangliuyang@nggirl.com.cn
 * @date 2015/11/18  14:11
 */
public class PastUtil {
    public static String token = null;
    public static String time = null;
    public static String jsapi_ticket = null;
    public static String getParam(String appid,String appsecret){
        if (token == null){
            token = CommonUtil.getAcessToken(appid,appsecret);
            jsapi_ticket = CommonUtil.getJsApiTicket(token);
            time = getTime();
        }else {
            if (!time.substring(0,13).equals(getTime().substring(0,13))){
                token = null;
                token = CommonUtil.getAcessToken(appid,appsecret);
                jsapi_ticket = CommonUtil.getJsApiTicket(token);
                time = getTime();
            }
        }
        String url ="";

        Map<String,String> params = sign(jsapi_ticket,url);
        params.put("appid",appid);

        JSONObject jsonObject = JSONObject.fromObject(params);
        String jsonStr = jsonObject.toString();
        System.out.println(jsonStr);
        return jsonStr;

    }
   /* public static String getUrl(){

        HttpServletRequest request = ServletActionContext.getRequest();

        StringBuffer requestUrl = request.getRequestURL();

        String queryString = request.getQueryString();
        String url = requestUrl +"?"+queryString;
        return url;
    }*/
    /**
     * 获取当前系统时间，用来判断accessToken是否过期
     * @return
     */
    public static String getTime(){
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(dt);
    }

    public static Map<String,String> sign(String jsapi_ticket,String url){
        Map<String,String> ret = new HashMap<String, String>();
        String nonce_str = create_nonce_str();
        String timestamp = create_timestamp();
        String str;
        String signature = "";
        //注意这里参数名必须全部小写，且必须有序，字典顺序
        str = "jsapi_ticket=" + jsapi_ticket + "&nonce_str=" + nonce_str + "&timestamp=" + "&url=" + url;
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(str.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        ret.put("url",url);
        ret.put("jsapi_ticket",jsapi_ticket);
        ret.put("nonceStr",nonce_str);
        ret.put("timestamp",timestamp);
        ret.put("signature",signature);

        return ret;

    }
    public static String byteToHex(final byte[] hash){
        Formatter formatter = new Formatter();
        for (byte b : hash){
            formatter.format("%02x",b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
    public static String create_nonce_str(){
        return UUID.randomUUID().toString();
    }
    public static String create_timestamp(){
            return Long.toString(System.currentTimeMillis()/1000);
    }
}
