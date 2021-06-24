// bevat alle nodige packages voor de service provider interfaces 
// en maakt het ook available de module die ze nodig heeft
module chapter.seventeen.serviceproviderinterface{
	requires mypackage;
	exports chapter17.models;
	exports chapter17.services;
	exports chapter17.utils;
	exports chapter17.repositories;
}