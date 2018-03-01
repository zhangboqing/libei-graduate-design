package com.libei.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

/**
 * @author zhangboqing
 * @date 2018/1/26
 */

@Configuration
public class FreeMarkerConfig {

    @Bean(name = "freeMarkerViewResolver")
    public FreeMarkerViewResolver sqlSessionFactoryBean() {
        FreeMarkerViewResolver freeMarkerViewResolver = new FreeMarkerViewResolver();
        freeMarkerViewResolver.setSuffix(".ftl");
        freeMarkerViewResolver.setContentType("text/html;charset=UTF-8");
        freeMarkerViewResolver.setExposeRequestAttributes(true);
        freeMarkerViewResolver.setExposeSessionAttributes(true);
        freeMarkerViewResolver.setExposeSpringMacroHelpers(true);
        freeMarkerViewResolver.setRequestContextAttribute("request");
        freeMarkerViewResolver.setCache(true);
        freeMarkerViewResolver.setOrder(0);

        return freeMarkerViewResolver;
    }
}
