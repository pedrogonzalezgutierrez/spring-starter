https://github.com/srs/gradle-node-plugin/blob/master/docs/node.md

# Spring Starter

Spring Starter it is a project that provides every useful tool you would like to use in your web application, so it is the perfect starting point. It is very flexible so it allows you to add new features in a easy way.

## Gradle and Spring Boot 
The Spring Boot Gradle Plugin provides Spring Boot support in Gradle, allowing you to package executable jar or war archives, run Spring Boot applications and use the dependency management provided by spring-boot-dependencies. Spring Boot makes it easy to create stand-alone, production-grade Spring based Applications that can you can "just run".

## JPA 2.1
The Java Persistence API is a POJO persistence API for object/relational mapping. It contains a full object/relational mapping specification supporting the use of Java language metadata annotations and/or XML descriptors to define the mapping between Java objects and a relational database. Directly, JPA can't be used for persistence. Persistence, means which deals with storing and retrieving of application data. It needs an ORM implementation to persist the java objects into DB like Hibernate
### Entity Graph
One of the latest features in JPA 2.1 is the ability to specify fetch plans using Entity Graphs. The definition of an entity graph is independent of the query and defines which attributes to fetch from the database. An entity graph can be used as a fetch or a load graph. This is useful since it allows you to customize the data that is retrieved with a query or find operation. When working with mid to large size applications is common to display data from the same entity in different and many ways. In other cases, you just want to select a smallest set of information to optimize the performance of your application.

### Hibernate Second-Level Cache with EHCache
One of the advantages of database abstraction layers such as ORM (object-relational mapping) frameworks is their ability to transparently cache data retrieved from the underlying store. This helps eliminate database-access costs for frequently accessed data. Hibernate Second Level cache providers include EHCache and Infinispan, but EHCache is more popular and we will use it this project.

Performance gains can be significant if read/write ratios of cached content are high, especially for entities which consist of large object graphs.

Statistics are expose by JMX bean only in **local** profile.
## Spring Security
Spring Security provides comprehensive security services for J2EE-based enterprise software applications. There is a particular emphasis on supporting projects built using The Spring Framework, which is the leading J2EE solution for enterprise software development. People use Spring Security for many reasons, but most are drawn to the project after finding the security features of J2EE's Servlet Specification or EJB Specification lack the depth required for typical enterprise application scenarios.

There are two roles *ROLE_ADMIN* (can manage users, roles and languages) and *ROLE_EDITOR* (can manage languages).

## Log4J
log4j is a reliable, fast and flexible logging framework (APIs) written in Java, which is distributed under the Apache Software License. log4j is a popular logging package written in Java.  The output from Log4j can go to the console, but it can also go to an email server, a database table, a log file, or various other destinations. Another great benefit of Log4j is that different levels of logging can be set. The levels are hierarchical and are as follows: TRACE, DEBUG, INFO, WARN, ERROR, and FATAL.

Typically, you can configure Log4j via a configuration file **log4j2.xml**. This means that you can change your logging configuration without requiring code updates. If you need to do something like set the log level to DEBUG for your deployed application in order to track down a particular bug, you can do this without redeploying your application. Instead, you can change your log configuration file, reread the configuration file, and your logging configuration gets updated.

## Mapping framework: Dozer
A mapping framework is useful in a layered architecture where you are creating layers of abstraction by encapsulating changes to particular data objects vs. propagating these objects to other layers (i.e. external service data objects, domain objects, data transfer objects, internal service data objects). Dozer bean is configured in the file *mapper.xml*.

## Tiles
A free open-sourced templating framework for modern Java applications, based upon the Composite pattern it is built to simplify the development of user interfaces. Tiles allows authors to define page fragments which can be assembled into a complete pages at runtime. These fragments, or tiles, can be used as simple includes in order to reduce the duplication of common page elements or embedded within other tiles to develop a series of reusable templates. These templates streamline the development of a consistent look and feel across an entire application.
    
In the file *tiles-definitions.xml* you can create your owns templates. There is one template *defaultTemplate* which provides a header, menu, content and a footer.

### Jquery, Bootstrap and Font Awesome
This project comes with a responsive design by Bootstrap3 and JQuery 3.2.1. You can use the set of icons of Bootstrap and Font Awesome as well.

## User Interface
There is a full User Interface for managing users, roles and languages.

Homepage:

<img src="screenshot/01.png" align="right" />

Profile page:

<img src="screenshot/02.png" align="right" />

Manage Languages:

<img src="screenshot/03.png" align="right" />

Manage Roles:

<img src="screenshot/04.png" align="right" />

Create user (Spring Validator):

<img src="screenshot/05.png" align="right" />

Update user:

<img src="screenshot/06.png" align="right" />