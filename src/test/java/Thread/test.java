package Thread;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * @ClassName test
 * @Description
 * @Author
 * @Date 2021/9/2 13:48
 * @Version 1.0
 **/
public class test {

    int value = 0;
    int startValue = 1;
    int i=0;

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException, ParseException {

        String date = "20210701000000";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        formatter.setLenient(false);
//        Date newDate= formatter.parse("20210804174143");
        Date newDate= formatter.parse(date);
        formatter = new SimpleDateFormat("yyyy-MM-dd");
        String now = formatter.format(newDate);
        System.out.println(now);

        test t = new test();
//        t.CountDownLatchTest();
        t.CyclicBarrierTest(5);
    }

    public void CountDownLatchTest() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("子线程执行");
                    latch.countDown(); //让latch中得数值减1
                }
            }).start();
            latch.await();//阻塞当前线程直到latch中的值为0
            System.out.println("结束");
        }
    }

    public void CyclicBarrierTest(int number) throws BrokenBarrierException, InterruptedException {
        final CyclicBarrier barrier = new CyclicBarrier(number);
        for ( i= 0 ; i < 4;i++){
            new Thread(new Runnable() {
                public void run() {
                    try {
                        System.out.println(++i +"@@@@"+value  + "   "+ startValue);
//                        DateFormat normalDf1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                        Date date = new Date();
//                        String dateStr=normalDf1.format(date);
//                        System.out.println("---->"+dateStr);
                        value = value + 100000;
                        startValue = value -100000;
                        System.out.println(i+" $$"+value  + "   "+ startValue);
                        Test(value ,startValue);
                    }catch (Exception e ){
                        e.printStackTrace();
                    }
                    System.out.println("子线程执行");
                    try {
                        barrier.await(); //达到屏障
                        System.out.println("1111");
                    }catch (Exception e ){
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        barrier.await();
        System.out.println("结束");
    }

    public void Test(int value ,int startValue){
        try {
            System.out.println(value  + "@@@@"+ startValue);
            Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
