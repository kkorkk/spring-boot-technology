package com.demo.springbootshirodemo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 5123636426792514942L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uid;

    @Column(unique = true)
    private String username;

    @Column
    private String name;

    @Column
    private String password;

    @Column
    private String salt;

    @Column
    private byte state;

    @ManyToMany(fetch = FetchType.EAGER)//立即从数据库中加载数据
    @JoinTable(name = "SysUserRole" ,joinColumns = {@JoinColumn(name = "uid")},
        inverseJoinColumns = {@JoinColumn(name = "roleId")})
    private List<SysRole> roleList; //一个用户具有多个角色


    public UserInfo() {
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setState(byte state) {
        this.state = state;
    }

    public void setRoleList(List<SysRole> roleList) {
        this.roleList = roleList;
    }

    public Integer getUid() {
        return uid;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getSalt() {
        return salt;
    }

    public byte getState() {
        return state;
    }

    public List<SysRole> getRoleList() {
        return roleList;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", state=" + state +
                ", roleList=" + roleList +
                '}';
    }
}
