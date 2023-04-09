- In @ManyToMany scenario, we can maintain an intermediate table which would contain mapping for 2 tables whose fields have such relations like Person (courses) and Course (persons).
- For that intermediate table, we can use @JoinTable annotation.
- We have to add mappedBy in @ManyToMany annotation for the relationship owner, this 'owner' thing is just logical and required to put in either of the fields which are annotated by it.

- @JsonIgnore annotation needs to be there in either one of the classes having @OneToMany/@ManyToOne/@ManyToMany annotations otherwise it would give errors in serialising the response when sending to client due to some circular dependency.
- More here: https://stackoverflow.com/questions/26572245/hibernate-and-jackson-java-lang-illegalstateexception-cannot-call-senderror
