javac -p mods -d chapter1/classes chapter1/chapter1/Inschrijving.java chapter1/chapter1/Class/*.java chapter1/chapter1/Student/*.java chapter1/module-info.java
:: -p or --module-path used for the path where the modules are. In case my module-info.java file requires a  module
:: java knows then where to look for it, the -d stands for the directory where the compiled files will be stored, we compile the module-info.java file as last,
:: because when we're exporting a package, the classes need be compiled for this to work
jar -cf mods/chapter.one.jar -C chapter1/classes .
:: -cf stands for create file, so we're packaging all the compiled classes within chapter1/classes to a jar named chapter.one and it's being stored in the mods folder

::java -p mods -m chapter.one/chapter1.Inschrijving
::to run the main() method in Inschrijving class of chapter one using the module

::java -p mods --list-modules 
:: list all the java modules including the modules I created in the mods folder

::java -p mods -d chapter.one
:: the -d describes the chapter.one module

::java --show-module-resolution -p chapter1 -m chapter.one/chapter1.Inschrijving
::a way of debugging modules

::jdeps mods/chapter.one.jar
::shows all the dependencies for chapter.one.jar 
