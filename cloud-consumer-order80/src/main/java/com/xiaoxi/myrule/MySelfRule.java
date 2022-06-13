package com.xiaoxi.myrule;


//import com.netflix.loadbalancer.IRule;
//import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 负载均衡的自定义规则类，此类为自定义规则类，要写在主启动类外面，不能被扫描到。
 * @Configuration:用于定义配置类，可替换xml配置文件，被注解的类内部包含有一个或多个被@Bean注解的方法，
 * 这些方法将会被AnnotationConfigApplicationContext或AnnotationConfigWebApplicationContext类进行扫描，并用于构建bean定义，初始化Spring容器。
 * @Bean:配置bean并注入容器
 */
@Configuration
public class MySelfRule {

    /**
     * IRule接口下有几种负载均衡的策略。
     * RandomRule类定义为随机策略。
     * 这里写完了后要求告诉主启动类用的哪种策略。
     */
//    @Bean
//    public IRule getIRule(){
//        return new RandomRule();
//    }
}
