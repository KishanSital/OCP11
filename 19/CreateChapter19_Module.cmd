javac -p mods -d classes chapter19/*.java chapter19/models/*.java chapter19/repositories/*.java chapter19/serviceImpl/*.java chapter19/services/*.java  chapter19/views/*.java chapter19/utils/*.java module-info.java
jar -cf mods/chapter.nineteen.jar -C classes .
java -p mods -m chapter.nineteen/chapter19.App
pause