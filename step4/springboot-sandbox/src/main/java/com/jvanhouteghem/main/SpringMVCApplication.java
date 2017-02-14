package com.jvanhouteghem.main;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication // = @Configuration + @EnableAutoConfiguration + WebApplication
@ComponentScan({"com.jvanhouteghem.main", "com.jvanhouteghem.controller", "com.jvanhouteghem.config"})
public class SpringMVCApplication {

    public static void main(final String[] args) throws Exception {
    	//SpringApplication.run(SpringMVCApplication.class, args);
    	
        ApplicationContext ctx = SpringApplication.run(SpringMVCApplication.class,  args);

        // Show all the beans that Spring just manages for us out of the box
        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames){
            System.out.println(beanName);
        }   
    	
    }

}

