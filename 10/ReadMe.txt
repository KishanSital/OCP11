--chapter10

--compile in directory classes
javac -d classes chapter10/*.java chapter10/services/*.java chapter10/entities/*.java chapter10/constants/*.java chapter10/util/*.java chapter10/exceptions/*.java chapter10/interfaces/*.java
-- run compiled classes
java -cp classes chapter10.App

--compile to jar file in jar
jar -cf jar/jar.jar -C classes .

--run jar file
java -cp jar/jar.jar chapter10.App
