# Step 2 :  Web application demo with Spring MVC
 
## I. A simple index.html page

1. Create new run class and call it SpringMVCApplication

```java
@SpringBootApplication // = @Configuration + @EnableAutoConfiguration + WebApplication
public class SpringMVCApplication {

    public static void main(final String[] args) throws Exception {
    	SpringApplication.run(SpringMVCApplication.class, args);
    }

}
```

2. Create index.html file in src/main/resources

```java
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>First page</title>
</head>
<body>

	<p>Hello world</p>

</body>
</html>
```

3. Go to http://localhost:8080/

## II. Our first controller

1. create src/main/webapp/hello.html

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Insert title here</title>
	</head>
	<body>
		<b>hello2</b>
	</body>
</html>
```

2. Create new package com.jvanhouteghem.controller and add inside a new class PageController

```java
@Controller
public class PageController {

	@RequestMapping("/helloworld")
	public String helloworld(){
		return "hello";
	}
	
}
```

3. Update SpringMVCApplication to scan the new package

```java
@SpringBootApplication
@ComponentScan({"com.jvanhouteghem.main", "com.jvanhouteghem.controller"}) // Update
public class SpringMVCApplication /*extends SpringBootServletInitializer*/{

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
```

Check output from sysout : pageController appears in the list.

4. Create src/main/resources/application.properties

```
spring.mvc.view.prefix: /
spring.mvc.view.suffix: .html
```


## FAQ

### @RestController vs @Controller

@RestController return value, @Controller return a view.

### javax.servlet.ServletException: Circular view path

