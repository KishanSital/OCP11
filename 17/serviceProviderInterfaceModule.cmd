javac -p mods -d serviceProviderInterfaceModule/classes serviceProviderInterfaceModule/chapter17/models/*.java serviceProviderInterfaceModule/chapter17/services/*.java serviceProviderInterfaceModule/chapter17/utils/*.java serviceProviderInterfaceModule/chapter17/repositories/*.java  serviceProviderInterfaceModule/*.java
jar -cf mods/chapter.seventeen.serviceproviderinterface.jar -C serviceProviderInterfaceModule/classes .
