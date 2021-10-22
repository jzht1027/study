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
        //resources就是竞争资源对象
        Resources resources = new Resources();
        Runnable1 runnable1 = new Runnable1(resources);

        for(int i = 0; i <100; i++) {
            // 这里是创建多线程去执行任务
            //多线程是竞争关系，所以多个线程竞争同一个资源，也就是同一个对象
            //所以这个竞争对象放到Thread中
            new Thread(runnable1,"Thread"+i).start();
        }
    }
}
