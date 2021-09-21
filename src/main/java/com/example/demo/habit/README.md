# Habit Package

The habit package is an example of a complete RESTful API endpoint

## Components

1. The data model is located in the `Habit` file
2. The configuration for the Beans is found in the `HabitConfig` file
3. The `HabitController` is the interface for the outer world. All REST mappings are located here
4. The published data object is mapped via the `HabitDTO` class. It maps the internal representation of the `Habit`
   object to a client object.
5. The `HabitRepository` implements a JPAPersistence Repository and handles internal object retrieval.
6. The `HabitService` is a Facade for the internal `HabitRepository`. It manages retrieval and updates of the repository
   via decomposing internal and external methods.

## Decorators

This section lists and explains decorators which are used throughout this package.

1. **Habit**
    1. `@Entity` `@Table` `@Id` `@SequenceGenerator` `@GeneratedValue`
2. **HabitConfig**
    1. `@Configuration` `@Bean`
3. **HabitController**
    1. `@RestController` `@RequestBody` `@RequestMapping` `@Autowired` `@PathVariable` `@GetMapping | @PutMapping | @PostMapping | @DeleteMapping`
4. **HabitRepository**
    1. `@Repository` `@Query`
5. **HabitService**
    1. `@Service` `@Autowired` `@Transactional`

### Decorators - Habit

1. **@Entity**
    1. A class of type Entity indicates a class that, at an abstract level, is correlated with a table in the database.
       Each object instantiated by this class indicates a tuple of the table itself, containing the information of the
       latter.
    2. Docs: https://www.baeldung.com/entity-to-and-from-dto-for-a-java-spring-application
2. **@Table**
    1. Defines the Entity Name as the JPA correlated Table name. Leaving the decorator as it is will name the DB table
       exactly as the class name. We can provide a parameter `@Table(name=xyz)`, which will name the table in the db
       accordingly
3. **JPA primary key assignment**
    1. **@SequenceGenerator**
        1. Defines a primary key generator that may be referenced by name when a generator element is specified for the
           GeneratedValue annotation. A sequence generator may be specified on the entity class or on the primary key
           field or property.
        2. Provide a SequenceGenerator if the decorator **@GeneratedValue** uses the following
           strategy: `strategy = GenerationType.SEQUENCE`
    2. **@GeneratedValue**
        1. Provides for the specification of generation strategies for the values of primary keys. The GeneratedValue
           annotation may be applied to a primary key property or field of an entity or mapped superclass in conjunction
           with the Id annotation. The use of the GeneratedValue annotation is only required to be supported for simple
           primary keys. Use of the GeneratedValue annotation is not supported for derived primary keys.
        2. Available
           strategies``GenerationType.AUTO | GenerationType.IDENTITY | GenerationType.SEQUENCE | GenerationType.TABLE``
        3. Docs: https://www.baeldung.com/jpa-strategies-when-set-primary-key

### Decorators - HabitConfig

1. **@Configuration**
    1. Indicates that a class declares one or more @Bean methods and may be processed by the Spring container to
       generate bean definitions and service requests for those beans at runtime, for example:
       ```Java
       @Configuration
       public class AppConfig {

              @Bean
              public MyBean myBean() {
                  // instantiate, configure and return bean ...
              }
       }
       ```
2. **@Bean**
    1. Basically Beans are objects in the Spring framework representation
    2. Uses the Inversion of Control principle to manage the object's dependencies.
    3. Removes the necessity to instantiate every object in our application, as the framework works out which object
       depends on which Beans through dependency injection.

### Decorators - HabitController

1. **@RestController**
    1. @RestController is a specialized version of the controller. It includes the @Controller and @ResponseBody
       annotations, and as a result, simplifies the controller implementation.
    2. Docs: https://www.baeldung.com/spring-controller-vs-restcontroller
2. **@Autowired**
    1. Marks a constructor, field, setter method, or config method as to be autowired by Spring's dependency injection
       facilities.
    2. Docs: https://www.baeldung.com/spring-autowired-abstract-class
3. **@GetMapping, @PostMapping ...**
    1. Provides the path parameter as `path = "/entity/{entityId}"` which can be accessed by the methods parameter
       preceeding `@PathVariable("entityId") type name`
4. **@PathVariable**
    1. Necessary to access a specified Path parameter inside the method
5. **@RequestBody**
    1. Specifies the incoming request body by using a Data Transfer Object **DTO**
    2. Docs: https://www.baeldung.com/java-dto-pattern

### Decorators - HabitRepository

1. **@Repository**
    1. Indicates that an annotated class is a "Repository", originally defined by Domain-Driven Design (Evans, 2003)
       as "a mechanism for encapsulating storage, retrieval, and search behavior which emulates a collection of objects"
       . Teams implementing traditional Java EE patterns such as "Data Access Object" may also apply this stereotype to
       DAO classes, though care should be taken to understand the distinction between Data Access Object and DDD-style
       repositories before doing so. This annotation is a general-purpose stereotype and individual teams may narrow
       their semantics and use as appropriate.
    2. By using an interface with a JPARepository we get useful common data access and modifier methods to use in our
       service
    3. Docs: https://www.baeldung.com/spring-component-repository-service
2. **@Query**
    1. Define database queries directly on the interface method.
    2. For example: ``@Query("SELECT h FROM Habit h WHERE h.name = ?1")``
    3. Docs: https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods

### Decorators - HabitService

1. **@Service**
    1. Indicates that an annotated class is a "Service", originally defined by Domain-Driven Design (Evans, 2003) as "an
       operation offered as an interface that stands alone in the model, with no encapsulated state."
       May also indicate that a class is a "Business Service Facade" (in the Core J2EE patterns sense), or something
       similar.
    2. Services work on the application layer and are accessed via the RestController.
2. **@Transactional**
    1. We can use @Transactional to wrap a method in a database transaction. It allows us to set propagation, isolation,
       timeout, read-only, and rollback conditions for our transaction. We can also specify the transaction manager.
    2. Side effects of different Isolation parameters: https://www.baeldung.com/spring-transactional-propagation-isolation
## Domain-Driven Design - Overview

![img](https://archfirst.org/static/bfoms-layered-architecture-e3fdd1f76db55bb7b2851a09442d1c70-91d80.png)