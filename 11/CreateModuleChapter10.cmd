javac -p mods -d chapter10/classes  chapter10/chapter10/*.java chapter10/chapter10/services/*.java chapter10/chapter10/entities/*.java chapter10/chapter10/constants/*.java chapter10/chapter10/util/*.java chapter10/chapter10/exceptions/*.java chapter10/chapter10/interfaces/*.java chapter10/module-info.java
jar -cf mods/chapter.ten.jar -C chapter10/classes .
::java -p mods -m chapter.ten/chapter10.App
