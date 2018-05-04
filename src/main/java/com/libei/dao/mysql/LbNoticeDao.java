package com.libei.dao.mysql;

import com.libei.domain.entity.LbNotice;
import com.libei.domain.result.LoginNoticeResult;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface LbNoticeDao {
    int deleteById(Integer id);

    int insert(LbNotice record);

    int insertSelective(LbNotice record);

    LbNotice findOne(Integer id);

    int updateByIdSelective(LbNotice record);

    int updateById(LbNotice record);

    @Select("select * from lb_notice order by release_time desc")
    List<LoginNoticeResult> findList();

}