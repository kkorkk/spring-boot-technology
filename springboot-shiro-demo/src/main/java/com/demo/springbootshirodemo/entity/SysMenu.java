package com.demo.springbootshirodemo.entity;

public class SysMenu {
    /**
     * 唯一标识
     * */
    private Integer menuId;
    /**
     * 上级菜单id
     * */
    private Integer parentId;
    /**
     * 菜单名称
     * */
    private String menuName;
    /**
     * 菜单url
     * */
    private String url;
    /**
     * 权限
     * */
    private String permissions;
    /**
     * 菜单类型，1：类别，2：菜单，3：按钮
     * */
    private Byte type;
    /**
     * 图标
     * */
    private String icon;
    /**
     * 排序
     * */
    private Integer orderNum;

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions == null ? null : permissions.trim();
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }
}