# Spring Manual

Spring is an `opinionated design` framework, meaning that, since it wraps and setup a lot of things under the hood for us, there is a more-than-suggested way to proceed when using the spring tools. In particular, spring boot comes with a set of default configurations options that the spring team as opinionated to be "the-way-to-go", and once you respect them, everything run smoother.

## Spring Initializr

*<https://start.spring.io/>*

Spring Initializr is a super useful tool to create Spring projects. It saves us a lot of time building up a project that contains all the dependencies needed.

*** when adding dependencies to the pom.xml we need maven to reload the project (from IntelliJ right-click on pom.xml -> maven -> reload project)

# JPA Java Persistence API

- Jpa requires an empty constructor in the POJOS (Plain Old Java Objects)

# Spring Data JPA

hadles data to

- Hibernate : handles all the SQL statements generations

# H2

It is an in-memory database (mem)

console web per visualizzare db, usa tomcat
needs to be enabled in application.properties to use the web console

look at spring initialization, it tell us which tomcat port is opened and where to locate the h2 console (usually /h2-console); then we need to be carefull to specify the correct session of the db (which is written in the spring log in the line H2ConsoleAutoConfiguration)

# Spring MVC

*<https://www.baeldung.com/spring-boot-dispatcherservlet-web-xml>*

`MVC` is a common design pattern for GUI and Web applications where the acronyms stands for:

- `M` Model - Simple POJO with a collections of property that may be used by the view
- `V` View - data as requested by the client (http, json, xml etc..)
- `C` Controller - Java class implemented to handle request mapping, with minimal business logic ( the business logic is usually contained in a service attached to the controller)

In a web application context, the **Client** send a request to the `Controller` that basically served as a traffic light (it can be connected to some business logic operations that manipulates the client request); its underling task is essentially to compile a `Model`, i.e. a POJO or some type of data structure that will be then displayed back to the user by means of a `View`. Each entity is independent and can be interchanged easily (e.g. we can change GUI for the view without effecting the underlying model).

In the context of spring mvc, the mvc model acquire a more complex structure:

<img src=".\Images\spring_mvc.png">

The client request is handled by a `dispatcher Servlet` in Tomcat  that  handle the routing to the **controller** that is coded to handle specific request (in the image above a database with a **spring data jpa** service). The controller receives data back form the service and populates the **model** which than is passed to a view technology (e.g. Thymeleaf) that generates a view and gets returned back to the client.


# Thymeleaf

*https://www.thymeleaf.org/*

Thymeleaf is a java template engine the is used to create dynamic html and it is an alternative to Java Server Pages JSP. It is one of the possible view technology that can handle the model output to our client request in an mvc model. It is also a **natural template engine**, meaning that it can be rendered naturally by the browser