package com.demo.springbootshirodemo.service;

import com.demo.springbootshirodemo.dao.SysRoleMapper;
import com.demo.springbootshirodemo.dao.SysRoleMenuMapper;
import com.demo.springbootshirodemo.entity.SysRoleMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author：kkorkk.
 * @Date：2018/10/27 10:47
 * @Description：角色菜单关联信息业务处理类
 */
public interface SysRoleMenuService {

    List<SysRoleMenu> selectByRoleId(Integer roleId);

}
