javac -p mods -d packagemodule/classes packagemodule/mypackage/application/*.java packagemodule/mypackage/utils/*.java packagemodule/mypackage/models/*.java packagemodule/mypackage/exceptions/*.java packagemodule/mypackage/services/*.java packagemodule/mypackage/serviceImpl/*.java packagemodule/mypackage/annotations/*.java packagemodule/mypackage/views/*.java  packagemodule/module-info.java
jar -cf mods/mypackage.jar -C packagemodule/classes .
::java -p mods -m (module name)/(main() location)

