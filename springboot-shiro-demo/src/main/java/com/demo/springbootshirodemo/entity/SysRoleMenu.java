package com.demo.springbootshirodemo.entity;

public class SysRoleMenu {
    /**
     * 唯一标识
     * */
    private Integer id;
    /**
     * 角色id
     * */
    private Integer roleId;
    /**
     * 菜单id
     * */
    private Integer menuId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }
}