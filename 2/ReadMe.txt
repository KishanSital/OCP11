--compile in directory classes
javac -d classes chapter2/*.java chapter2/Fish/*.java

-- run compiled classes
java -cp classes chapter2.Aquaria

--compile to jar file in jar
cd jar
jar -cvf jar.jar -C C:\OCP11\2\classes .

--run jar file
java -cp jar.jar chapter2.Aquaria