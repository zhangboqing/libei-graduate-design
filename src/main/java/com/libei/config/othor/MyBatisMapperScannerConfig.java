package com.libei.config.othor;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;

/**
 * @author zhangboqing
 * @date 2018/1/1
 */
//@Configuration
//TODO 注意，由于MapperScannerConfigurer执行的比较早，所以必须有下面的注解
//@AutoConfigureAfter(MyBatisConfig.class)
//TODO 此处由该注解替代 @MapperScan(basePackages = "com.libei.dao.mysql", sqlSessionFactoryRef = "sqlSessionFactory")
public class MyBatisMapperScannerConfig {


    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("com.libei.dao.mysql");
        return mapperScannerConfigurer;
    }

}
