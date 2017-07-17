# spring-boot-security
SpringBoot + Gradle + Security + JPA

From: https://medium.com/@gustavo.ponce.ch/spring-boot-spring-mvc-spring-security-mysql-a5d8545d837d

- Also added admin/home
- Added nekohtml on Gradle
- @Transient was remove from field 'password' in order to make it work

## SQL Scripts modified to be used inside a DB

```sql
--
-- Table structure for table `role`
--

CREATE TABLE `springboot_security`.`role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `active` int(11) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;


--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `springboot_security`.`user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  CONSTRAINT FK_USER FOREIGN KEY (`user_id`) REFERENCES `springboot_security`.`user` (`user_id`),
  CONSTRAINT FK_ROLE FOREIGN KEY (`role_id`) REFERENCES `springboot_security`.`role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```
