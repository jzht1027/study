package Thread;

/**
 * @ClassName Test66
 * @Description
 * @Author
 * @Date 2021/9/2 10:36
 * @Version 1.0
 **/
public class Test66 {
    public static void main(String[] args) {
        A a = new A();
        Thread1 thread1 = new Thread1(a);
        Thread2 thread2 = new Thread2(a);

        thread1.start();
        thread2.start();
    }
}
