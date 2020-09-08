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
  
    <i>Note: Executing above command , the unit test are executed before. If it is everything is ok, is created the .jar</i>

  - Execute jar: - java -jar ./target/bowling-1.0.jar
   
    <i>Note: Must be execute the above command explained on "Generate jar" section before.</i>
   
  - Run test
    - mvn test
    
    <i>Note: the application has different test input file. You can see inside of resources folder.</i>


<b>Run or test Project 2: bowling-spring</b>
  - Go to dir: bowling-spring
    cd ./bowling-spring
    
  - Generate jar
    - mvn clean package or mvn install
  
    <i>Note: Executing above command , the unit test are executed before. If it is everything is ok, is created the .jar</i>

  - Execute jar: - java -jar ./target/bowling-spring-boot-1.0.0.jar
  
    <i>Note: Must be execute the above command explained on "Generate jar" section before.</i>
  
  - Run test
    - mvn test
    <i>Note: the application has different test input file. You can see inside of resources folder.</i>


<b>How to use the application</b>
  - You must enter the absolute file path where it is the score of player
  - The application validate input incorrect, for example: result > 10 or < 0 or letter different than F
  - To exit you must enter 1.
  - The final score file is named 'output.txt' and is copied in the same directory of input file path.
  
    Example:
      - input file path: /home/adolfo/bowling/input.txt
      - output file path: /home/adolfo/bowling/output.txt
      
  
  
