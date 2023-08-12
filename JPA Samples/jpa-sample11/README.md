- Static Sorting - Retrieved data is always sorted by specified columns and directions. findByOrderByColumnNameDesc()
- Dynamic Sorting - Sorting column and direction is chosen at runtime. Sort parameter can be passed to query method to achieve same. coursesRepository.findAll(Sort.by("name").descending())
- Whenever we want to apply pagination to query results, we just need to add Pageable parameter to the query definition and the return type as Page<T>.
```
Pageable pageable = PageRequest.of(0, 5, Sort.by("name"));
// 0 is the page number and 5 is the number of records.
// If we have 20 records then there would be total 4 pages and inside each spring jpa would fetch 5 records. 

Page<Person> findByName(String name, Pageable pageable);
```

- Derived Query
```
// @Query("SELECT c FROM Contact c WHERE c.subject = :subject")
@Query(value = "SELECT * FROM contact_msg c WHERE c.subject = :subject", nativeQuery = true)
Page<Contact> findBySubject(String subject);

@Transactional // Ensures atomicity
@Modifying // Required for update queries
@Query("UPDATE Contact c SET c.subject = :status WHERE c.contactId = :id")
int updateSubjectById(int id, String subject);
```

- In ResponseEntity, we can set other HTTP response data such as status, header, body.
```
return ResponseEntity
.status(HttpStatus.ACCEPTED)
.header("header1", "value1")
.header("header2", "value2")
.body(person)
```

- Enable CORS on backend side to allow request from frontend via browser, it is the browser only which disables CORS by default.
```
@CrossOrigin(origins = "http://localhost:8080")
@RestController
```

- We can control logging at package level by mentioning properties:
```
# For all classes (ones in imported jars as well), use this severity for logging
logging.level.root=INFO

# For classes in this package, use this severity for logging
logging.level.com.packageName=ERROR
```

- By default SpringBoot uses logback for logging which logs to the console. We can create a file logback.xml and define our logging requirements in it like writing log files.
