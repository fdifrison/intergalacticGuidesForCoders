# Spring Manual

*<https://docs.spring.io/spring-framework/docs/4.3.12.RELEASE/spring-framework-reference/html/index.html>* -> Manual

*<https://docs.spring.io/spring-boot/docs/2.7.0/reference/htmlsingle/>* -> Spring Boot

---

Spring is a java framework built to create enterprise ready applications; it is composed of different modules that are
meant to take care of different aspect of the applications' lifecycle, from databases, to security. However, to
integrate all these capabilities, we have to take care of a lot of configurations step. To solve this problem, spring
boot was introduced; it is essentially an extension layer built upon the spring framework that introduces a lot of
benefits in terms of managing dependencies (templates for jdbc, jpa, testing etc...), autoconfiguration, deployment (the
server is embedded in our application, we don't need to create a war file and deploy it to a server), and more in
general for the creation of microservices.

Spring is an `opinionated design` framework, meaning that, since it wraps and setup a lot of things under the hood for
us, there is a more-than-suggested way to proceed when using the spring tools. In particular, spring boot comes with a
set of default configurations options that the spring team as opinionated to be "the-way-to-go", and once you respect
them, everything run smoother.

---

# The Spring Context

*<https://www.baeldung.com/spring-application-context>*

Spring contexts are also called Spring IoC containers, which are responsible for instantiating, configuring, and
assembling beans by reading configuration metadata from XML, Java annotations, and/or Java code in the configuration
files.

---

# Dependency Injection DI

*<https://martinfowler.com/articles/injection.html>* -> DI

*<https://martinfowler.com/bliki/InversionOfControl.html>* -> IoC

```sh
"When you go and get things out of the refrigerator for yourself, you can cause problems. You might leave the door open,
 you might get something Mommy or Daddy doesn't want you to have. You might even be looking for something we don't even 
 have or which has expired. What you should be doing is stating a need, "I need something to drink with lunch," and then
 we will make sure you have something when you sit down to eat."
```

Dependency injection is a specific form of Inversion of Control IoC; it allows the spring framework to compose the
application by controlling which implementation is injected. DI or IoC can be described as follows:

```sh
"One important characteristic of a framework is that the methods defined by the user to tailor the framework will often
 be called from within the framework itself, rather than from the user’s application code. The framework often plays the
 role of the main program in coordinating and sequencing application activity. This inversion of control gives
 frameworks the power to serve as extensible skeletons. The methods supplied by the user tailor the generic algorithms 
 defined in the framework for a particular application."
```

It is the framework that decide the flow of operations needed by the application.

```sh
"The major difference between an object-oriented framework and a class library is that the framework calls the 
application code. Normally the application code calls the class library. This inversion of control is sometimes named
 the Hollywood principle, “Do not call us, we call You”.
```

Dependency Injection is a software design technique in which the creation and binding of dependencies are done outside
the dependent class. Afterwards, said dependencies are provided already instantiated and ready to be used, hence the
term “injection”; in contrast to the dependent class having to instantiate its dependencies internally, and having to
know how to configure them, thus causing coupling. In essence DI is just a renaming (by Martin Flower) of IoC to better
fit its use in the context of injecting dependencies.

We have 3 types of Injection:

* Constructor Injection: When dependencies are provided through the dependent class constructor
* Setter Injection: When dependencies are provided via a public property of the dependent class
* Interface Injection: When dependencies are provided directly into a dependent class method as an argument

Interface injection is to be preferred because it allows spring to decide at runtime the implementation of the object to
inject (based on annotations) and makes the code more testable.

## Dependency Inversion

*<https://martinfowler.com/articles/dipInTheWild.html>*

The concept of dependency inversion is strictly related to dependency injection; with inversion (the **D** in the SOLID
principle) we indicated how we should structure our code to avoid **strong coupling** between java objects, a bad
practice especially in large application where one small change could cause an avalanche. With dependency injection we
instead refer to how the code functionally works, in fact it is how spring assemble our application, by injecting
dependency autonomously where required.

The dependency inversion principle in java relies on a heavy usage of **interfaces** and **abstract** class as a mean
of connection between two classes. Imagine the following example:

We have one class called **LightBulb** with two methods: **turnOn** and **turnOf**; Then we have a class called
**PowerSwitch** that we want to connect to any electrical device that implements the methods on/off. The bad practice,
that produce a strong coupling and doesn't follow the `D` principle, would be to add a field referencing the LightBulb
inside the PowerSwitch and pass it to its constructor, and then add methods for turning the object on and off. Here it
is clear the PowerSwitch is a higher-level module and should be able to interact with any object that represent the
feature on/off. In fact, the D principle states that:

*High-level modules should not depend on low-level modules. Both should depend on abstractions.*

What we should do instead is to follow the dependency inversion principle creating two interfaces, one called **Switch**
with the methods **isOn** and **press** and one called **Switchable** with the methods **turnOn** and **turnOff**. Now,
the switch interface is general enough to be attached to any type of switches (a remote control or a light switch) while
the switchable interface can be applied to any object that can be turned on/off by a switch.

Now the **PowerSwitch** that implements the Switch interface can have a statement in the constructor that is of the
Switchable type (and not specifically a light bulb) and the LightBulb can implement the Switchable interface overriding
the methods on and off for the specific object usage.

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

    public boolean isOn() {
        return this.on;
    }

    public void press() {
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
    public void turnOn() {
        System.out.println("LightBulb: Bulb turned on...");
    }

    @Override
    public void turnOff() {
        System.out.println("LightBulb: Bulb turned off...");
    }
}
```

---

# Beans

Beans are the backbone of the spring architecture; managed by the Spring IoC (Inversion of Control) to be instantiated,
assembled and managed; to let spring see a class as a bean we need to use one annotation (e.g. `@Controller`). To refer
to the class bean, e.g. when using `@Qualifier` or when retrieving a bean instance from the application context, we need
to refer to it has **the class name in lower string**.

```java
// to retrieve an instance of a bean from the ctx we need to cast it back to the bean itself
ClassName className=(ClassName)ctx.getBean("className");
// or we can use reflection
        ClassName className=ctx.getBean(ClassName.class);
```

Beans are the backbone of the spring architecture; managed by the Spring IoC (Inversion of Control) to be instantiated,
assembled and managed.

## Beans LifeCycle

<img src="./Images/bean_lifecycle.png" alt="bean_lifecycle">

There are a lot of operation on the spring side in order to initialize a bean and there are a series of interface that
we can implement to be executed after most of the steps of both initialization and destruction. Some example are:

* `InitializingBean.afterPropertiesSet()` : perform action after the beans properties are set
* `DisposableBean.destroy()` : called during bean destruction in shutdown

Similarly, we have some annotations to hook into the bean lifecycle:

* `@PostConstruct` : the method with this annotation will be called after the bean construction but before it is
  returned to the requesting object
* `@PreDestroy` : the method with this annotation will be destroyed by the container

## Beans Scope

By default, beans are created as **Singleton**, meaning that only one instance of the beam is created and each object
that needs the bean get a reference to it. Other possible scopes are:

* **Prototype** : the opposite of singleton, a new instance is created each time the bean is requested, hence each
  object will get its own instance of the bean
* **Request** : a single instance for http request
* **Session** : a single instance for http session
* **Application** : bean is scoped to the lifecycle of a **ServletContext**
* **Websocket** : bean is scoped to the lifecycle of a **WebSocket**

To change the scope of a bean we simply need to annotate it with : `@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)`

# @Component, ComponentScan and Stereotype annotations

*<https://docs.spring.io/spring-framework/docs/4.3.12.RELEASE/spring-framework-reference/html/beans.html#beans-stereotype-annotations>*

Prior to Spring Framework 3, XML document were used to specify spring configuration options; nowadays, we use
annotation.
At start up, the application will perform a `Component Scan`, meaning that each class and method will be parsed to see
if it is annotated or not.

N.B. to perform component scan, Spring leverage the **java reflection util**, known to be a slow process, but this
happens only at
startup. *<https://www.oracle.com/technical-resources/articles/java/javareflection.html#:~:text=Reflection%20is%20a%20feature%20in,its%20members%20and%20display%20them>
.*

A Spring `Stereotype` is a class-level annotation that defines a Spring **Beans**; when the component scan detect a
stereotype it adds an instance of that class to the `Spring Context`.

There are different stereotype that we can use and all descent from the `@Component` stereotype; in general they don't
have much functional difference but more a context meaning.

<img src="./Images/stereotype.PNG" alt="stereotype">

When using Spring Boot, its autoconfiguration will tell Spring to perform a scan of the package in the main class (the
one annotated with `@SpringBootApplication`), therefore if a class is outside the main class package tree we need to
explicitly declare that Spring has to scan also the external package

To add an external package we need to override the Spring default behavior of looking only in the main package tree; to
do this we need to annotate the main class with:

```java
@ComponentScan(basPackages = {"re.path.to.main.package", "rel.path.to.external.added.package"})
@SpringBootApplication
```

## Configuration

*<https://spring.io/blog/2020/04/23/spring-tips-configuration>*

There are a different way to configure our spring app; the one that we will use most commonly for personal development
make uses of the stereotype annotations to define beans. But, especially if we work with third party libraries, we may
want to use the `Java-based container configuration` approach. Essentially we wil create a configuration class,
annotated with the stereotype `@Configuration` and `@ComponentScan(basePackages="nameOfTheBasePackage")` (to tell spring
where to find the class that we are annotating as beans), where we will have methods for instantiation, configuration
and initialization logic, annotated with `@Bean`.

Some details:

* `@Bean("name_of_bean")`: when setting a bean we can specify its name inside brackets, otherwise the name of the bean
  will be the name of the method upon it is placed

```java
@Bean
MyService nameOfBean(){return new MyService();}
// in this case "nameOfBean" is the name of the beam; if not specified, it is the name of the class with first letter lowercase 
```

## @Service and @Controller

@Service and @Controller are two spring annotation that tells spring if a class is a bean and therefore can be handled
by the spring application context as a spring element.

## @Qualifier and @Primary

Imagine to have more than one @Service that implement the same interface, but each one of them has a different
implementation of its methods; If we try to ask spring to inject one of the @Service, calling its @Controller, then it
will return an error since it won't be able to choose which @Service inject since they all inherit from the same
interface. To tell spring which is the @Service that the @Controller should call we need to use an annotations, and here
we have two choice depending on the context:

* `@Qualifier`: if we need to use more than one @service at the same time, then we can annotate their constructor (in
  case of a constructor injection) with the tag `@Qualifier("nameOfTheBean")` where **nameOfTheBean** correspond to the
  @Service class name (with the first letter lowercase).
* `@Primary`: Otherwise we can tag as @Primary the @Service that need to have the injection priority over the others
  that implements the same interface; this will become the `Primary Bean`.

## Spring @Profile

*<https://docs.spring.io/spring-framework/docs/4.3.12.RELEASE/spring-framework-reference/html/beans.html#beans-definition-profiles-java>*

Spring profiles are a way to have multiple runtime environment capabilities inside the same applications. As a matter of
fact, spring allows us to annotate beans with a @Profile tag that will enable or disable the beans not annotated with
the current chosen profile. (Active profiles is set in `resources.application.properties`) For example, if we want to
switch from one db to another (maybe we have an H2 mem db for development and MySQL for production) we will simply tag
with a different profile the two statements that enable one db or the other.

Inside the **application.properties** w just need to declare:

* `spring.profiles.active=IT` : if the identifier used as a tag in the relevant beans was `@Profile("IT")`; we can have
  more than one profile active simply adding them after the equal sign in a unique comma separated string list . (N.B.
  profiles name are case-sensitive)

If not particular @Profile is active than the `default` @Profile is in place; this implies that, in the latter case, all
the elements that are not tagged with any specific profile will be active (or those tagged with "default"). Another
approach that we can take is to double tag the class/method with more than one profiles; in particular, if we
specify ***default*** as tag, the class will be active even if no profile is set to active in the
application.properties.

```java
// both the "IT" and the default profile are valid for this class
@Profile({"IT", "default"})
public SomeClass{}
```

## Common Exceptions

* NoSuchBeanDefinitionException: we forgot to annotate the particular object; spring doesn't know that it is a bean (add
  annotation such as @Controller)

---

# External Properties

*<https://docs.spring.io/spring-boot/docs/2.7.0/reference/htmlsingle/#features.external-config>*

Our applications should be portable and able to be deployed on different environments; to do so, we need to take care of
properties that are bounded to the environment external to our application, such as username, password, urls, API keys,
paths etc. Hard coding this kind of variable is always a bed practice also because it renders our application rigid and
harder to change.

For these reasons we have `external properties` that can be set in different ways to be handled by spring:

* Command line arguments (override everything)
* SPRING_APPLICATION_JSON
* JNDI (in disuse)
* OS Environment variables
* `Property files` (yml file -> way to go -> **application.properties** will be packaged in the Jar)

Spring will inject these properties at runtime. We can use the Spring approach, i.e. create a custom property files and
then import it in the configuration file adding the tag **@PropertySource(classpath:mySource.properties)"** or the
**Spring Boot** approach where we use directly the native **application.properties** file and spring will automatically
inject it in the code, without any further referencing.

---

# AOP - Aspect Oriented Programming

<https://docs.spring.io/spring-framework/docs/4.0.x/spring-framework-reference/html/aop.html>

Aspect oriented programming is nothing more tha a methodology of implementation for our application that helps to reduce
boilerplate code. Essentially, its main benefit is to separates the **cross-cutting concerns** from the main business
logic. For example, logging and authentication/security are fundamental parts of the application that are not directly
related to its business logic but are present in each step (therefore cross-cutting). The idea of aop is to separate
and abstract the part of code that is not related to the business logic, to define the cross-cutting concerns in a
specific part of the application and connect back to the business logic using smart annotations that can be easily
reused, modified and removed.

To create an aspect we need the to import the `spring-boot-starter-aop` artifact; also we can optionally leverage the **
aspectj** implementation (the most mature currently).

To implement an aspect we leverage the annotations introduced by AspectJ, and the first one is `@Aspect` to be placed
upon the class which will contain a particular aspect, like logging. Inside this class we can define methods that are
executed/triggered by specific action, called `Join point`, of the main  (in spring-aop a join point correspond always
to a method execution). We have different annotations for this method depending on the particular behavior we are
looking for, but the most common is the `@Pointcut` which is a predicate that match a join point (hence a method
invocation in the main application). a Pointcut is always associated to an `Advice` which specify when the pointcut has
to be executed with respect to the joint point. We have different type of Advice:

* `@Before`: the advice is executed before the joint point but, unless it throws an exception, it cant stop the
  execution
* `@After`: the advice is executed after the joint point no matter what was the outcome
* `@AfterReturning`: the advice is executed after a joint point executes as expected
* `@AfterThrowing`: the advice is executed if the method at joint point result in an exception
* `@Around`: most powerful advice since **can perform custom behavior before and after the method execution**, and it
  can also interrupt the execution at the joint point or return its custom value

**N.B. see [Daily Code Buffer Example of AOP application](../Samples/DailyCodeBuffer/aop)**

---

# Spring MVC

In the context of we-development, the spring framework introduce **Spring MVC**

## What is MVC

*<https://www.baeldung.com/spring-boot-dispatcherservlet-web-xml>* -> servlet in spring boot context

`MVC` is a common design pattern for GUI and Web applications where the acronyms stand for:

* `M` Model - Simple POJO with a collections of property that may be used by the view
* `V` View - data as requested by the client (http, json, xml etc..)
* `C` Controller - Java class implemented to handle request mapping, with minimal business logic ( the business logic is
  usually contained in a service attached to the controller)

In a web application context, the **Client** send a request to the `Controller` that basically served as a traffic
light (it can be connected to some business logic operations that manipulates the client request); its underling task is
essentially to compile a `Model`, i.e. a POJO or some type of data structure that will be then displayed back to the
user by means of a `View`. Each entity is independent and can be interchanged easily (e.g. we can change GUI for the
view without effecting the underlying model).

In the context of spring mvc, the mvc model acquire a more complex structure:

<img src=".\Images\spring_mvc.png" alt="spring_mvc>

The client request is handled by a `dispatcher Servlet` in Tomcat that handle the routing to the **controller** that is
coded to handle specific request (in the image above a database with a **spring data jpa** service). The controller
receives data back form the service and populates the **model** which than is passed to a view technology (e.g.
Thymeleaf) that generates a view and gets returned to the client.

## Project structure

The typical project structure for a spring-boot application that concerns an MVC model end a database (jpa) is the
following:

* the **Entity** or the **Model** is the package that contains the java translation of a sql table; if using jpa we need
  to mark it with **@Entity**, define a primary key (annotating with **@Id**) and set a generation strategy. The Entity
  will need a  **Controller** to handle the rest operations (concerning web, a **@RestController**), a **Repository**
  which in case of jpa is simply and interface that extends **JpaRepository**, an interface that easily implements all
  the crud methods we may need (if something is not included we can add it to the interface body); a **Service** which
  will handle the business logic and wil be divided in an interface and its implementation.
* The **Repository** will be an interface that extends the JpaRepository (or the CrudRepository in case of jdbc
  implementation) and simply add the crud functionality to access our entity
* The **Service**, composed of an interface and its (possibly multiple) implementation, is the layer that handles the
  business logic. The implementation of the service will be injected with an instance of the repository in order to be
  able to leverage its crud capabilities.
* The **Controller** is just a manager layer, it doesn't contain any business logic but is the center of communication
  between the view the models and the services. It is injected with an instance of the service and contains the REST
  API of our applications

---

# Spring Data JPA

To make spring recognize a class as a persistent db object we need to annotate it with `@Entity`, moreover, we can give
the table a name specifying the annotation `@Table(name = "table_name")`.

## JPA entity relationship

* `one-to-one` : one entity related to only one other entity; **eager fetching type**
* `one-to-many` : one entity is related to many entities (typically List, Set, Map); **lazy fetching type**; a join
  column is used to define the relationship
* `many-to-one` : the inverse of one-to-may; **eager fetching type**; a join column is used to define the relationship
* `many-to-many` : many entities to many entities; a **join table** is used to define the relationship; **lazy fetching
  type**

Mapping between entities can be either `Unidirectional`, meaning that the mapping is done one-way (one side of the
relationship won't know about the other), or `Bidirectional` where both sides of the map know about each other (
preferred since you can navigate both direction). Only one side of the mapping will be defined as `Owning Side`, meaning
that will hold the **foreign keys** in the database; in one-to-one relationship, the side that hold the foreign key is
the owner, and in one-to-many and many-to-one is typically the "many" side. The ownership is defined by the
annotation `@mappedBy`.

**JPA Cascade types**

Cascade operations are such that if performed on a parent entity, they will be reverberated also to the child entities.
JPA Cascade type are:

* PERSIST : saving
* MERGE : merging
* REFRESH
* REMOVE : remove children when parent is deleted
* ALL : all of the above

**Embeddable Types**

These are types used to define a common set of properties between two or more objects (e.g. an order object have a
billing and a shipping address, both fields evidently with common properties; this is a good use case for embedded
types)

## Loading data at creation

Spring JDBC has its own data initializer capabilities via Spring Boot; it will by default try to load `schema.sql`
and `data.sql` from root of class path; we can also use platform specific data source file, but we need to enable in the
application.properties the string `spring.datasource.platform` and specify the schema and data files
like `schema-${platform}.sql` and `data-${platform}.sql`. If we use Spring Boot data initializer we should set Hibernate
DLL to **none** or **validate** to avoid conflicts.

## Hibernate

Definitions:

* DDL = Data Definition Language, the type of SQL language we are going to use for defining table, indices, foreign keys
  etc. anything that deals with the data structure
* DML = Data Manipulation Language, the SQL statement for CRUD operations

We can set to hibernate behavior in the application.properties as:

`spring.jpa.hibernate.ddl-auto=`:

* none
* validate: hibernate will fail if something unexpected is present in the ddl (e.g. column missing) -> preferred option
  in production
* update: hibernate will try to update the schema -> DANGEROUS, we don't want to hibernate to have DDL capabilities, it
  is
  a security issue (it should only have DML capabilities)
* create
* create-drop: everything is dropped when the connection is shut down (default with h2)

### Import data

Data can be loaded from an `import.sql` file that as to be placed on root of class path (executed only if DLL behavior
is set to CREATE or CREATE-DROP); it is different from what happen with Spring JDBC

---

# Spring Testing

Definitions:

* `Text Fixture` : is the set of objects and data that ensure a known test environment (we need to know the expected
  outcome of the test, and it must be repeatable).
* `Unit testing`: code written to test unity, i.e. single fast-executable portion of code; should have no external
  dependency (no db, no spring context) and should cover around 70, 80% of the code (not more or become
  counterproductive).
* `Integration test`: these are tests that have a larger scope, i.e. test the behavior between objects and part of the
  system; they can rely on external dependency
* `Functional test`: (also called end-to-end test) tests that are performed on the running application using external
  framework like selenium
* `TDD`: Test Driven Development, write the test first, which will fail, than code to fix the test
* `BDD`: Behavior Drive Development, build on top of TDD, where test should be built to check that a unit satisfy a
  desired behavior (more business oriented)
* `Mock`: fake implementation of a class used for testing
* `Spy`: partial mock to override only some methods of a class that is tested

Importing in maven **spring-boot-starting-test** we will bring in a set of tools for testing:

* JUnit
* Spring Test and Spring Boot Test
* AsserJ
* Hamcrest
* Mockito
* JSONasser
* JSONPath

---

# Spring Initializr

*<https://start.spring.io/>*

Spring Initializr is a super useful tool to create Spring projects. It saves us a lot of time building up a project that
contains all the dependencies needed.

# Maven

* check if maven has built properly: on the right panel select Maven -> Lifecycle -> verify

* when adding dependencies to the pom.xml we need maven to reload the project (from IntelliJ right-click on pom.xml ->
  maven -> reload project)

## Multi-Module build

*<https://www.baeldung.com/maven-multi-module>*

*<https://spring.io/guides/gs/multi-module/>*

## Maven release plugin

*<https://maven.apache.org/maven-release/maven-release-plugin/>*

This plugin is used to release a project with Maven, saving a lot of repetitive, manual work. Releasing a project is
made in two steps: prepare and perform.

Basically we automate the process of building, testing and deploying to GitHub. The two command I used until now, one
after the other are:

* `mvn release:prepare` -> prepare for push
* `mvn release:perform` -> push to repository

## Launch Spring Boot from Maven

We can directly launch our application from terminal using maven with the following command from the application folder:

```shell
mvn spring-boot:run
```

# Thymeleaf

*<https://www.thymeleaf.org/>*

Thymeleaf is a java template engine the is used to create dynamic html, and it is an alternative to Java Server Pages
JSP. It is one of the possible view technology that can handle the model output to our client request in a mvc model.
It is also a **natural template engine**, meaning that it can be rendered naturally by the browser

# H2

It is an in-memory database (mem) with the possibility of browser GUI (needs to be enabled in application.properties to
use the web console)

looking at spring initialization, it tells us which tomcat port is opened and where to locate the h2 console (usually
/h2-console); then we need to be careful to specify the correct session of the db (which is written in the spring log in
the line H2ConsoleAutoConfiguration)

---

# The SOLID principle in OOP

SOLID is a set of programming principles developed by Robert MArtin (Uncle Bob); the acronym brake downs in:

* `S` - Single Responsibility -> *"Just because you can it doesn't mean you should"*
    * Every class should have a single responsibility
    * There should never be more than one reason for a class to change
    * Classes should be small. No more than a full screen of code (avoid "God" classes)
* `O` - Open Closed Principle
    * Classes should be open for extension, but close for modification; The behavior should be extendable without
      modifying the class
    * Use getters and setters ONLY when needed
    * Use abstract base classes
* `L` - Liskov Substitution Principle -> "Looks like a duck, quacks like a duck, but needs batteries - You probably have
  the wrong abstraction"
    * From Barbara Liskov (1998)
    * Violation of the principle often fail the `"Is a"` test
* `I` - Interface Segregation Principle
    * Make interfaces that are client specific
    * Keep the component focused and minimize interdependency
    * Single responsibility
* `D` - Dependency Inversion Principle
    * Abstraction should NOT depend upon details; details should depend upon abstraction

Following SOLID principle will lead in general to better coding and better testing, but remember to be Pragmatic!

---

# HTTP Primer

Developed by Tim Berners-Lee at CERN in 1989, HTTP it is the standard protocol for web browser operations (IETF and W3C
created the standards). The protocol has been updated in the years (2014 last HTTP/1.1 update); just watching how the
header has changed from back in the days we can see that a lot of specification have been added, such as the host, the
user agent, the encodings and cookies. In 2015 HTTP/2.0 was introduced to introduce performance in the transport of
information, but it's having a slow adoption (50% in 2020), but for developer concerns, the semantics remain pretty much
the same with the version 1.1.

## HTTP Request Methods

Request methods, also refereed as **verbs**, are used to indicate the desired action to be performed; following the most
important ones:

* `GET` : is the request for any kind of resources (html file, js, image) and it is also what the browser perform to
  visit a website
* `HEAD`: similar to GET but request only **meta** information without the body
* `POST`: is a **create** request; send data to the server
* `PUT`: it is a **create** or **update** request
* `DELETE`: is a request to delete a resource; n.b. html doesn't support delete
* `TRACE`: echo the received request; can be used to see if the request was altered by intermediate servers
* `OPTIONS`: return the HTTP methods supported by the server for the specific URL

We can further group them in:

* `Safe Methods` as the ones that only fetch information and do not cause changes on the server: GET, HEAD, OPTIONS and
  TRACE
* `Idempotent` as the ones that once the action is performed, the repetition of that action should not have any further
  outcome: PUT and DELETE + the Safe methods

<img src="./Images/HTTP_requests.PNG" alt="HTTP_requests">

---

# Spring Boot Developer Tools

*<https://docs.spring.io/spring-boot/docs/2.7.0/reference/htmlsingle/#using.devtools>*

Dev tools are a series of facilitators applicative that help us during the development of our spring web app. By
default,
they are disabled when running a jar and are not included in to repackage archives.

Some useful utilities are:

* **Automatic restart**: a restart of the application is triggered when the spring context detect a class change; by
  using 2 class loaders the process of restart is very fast
* **Template caching disabled**: templates are no longer cached therefore the application doesn't need to restart in
  order to see changes
* **Live Reload**: automatically trigger a browser refresh when resources change

*<https://mkyong.com/spring-boot/intellij-idea-spring-boot-template-reload-is-not-working/>*

*<https://youtrack.jetbrains.com/issue/IDEA-274903/In-IntelliJ-20212-compilerautomakeallowwhenapprunning-disappear-Unable-to-enable-live-reload-under-Spring-boot>*
