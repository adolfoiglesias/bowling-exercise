# bowling-exercise
Bowling application. The other project was developed using Java with Spring + Spring Boot.


Java used
      - version: 11

<b>Run or Test: bowling-spring</b>
  - Go to dir: bowling-spring
    cd ./bowling-spring
    
  - Generate jar
    - mvn clean package or mvn install
  
    <i>Note: Executing above command , the unit test are executed before. If it is everything is ok, is created the .jar</i>

  - Execute application: - java -jar ./target/bowling-spring-boot-1.0.0.jar [dir-file-path]
  
    Example:  java -jar ./target/bowling-spring-boot-1.0.0.jar /home/adolfo/bowling/input.txt
  
    <i>Note: Execute the above command explained on "Generate jar" section before.</i>
  
  - Run test
    - mvn test
    <i>Note: the application has different tests input files. You can see inside of resources folder. There are 12 test, between unit and integration test</i>
    


<b>About the application</b>
  - The final score is written in console.
    
