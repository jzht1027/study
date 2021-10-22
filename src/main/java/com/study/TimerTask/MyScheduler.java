package com.study.TimerTask;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName MyScheduler
 * @Description
 * @Author
 * @Date 2021/3/19 18:10
 * @Version 1.0
 **/
public class MyScheduler {
    public static void main(String[] args) throws SchedulerException, InterruptedException {
        // 1、创建调度器Scheduler
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        // 2、创建JobDetail实例，并与TimerTask类绑定(Job执行内容)
        JobDetail jobDetail = JobBuilder.newJob(TimerTask.class)
                .withIdentity("job1", "group1").build();
        // 3、构建Trigger实例
        //使用SimpleScheduleBuilder或者CronScheduleBuilder
        Trigger trigger = TriggerBuilder.newTrigger()
                .withSchedule(CronScheduleBuilder.cronSchedule("0 * * * * ?"))
                .build();

        //4、执行
        scheduler.scheduleJob(jobDetail, trigger);
        System.out.println("--------scheduler start ! ------------");
        scheduler.start();

        //睡眠
        TimeUnit.MINUTES.sleep(1);
        scheduler.shutdown();
        System.out.println("--------scheduler shutdown ! ------------");


    }
}
