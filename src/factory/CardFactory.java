package factory;

/**
 * Created by zhangliuyang on 2015/11/3 15:45.
 */
public class CardFactory {
    public Card buyCard(String styleName){
        if (styleName.toLowerCase().equals("mobile")) {
            return new Mobile();
        }else if (styleName.toLowerCase().equals("unicom")) {
            return new Unicom();
        }
        return null;
    }
}
