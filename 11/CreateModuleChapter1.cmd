javac -p mods -d chapter1/classes chapter1/chapter1/Inschrijving.java chapter1/chapter1/Class/*.java chapter1/chapter1/Student/*.java chapter1/module-info.java
jar -cf mods/chapter.one.jar -C chapter1/classes .
::java -p mods -m chapter.one/chapter1.Inschrijving
pause