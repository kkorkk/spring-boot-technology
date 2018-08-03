package com.demo.springbootshirodemo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class SysRole implements Serializable {


    private static final long serialVersionUID = 7691754109673893792L;
    @Id
    @GeneratedValue
    private Integer id;

    private String role;

    private String description;

    private Boolean available = Boolean.FALSE;

    //角色--权限关系：多对多关系
    @ManyToMany(fetch = FetchType.EAGER)        //立即加载
    @JoinTable(name = "SysRolePermission",joinColumns = {@JoinColumn(name = "roleId")},
        inverseJoinColumns = {@JoinColumn(name = "permissionId")})
    private List<SysPermission> permissionList;

    // 用户 - 角色关系定义;
    @ManyToMany
    @JoinTable(name="SysUserRole",joinColumns={@JoinColumn(name="roleId")},inverseJoinColumns={@JoinColumn(name="uid")})
    private List<UserInfo> userInfos;// 一个角色对应多个用户

    public SysRole() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAvailabel() {
        return available;
    }

    public void setAvailabel(Boolean availabel) {
        this.available = availabel;
    }

    public List<SysPermission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<SysPermission> permissionList) {
        this.permissionList = permissionList;
    }

    public List<UserInfo> getUserInfos() {
        return userInfos;
    }

    public void setUserInfos(List<UserInfo> userInfos) {
        this.userInfos = userInfos;
    }
}
