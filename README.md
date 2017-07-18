# spring-boot-security
SpringBoot + Gradle + Security + JPA

From: https://medium.com/@gustavo.ponce.ch/spring-boot-spring-mvc-spring-security-mysql-a5d8545d837d

- Also added admin/home
- Added nekohtml on Gradle
- @Transient was remove from field 'password' in order to make it work

## SQL Scripts modified to be used inside a DB

```sql
-- Table structure for table `role`
CREATE TABLE `springboot_security`.`role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


-- Table structure for table `user`
CREATE TABLE `springboot_security`.`user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `active` int(11) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;


-- Table structure for table `user_role`
CREATE TABLE `springboot_security`.`user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  CONSTRAINT FK_USER FOREIGN KEY (`user_id`) REFERENCES `springboot_security`.`user` (`user_id`),
  CONSTRAINT FK_ROLE FOREIGN KEY (`role_id`) REFERENCES `springboot_security`.`role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

## Define Roles and Access differente pages

Based on: http://www.baeldung.com/spring_redirect_after_login


## List Users only for ADMINs

Taken from:
- https://g00glen00b.be/spring-webapp/
- https://springframework.guru/displaying-list-of-objects-in-table-using-thymeleaf/


## Define a List of Clients for each User

- Add clients Table and user_clients Table according to this SQL Script

```sql
-- Table structure for table `client`
CREATE TABLE `springboot_security`.`client` (
   `client_id` int(11) NOT NULL AUTO_INCREMENT,
   `name` varchar(255) NOT NULL,
   `last_name` varchar(255) NOT NULL,
   PRIMARY KEY (`client_id`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- Table structure for table `user_client`
CREATE TABLE `springboot_security`.`user_client` (
    `user_id` int(11) NOT NULL,
    `client_id` int(11) NOT NULL,
    PRIMARY KEY (`user_id`,`client_id`),
   CONSTRAINT FK_USER_CLIENT FOREIGN KEY (`user_id`) REFERENCES `springboot_security`.`user` (`user_id`),
   CONSTRAINT FK_CLIENT FOREIGN KEY (`client_id`) REFERENCES `springboot_security`.`client` (`client_id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```
- OneToMany relationship according to https://www.youtube.com/watch?v=j6D-9XC-mDQ
- Delete a Client https://stackoverflow.com/questions/43606063/spring-thymeleaf-delete-object-from-html-table-and-pass-id-to-controller


## Updating Client

- Alter MySQL table according to:
```sql
ALTER TABLE springboot_security.client ADD COLUMN state INT DEFAULT 0;
```
- Using Bootstrap-switch http://bootstrapswitch.com
- Check state depends on flag: https://stackoverflow.com/questions/29826576/thymeleaf-how-to-add-checked-attribute-to-input-conditionally
