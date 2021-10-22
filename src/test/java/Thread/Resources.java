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

    //多线程去干活了，它们争着抢着去执行竞争资源里面的方法，所以这个方法区域需要加锁
    public synchronized  void methodA() {
        if(count > 0) {
            count--;
        }
        System.out.println(Thread.currentThread().getName() + "  " +"count:"+count);
    }
}
