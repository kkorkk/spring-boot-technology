package com.example.demo.service;

import com.example.demo.entity.ScheduleJob;
import com.example.demo.repository.ScheduleJobRepository;
import com.example.demo.utils.ScheduleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @Author：kkorkk.
 * @Date：2018/10/14 10:05
 * @Description：ScheduleJob实体类业务处理类
 */
@Service
public class ScheduleJobService {

    @Autowired
    ScheduleJobRepository scheduleJobRepository;

    @Autowired
    ScheduleUtils scheduleUtils;

    /**
     * @author: kkorkk
     * @description: 根据id获取任务
     * @date 2018/10/18 14:33
     * @param jobId
     * @return ScheduleJob
     * */
    public ScheduleJob findById(Long jobId){
        return scheduleJobRepository.findById(jobId).get();
    }

    /**
     * @author: kkorkk
     * @description: 保存任务信息
     * @date 2018/10/18 14:37
     * @param scheduleJob
     * @return ScheduleJob
     * */
    public ScheduleJob save(ScheduleJob scheduleJob){
        scheduleJob.setCreateDate(new Date());
        if(scheduleJob.getStatus() == null){
            scheduleJob.setStatus(0);
        }
        return scheduleJobRepository.save(scheduleJob);
    }

    /**
     * @author: kkorkk
     * @description: 根据任务id删除任务
     * @date 2018/10/18 14:37
     * @param jobId
     * @return
     * */
    public void delete(Long jobId){
        scheduleJobRepository.deleteById(jobId);
    }

    /**
     * @author: kkorkk
     * @description: 更新任务信息
     * @date 2018/10/18 14:38
     * @param scheduleJob
     * @return
     * */
    public void update(ScheduleJob scheduleJob){
        ScheduleJob job = scheduleJobRepository.findById(scheduleJob.getJobId()).get();
        //如果任务在运行中，则需要更新任务表达式
        if(job.getStatus()==1){
            scheduleUtils.updateCronExpression(scheduleJob);
        }
        scheduleJob.setStatus(job.getStatus());
        scheduleJobRepository.save(scheduleJob);
    }

    /**
     * @author: kkorkk
     * @description: 根据任务id查询任务信息
     * @date 2018/10/18 14:38
     * @param jobId
     * @return ScheduleJob
     * */
    public ScheduleJob query(Long jobId){
        return scheduleJobRepository.findById(jobId).get();
    }

    /**
     * @author: kkorkk
     * @description: 查询所有任务
     * @date 2018/10/18 14:38
     * @param
     * @return List<ScheduleJob>
     * */
    public List<ScheduleJob> queryAll(){
        Iterator<ScheduleJob> scheduleJobIterator = scheduleJobRepository.findAll().iterator();
        List<ScheduleJob> scheduleJobList = new ArrayList<>();
        while(scheduleJobIterator.hasNext()){
            ScheduleJob scheduleJob = scheduleJobIterator.next();
            scheduleJobList.add(scheduleJob);
        }
        return scheduleJobList;
    }

    /**
     * @author: kkorkk
     * @description: 批量删除任务
     * @date 2018/10/18 14:39
     * @param ids
     * @return
     * */
    public void batchDelete(List<Long> ids){
        ids.forEach(id->scheduleJobRepository.deleteById(id));
    }

    /**
     * @author: kkorkk
     * @description: 获取所有任务
     * @date 2018/10/18 14:39
     * @param
     * @return Integer
     * */
    public Integer getCount(){
        return scheduleJobRepository.getCount();
    }

    /**
     * @author: kkorkk
     * @description: 分页查询数据
     * @date 2018/10/18 14:40
     * @param offset,limit
     * @return List<ScheduleJob>
     * */
    public List<ScheduleJob> findByPage(Integer offset,Integer limit){
        Integer page = offset/limit;
        Integer size = limit;
        Pageable pageable = PageRequest.of(page,size);
        List<ScheduleJob> scheduleJobs = scheduleJobRepository.findAll(pageable).getContent();
        return scheduleJobs;
    }

}
