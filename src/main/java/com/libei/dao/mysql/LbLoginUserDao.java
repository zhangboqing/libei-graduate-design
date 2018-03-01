package com.libei.dao.mysql;

import com.libei.domain.entity.LbLoginUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository("lbLoginUserDao")
public interface LbLoginUserDao {
    int deleteById(Integer id);

    int insert(LbLoginUser record);

    int insertSelective(LbLoginUser record);

    LbLoginUser findOne(Integer id);

    int updateByIdSelective(LbLoginUser record);

    int updateById(LbLoginUser record);

    @Select("select * from lb_login_user where user_name = #{userName} and password = #{password} limit 1")
    LbLoginUser getUserByUsernameAndPassword(@Param("userName") String userName,@Param("password") String password);
}