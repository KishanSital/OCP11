javac -p mods -d chapter4/classes chapter4/chapter4/*.java chapter4/chapter4/services/*.java chapter4/chapter4/entities/*.java chapter4/chapter4/constants/*.java chapter4/chapter4/repositories/*.java chapter4/chapter4/views/*.java chapter4/module-info.java
jar -cf mods/chapter.four.jar -C chapter4/classes .
::java -p mods -m chapter.four/chapter4.App
