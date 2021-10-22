package Thread;

/**
 * @ClassName Thread1
 * @Description
 * @Author
 * @Date 2021/9/2 10:37
 * @Version 1.0
 **/
public class Thread1 extends Thread {
    A a;
    public Thread1(A a) {
        this.a = a;
    }

    @Override
    public void run() {
        try {
            a.method1();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
