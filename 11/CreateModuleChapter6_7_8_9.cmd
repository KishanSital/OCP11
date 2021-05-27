javac -p mods -d chapter6_7_8_9/classes chapter6_7_8_9/chapter6_7/*.java chapter6_7_8_9/chapter6_7/services/*.java chapter6_7_8_9/chapter6_7/entities/*.java chapter6_7_8_9/chapter6_7/constants/*.java chapter6_7_8_9/chapter6_7/repositories/*.java chapter6_7_8_9/chapter6_7/views/*.java chapter6_7_8_9/chapter6_7/interfaces/*.java chapter6_7_8_9/module-info.java
jar -cf mods/chapter.six.seven.eight.nine.jar -C chapter6_7_8_9/classes .
::java -p mods -m chapter.six.seven.eight.nine/chapter6_7.App
