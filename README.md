# QA Automation

## To run the automated tests
This automation framework is using *Selenium Webdriver* tool with *Cucumber* and also API testing using *RestAssured* library. To run the tests on your windows machine do the following.

#### STEPS
##### Follow commands to run tests via cmd
1. `git clone https://github.com/survepravin/petstore-qa-api-test.git`
2. `cd petstore-qa-api-test`
3. `mvn install -DskipTests`
4. `mvn test -Dheadless=on` or `mvn test` (will take values from config.properties)

##### Follow commands to run tests from eclipse or intelliJ
1. `git clone https://github.com/survepravin/petstore-qa-api-test.git`
2. Import project in IDE as a maven project
3. Right click on pom.xml -> Run As -> Maven install
or 
Navigate to ``\src\test\java\runner`` and Run As _**TestRunner.java**_ as JUnit

**Note:** You can individually run each feature by navigating to _\src\test\java\features_ and Run As Cucumber feature. Reports won't be created via this approach.

#### REPORTS
Report will be generated in `\target\cucumber-html-report.html`

Report is also published on cucumber cloud, once execution is completed. Unique URL will be generated, open that url in browser.
``` 
Example:
? View your Cucumber Report at:                                            ?
? https://reports.cucumber.io/reports/2957216a-6940-4392-9acc-e16fa7598746 ?
?                                                                          ?
? This report will self-destruct in 24h.                                   ?
? Keep reports forever: https://reports.cucumber.io/profile                ?
```

## Framework Details
Framework is built with an intention to test RestAPIs with **RestAssured** library and **Cucumber** BDD.
Cucumber is an open-source testing framework that supports Behavior Driven Development for automation testing of web applications. The tests are first written in a simple scenario form that describes the expected behavior of the system from the user’s perspective.REST Assured is a Java library that provides a domain-specific language (DSL) for writing powerful, maintainable tests for RESTful APIs

## Project Structure

### src/test/resources/config.properties
Web and Api configurations are stored in this file as key value pair. _**EnvironmentVariables.java**_ class will access all the values.

### src/main/java/utils
Utility classes that can be used across the project. *EnvironmentVariables.java* global variables are set here.

### src/test/java/testRunner
This includes Runner class. Run As JUnit to execute tests based on tags.You can configure cucumber options like report name, features, stepDefinitions, etc.

### src/test/java/features
Feature files as per functionality, includes '*.feature*' files.

### src/test/java/stepDefinitions
Cucumber Steps are implemented in this package.
