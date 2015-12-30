package DesignPattern;

/**
 * @author zhangliuyang
 * @email zhangliuyang@nggirl.com.cn
 * @date 2015/11/20  11:27
 */
public class SingleTon3 {
    private static SingleTon3 instance = new SingleTon3();
    private SingleTon3(){}
    public static SingleTon3 getInstance(){
        return instance;
    }
}
/*恶汉式 - 这种方式给予classloader机制避免了多线程同步的问题。instance在类装载 的时候就实例化
虽然导致类装载的原因有很多，在单例模式中大多数都是调用getInstance方法，但是也不能确定有其他的方式导致类装载，
这个时候初始化instance显然没有达到lazy loading的效果*/