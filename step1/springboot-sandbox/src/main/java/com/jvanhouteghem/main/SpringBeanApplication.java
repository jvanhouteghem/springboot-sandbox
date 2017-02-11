package com.jvanhouteghem.main;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.jvanhouteghem.foo.User;

@SpringBootApplication // = @Configuration + @EnableAutoConfiguration + WebApplication
@ComponentScan({"com.jvanhouteghem.main", "com.jvanhouteghem.foo"})
public class SpringBeanApplication {
	
	// Create bean withing spring boot application
	@Bean
	public User user(){
		return new User("Jonathan", "Vanhouteghem");
	}

    public static void main(final String[] args) throws Exception {
    	//SpringApplication.run(WebApplication.class, args);
    	
    	ApplicationContext ctx = SpringApplication.run(SpringBeanApplication.class,  args);
    	
    	// Show all the beans that Spring just manages for us out of the box
    	String[] beanNames = ctx.getBeanDefinitionNames();
    	Arrays.sort(beanNames);
    	for (String beanName : beanNames){
    		System.out.println(beanName);
    	}
    	
    	System.out.println(ctx.getBean("user").toString());
    	
    }

}

