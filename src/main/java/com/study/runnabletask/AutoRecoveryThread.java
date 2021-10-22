package com.study.runnabletask;

/**
 * @ClassName AutoRecoveryThread
 * @Description
 * @Author
 * @Date 2021/4/26 11:11
 * @Version 1.0
 **/
public class AutoRecoveryThread extends Thread{

    public AutoRecoveryThread (String threadName,Runnable runner){
//        new Thread(runner).start();
        super(runner);
        this.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable t) {
                (new AutoRecoveryThread(threadName, runner)).start();
            }
        });
    }
}
