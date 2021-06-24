javac -p mods -d chapter15/classes chapter15/chapter15/*.java chapter15/chapter15/models/*.java chapter15/chapter15/repositories/*.java chapter15/chapter15/serviceImpl/*.java chapter15/chapter15/services/*.java  chapter15/chapter15/views/*.java chapter15/module-info.java
jar -cf mods/chapter.fifteen.jar -C chapter15/classes .
java -p mods -m chapter.fifteen/chapter15.App
