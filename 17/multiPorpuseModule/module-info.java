//consumer met service locator en service provider 
// module-info.java
module chapter.seventeen.consumer {
   requires mypackage;
   requires chapter.seventeen.serviceproviderinterface; // bevat de interfaces

   provides chapter17.services.FireArmsService with chapter17.serviceImpl.FireArmsServiceImpl; // service provider met een implementatie
   provides chapter17.services.ExpansionService with chapter17.serviceImpl.ExpansionServiceImpl;


}