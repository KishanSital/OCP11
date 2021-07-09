//consumer met service locator en service provider 
// module-info.java
module chapter.twentyone.consumer {
   requires java.sql;
   requires mypackage;
   requires chapter.twentyone.serviceproviderinterface; // bevat de interfaces

   provides chapter21.services.FireArmsService with chapter21.serviceImpl.FireArmsServiceImpl; // service provider met een implementatie
   provides chapter21.services.ExpansionService with chapter21.serviceImpl.ExpansionServiceImpl;


}