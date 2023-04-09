Spring Security Custom Authentication Logic

- Spring security allows us to write our own custom logic to authenticate a user based on our requirements by implementing AuthenticationProvider interface.
- Spring security will always maintain your roles with ROLE prefix.
- hasRole() implies user is authenticated, now we will check if the authenticated user has required role
```
.mvcMatchers(GET, "/public/**").hasRole("STUDENT")
```
- There are different PasswordEncoder present in Spring Security, we can choose any implementation of that when storing password in DB.

BCrypt Hash Generator

- Encoding - Involves no secret and is reversible.
- Encryption - Reversible by using decryption with help of the secret key involved while encrypting the password.
- Hashing - Data once hashed is non-reversible, one cannot determine the original data from a hash value generated.
- Every time we hash a value, the output of hashing function is different text but the hash value is same for all these outputs. https://bcrypt-generator.com/
