package com.libei.dao.mysql;

import com.libei.domain.entity.LbUserInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface LbUserInfoDao {
    int deleteById(Integer id);

    int insert(LbUserInfo record);

    int insertSelective(LbUserInfo record);

    LbUserInfo findOne(Integer id);

    int updateByIdSelective(LbUserInfo record);

    int updateById(LbUserInfo record);

    @Select("select * from lb_user_info where user_id = #{userId}")
    LbUserInfo findByUserId(@Param("userId") Integer userId);

    @Delete("delete from lb_user_info where user_id = #{userId}")
    void deleteByUserId(@Param("userId") Integer userId);
}