package com.syhg.dao;
import com.syhg.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    /**
     * 判断用户名是否存在
     * select count(*) from user where username=?
     */
    Integer findByUsername(@Param("username") String username);
    /**
     * 根据用户名和密码查询
     */
    User findByUsernameAndPassword(@Param("username") String username,@Param("password") String password);

}