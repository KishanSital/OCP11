javac -p mods -d chapter12_13/classes chapter12_13/chapter12_13/*.java chapter12_13/chapter12_13/models/*.java chapter12_13/chapter12_13/repositories/*.java chapter12_13/chapter12_13/serviceImpl/*.java chapter12_13/chapter12_13/services/*.java chapter12_13/chapter12_13/utils/*.java chapter12_13/module-info.java
jar -cf mods/chapter.twelve.thirteen.jar -C chapter12_13/classes .
java -p mods -m chapter.twelve.thirteen/chapter12_13.App
