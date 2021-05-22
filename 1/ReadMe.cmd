javac -d classes chapter1/Inschrijving.java chapter1/Class/*.java chapter1/Student/*.java
jar -cvf jar/jar.jar -C classes .
java -cp jar/jar.jar chapter1.Inschrijving
pause