# bowling-exercise
Bowling application. There are 2 projects. One of them is java application projcted. The other project was developed using Java with Spring + Spring Boot

Requirements
  - Java
      - version: 11

<b>Run or test Project 1: Bowling-java</b>
  - Go to dir: bowling-java
    cd ./bowling-java
    
  - Generate
    - mvn clean package or mvn install
  
    <i>Note: executing above command , the unit test are executed before. If it is everything is ok, is created the .jar</i>

  - Execute jar: - java -jar ./target/bowling-1.0.jar
   
    <i>Note: must be execute the above command explained on "Generate jar" section before.</i>
   
  - Run test
    - mvn test



<b>Run or test Project 2: bowling-spring</b>
  - Go to dir: bowling-spring
    cd ./bowling-spring
    
  - Generate jar
    - mvn clean package or mvn install
  
    <i>Note: executing above command , the unit test are executed before. If it is everything is ok, is created the .jar</i>

  - Execute jar: - java -jar ./target/bowling-spring-boot-1.0.0.jar
  
    <i>Note: must be execute the above command explained on "Generate jar" section before.</i>
  
  - Run test
    - mvn test


