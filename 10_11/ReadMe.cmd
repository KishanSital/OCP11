javac -d classes chapter10_11/*.java chapter10_11/services/*.java chapter10_11/entities/*.java chapter10_11/constants/*.java chapter10_11/util/*.java chapter10_11/exceptions/*.java chapter10_11/interfaces/*.java
jar  -cf jar/jar.jar -C classes .
java -cp jar/jar.jar chapter10_11.App
pause