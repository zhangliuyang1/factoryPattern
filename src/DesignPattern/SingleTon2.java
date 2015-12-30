package DesignPattern;

/**
 * @author zhangliuyang
 * @email zhangliuyang@nggirl.com.cn
 * @date 2015/11/20  11:23
 */
public class SingleTon2 {
    private static SingleTon2 instance;
    private SingleTon2(){}
    public static synchronized SingleTon2 getInstance(){
        if (instance == null){
            instance = new SingleTon2();
        }
        return instance;
    }
}
/*懒汉--线程安全 但是效率低*/
