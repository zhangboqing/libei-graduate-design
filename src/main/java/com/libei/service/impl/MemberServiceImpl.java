package com.libei.service.impl;

import com.libei.dao.mysql.LbMemberDao;
import com.libei.domain.entity.LbMember;
import com.libei.domain.form.MemberAddForm;
import com.libei.domain.form.MemberUpdateForm;
import com.libei.service.MemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhangboqing
 * @date 2018/1/1
 */
@Service("memberService")
public class MemberServiceImpl implements MemberService {

    @Autowired
    private LbMemberDao lbMemberDao;


    @Override
    public List<LbMember> findList() {
        return lbMemberDao.findList();
    }

    @Override
    public void addMember(MemberAddForm form) {
        LbMember lbMember = new LbMember();
        lbMember.setName(form.getName());
        lbMember.setPhone(form.getPhone());
        lbMember.setIdentity(form.getIdentity());
        lbMemberDao.insertSelective(lbMember);
    }

    @Override
    public void deleteMember(Integer memberId) {
        lbMemberDao.deleteById(memberId);
    }

    @Override
    public LbMember memberEdit(Integer memberId) {
        return lbMemberDao.findOne(memberId);
    }

    @Override
    public void memberUpdate(MemberUpdateForm form) {
        LbMember member = new LbMember();
        BeanUtils.copyProperties(form,member);

        lbMemberDao.updateByIdSelective(member);
    }
}
