javac -d classes chapter6_7/*.java chapter6_7/services/*.java chapter6_7/entities/*.java chapter6_7/constants/*.java chapter6_7/repositories/*.java chapter6_7/views/*.java chapter6_7/interfaces/*.java
jar -cf jar.jar -C classes .
java -cp jar.jar chapter6_7.App
pause