javac -d classes chapter3/*.java chapter3/Services/*.java chapter3/Entities/*.java
cd jar
jar -cvf jar.jar -C C:\OCP11\3\classes .
java -cp jar.jar chapter3.App
pause