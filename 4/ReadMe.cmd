javac -d classes chapter4/*.java chapter4/services/*.java chapter4/entities/*.java chapter4/constants/*.java chapter4/repositories/*.java chapter4/views/*.java
cd jar
jar -cvf jar.jar -C C:\OCP11\4\classes .
java -cp jar.jar chapter4.App
pause