package com.demo.springbootshirodemo.service.impl;

import com.demo.springbootshirodemo.dao.SysMenuMapper;
import com.demo.springbootshirodemo.entity.SysMenu;
import com.demo.springbootshirodemo.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author：kkorkk.
 * @Date：2018/10/29 9:48
 * @Description：系统菜单接口实现类
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    SysMenuMapper sysMenuMapper;

    @Override
    public SysMenu getById(Integer id) {
        return sysMenuMapper.selectByPrimaryKey(id);
    }
}
