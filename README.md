# MDHSkeleton
IDE  - STS

Spring Dependencies - JPA,WEB,DOC,JUNIT,ACTUATOR,AOP

This skeletal project is created to provide the basic functionalities like documentation, unit testing,logging and datasource connection.
Every time a test case is triggered it creates ".adoc" file which collectively forms a .html file in target/generated-docs folder once we execute the command "mvn package". We can view set of requests and responses documentation.

AOP is used to handle to the logging requirement. @before,@AfteterReturn and @AfterThrows advices are used to inject the logs.

Historicalvesseldata is for communicating with aws s3 (retireive/update/download dynamically)
