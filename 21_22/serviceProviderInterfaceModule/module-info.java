// bevat alle nodige packages voor de service provider interfaces 
// en maakt het ook available de module die ze nodig heeft
module chapter.twentyone.serviceproviderinterface{
	requires java.sql;
	requires mypackage;
	exports chapter21.models;
	exports chapter21.services;
	exports chapter21.utils;
	exports chapter21.repositories;
}