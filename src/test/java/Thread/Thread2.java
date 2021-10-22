package Thread;

/**
 * @ClassName Thread2
 * @Description
 * @Author
 * @Date 2021/9/2 10:37
 * @Version 1.0
 **/
public class Thread2 extends Thread {
    A a;
    public Thread2(A a) {
        this.a = a;
    }

    @Override
    public void run() {
        try {
            a.method2();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
