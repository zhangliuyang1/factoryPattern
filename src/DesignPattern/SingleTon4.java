package DesignPattern;

/**
 * @author zhangliuyang
 * @email zhangliuyang@nggirl.com.cn
 * @date 2015/11/20  11:31
 */
public class SingleTon4 {
    private static SingleTon4 instance = null;
    static {
        instance = new SingleTon4();
    }
    private SingleTon4(){}
    public static SingleTon4 getInstance(){
        return instance;
    }
}
/*同第三种，都是在乎类初始化即实例化instance*/