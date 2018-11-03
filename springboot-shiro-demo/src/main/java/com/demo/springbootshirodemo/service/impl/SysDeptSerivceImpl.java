package com.demo.springbootshirodemo.service.impl;

import com.demo.springbootshirodemo.dao.SysDeptMapper;
import com.demo.springbootshirodemo.service.SysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author：kkorkk.
 * @Date：2018/10/29 9:47
 * @Description：部门信息业务处理接口实现类
 */
@Service
public class SysDeptSerivceImpl implements SysDeptService {

    @Autowired
    SysDeptMapper sysDeptMapper;



}
