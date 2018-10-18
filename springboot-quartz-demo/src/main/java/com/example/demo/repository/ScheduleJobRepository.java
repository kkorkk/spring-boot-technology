package com.example.demo.repository;

import com.example.demo.entity.ScheduleJob;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author：kkorkk.
 * @Date：2018/10/14 10:03
 * @Description：Schedule实体类持久化类
 */
@Repository
public interface ScheduleJobRepository extends CrudRepository<ScheduleJob,Long>,
        PagingAndSortingRepository<ScheduleJob,Long> {

    @Query("select count(jobId) from ScheduleJob ")
    Integer getCount();

}
