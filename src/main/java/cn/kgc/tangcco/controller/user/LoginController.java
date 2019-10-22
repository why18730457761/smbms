package cn.kgc.tangcco.controller.user;

import cn.kgc.tangcco.smbms.pojo.User;
import cn.kgc.tangcco.smbms.service.user.UserService;
import cn.kgc.tangcco.smbms.tools.Constants;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    private Logger logger=Logger.getLogger(LoginController.class);
    @Resource
    private UserService userService;

    @RequestMapping(value = "dologin.html",method = RequestMethod.POST)
    public String dologin(@RequestParam String userCode, @RequestParam String userPassword, HttpSession session, HttpServletRequest request){
        logger.info("处理登录请求"+userCode+","+userPassword);
        User user=userService.login(userCode,userPassword);
        if (null!=user){//登录成功
            //将user对象储存到session作用域
            session.setAttribute(Constants.USER_SESSION,user);
            return "frame";
        }else{//登录失败
            request.setAttribute("error","登录失败,用户名或者密码错误");
            return "login";
        }
    }
    /*
    * 注销
    * */
    @RequestMapping(value = "dologout.html",method = RequestMethod.GET)
    public String logout(HttpSession session){
        session.removeAttribute(Constants.USER_SESSION);
        return "login";

    }

    @RequestMapping(value = "exlogin.html",method = RequestMethod.POST)
    public String exlogin(@RequestParam String userCode, @RequestParam String userPassword, HttpSession session, HttpServletRequest request){
        logger.info("处理登录请求"+userCode+","+userPassword);
        User user=userService.login(userCode,userPassword);
        if (null!=user){//登录成功
            //将user对象储存到session作用域
            session.setAttribute(Constants.USER_SESSION,user);
            return "frame";
        }else{//登录失败
            /*request.setAttribute("error","登录失败,用户名或者密码错误");
            request.setAttribute("userCode",userCode);
            return "login";*/
            throw new RuntimeException("登录失败,用户名或者密码错误");
        }
    }
    @ExceptionHandler(value = {RuntimeException.class})
    public String handlerException(RuntimeException e,HttpServletRequest request){
        request.setAttribute("e",e);
        return "error";
    }

}
