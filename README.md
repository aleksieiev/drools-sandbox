# drools-sandbox

The goal of this project is to to demonstrate how BDD stories could be generated using [Spring][1] + [JBoss Drools][2] + [Apache Freemarker][3].
  
## Projects

* [tests-holder][4]
Main project with all configs and test cases.
* [lebowski-drools-core][5]
Main Drools module. It contains all Kie configs and main KieSession configuration
* [lebowski-drools-model][6]
Module contains messages for Drools engine.
* [lebowski-drools-booking][7]
Module contains Drools decision tables for Booking service tests.

## Used keys

`qaa.serviceName`: specifies service name for story generation. E.g. `-Dqaa.serviceName="Booking"`

## How to build

In most cases it's enough to execute `./gradlew clean build` for all projects builds. 

## How to execute

Using Spring Boot runner: `./gradlew -PjvmArgs="-Dqaa.serviceName=Booking" tests-holder:bootRun`

Using Java with jar: `java -Dqaa.serviceName="Booking" -jar tests-holder/build/libs/tests-holder.jar`

## Additional information

Keep in mind that thi is just sandbox project and some features could be implemented much better, of course.


[1]: https://spring.io/projects/spring-boot
[2]: https://www.drools.org/
[3]: https://freemarker.apache.org/index.html
[4]: tests-holder
[5]: drools/lebowski-drools-core
[7]: drools/lebowski-drools-model
[6]: drools/lebowski-drools-common
[7]: drools/lebowski-drools-booking
