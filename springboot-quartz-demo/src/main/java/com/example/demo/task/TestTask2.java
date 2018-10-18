package com.example.demo.task;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.time.Instant;

/**
 * @Author：kkorkk.
 * @Date：2018/10/17 17:30
 * @Description：测试任务类
 */
public class TestTask2 implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println(this.getClass()+"正在运行中,当前时间"+Instant.now().toString());
    }

}
