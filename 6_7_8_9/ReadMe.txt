--compile in directory classes
javac -d classes chapter6_7/*.java chapter6_7/services/*.java chapter6_7/entities/*.java chapter6_7/constants/*.java chapter6_7/repositories/*.java chapter6_7/views/*.java chapter6_7/interfaces/*.java
-- run compiled classes
java -cp classes chapter6_7.App

--compile to jar file in jar
cd jar
jar -cvf jar.jar -C C:\OCP11\6_7_8_9\classes .

--run jar file
java -cp jar.jar chapter6_7.App