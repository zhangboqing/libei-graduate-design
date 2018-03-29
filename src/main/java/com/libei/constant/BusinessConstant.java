package com.libei.constant;

/**
 * @author zhangboqing
 * @date 2018/3/16
 */
public class BusinessConstant {

    //用户登录成功后，将用户信息存入session中的key值
    public static String USER_LOGIN_KEY = "user_login_key";

    public static class UserInfo {

        public static Integer SEX_MAN = 0;
        public static Integer SEX_WUMON = 1;

        public static String transforSexValue(Integer sexType) {
            String str = "";

            switch (sexType) {
                case 0:
                    str = "男";
                    break;
                case 1:
                    str = "女";
                    break;
                default:
                    break;
            }

            return str;
        }
    }


    public static class MeetingRoome {

        //待审核
        public static Integer STATUS_WAIT = 0;
        //未通过
        public static Integer STATUS_NO = 1;
        //通过
        public static Integer STATUS_PASS = 2;


        public static String transforStatusValue(Integer type) {
            String str = "";

            switch (type) {
                case 0:
                    str = "待审核";
                    break;
                case 1:
                    str = "审核不通过";
                    break;
                case 2:
                    str = "审核通过";
                    break;
                default:
                    break;
            }

            return str;
        }
    }

}
