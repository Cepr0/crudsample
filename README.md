# crudsample
##Тестовое задание для [JavaRush](http://javarush.ru) </br>
Реализация простого [CRUD](https://ru.wikipedia.org/wiki/CRUD) Web приложения

![CRUD sample screenshot][screenshot]
[screenshot]: https://github.com/Cepr0/crudsample/blob/master/crudsample.png "CRUD sample screenshot" 

### Использованные технологии:
 - [Spring Boot](http://projects.spring.io/spring-boot/)
 - [Vaadin](https://vaadin.com)
 - [Spring Data JPA](http://projects.spring.io/spring-data-jpa/)
 
### За основу были взяты следующие примеры:
 - [Creating CRUD UI with Vaadin](https://spring.io/guides/gs/crud-with-vaadin/)
 - [Spring Data JPA CRUD with Vaadin](https://github.com/mstahv/spring-data-vaadin-crud#spring-data-jpa-crud-with-vaadin)
 - [Spring Boot Sample Data JPA](https://github.com/spring-projects/spring-boot/tree/master/spring-boot-samples/spring-boot-sample-data-jpa)
   
 ### База Данных
 Приложение работает с БД MySQL:
 ```
 Адрес БД: mysql://localhost:3306/test?useSSL=false
 Логин/пароль: root
 Схема: test
 Таблица: user
 ```
Приложение протестировано со следующей структурой таблицы **user**:
```MySQL
 CREATE TABLE `user` (
   `id` int(8) NOT NULL AUTO_INCREMENT,
   `name` varchar(25) NOT NULL DEFAULT 'undefined',
   `age` int(11) NOT NULL DEFAULT '0',
   `isAdmin` bit(1) NOT NULL DEFAULT b'0',
   `createDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (`id`),
  KEY `date` (`createDate`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

###Запуск приложения
Установите установленные [JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html), [Maven](https://maven.apache.org/), [Git](https://git-scm.com/) и [MySQL](http://dev.mysql.com/downloads/mysql/) </br>
Во время установки MySQL укажите логин и пароль **root**. Создайте схему **test** и таблицу **user** как указано выше.  </br>

Для запуска приложения выполните в рабочей директории следующие команды:
```
git clone https://github.com/Cepr0/crudsample.git
cd crudsample
mvn spring-boot:run
```
Откройте приложение в браузере по адресу: [http://localhost:8080/](http://localhost:8080/)


