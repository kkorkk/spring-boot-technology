package com.demo.springbootshirodemo.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private Producer producer;

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

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

}
