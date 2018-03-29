package com.libei.dao.mysql;

import com.libei.domain.entity.LbLoginUser;
import com.libei.domain.result.LoginUserInfoResult;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface LbLoginUserDao {
    int deleteById(Integer userId);

    int insert(LbLoginUser record);

    int insertSelective(LbLoginUser record);

    LbLoginUser findOne(Integer userId);

    int updateByIdSelective(LbLoginUser record);

    int updateById(LbLoginUser record);

    @Select("select * from lb_login_user where user_name = #{userName} and password = #{password}")
    LbLoginUser getUserByUsernameAndPassword(@Param("userName") String userName,@Param("password") String password);

    @Select("select * from lb_login_user a left join lb_user_info b on b.user_id = a.user_id where a.type = 0 order by a.user_id desc")
    List<LoginUserInfoResult> findList();

    @Select("select * from lb_login_user where user_name = #{userName}")
    LbLoginUser findByUserName(@Param("userName") String userName);
}