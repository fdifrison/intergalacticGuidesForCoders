# Spring Manual

*<https://docs.spring.io/spring-framework/docs/4.3.12.RELEASE/spring-framework-reference/html/index.html>* -> Manual

---

Spring is an `opinionated design` framework, meaning that, since it wraps and setup a lot of things under the hood for us, there is a more-than-suggested way to proceed when using the spring tools. In particular, spring boot comes with a set of default configurations options that the spring team as opinionated to be "the-way-to-go", and once you respect them, everything run smoother.

---

# The Spring Context

*<https://www.baeldung.com/spring-application-context>*

Spring contexts are also called Spring IoC containers, which are responsible for instantiating, configuring, and assembling beans by reading configuration metadata from XML, Java annotations, and/or Java code in the configuration files.

---

# Dependency Injection DI

*<https://martinfowler.com/articles/injection.html>* -> DI

*<https://martinfowler.com/bliki/InversionOfControl.html>* -> IoC

```sh
"When you go and get things out of the refrigerator for yourself, you can cause problems. You might leave the door open, you might get something Mommy or Daddy doesn't want you to have. You might even be looking for something we don't even have or which has expired. What you should be doing is stating a need, "I need something to drink with lunch," and then we will make sure you have something when you sit down to eat."
```

Dependency injection is a specific form of Inversion of Control IoC; it allows the spring framework to compose the application by controlling which implementation is injected. DI or IoC can be described as follows:

```sh
"One important characteristic of a framework is that the methods defined by the user to tailor the framework will often be called from within the framework itself, rather than from the user’s application code. The framework often plays the role of the main program in coordinating and sequencing application activity. This inversion of control gives frameworks the power to serve as extensible skeletons. The methods supplied by the user tailor the generic algorithms defined in the framework for a particular application."
```

It is the framework that decide the flow of operations needed by the application.

```sh
"The major difference between an object-oriented framework and a class library is that the framework calls the application code. Normally the application code calls the class library. This inversion of control is sometimes named the Hollywood principle, “Do not call us, we call You”.
```

Dependency Injection is a software design technique in which the creation and binding of dependencies are done outside of the dependent class. Afterwards, said dependencies are provided already instantiated and ready to be used, hence the term “injection”; in contrast to the dependent class having to instantiate its dependencies internally, and having to know how to configure them, thus causing coupling. In essence DI is just a renaming (by Martin Flower) of IoC to better fit its use in the context of injecting dependencies.

 We have 3 types of Injection:

* Constructor Injection: When dependencies are provided through the dependent class constructor
* Setter Injection: When dependencies are provided via a public property of the dependent class
* Interface Injection: When dependencies are provided directly into a dependent class method as an argument

Interface injection is to be preferred  because it allows spring to decide at runtime the implementation of the object to inject (based on annotations) and makes the code more testable.

## Dependency Inversion

*<https://martinfowler.com/articles/dipInTheWild.html>*

The concept of dependency inversion is strictly related to dependency injection; with inversion (the **D** in the SOLID principle) we indicated how we should structure our code to avoid **strong coupling** between java objects, a bad practice especially in large application where one small change could cause an avalanche. With dependency injection we instead refer to how the code functionally works, in fact it is how spring assemble our application, by injecting dependency autonomously where required.

The dependency inversion principle in java relies on an heavy usage of **interfaces** and **abstract** class as a mean of connection between two classes. Imagine the following example:

We have one class called **LightBulb** with two methods: **turnOn** and **turnOf**; Then we have a class called **PowerSwitch** that we want to connect to any electrical device that implements the methods on/off. Th bad practice, that produce a strong coupling and doesn't follow the `D` principle, would be to add a field referencing the LightBulb inside the PowerSwitch and pass it to its constructor, and then add methods for turning the object on and off. Here it is clear the the PowerSwitch is an higher-level module and should be able to interact with any object that represent the feature on/off. In fact, the D principle states that:

*High-level modules should not depend on low-level modules. Both should depend on abstractions.*

What we should do instead is to follow th dependency inversion principle creating two interfaces, one called **Switch** with the methods **isOn** and **press** and one called **Switchable** with the methods **turnOn** and **turnOff**. Now, the switch interface is general enough to be attached to any type of switches (a remote control or a light switch) while the switchable interface can be applied to any object that can be turned on/off by a switch.

Now the **PowerSwitch** that implements the Switch interface can have a statement in the constructor that is of the Switchable type (and not specifically a light bulb) and the LightBulb can implements the Switchable interface overriding the methods on and off for the specific object usage.

```java
// THE TWO INTERFACES
public interface Switch {
    boolean isOn();
    void press();
}

public interface Switchable {
    void turnOn();
    void turnOff();
}
```

```java
// THE HIGHER-LEVEL CLASS that shouldn't depend on the lower one
public class ElectricPowerSwitch implements Switch {
    public Switchable client;
    public boolean on;
    public ElectricPowerSwitch(Switchable client) {
        this.client = client;
        this.on = false;
        }
    public boolean isOn() {return this.on;}
   public void press(){
       boolean checkOn = isOn();
       if (checkOn) {
           client.turnOff();
           this.on = false;
       } else {
             client.turnOn();
             this.on = true;
       }
   }
}
```

```java
// THE LOWER-LEVEL class
public class LightBulb implements Switchable {
    @Override
    public void turnOn() {System.out.println("LightBulb: Bulb turned on...");}
 
    @Override
    public void turnOff() {System.out.println("LightBulb: Bulb turned off...");}
}
```

---

# Beans

Beans are the backbone of the spring architecture; managed by the Spring IoC (Inversion of Control) to be instantiated, assembled and managed; to let spring see a class as a bean we need to use one annotation (e.g. `@Controller`). To refer to the class bean, e.g. when using `@Qualifier` or when retrieving a bean instance from the application context, we need to refer to it has **the class name in lower string**.

```java
/* to retrieve an instance of a bean from the ctx we need to cast it back to the
bean itself */
ClassName className = (ClassName) ctx.getBean("className");
```

Beans are the backbone of the spring architecture; managed by the Spring IoC (Inversion of Control) to be instantiated, assembled and managed.

## Beans LifeCycle

<img src="Images\bean_lifecycle.png">

There are a lot of operation on the spring side in order to initialize a bean and there are a series of interface that we can implement to be executed after most of the steps of both initialization and destruction. Some example are:

* `InitializingBean.afterPropertiesSet()` : perform action after the beans properties are set
* `DisposableBean.destroy()` : called during bean destruction in shutdown

Similarly we have some annotations to hook into the bean lifecycle:

* `@PostConstruct` : the method with this annotation will be called after the bean construction but before it is returned to the requesting object
* `@PreDestroy` : the method with this annotation will be destroyed by the container

## Beans Scope

By default, beans are created as **Singleton**, meaning that only one instance of the beam is created and each object that needs the bean get a reference to it. Other possible scopes are:

* **Prototype** : the opposite of singleton, a new instance is created each time the bean is requested, hence each object will get its own instance of the bean
* **Request** : a single instance for http request
* **Session** : a single instance for http session
* **Application** : bean is scoped to the lifecycle of a **ServletContext**
* **Websocket** : bean is scoped to the lifecycle of a **WebSocket**

To change the scope of a bean we simply need to annotate it with : `@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)`

# @Component, Component Scan and Stereotype annotations

*<https://docs.spring.io/spring-framework/docs/4.3.12.RELEASE/spring-framework-reference/html/beans.html#beans-stereotype-annotations>*

Prior to Spring Framework 3, XML document were used to specify spring configuration options; nowadays we use annotation. At start up, the application will perform a `Component Scan`, meaning that each class and method will be parsed to see if it is annotated or not.

N.B. to perform component scan, Spring leverage the **java reflection util**, known to be a slow process, but this happen only at startup. *<https://www.oracle.com/technical-resources/articles/java/javareflection.html#:~:text=Reflection%20is%20a%20feature%20in,its%20members%20and%20display%20them>.*

A Spring `Stereotype` is a class-level annotation that defines a Spring **Beans**; when the component scane detect a stereotype it add an instance of that class to the `Spring Context`.

There are different stereotype that we can use and all descent from the `@Component` stereotype; in general they don't have much functional difference but more a context meaning.

<img src=".\Images\stereotype.png">

When using Spring Boot, its auto configuration will tell Spring to perform a scan of the package in the main class (the one annotated with `@SpringBootApplication`), therefore if a class is outside the main class package tree we need to explicitly declare that Spring has to scan also the this external package

To add an external package we need to override the Spring default behavior of looking only in the main package tree; to do this we need to annotate the main class with:

```java
@ComponentScan(basPackages = {"re.path.to.main.package", "rel.path.to.external.added.package"})
@SpringBootApplication
```

## Configuration

*<https://spring.io/blog/2020/04/23/spring-tips-configuration>*

There are different way to configure our spring app; the one that we will use most commonly for personal development make uses of the stereotype annotations to define beans. But, especially if we work with third party libraries, we may want to use the `Java-based container configuration` approach. Essentially we wil create a configuration class, annotated with the stereotype `@Configuration`, where we will have methods for instatiation, configuration ans initialization logic, annotated with `@Bean`.

Some details:

* `@Bean("name_of_bean")`: when setting a bean we can specify its name inside brackets, otherwise the name of the bean will be the name of the method upon it is placed

```java
@Bean
MyService nameOfBean() {
  return new MyService();
}
// in this case "nameOfBean" is the name of the beam; if not specified, it is the name of the class with first letter lowercase 
```

## @Service and @Controller

@Service and @Controller are two spring annotation that tells spring if a class is a bean and therefore can be handled by the spring application context as a spring element.

## @Qualifier and @Primary

Imagine to have more than one @Service that implement the same interface, but each one of them has a different implementation of its methods; If we try to ask spring to inject one of the @Service, calling its @Controller, then it will return an error since it won't be able to choose which @Service inject since they all inherit from the same interface. To tell spring which is the @Service that the @Controller should call we need to use an annotations, and here we have two choice depending on the context:

* `@Qualifier`: if we need to use more than one @service at the same time, then we can annotate their constructor (in case of a constructor injection) with the tag `@Qualifier("nameOfTheBean")` where **nameOfTheBean** correspond to the @Service class name (with the first letter lowercase).
* `@Primary`: Otherwise we can tag as @Primary the @Service that need to have the injection priority over the others that implements the same interface; this will become the `Primary Bean`.

## Spring @Profile

*<https://docs.spring.io/spring-framework/docs/4.3.12.RELEASE/spring-framework-reference/html/beans.html#beans-definition-profiles-java>*

Spring profiles are a way to have multiple runtime environment capabilities inside the same applications. As a matter of fact, spring allows us to annotate beans with a @Profile tag that will enable or disable the beans not annotated with the current chosen profile. (Active profiles is set in `resources.application.properties`) For example, if we want to switch from one db to another (maybe we have an H2 mem db for development and MySQL for production) we will simply tag with a different profile the two statements that enable one db or the other.

Inside the **application.properties** w just need to declare:

* `spring.profiles.active=IT` : if the identifier used as a tag in the relevant beans was `@Profile("IT")`; we can have more than one profile active simply adding them after the equal sign in a unique comma separated string list . (N.B. profiles name are case sensitive)

If not particular @Profile is active than the `default` @Profile is in place; this implies that, in the latter case, all the elements that are not tagged with any specific profile will be active (or those tagged with "default"). Another approach that we can take is to double tag the class/method with more than one profiles; in particular, if we specify ***default*** as tag, the class will be active even if no profile is set to active in the application.properties.

```java
// both the "IT" and the default profile are valid for this class
@Profile({"IT", "default"})
public SomeClass {}
```

## Common Exceptions

* NoSuchBeanDefinitionException: we forgot to annotate the particular object; spring doesn't know that it is a bean (add annotation such as @Controller)

---


# External Properties

*https://docs.spring.io/spring-boot/docs/2.7.0/reference/htmlsingle/#features.external-config*

Our applications should be portable and able to be deployed on different environments; to do so, we need to take care of properties that are bounded to the environment external to our application, such as username, password, urls, API keys, paths etc.. Hard coding this kind of variable is always a bed practice also because it render our application rigid and harder to change.

For these reasons we have `external properties` that can be set in different ways to be handled by spring:

* Command line arguments (override everything)
* SPRING_APPLICATION_JSON
* JNDI (in disuse)
* OS Environment variables
* `Property files` (yml file -> way to go -> **application.properties** will be packaged in the Jar)

Spring will inject these properties at runtime. We can use the Spring approach, i.e. create a custom property files and then import it in the configuration file adding the tag **@PropertySource(classpath:mysource.properties)"** or the **Spring Boot** approach where we use directly the native **application.properties** file and spring will automatically inject it in the code. without any further referencing.











---

# JPA Java Persistence API

* Jpa requires an empty constructor in the POJOS (Plain Old Java Objects)

# Spring Data JPA

handles data to

* Hibernate : handles all the SQL statements generations

# H2

It is an in-memory database (mem) with the possibility of browser GUI (needs to be enabled in application.properties to use the web console)

looking at spring initialization, it tell us which tomcat port is opened and where to locate the h2 console (usually /h2-console); then we need to be careful to specify the correct session of the db (which is written in the spring log in the line H2ConsoleAutoConfiguration)

# Spring MVC

*<https://www.baeldung.com/spring-boot-dispatcherservlet-web-xml>*

`MVC` is a common design pattern for GUI and Web applications where the acronyms stands for:

* `M` Model - Simple POJO with a collections of property that may be used by the view
* `V` View - data as requested by the client (http, json, xml etc..)
* `C` Controller - Java class implemented to handle request mapping, with minimal business logic ( the business logic is usually contained in a service attached to the controller)

In a web application context, the **Client** send a request to the `Controller` that basically served as a traffic light (it can be connected to some business logic operations that manipulates the client request); its underling task is essentially to compile a `Model`, i.e. a POJO or some type of data structure that will be then displayed back to the user by means of a `View`. Each entity is independent and can be interchanged easily (e.g. we can change GUI for the view without effecting the underlying model).

In the context of spring mvc, the mvc model acquire a more complex structure:

<img src=".\Images\spring_mvc.png">

The client request is handled by a `dispatcher Servlet` in Tomcat  that  handle the routing to the **controller** that is coded to handle specific request (in the image above a database with a **spring data jpa** service). The controller receives data back form the service and populates the **model** which than is passed to a view technology (e.g. Thymeleaf) that generates a view and gets returned back to the client.

# Spring Initializr

*<https://start.spring.io/>*

Spring Initializr is a super useful tool to create Spring projects. It saves us a lot of time building up a project that contains all the dependencies needed.

# Maven

* check if maven has built properly: on the right panel select Maven -> Lifecycle -> verify

* when adding dependencies to the pom.xml we need maven to reload the project (from IntelliJ right-click on pom.xml -> maven -> reload project)

## Multi-Module build

*<https://www.baeldung.com/maven-multi-module>*

*<https://spring.io/guides/gs/multi-module/>*

## Maven release plugin

*<https://maven.apache.org/maven-release/maven-release-plugin/>*

This plugin is used to release a project with Maven, saving a lot of repetitive, manual work. Releasing a project is made in two steps: prepare and perform.

Basically we automate the process of building, testing and deploying to github. The two command I used until now, one after the other are:

* `mvn release:prepare` -> prepare for push
* `mvn release:perform` -> push to repository

# Thymeleaf

*<https://www.thymeleaf.org/>*

Thymeleaf is a java template engine the is used to create dynamic html and it is an alternative to Java Server Pages JSP. It is one of the possible view technology that can handle the model output to our client request in an mvc model. It is also a **natural template engine**, meaning that it can be rendered naturally by the browser

# The SOLID principle in OOP

SOLID is a set of programming principles developed by Robert MArtin (Uncle Bob); the acronym brake downs in:

* `S` - Single Responsibility -> *"Just because you can it doesn't mean you should"*
  * Every class should have a single responsibility
  * There should never be more than one reason for a class to change
  * Classes should be small. No more then a full screen of code (avoid "God" classes)
* `O` - Open Closed Principle
  * Classes should be open for extension, but close for modification; The behavior should be extendible without modifying the class
  * Use getters and setters ONLY when needed
  * Use abstract base classes
* `L` - Liskov Substitution Principle -> "Looks like a duck, quacks like a duck, but needs batteries - You probably have the wrong abstraction"
  * From Barbara Liskow (1998)
  * Violation of the principle often fail the `"Is a"` test
* `I` - Interface Segregation Principle
  * Make interfaces that are client specific
  * Keep the component focused and minimize interdependency
  * Single responsibility
* `D` - Dependency Inversion Principle
  * Abstraction should NOT depend upon details; details should depend upon abstraction

Following SOLID principle will lead in general to better coding and better testing, but remember to be Pragmatic!
