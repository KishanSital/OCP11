--compile in directory classes
javac -d classes chapter4/*.java chapter4/services/*.java chapter4/entities/*.java chapter4/constants/*.java chapter4/repositories/*.java chapter4/views/*.java
-- run compiled classes
java -cp classes chapter4.App

--compile to jar file in jar
cd jar
jar -cvf jar.jar -C C:\OCP11\4\classes .

--run jar file
java -cp jar.jar chapter4.App