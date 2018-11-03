package com.demo.springbootshirodemo.service;

import com.demo.springbootshirodemo.dao.SysMenuMapper;
import com.demo.springbootshirodemo.entity.SysMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author：kkorkk.
 * @Date：2018/10/27 10:45
 * @Description：菜单信息业务处理类
 */
public interface SysMenuService {

    SysMenu getById(Integer id);

}
