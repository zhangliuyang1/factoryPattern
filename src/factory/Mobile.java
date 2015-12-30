package factory;

/**
 * Created by zhangliuyang on 2015/11/3 15:41.
 */
public class Mobile implements Card{
    @Override
    public void buyCard() {
        System.out.println("购买移动卡");
    }
}
