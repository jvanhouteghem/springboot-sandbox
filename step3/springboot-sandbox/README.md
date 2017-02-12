# Step 3 :  Spring security
 
## I. Initialize

New > Spring starter project > check web and security

Launch main and check console, a message like this one must appears : "Using default security password: 0786befd-4d90-447a-8b58-4279ef50b76g"

Go to localhost:8080 and fill with "user" and the password above.

## II. Update default user name and password

Update application.properties : 

```
security.user.name=user
security.user.password=pass
```
Restart main, in console you don't see no more default password.
Go back and refresh page and use "user" and "pass"

At this point we haven't authorized specific users to have access to different sections and we have only one account.

## III. Custom configuration (part 1)

1) Create package and call it com.jvanhouteghem.config


2) Add inside the package a new class, call it SecurityConfig

```java
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig  extends WebSecurityConfigurerAdapter{
	
}

```

3) Update SpringMVCApplication to scan the new package


4) Update application.properties by adding 

```
security.user.role=USER
```

5) Create new controller class

```java
@Controller
@RequestMapping("/post")
public class PostController {

	@RequestMapping("/user")
	public String user(){
		return "user";
	}
	
}
```

6) Create new view src/main/webapp/user.html

```
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Insert title here</title>
	</head>
	<body>
		<b>list</b>
	</body>
</html>
```

7) Go to localhost:8080/post/list

At this point you still can access to all pages.


8) Create new view src/main/webapp/admin.html
```
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Insert title here</title>
	</head>
	<body>
		<b>admin</b>
	</body>
</html>
```


9) Update PostController by adding role that user have

```java
@Controller
@RequestMapping("/post")
public class PostController {

	@Secured("ROLE_ADMIN") // ROLE_GUEST
	@RequestMapping("/admin")
	public String admin(){
		return "admin";
	}
	
	@Secured("ROLE_USER")
	@RequestMapping("/user")
	public String user(){
		return "user";
	}
	
}
```

10) Try http://localhost:8080/post/user and http://localhost:8080/post/admin

## IV. Custom configuration (part 2)

1) Add custom user and admin by updating SecurityConfig

```java
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	public void configureAuth(AuthenticationManagerBuilder auth) throws Exception{
		auth
			.inMemoryAuthentication()
				.withUser("admin")
				.password("password")
				.roles("ADMIN")
			.and()
				.withUser("user")
				.password("password")
				.roles("USER");
	}
	
}
```

2) Update security Config

```java
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	public void configureAuth(AuthenticationManagerBuilder auth) throws Exception{
		auth
			.inMemoryAuthentication()
				.withUser("admin")
				.password("password")
				.roles("ADMIN")
			.and()
				.withUser("user")
				.password("password")
				.roles("USER");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
			.authorizeRequests()
				.antMatchers("/posts/list").permitAll() // to allow anything from posts/list
				.antMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.and()
			.logout();
	}
	
}
```
