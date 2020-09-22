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
    
    
<b>Game rules</b>

Que es un spare 
	- que la suma de las 2 tiros sean 10 (bolos caidos)

Que es un strike 
	- en un solo tiro se caen todos los bolos.


 1. spare en un frame
	- tienes 10 ptos  + el resultado del proximo tiro del frame siguiente

  2. el total de un frame es =
      
      el total del frame anterior  + el total que le correspode a ese frame

  
  3. si la suma de los bolos caidos en los dos tiros de un frame no es 10 entonces el score del frame es = 
	score del frame anterior + total de bolos caidos

  4.  cuadno ocurre un strike
	- le da 10 ptos al frame anterior
	- tienes 10 ptos acumulados para el frame actual
	- tiene la posibilidad de tirar dos tiros para sumarselo al frame actual
		ejemplo
		frame1 : strike entonces tienes 2 tiros 
		frame2	: caen 2 bolos y el 2do tiro caen 8 bolos, por tanto tienes un spare en este frame
		score frame1 es = 
			10 + 10 (del spare logrado en frame2)	 


  5. en los 2 tiros de un frame hay un falso o cero entonces el resultado de ese frame es:
		total de bolos tirado en algunos de los tiros
	 		
