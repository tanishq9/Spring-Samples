CREATE TABLE `users` (
 `id` INT NOT NULL AUTO_INCREMENT,
 `username` VARCHAR(64) NOT NULL,
 `password` VARCHAR(64) NOT NULL,
 `enabled` INT NOT NULL,
 PRIMARY KEY (`id`)
);

CREATE TABLE `authorities` (
    `id` int NOT NULL AUTO_INCREMENT,
    `username` varchar(64) NOT NULL,
    `authority` varchar(64) NOT NULL,
    PRIMARY KEY (`id`)
);

INSERT IGNORE INTO `users` VALUES (NULL, 'happy', '12345', '1');
INSERT IGNORE INTO `authorities` VALUES (NULL, 'happy', 'write');

/*Using custom users table for authentication instead of what is required by JdbcUserDetailsManager*/
CREATE TABLE `customer` (
    `id` int NOT NULL AUTO_INCREMENT,
    `email` varchar(64) NOT NULL,
    `pwd` varchar(64) NOT NULL,
    `role` varchar(64) NOT NULL,
    PRIMARY KEY (`id`)
);

INSERT INTO `customer` (`email`, `pwd`, `role`)
VALUES ('johndoe@example.com', '54321', 'admin');
