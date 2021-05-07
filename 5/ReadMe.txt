--compile in directory classes
javac -d classes chapter5/*.java chapter5/services/*.java chapter5/entities/*.java chapter5/constants/*.java chapter5/repositories/*.java chapter5/views/*.java
-- run compiled classes
java -cp classes chapter5.App

--compile to jar file in jar
cd jar
jar -cvf jar.jar -C C:\OCP11\5\classes .

--run jar file
java -cp jar.jar chapter5.App