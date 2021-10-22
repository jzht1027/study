package com.study.TimerTask;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @ClassName TimerTask
 * @Description
 * @Author
 * @Date 2021/3/19 17:53
 * @Version 1.0
 **/
public class TimerTask implements Job {
    
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        String printTime = new SimpleDateFormat("yy-MM-dd HH-mm-ss").format(new Date());
        System.out.println("TimerTask start at:" + printTime + ", prints: Hello Job-" + new Random().nextInt(100));
    }
}
