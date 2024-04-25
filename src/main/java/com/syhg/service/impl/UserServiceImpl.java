package com.syhg.service.impl;

import com.syhg.common.Const;
import com.syhg.common.ResponseCode;
import com.syhg.dao.UserMapper;
import com.syhg.pojo.User;
import com.syhg.service.UserService;
import com.syhg.utils.DateUtils;
import com.syhg.utils.MD5Utils;
import com.syhg.utils.ServerResponse;
import com.syhg.vo.UserVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.security.provider.MD5;
import sun.text.resources.FormatData;

import java.awt.print.Printable;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    private UserVO convert(User user){
        UserVO userVO=new UserVO();
        userVO.setId(user.getId());
        userVO.setUsername(user.getUsername());
        userVO.setEmail(user.getEmail());
        userVO.setPhone(user.getPhone());
        userVO.setCreateTime(DateUtils.date2String(user.getCreateTime()));
        userVO.setUpdateTime(DateUtils.date2String(user.getUpdateTime()));
        return userVO;
    }


    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public ServerResponse login(String username, String password) {

        //1.用户名和密码的非空判断
        if (StringUtils.isBlank(username)) {
            return ServerResponse.creatServerResponseByFail(ResponseCode.USERNMAME_NOT_EMPTY.getCode(), ResponseCode.USERNMAME_NOT_EMPTY.getMsg());
        } else if (StringUtils.isBlank(password)) {
            return ServerResponse.creatServerResponseByFail(ResponseCode.PASSWORD_NOT_EMPTY.getCode(), ResponseCode.PASSWORD_NOT_EMPTY.getMsg());
        }
        //2.查看用户名是否存在
        Integer count = userMapper.findByUsername(username);
        if (count == 0) {
            //用户名不存在
            return ServerResponse.creatServerResponseByFail(ResponseCode.USERNAME_NOT_EXISTS.getCode(), ResponseCode.USERNAME_NOT_EXISTS.getMsg());
        }
        //3.根据用户名和密码查询
        User user = userMapper.findByUsernameAndPassword(username,MD5Utils.getMD5Code(password));
        if (user == null) {
            return ServerResponse.creatServerResponseByFail(ResponseCode.PSSWORD_ERROR.getCode(), ResponseCode.PSSWORD_ERROR.getMsg());
        }
        //4.返回结果
        String msg="登陆成功";
        return ServerResponse.creatServerResponseBySucess(convert(user),msg);


    }

    /**
     * 注册
     * @param user
     * @return
     */
    @Override
    public ServerResponse register(User user) {
//        1.参数非空校验
        if(user==null){
            return ServerResponse.creatServerResponseByFail(ResponseCode.PARAMTER_NOT_EMPTY.getCode(), ResponseCode.PARAMTER_NOT_EMPTY.getMsg());
        }
        user.setRole(Const.NORMAL_USER);    //角色0-管理员,1-普通用户
        String username=user.getUsername();
        String password=user.getPassword();
        String email=user.getEmail();
        String phone=user.getPhone();
        String question=user.getQuestion();
        String answer=user.getAnswer();
        if (StringUtils.isBlank(username)) {
            return ServerResponse.creatServerResponseByFail(ResponseCode.USERNMAME_NOT_EMPTY.getCode(), ResponseCode.USERNMAME_NOT_EMPTY.getMsg());
        } else if (StringUtils.isBlank(password)) {
            return ServerResponse.creatServerResponseByFail(ResponseCode.PASSWORD_NOT_EMPTY.getCode(), ResponseCode.PASSWORD_NOT_EMPTY.getMsg());
        }else if (StringUtils.isBlank(email)) {
            return ServerResponse.creatServerResponseByFail(ResponseCode.EMAIL_NOT_EMPTY.getCode(), ResponseCode.EMAIL_NOT_EMPTY.getMsg());
        }else if (StringUtils.isBlank(phone)) {
                return ServerResponse.creatServerResponseByFail(ResponseCode.PHONE_NOT_EMPTY.getCode(), ResponseCode.PHONE_NOT_EMPTY.getMsg());
        }else if (StringUtils.isBlank(question)) {
            return ServerResponse.creatServerResponseByFail(ResponseCode.QUESTION_NOT_EMPTY.getCode(), ResponseCode.QUESTION_NOT_EMPTY.getMsg());
        }else if (StringUtils.isBlank(answer)) {
            return ServerResponse.creatServerResponseByFail(ResponseCode.ANSWER_NOT_EMPTY.getCode(), ResponseCode.ANSWER_NOT_EMPTY.getMsg());
        }
//        2.用户名是否存在
        Integer count = userMapper.findByUsername(username);
        if (count == 1) {
            //用户名已存在
            return ServerResponse.creatServerResponseByFail(ResponseCode.USERNAME_EXISTS.getCode(), ResponseCode.USERNAME_EXISTS.getMsg());
        }
//        3.注册		——对密码加密——MD5
        user.setPassword(MD5Utils.getMD5Code(user.getPassword()));

        Integer result=userMapper.insert(user);
        if(result==0){
            //注册失败
            return ServerResponse.creatServerResponseByFail(ResponseCode.REGISTER_FAIL.getCode(), ResponseCode.REGISTER_FAIL.getMsg());

        }
//        4.返回结果
        String msg="注册成功";
        return ServerResponse.creatServerResponseBySucess(null,msg);
    }


}
