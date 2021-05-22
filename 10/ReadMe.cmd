javac -d classes chapter10/*.java chapter10/services/*.java chapter10/entities/*.java chapter10/constants/*.java chapter10/util/*.java chapter10/exceptions/*.java chapter10/interfaces/*.java
jar  -cf jar/jar.jar -C classes .
java -cp jar/jar.jar chapter10.App
pause