package com.syhg.service;

import com.syhg.pojo.User;
import com.syhg.utils.ServerResponse;
import sun.text.resources.FormatData;


public interface UserService {
    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    public ServerResponse login(String username,String password);
    /**
     * 注册
     */
    public ServerResponse register(User user);
}
