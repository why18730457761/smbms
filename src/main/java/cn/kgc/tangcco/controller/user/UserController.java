package cn.kgc.tangcco.controller.user;

import cn.kgc.tangcco.pojo.User;
import cn.kgc.tangcco.service.role.RoleService;
import cn.kgc.tangcco.service.user.UserService;
import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import sun.security.pkcs11.wrapper.Constants;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping(value = "viewUser",method = RequestMethod.GET)
    public String getUserId(Integer id,Model model){
        try{
        User user=userService.getUserById(id);
        model.addAttribute(user);
        return "useradd";
        }catch (Exception e){
            e.printStackTrace();
            return "redirect:error";
        }
    }
    @RequestMapping(value = "error")
        public String error(){
        return "error";
    }


}
