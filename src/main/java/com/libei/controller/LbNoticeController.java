package com.libei.controller;

import com.libei.common.utils.JodaTimeUtils;
import com.libei.dao.mysql.LbNoticeDao;
import com.libei.domain.entity.LbNotice;
import com.libei.domain.form.NoticeAddForm;
import com.libei.domain.form.NoticeUpdateForm;
import com.libei.domain.result.LoginNoticeResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author zhangboqing
 * @date 2018/1/1
 * 成员管理
 */
@Controller
@RequestMapping("/notice")
public class LbNoticeController {

    @Autowired
    private LbNoticeDao lbNoticeDao;

    /**
     * 通知列表
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/list")
    public String noticeList(Model model) throws Exception {

        List<LoginNoticeResult> noticeList = lbNoticeDao.findList();
        if (!CollectionUtils.isEmpty(noticeList)) {
            for (LoginNoticeResult notice : noticeList) {
                notice.setReleaseTimeDesc(JodaTimeUtils.timestampToString(notice.getReleaseTime().getTime()/1000, JodaTimeUtils.DateFormat.DATETIME_FORMAT));
            }
        }

        model.addAttribute("noticeList",noticeList);
        return "noticeList";
    }


    /**
     * 添加通知
     * @param form
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public String addNotice(NoticeAddForm form) throws Exception {

        //新增通知
        LbNotice lbNotice = new LbNotice();
        lbNotice.setContent(form.getContent());

        lbNoticeDao.insert(lbNotice);

        return "success";
    }


    /**
     * 删除通知
     * @param id
     * @param model
     * @throws Exception
     */
    @RequestMapping(value = "/delete")
    public String  deleteNotice(Integer id,Model model) throws Exception {
        lbNoticeDao.deleteById(id);

        return noticeList(model);
    }


    /**
     * 编辑通知
     * @param id
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/edit")
    public String  noticeEdit(Integer id,Model model) throws Exception {


        LbNotice lbNotice = lbNoticeDao.findOne(id);
        model.addAttribute("lbNotice",lbNotice);
        return "editNotice";
    }


    /**
     * 通知更新
     * @param form
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public String  noticeUpdate(NoticeUpdateForm form, Model model) throws Exception {

        LbNotice lbNotice = lbNoticeDao.findOne(form.getId());
        lbNotice.setContent(form.getContent());
        lbNoticeDao.updateByIdSelective(lbNotice);
        return "success";
    }


    /**
     * 普通用户查看-通知列表
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/list/view")
    public String noticeListView(Model model) throws Exception {

        List<LoginNoticeResult> noticeList = lbNoticeDao.findList();
        if (!CollectionUtils.isEmpty(noticeList)) {
            for (LoginNoticeResult notice : noticeList) {
                notice.setReleaseTimeDesc(JodaTimeUtils.timestampToString(notice.getReleaseTime().getTime()/1000, JodaTimeUtils.DateFormat.DATETIME_FORMAT));
            }
        }

        model.addAttribute("noticeList",noticeList);
        return "main2";
    }
}
