package DesignPattern;

/**
 * @author zhangliuyang
 * @email zhangliuyang@nggirl.com.cn
 * @date 2015/11/20  11:45
 */
public class SingleTon7 {
    private volatile static SingleTon7 instance;
    private SingleTon7(){}

    public static SingleTon7 getInstance(){
        if (instance == null){
            synchronized (SingleTon7.class){
                if (instance == null){
                    instance = new SingleTon7();
                }
            }
        }
        return instance;
    }
}
