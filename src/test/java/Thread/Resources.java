package Thread;

/**
 * @ClassName Resources
 * @Description
 * @Author
 * @Date 2021/9/2 10:32
 * @Version 1.0
 **/
public class Resources {
    private int count = 100;

    //���߳�ȥ�ɻ��ˣ�������������ȥִ�о�����Դ����ķ����������������������Ҫ����
    public synchronized  void methodA() {
        if(count > 0) {
            count--;
        }
        System.out.println(Thread.currentThread().getName() + "  " +"count:"+count);
    }
}
