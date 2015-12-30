package factory;

/**
 * Created by zhangliuyang on 2015/11/3 15:48.
 */
public class Test {
    public static void main (String[] args){
        CardFactory cf = new CardFactory();
        cf.buyCard("unicom").buyCard();
    }
}
