package com.syhg.controller;

import com.syhg.common.Const;
import com.syhg.pojo.User;
import com.syhg.service.UserService;
import com.syhg.utils.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/portal/")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping(value = "user/login.do")
    public ServerResponse login(String username, String password, HttpSession session){
       ServerResponse serverResponse= userService.login(username,password);
       if(serverResponse.isSucess()){
           session.setAttribute(Const.CURRENT_USER,serverResponse.getDate());
       }
        return serverResponse;
    }
    @PostMapping("user/register.do")
    public ServerResponse register(@RequestBody User user){
        System.out.println(user);
        ServerResponse serverResponse=userService.register(user);
        return serverResponse;
    }
}
