package com.demo.springbootshirodemo.controller;

import com.demo.springbootshirodemo.service.SysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author：kkorkk.
 * @Date：2018/10/30 9:15
 * @Description：部门信息路由处理类
 */
@Controller
public class DeptController {

    @Autowired
    SysDeptService sysDeptService;

    @RequestMapping("dept")
    public String dept(){
        return "dept";
    }

}
