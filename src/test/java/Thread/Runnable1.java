package Thread;

/**
 * @ClassName Runnable1
 * @Description
 * @Author
 * @Date 2021/9/2 10:33
 * @Version 1.0
 **/
public class Runnable1 implements Runnable{
    Resources resources = null;
    Runnable1(Resources resources) {
        this.resources = resources;
    }

    public void run() {
        //���methodA����ʱResources����ľ�����Դ����
        resources.methodA();
    }
}
