package com.example.demo.utils;

import com.example.demo.entity.ScheduleJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author：kkorkk.
 * @Date：2018/10/15 14:24
 * @Description：定时任务工具类
 */
@Component
public class ScheduleUtils {

    @Autowired
    Scheduler scheduler;

    /**
     * @author: kkorkk
     * @description: 启动/恢复任务
     * @date 2018/10/18 9:18
     * @param scheduleJob
     * @return
     * */
    public void addJob(ScheduleJob scheduleJob) {
        //如果之前存在任务，则恢复，否则新增任务
        JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(),scheduleJob.getJobGroup());
        try {
            if(scheduler.checkExists(jobKey)){
                scheduler.resumeJob(jobKey);
            }else {
                try {
                    Class jobClass = Class.forName(scheduleJob.getClassName());
                    JobDetail jobDetail = JobBuilder.newJob(jobClass)
                            .withIdentity(scheduleJob.getJobName(), scheduleJob.getJobGroup())
                            .build();
                    //定义调度触发规则
                    Trigger trigger = TriggerBuilder.newTrigger()
                            .withIdentity(scheduleJob.getJobName(), scheduleJob.getJobGroup())
                            .withSchedule(CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression()))
                            .startNow()
                            .build();
                    //把作业和触发规则注册到任务调度中
                    scheduler.scheduleJob(jobDetail, trigger);
                    scheduler.start();

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * @author: kkorkk
     * @description: 停止任务
     * @date 2018/10/18 9:15
     * @param scheduleJob
     * @return
     * */
    public void pauseJob(ScheduleJob scheduleJob){
        JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(),scheduleJob.getJobGroup());
        try {
            scheduler.pauseJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * @author: kkorkk
     * @description: 恢复任务
     * @date 2018/10/18 9:14
     * @param scheduleJob
     * @return
     * */
    public void resumeJob(ScheduleJob scheduleJob){
        JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(),scheduleJob.getJobGroup());
        try {
            scheduler.resumeJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * @author: kkorkk
     * @description: 更新cron表达式
     * @date 2018/10/18 14:40
     * @param scheduleJob
     * @return
     * */
    public void updateCronExpression(ScheduleJob scheduleJob){
        //获取TriggerKey
        TriggerKey triggerKey = TriggerKey.triggerKey(scheduleJob.getJobName(),scheduleJob.getJobGroup());
        try {
            //创建触发规则
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression());
            Trigger trigger = TriggerBuilder.newTrigger()
                                .withIdentity(triggerKey)
                                .withSchedule(scheduleBuilder)
                                .startNow()
                                .build();
            scheduler.rescheduleJob(triggerKey,trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

}
