package Thread;

/**
 * @ClassName MultiThread
 * @Description
 * @Author
 * @Date 2021/9/2 10:31
 * @Version 1.0
 **/
public class MultiThread {
    public static void main(String[] args) {
        //resources���Ǿ�����Դ����
        Resources resources = new Resources();
        Runnable1 runnable1 = new Runnable1(resources);

        for(int i = 0; i <100; i++) {
            // �����Ǵ������߳�ȥִ������
            //���߳��Ǿ�����ϵ�����Զ���߳̾���ͬһ����Դ��Ҳ����ͬһ������
            //���������������ŵ�Thread��
            new Thread(runnable1,"Thread"+i).start();
        }
    }
}
