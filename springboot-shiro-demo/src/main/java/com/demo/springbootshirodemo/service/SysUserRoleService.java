package com.demo.springbootshirodemo.service;

import com.demo.springbootshirodemo.dao.SysUserRoleMapper;
import com.demo.springbootshirodemo.entity.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author：kkorkk.
 * @Date：2018/10/27 10:49
 * @Description：用户角色关联信息业务处理类
 */
public interface SysUserRoleService {

    List<SysRole> selectByUserId(Integer userId);

}
