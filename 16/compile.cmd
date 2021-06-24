javac chapter16/*.java chapter16/annotations/*.java chapter16/exceptions/*.java chapter16/models/*.java chapter16/repositories/*.java chapter16/serviceImpl/*.java chapter16/services/*.java chapter16/utils/*.java chapter16/views/*.java
chcp 1252

java -ea chapter16/App 

::enable assertions for all classes except system classes
:: java -enableassertions chapter16/App 

::enable assertions for all classes and subpackages in chapter16 package
:: java -ea :chapter16... chapter16/App

::enable assertions for specific class 
:: java -ea:chapter16.App chapter16/App

::enable assertions for all classes and subpackages in chapter16 package, but disable it for a specific class
:: java -ea:chapter16... -da:chapter16.views.LoggedInMenuView chapter16/App
:: java -enableassertions:chapter16... -disableassertions:chapter16.views.LoggedInMenuView chapter16/App

pause