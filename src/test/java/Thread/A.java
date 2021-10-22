package Thread;

/**
 * @ClassName A
 * @Description
 * @Author
 * @Date 2021/9/2 10:37
 * @Version 1.0
 **/
public class A {
    public synchronized void method1() throws InterruptedException {
        System.out.println("method1---5ms");
        Thread.sleep(5000);
        System.out.println("method1---end");
    }

    public void method2() throws InterruptedException {
        System.out.println("method2--2ms");
        Thread.sleep(2000);
        System.out.println("method2---end");
    }
}
