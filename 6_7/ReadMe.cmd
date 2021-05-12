javac -d classes chapter6_7/*.java chapter6_7/services/*.java chapter6_7/entities/*.java chapter6_7/constants/*.java chapter6_7/repositories/*.java chapter6_7/views/*.java chapter6_7/interfaces/*.java
cd jar
jar -cvf jar.jar -C C:\OCP11\6_7\classes .
java -cp jar.jar chapter6_7.App
pause