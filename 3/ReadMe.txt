--compile in directory classes
javac -d classes chapter3/*.java chapter3/services/*.java
-- run compiled classes
java -cp classes chapter3.App

--compile to jar file in jar
cd jar
jar -cvf jar.jar -C C:\OCP11\3\classes .

--run jar file
java -cp jar.jar chapter3.App