package com.demo.springbootshirodemo.controller;

import com.demo.springbootshirodemo.common.CoreConstants;
import com.demo.springbootshirodemo.common.ResultVO;
import com.demo.springbootshirodemo.entity.SysUser;
import com.demo.springbootshirodemo.service.SysUserService;
import com.demo.springbootshirodemo.utils.MD5Util;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;

@Controller
public class UserController {

    @Autowired
    private Producer producer;

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/register")
    public String register(){
        return "register";
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    /**
     * @author: kkorkk
     * @description: 用户注销
     * @date 2018/10/24 11:30
     * */
    @RequestMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }

    /**
     * @author: kkorkk
     * @description: 用户登陆
     * @date 2018/10/24 11:30
     * */
    @RequestMapping(value = "/doLogin",method = RequestMethod.POST)
    public @ResponseBody Map<String,Object> doLogin(String username, String password, Boolean rememberMe,String kaptchaText,HttpServletRequest request){
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("retCode",0);
        String status = "登录正常";
        Subject subject = SecurityUtils.getSubject();
        String rightKaptchText = (String)subject.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if(!rightKaptchText.equalsIgnoreCase(kaptchaText)){
            result.put("retCode",3);
            result.put("retMsg","验证码输入错误");
            return result;
        }
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        if(null != rememberMe && Boolean.TRUE.equals(rememberMe)){
            token.setRememberMe(true);
        }
        try {
            subject.login(token);
        }catch (UnknownAccountException e){
            result.put("retCode",1);
            status = e.getMessage();
        }catch (IncorrectCredentialsException e){
            result.put("retCode",2);
            status = "密码错误";
        }
        result.put("retMsg",status);
        return result;
    }

    /**
     * @author: kkorkk
     * @description: 用户注册
     * @date 2018/10/24 11:30
     * */
    @RequestMapping("/doRegister")
    @ResponseBody
    public ResultVO doRegister(String username,String password,String password2,String kaptchaText){
        ResultVO result = ResultVO.SUCCESS(null);
        //检查验证码
        Subject subject = SecurityUtils.getSubject();
        String rightKaptchaText = (String)subject.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);

        if(!Objects.equals(rightKaptchaText,kaptchaText)){
            result = ResultVO.ERROR("3","验证码错误");
        }else if(!Objects.equals(password,password2)){
            result = ResultVO.ERROR("4","密码输入不一致");
        }else if(sysUserService.findByUsername(username)!=null){
            result = ResultVO.ERROR("5","用户名已经存在");
        }else{
            SysUser sysUser = new SysUser();
            sysUser.setUsername(username);
            sysUser.setSalt(CoreConstants.PASSWORD_SALT);
            sysUser.setState(CoreConstants.USER_STATE_NORMAL);
            sysUser.setPassword(MD5Util.getMD5Str(password));
            sysUserService.save(sysUser);
        }
        return result;
    }

    /**
     * @author: kkorkk
     * @description: 获取验证码图片
     * @date 2018/10/24 9:56
     * @param request
     * @param response
     * @return
     * */
    @RequestMapping("defaultKaptcha")
    public void defaultKaptcha(HttpServletRequest request,HttpServletResponse response) throws IOException {
        response.setHeader("Cache-Control","no-store,no-cache");
        response.setContentType("image/jpeg");

        String text = producer.createText();
        BufferedImage image = producer.createImage(text);
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY,text);
        SecurityUtils.getSubject().getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY,text);
        System.out.println("=====================当前验证码："+text+"============================");
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image,"jpg",out);
    }

    @RequestMapping("/menu_list")
    @ResponseBody
    public ResultVO menuList(){
        //获取userId
        SysUser sysUser = (SysUser)SecurityUtils.getSubject().getPrincipal();
        Integer userId = sysUser.getuId();
        //获取用户的权限列表
        return null;

    }

    @GetMapping("/user_info")
    @ResponseBody
    public ResultVO userInfo(){
        SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        sysUser.setPassword("");
        sysUser.setSalt("");
        return ResultVO.SUCCESS(sysUser);
    }


}
