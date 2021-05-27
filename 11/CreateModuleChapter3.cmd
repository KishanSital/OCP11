javac -p mods -d chapter3/classes chapter3/chapter3/entities/*.java chapter3/chapter3/*.java chapter3/chapter3/services/*.java chapter3/module-info.java
jar -cf mods/chapter.three.jar -C chapter3/classes .
::java -p mods -m chapter.three/chapter3.App
