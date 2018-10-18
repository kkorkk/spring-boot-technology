package com.example.demo.controller;

import com.example.demo.common.ResultVO;
import com.example.demo.entity.ScheduleJob;
import com.example.demo.service.ScheduleJobService;
import com.example.demo.utils.ScheduleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author：kkorkk.
 * @Date：2018/10/11 16:04
 * @Description：
 */
@CrossOrigin(origins = "http://cron.qqe2.com:80", maxAge = 3600)
@Controller
public class IndexController {

    @Autowired
    ScheduleJobService scheduleJobService;

    @Autowired
    ScheduleUtils scheduleUtils;

    /**
     * 访问首页
     * */
    @RequestMapping("/")
    public String index(){
        return "index";
    }

    /**
     * 添加任务
     * */
    @PostMapping("/add")
    @ResponseBody
    public ResultVO add(@RequestBody ScheduleJob scheduleJob){
        ResultVO resultVO = ResultVO.SUCCESS(null);
        try{
            scheduleJob.setCreateDate(new Date());
            scheduleJobService.save(scheduleJob);
        }catch (Exception e){
            e.printStackTrace();
            resultVO = ResultVO.ERROR("1","FAILER");
        }
        return resultVO;
    }

    @PostMapping("/update")
    @ResponseBody
    public ResultVO update(@RequestBody ScheduleJob scheduleJob){
        ResultVO resultVO = ResultVO.SUCCESS(null);
        scheduleJobService.update(scheduleJob);
        return resultVO;
    }

    /**
     * 获取任务列表
     * */
    @GetMapping("/job_list")
    @ResponseBody
    public Map<String,Object> jobList(@RequestParam Integer offset,@RequestParam Integer limit){
        List<ScheduleJob> userList = scheduleJobService.findByPage(offset,limit);
        Integer total = scheduleJobService.getCount();
        Map<String,Object> result = new HashMap<>();
        result.put("data",userList);
        result.put("total",total);
        return result;
    }

    @PostMapping("/delete")
    @ResponseBody
    public ResultVO delete(@RequestBody ScheduleJob scheduleJob){
        scheduleJobService.delete(scheduleJob.getJobId());
        return ResultVO.SUCCESS(null);
    }

    /**
     * 开启任务
     * */
    @PostMapping("/job_start")
    @ResponseBody
    public ResultVO jobRun(@RequestBody ScheduleJob scheduleJob){
        //保存变更到数据库中
        ScheduleJob scheduleJobBefore = scheduleJobService.findById(scheduleJob.getJobId());
        scheduleJobBefore.setStatus(1);
        scheduleJobService.save(scheduleJobBefore);
        //启动任务
        scheduleUtils.addJob(scheduleJobBefore);
        return ResultVO.SUCCESS(null);
    }

    /**
     * 暂停任务
     * */
    @PostMapping("/job_pause")
    @ResponseBody
    public ResultVO jobPause(@RequestBody ScheduleJob scheduleJob){
        //保存变更到数据库中
        ScheduleJob scheduleJobBefore = scheduleJobService.findById(scheduleJob.getJobId());
        scheduleJobBefore.setStatus(0);
        scheduleJobService.update(scheduleJobBefore);
        //暂停任务
        scheduleUtils.pauseJob(scheduleJobBefore);
        return ResultVO.SUCCESS(null);
    }

    /**
     * 批量删除任务
     * */
    @PostMapping("/batch_delete")
    @ResponseBody
    public ResultVO batchDelete(@RequestBody List<Long> ids){
        ids.forEach(id->scheduleJobService.delete(id));
        return ResultVO.SUCCESS(null);
    }

    /**
     * 获取某一任务信息
     * */
    @GetMapping("/job")
    @ResponseBody
    public ResultVO scheduleJob(Long jobId){
        ScheduleJob scheduleJob = scheduleJobService.query(jobId);
        return ResultVO.SUCCESS(scheduleJob);
    }

}
