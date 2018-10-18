package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author：kkorkk.
 * @Date：2018/10/14 9:56
 * @Description：定时任务实体类
 */
@Entity
public class ScheduleJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobId;

    /**
     * 任务名称
     * */
    @Column
    private String jobName;

    /**
     * 任务分组
     * */
    @Column
    private String jobGroup;

    /**
     * 任务类名称，包名+类名
     * */
    @Column
    private String className;

    /**
     *参数
     * */
    @Column
    private String params;

    /**
     * cron表达式
     * */
    @Column
    private String cronExpression;

    /**
     * 状态
     * */
    @Column
    private Integer status;

    /**
     * 备注
     * */
    @Column
    private String remark;

    /**
     * 创建时间
     * */
    @Column
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    public ScheduleJob() {
    }

    public ScheduleJob(String jobName, String jobGroup, String className, String params, String cronExpression, Integer status, String remark, Date createDate) {
        this.jobName = jobName;
        this.jobGroup = jobGroup;
        this.className = className;
        this.params = params;
        this.cronExpression = cronExpression;
        this.status = status;
        this.remark = remark;
        this.createDate = createDate;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "ScheduleJob{" +
                "jobId=" + jobId +
                ", jobName='" + jobName + '\'' +
                ", jobGroup='" + jobGroup + '\'' +
                ", className='" + className + '\'' +
                ", params='" + params + '\'' +
                ", cronExpression='" + cronExpression + '\'' +
                ", status=" + status +
                ", remark='" + remark + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
