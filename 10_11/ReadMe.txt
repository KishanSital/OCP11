--chapter10 and 11

--compile in directory classes
javac -d classes chapter10_11/*.java chapter10_11/services/*.java chapter10_11/entities/*.java chapter10_11/constants/*.java chapter10_11/util/*.java chapter10_11/exceptions/*.java chapter10_11/interfaces/*.java
-- run compiled classes
java -cp classes chapter10_11.App

--compile to jar file in jar
jar -cf jar/jar.jar -C classes .

--run jar file
java -cp jar/jar.jar chapter10_11.App
