javac -p mods -d chapter2/classes chapter2/chapter2/*.java chapter2/chapter2/Fish/*.java chapter2/*.java
jar -cf mods/chapter.two.jar -C chapter2/classes .
::java -p mods -m chapter.two/chapter2.Aquaria
pause