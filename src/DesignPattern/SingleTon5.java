package DesignPattern;

/**
 * @author zhangliuyang
 * @email zhangliuyang@nggirl.com.cn
 * @date 2015/11/20  11:35
 */
public class SingleTon5 {
    private static class SingleTon5Holder{
        private static final SingleTon5 instance = new SingleTon5();
    }
    private SingleTon5(){
        super();
    }
    public static final SingleTon5 getInstance(){
        return SingleTon5Holder.instance;
    }
}
