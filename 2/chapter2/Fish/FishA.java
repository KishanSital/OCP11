package chapter2.Fish;
public class FishA
{
	public FishA(){ // constuctor ( does not consist of a return type and has same name as class)
		_$visKleur = "orange";
		{System.out.println("Vis kleur: " + _$visKleur + " Vis naam: " + _1visNaam$);} //local block
	}
	public String _$visKleur = "blue"; //instance variable (viskleur) that is being declared + initialized
	//this type of identifier naming is allowed

	public String _1visNaam$ = "Kishan"; //instance variable (Kishan) that is being declared + initialized
	public int leeftijd = 2_________1;
	public double lengte = 200.699;
	public float breedte = 2.69f;
	{System.out.println( "Vis kleur: " + _$visKleur + " , Vis naam: " + _1visNaam$ + " , Vis leeftijd: " + leeftijd + " , lengte: "+ lengte + " breedte: "+ breedte);}

}