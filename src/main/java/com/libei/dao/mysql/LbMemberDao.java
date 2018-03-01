package com.libei.dao.mysql;

import com.libei.domain.entity.LbMember;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("lbMemberDao")
public interface LbMemberDao {
    int deleteById(Integer memberId);

    int insert(LbMember record);

    int insertSelective(LbMember record);

    LbMember findOne(Integer memberId);

    int updateByIdSelective(LbMember record);

    int updateById(LbMember record);

    @Select("select * from lb_member")
    List<LbMember> findList();

}