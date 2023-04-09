- Using Spring Security
  - We can define which pages and API should be publicly exposed and which should be protected that way only authnz (authentication and access-role) users can access the web app. It also protects from CSRF, session fixation and CORS by making some small configurations.
  - Spring security provides out of the box features to handle common security attacks like CSRF, CORS. It also has good integration with security standards like JWT, OAuth2, OpenIdConnect, etc.
  - By default when we add dependency of spring security in pom then it enables authentication for all pages of application.
    
- By default, Spring Security would try to protect the web application by redirecting to the /login page.

- Also by default, CSRF protection is enabled, therefore a CSRF token need to be present in the request -- we can disable this for public webpages and enable for private ones.

- WebSecurityConfigurerAdapter class, part of Spring Security framework, is the one that consists of security features that Spring Security is providing.
    - This class has a method configure(HttpSecurity) which is doing magic of protecting webpages by default.
    - In recent version, the default security configurations can be found inside the class SpringBootWebSecurityConfiguration and method defaultSecurityFilterChain(HttpSecurity)

```
http.authorizeRequests((requests) -> request.anyRequest().authenticated())
```
This means by default any requests are protected, authenticated() means every request should come from authenticated user.

```
http.formLogin()
```
This means username and password can be submitted via html form pages.

```
http.httpBasic()
```
The client sends HTTP requests with the Authorization header that contains the word Basic word followed by a space and a base64-encoded string username:password.

- Using permitAll() configurations we can allow full/public access to a specific resource/path or all the resources/paths inside a web application.

- Using denyAll() configuration we can deny access to a specific resource/path or all the resources/paths inside a web application regardless of user authentication.

- Regarding matchers()
  - Generally mvcMatcher is more secure than an antMatcher(), as an example - antMatchers("/secured") matches only the exact /secured URL.
  - mvcMatchers("/secured") matches /secured as well as /secured/, /secured.html, /secured.xyz.
  - In regexMatchers, we can mention path regex to configure restrictions.
