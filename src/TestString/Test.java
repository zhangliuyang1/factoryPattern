package TestString;

/**
 * @author zhangliuyang
 * @email zhangliuyang@nggirl.com.cn
 * @date 2015/11/20  13:50
 */
public class Test {
    public static void main(String[] args){
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
        String reqUrl = String.format(url,"",2);
        System.out.println(reqUrl);
    }
}
