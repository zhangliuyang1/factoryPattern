package DesignPattern;

/**
 * @author zhangliuyang
 * @email zhangliuyang@nggirl.com.cn
 * @date 2015/11/20  11:10
 */
public class SingleTon1 {
    private static SingleTon1 instance;
    private SingleTon1(){}
    public static SingleTon1 getInstance(){
        if (instance == null){
            instance = new SingleTon1();
        }
        return instance;
    }
}
/*懒汉--线程不安全
这种写法lazy loading很明显，但是致命的是多线程不能正常工作*/