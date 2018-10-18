package com.example.demo.quarz;

import com.example.demo.entity.ScheduleJob;
import com.example.demo.service.ScheduleJobService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author：kkorkk.
 * @Date：2018/10/18 10:44
 * @Description：任务初始化类
 */
@Component
public class ScheduleJobInit implements ApplicationRunner {

    @Autowired
    ScheduleJobService scheduleJobService;

    @Autowired
    Scheduler scheduler;

    /**
     * @author: kkorkk
     * @description: 在项目启动时，重新获取状态为运行中的任务并重新添加到任务列表中
     * @date 2018/10/18 10:45
     * @param args
     * @return
     * */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        //获取所有状态为启动的任务
        List<ScheduleJob> scheduleJobList = scheduleJobService.queryAll()
                .stream()
                .filter(scheduleJob -> scheduleJob.getStatus().equals(1))
                .collect(Collectors.toList());
        //将所有的任务添加到scheduler中
        for (ScheduleJob scheduleJob : scheduleJobList) {
            try {
                //获取jobDetail
                Class jobClass = Class.forName(scheduleJob.getClassName());
                JobDetail jobDetail = JobBuilder.newJob(jobClass)
                        .withIdentity(scheduleJob.getJobName(), scheduleJob.getJobGroup())
                        .build();
                //获取trigger
                CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression());
                Trigger trigger = TriggerBuilder.newTrigger()
                        .withIdentity(scheduleJob.getJobName(),scheduleJob.getJobGroup())
                        .withSchedule(cronScheduleBuilder)
                        .build();
                scheduler.scheduleJob(jobDetail,trigger);

                System.out.println("成功将"+scheduleJob.getJobName()+"添加到任务中");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
