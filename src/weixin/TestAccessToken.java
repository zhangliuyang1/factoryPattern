package weixin;

/**
 * @author zhangliuyang
 * @email zhangliuyang@nggirl.com.cn
 * @date 2015/11/18  13:51
 */
public class TestAccessToken {
    public static  void main(String[] args){
        /*String accessToken = CommonUtil.getAcessToken("","");
        System.out.println(accessToken);*/

        System.out.println(PastUtil.create_nonce_str());
    }
}
