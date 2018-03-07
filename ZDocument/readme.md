### Maven 命令 
```
    mvn dependency:tree：打印项目依赖关系的树形表示
    mvn package -Dmaven.test.skip=true: 打包jar
``` 
  
### Spring Boot 常用注解
```    
    1.@RestController: 告诉Spring将生成的字符串直接返回给调用者
    2.@RequestMapping: 提供“路由”信息
  （注：@RestController和@RequestMapping注解是Spring MVC 的注解（它们不是Spring Boot特有的））
  
    3.@EnableAutoConfiguration: 这个注解告诉 Spring Boot 根据您添加的jar依赖关系来“猜(guess)”你将如何配置Spring;
                                exclude属性来禁用不需要的自动配置类
    eg:spring-boot-starter-web添加了Tomcat和Spring MVC
        @Configuration
        @EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
        public class MyConfiguration {
        }
    
    @ComponentScan
    @EntityScan
    @SpringBootApplication：相当于使用@Configuration，@EnableAutoConfiguration和@ComponentScan
    @Configuration
    @Import：可用于导入其他配置类
    @ImportResource：加载XML配置文件
    @ConfigurationProperties：@ConfigurationProperties(prefix="my")
    @Profile：任何@Component或@Configuration都能被@Profile标记，从而限制加载它的时机
        1.spring.profiles.active=dev,hsqldb
        
        spring.profiles.include属性可以用来无条件的添加生效的配置
        spring.profiles.include: proddb,prodmq
        2.或使用命令行开关：
        --spring.profiles.active=dev,hsqldb
        
        3.以编程方式设置profiles
        在应用运行前，你可以通过调用SpringApplication.setAdditionalProfiles(…)方法，以编程的方式设置生效的配置
        
    @PropertySource：@PropertySource("classpath:config.properties")；YAML文件无法通过@PropertySource注解加载。 因此，在需要以这种方式加载值的情况下，需要使用properties文件。
    @EnableConfigurationProperties

    
    
    
```

### Spring Boot 启动
```
    1.用java -jar运行应用程序
    $ java -jar target/myproject-0.0.1-SNAPSHOT.jar
    启动debug日志：    
    java -jar myproject-0.0.1-SNAPSHOT.jar --debug

    
    2.使用maven命令
    mvn spring-boot:run
    
```
    
### Spring Boot 热部署
````
     当DevTools监视类路径资源时，触发重新启动的唯一方法是更新类路径中的文件时。 导致类路径更新的方式取决于您正在使用的IDE。 
     在Eclipse中，保存修改的文件将导致类路径被更新并触发重新启动。 
     在IntelliJ IDEA中，构建项目（Build→Make Project）将具有相同的效果
       
````

### Spring Boot 应用实战
```
    一、ApplicationRunner或CommandLineRunner的应用
    SpringApplication启动时如果您需要运行一些特定的代码，就可以实现ApplicationRunner或CommandLineRunner接口。 
    两个接口都以相同的方式工作，并提供一个单独的运行方法，这将在SpringApplication.run（…）完成之前调用。
    
    CommandLineRunner接口提供对应用程序参数的访问（简单的字符串数组），而ApplicationRunner使用上述的ApplicationArguments接口。
    @Component
    public class MyBean implements CommandLineRunner {
    
        public void run(String... args) {
            // Do something...
        }
    
    }
    
    如果定义了若干CommandLineRunner或ApplicationRunner bean，这些bean必须按特定顺序调用，
    您可以实现org.springframework.core.Ordered接口，也可以使用org.springframework.core.annotation.Order注解。
    
    二、日志
    彩色输出
    spring.output.ansi.enabled ：如果你的终端支持ANSI，为了增加可读性将会使用彩色的日志输出
    logging.file或logging.path：默认情况下，Spring Boot只会将日志记录到控制台而不会写进日志文件。如果除了输出到控制台你还想写入到日志文件，那你需要设置logging.file或logging.path属性
                                日志文件每达到10M就会被轮换（分割），和控制台一样，默认记录ERROR, WARN和INFO级别的信息
     'LEVEL'是TRACE, DEBUG, INFO, WARN, ERROR, FATAL, OFF
    

    三、相关配置属性
    1.spring.config.name和spring.config.location一开始就被用于确定哪些文件必须被加载，因此必须将它们定义为环境属性（通常是OS env，system属性或命令行参数）
        $ java -jar myproject.jar --spring.config.location=classpath:/default.properties,classpath:/override.properties
        $ java -jar myproject.jar --spring.config.name=myproject
    2.spring.profiles.active
    3.logging.level.root=WARN
      logging.level.org.springframework.web=DEBUG
      logging.level.org.hibernate=ERROR
    
    
    四、@ConfigurationProperties验证
        @ConfigurationProperties(prefix="foo")
        @Validated
        public class FooProperties {
        
            @NotNull
            private InetAddress remoteAddress;
        
            // ... getters and setters
        
        }
        
        或 验证嵌套属性的值您必须将关联字段注释为@Valid以触发其验证
        @ConfigurationProperties(prefix="connection")
        @Validated
        public class FooProperties {
        
            @NotNull
            private InetAddress remoteAddress;
        
            @Valid
            private final Security security = new Security();
        
            // ... getters and setters
        
            public static class Security {
        
                @NotEmpty
                public String username;
        
                // ... getters and setters
        
            }
        
        }
        
        
        



```