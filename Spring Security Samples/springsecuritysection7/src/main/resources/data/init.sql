/*Using custom users table for authentication instead of what is required by JdbcUserDetailsManager*/
CREATE TABLE `customer` (
    `id` int NOT NULL AUTO_INCREMENT,
    `email` varchar(64) NOT NULL,
    `pwd` varchar(64) NOT NULL,
    `role` varchar(64) NOT NULL,
    PRIMARY KEY (`id`)
);

INSERT INTO `customer` (`email`, `pwd`, `role`)
VALUES ('johndoe@example.com', '$2a$10$/SNzxrdpDn8SIQMWE2egLupIUY9HYP14z2scAMcZaTDP10fofsgxO', 'admin');

CREATE TABLE `authorities` (
    `id` int NOT NULL AUTO_INCREMENT,
    `customer_id` int NOT NULL,
    `name` varchar(64) NOT NULL,
    PRIMARY KEY (`id`),
    KEY `customer_id` (`customer_id`),
    CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
);

INSERT INTO `authorities` (`customer_id`, `name`)
VALUES (1, 'VIEWACCOUNT');

INSERT INTO `authorities` (`customer_id`, `name`)
VALUES (1, 'VIEWCARDS');

INSERT INTO `authorities` (`customer_id`, `name`)
VALUES (1, 'VIEWLOANS');

INSERT INTO `authorities` (`customer_id`, `name`)
VALUES (1, 'VIEWBALANCE');
