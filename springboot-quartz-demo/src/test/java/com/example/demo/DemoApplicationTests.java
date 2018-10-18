package com.example.demo;

import com.example.demo.entity.ScheduleJob;
import com.example.demo.task.TestTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {


    @Test
    public void contextLoads() {
    }

    @Test
    public void testUserRepository(){
        System.out.println(Integer.toBinaryString(Integer.MAX_VALUE));
    }

    @Test
    public void testSchedule() throws SchedulerException {
        //创建一个Scheduler
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        //创建作业
        JobDetail jobDetail = JobBuilder.newJob(TestTask.class)
                .withIdentity("job1","group1")
                .build();
        //创建触发规则
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/1 * * * * ? ");
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("job1","group1")
                .withSchedule(cronScheduleBuilder)
                .build();
        //添加作业与触发规则到scheduler中
        scheduler.scheduleJob(jobDetail,trigger);
        //开始运行
        scheduler.start();
    }

}
