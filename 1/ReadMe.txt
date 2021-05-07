--compile in directory classes
javac -d classes chapter1/Inschrijving.java chapter1/Class/*.java chapter1/Student/*.java

-- run compiled classes
java -cp classes chapter1.Inschrijving

--compile to jar file in jar
cd jar
jar -cvf jar.jar -C C:\OCP11\1\classes .

--run jar file
java -cp jar.jar chapter1.Inschrijving