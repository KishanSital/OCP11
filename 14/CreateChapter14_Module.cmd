javac -p mods -d chapter14/classes chapter14/chapter14/*.java chapter14/chapter14/models/*.java chapter14/chapter14/repositories/*.java chapter14/chapter14/serviceImpl/*.java chapter14/chapter14/services/*.java chapter14/chapter14/utils/*.java chapter14/chapter14/views/*.java chapter14/module-info.java
jar -cf mods/chapter.fourteen.jar -C chapter14/classes .
java -p mods -m chapter.fourteen/chapter14.App
