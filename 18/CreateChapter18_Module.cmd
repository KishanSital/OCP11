javac -p mods -d chapter18/classes chapter18/chapter18/*.java chapter18/chapter18/models/*.java chapter18/chapter18/repositories/*.java chapter18/chapter18/serviceImpl/*.java chapter18/chapter18/services/*.java  chapter18/chapter18/views/*.java chapter18/chapter18/utils/*.java chapter18/module-info.java
jar -cf mods/chapter.eighteen.jar -C chapter18/classes .
java -p mods -m chapter.eighteen/chapter18.App
pause