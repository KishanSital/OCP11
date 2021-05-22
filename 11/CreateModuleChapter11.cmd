javac -p mods -d chapter11/classes chapter11/chapter11/*.java chapter11/chapter11/services/*.java chapter11/module-info.java
jar -cf mods/chapter.eleven.jar -C chapter11/classes .
java -p mods -m chapter.eleven/chapter11.App
pause