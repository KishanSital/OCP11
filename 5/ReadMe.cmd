javac -d classes chapter5/*.java chapter5/services/*.java chapter5/entities/*.java chapter5/constants/*.java
cd jar
jar -cvf jar.jar -C C:\OCP11\5\classes .
java -cp jar.jar chapter5.App
pause