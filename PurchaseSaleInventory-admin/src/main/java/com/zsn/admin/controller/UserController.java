package com.zsn.admin.controller;


import com.zsn.admin.exceptions.ParamsException;
import com.zsn.admin.model.RespBean;
import com.zsn.admin.pojo.User;
import com.zsn.admin.service.IUserService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author zhaoosn
 * @since 2021-05-30
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;

    @RequestMapping("login")
    @ResponseBody

    //接收前端ajex请求，返回json
    //使用 utils包下的 自定义断言
    //在登录界面 验证用户名和密码，避免判断时使用大量if-else
/*
    public RespBean login(String userName, String password, HttpSession session){
        try {
            User user = userService.login(userName,password);
            session.setAttribute("user",user);
            return RespBean.success("用户登录成功!");
        } catch (ParamsException e) {
            e.printStackTrace();
            return RespBean.error(e.getMsg());
        }catch (Exception e) {
            e.printStackTrace();
            return RespBean.error("用户登录失败!");
        }
    }
*/
    //发生异常交给全局异常（GlobalExceptionHandler）
    public RespBean login(String userName, String password, HttpSession session){
            User user = userService.login(userName,password);
            session.setAttribute("user",user);
            return RespBean.success("用户登录成功!");
    }

    /**
     * 用户信息设置页面
     * @return
     */
    @RequestMapping("setting")
    public String setting(HttpSession session){
        User user = (User) session.getAttribute("user");
        session.setAttribute("user",userService.getById(user.getId()));
        return "user/setting";
    }

    /**
     * 用户信息更新
     * @param user
     * @return
     */
    @RequestMapping("updateUserInfo")
    @ResponseBody
    public RespBean updateUserInfo(User user){
            userService.updateUserInfo(user);
            return RespBean.success("用户信息更新成功");
    }

    /**
     * 用户密码更新页
     * @return
     */
    @RequestMapping("password")
    public String password(){
        return "user/password";
    }

    /**
     * 用户密码更新
     * @param session
     * @param oldPassword
     * @param newPassword
     * @param confirmPassword
     * @return
     */
    @RequestMapping("updateUserPassword")
    @ResponseBody
    public RespBean updateUserPassword(HttpSession session,String oldPassword,String newPassword,String confirmPassword){
        try {
            User user = (User) session.getAttribute("user");
            userService.updateUserPassword(user.getUserName(),oldPassword,newPassword,confirmPassword);
            return RespBean.success("用户密码更新成功");
        } catch (ParamsException e) {
            e.printStackTrace();
            return RespBean.error(e.getMsg());
        }catch (Exception e) {
            e.printStackTrace();
            return RespBean.error("用户密码更新失败!");
        }
    }

}
