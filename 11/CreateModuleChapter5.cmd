javac -p mods -d chapter5/classes chapter5/chapter5/*.java chapter5/chapter5/services/*.java chapter5/chapter5/entities/*.java chapter5/chapter5/constants/*.java chapter5/module-info.java
jar -cf mods/chapter.five.jar -C chapter5/classes .
::java -p mods -m chapter.five/chapter5.App
