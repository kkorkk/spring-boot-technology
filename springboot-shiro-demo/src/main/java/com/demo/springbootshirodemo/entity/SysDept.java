package com.demo.springbootshirodemo.entity;

public class SysDept {
    /**
     * 部门唯一标识
     * */
    private Integer deptId;
    /**
     * 上级部门id
     * */
    private Integer parentId;
    /**
     * 部门名称
     * */
    private String deptName;
    /**
     * 排序
     * */
    private Integer orderNum;
    /**
     * 状态：1：正常，2：不可用
     * */
    private Byte status;

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? null : deptName.trim();
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SysDept{" +
                "deptId=" + deptId +
                ", parentId=" + parentId +
                ", deptName='" + deptName + '\'' +
                ", orderNum=" + orderNum +
                ", status=" + status +
                '}';
    }
}