package com.demo.springbootshirodemo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class SysPermission implements Serializable {


    private static final long serialVersionUID = -6260132904966174998L;
    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    @Column(columnDefinition = "enum('menu','button')")
    private String resourceType;

    private String url;

    private String permission;

    private Long parentId;  //父编号

    private String parentIds;   //父编号列表

    private Boolean available = Boolean.FALSE;

    @ManyToMany
    @JoinTable(name = "SysRolePermission",joinColumns = {@JoinColumn(name = "permissionId")},
        inverseJoinColumns = {@JoinColumn(name = "roleId")})
    private List<SysRole> roles;

    public SysPermission() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public Boolean getAvailabel() {
        return available;
    }

    public void setAvailabel(Boolean availabel) {
        this.available = availabel;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }
}
