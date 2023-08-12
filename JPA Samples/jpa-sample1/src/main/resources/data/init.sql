create table contact_msg
(
    contact_id  int AUTO_INCREMENT,
    name   varchar(100) NOT NULL,
    mobile_number varchar(10) NULL,
    email   varchar(100) NULL,
    subject   varchar(100) NULL,
    primary key (contact_id)
);
