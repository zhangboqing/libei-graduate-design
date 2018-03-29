package com.libei.config.othor;

import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author zhangboqing
 * @date 2018/1/29
 */
//@Configuration
//@EnableScheduling
public class ScheduleConfig {



    @Scheduled(initialDelay = 1000,fixedDelay = 5000)
    public void test() {
        System.out.println("hahahhah");
    }

}
