# springboot-sandbox
 
## I.Display all beans

- Create new package (for example com.jvanhouteghem.main)
- Create new class and call it SpringBeanApplication

```java
package com.jvanhouteghem.main;

@SpringBootApplication // = @Configuration + @EnableAutoConfiguration + WebApplication
public class SpringBeanApplication {

    public static void main(final String[] args) throws Exception {
        //SpringApplication.run(WebApplication.class, args);

        ApplicationContext ctx = SpringApplication.run(SpringBeanApplication.class,  args);

        // Show all the beans that Spring just manages for us out of the box
        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames){
            System.out.println(beanName);
        }   
    }

}
```

## II. Our first bean

Create new package (for example com.jvanhouteghem.foo)

Create new class in this package and call it User

```java
public class User {

    private String firstName;
    private String lastName;
    private String emailAdress;

    public User(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAdress() {
        return emailAdress;
    }

    public void setEmailAdress(String emailAdress) {
        this.emailAdress = emailAdress;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

}
```

Update SpringBeanApplication
```java
@SpringBootApplication 
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
```

## 3. Dependency injection

- Create new class in com.jvanhouteghem.foo and call it NotificationService

```java
@Service
public class NotificationService {

    public NotificationService(){

    }

    public void Send(){

    }

    public void sendAssync(){

    }

    @Override
    public String toString() {
        return "NotificationService";
    }

}
```

- Create new class in com.jvanhouteghem.main and call it PageController In this class we want to retrieve data from NotificationService. Several methods exists but it's recommanded to use setter injection (testability).

```java
@RestController
public class PageController {

    // Avoid this (not testable)
    //@Autowired
    //private NotificationService notificationService;

    // Better but avoid this too : 
    /*@Autowired
    public PageController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }*/

    // Use this (setter based injection)
    private NotificationService notificationService;
    @Autowired
    public void setNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @RequestMapping("/page")
    public String home(){
        return notificationService.toString();
    }


}
```

## FAQ

### A bean ?
Its a recipe for creating actual instances of the class defined by that bean definition [
[Link 1](http://docs.spring.io/spring-javaconfig/docs/1.0.0.M4/reference/html/ch02s02.html) , 
[Link 2](http://stackoverflow.com/a/17193458)]


### Spring core 
[[Link 1](https://www.jmdoudoux.fr/java/dej/chap-spring_core.htm)]


### Java bean ? Spring bean ?
Java beans are normal POJO classes with getter and setter methods.
Spring beans are the beans that are managed by spring IOC container. Here Java beans are also spring beans.
[[Link 1](https://www.quora.com/What-are-the-differences-between-Spring-beans-and-Java-beans)]

### SpringBootApplication

@SpringBootApplication is a convenience annotation that adds all of the following:
- @Configuration tags the class as a source of bean definitions for the application context.
- @EnableAutoConfiguration tells Spring Boot to start adding beans based on classpath settings, other beans, and various property settings.
Normally you would add @EnableWebMvc for a Spring MVC app, but Spring Boot adds it automatically when it sees spring-webmvc on the classpath. This flags the application as a web application and activates key behaviors such as setting up a DispatcherServlet.
- @ComponentScan tells Spring to look for other components, configurations, and services in the the hello package, allowing it to find the controllers.
- 
[[Link 1](https://spring.io/guides/gs/caching/)]

### Autowiring

Don't do :-1:

```java
@RestController
public class PageController {

	private NotificationService notificationService;
	
	public PageController(){
		// Problem : here we are always tied to creating an instance of the class notification service (tighlty coupled software) ; also probs for tests
		
		notificationService = new NotificationService();
	}
	
	@RequestMapping("/")
	public String home(){
		return "Hello Page Controller...";
	}

}
```

Use dependency injection, 3 ways : 

1- Avoid this (property based injection makes testing very difficult) : :-1:

```java
@RestController
public class PageController {
	
	// (java reflection)
	@Autowired 
	private NotificationService notificationService;
	
	@RequestMapping("/")
	public String home(){
		return "Hello Page Controller...";
	}

}
```

2 - Another way : constructor injection
(...)

3 - Do this (setter based injection) (the better way) : :+1:

```java
@RestController
public class PageController {
	
	private NotificationService notificationService;
	
	// Now when this class is constructed a setter method is called and that dependency is inject into our class
	@Autowired
	public void setNotificationService(NotificationService notificationService) {
		this.notificationService = notificationService;
	}

}
```

### @SpringBootApplication
@SpringBootApplication = @Configuration + @EnableAutoConfiguration + WebApplication

The app configuration annotation basically says this class right here is a source of definitions for the application context.
So if we create beans here they're going to be in the application context component scan says hey i want you to scan for components configurations and services within this package.

### @Service
@Service tells spring that this class is some type of spring class that it have to manage for.

### Bean
- Create user class
- In @SpringBootApplication class add 

```java
@Bean public User user(){
	return new user("Jonathan", "Vanhouteghem");
}
```
- Interact with user bean : get the application context