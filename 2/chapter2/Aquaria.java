package chapter2;
import java.util.Scanner;
import chapter2.Fish.FishA;
import chapter2.Fish.FishB;
public class Aquaria {


    public void menu() {
        System.out.println("*        Welkom te Aquaria          *");
        System.out.println("1)Show fish in aquarium\n2)Show primitive types\n3)Show example of working with var (inference type)\n4)Show an example of garbage collection eligibility\n5) Exit..");
        System.out.println("Maak uw keuze:");
    }

    public void menuInput() {
        Scanner menuOption = new Scanner(System.in);
        var fishB = new FishB();	// declaration and initialization of FishB 
        menu();
        switch (menuOption.nextInt()) {
            case 1:
                System.out.println("Uw selectie was 'Show fish in aquarium'");
				System.out.println("package chapter2.Fish;\n" +
                "public class FishA\n" +
                "{\n" +
                "public FishA(){ // constuctor ( does not consist of a return type and has same name as class)\n" +
                "\t_$visKleur = \"orange\";\n" +
                "{System.out.println(\"Vis kleur: \" + _$visKleur + \" Vis naam: \" + _1visNaam$);} //local block\n" +
                "}\n" +
                "public String _$visKleur = \"blue\"; //instance variable (viskleur) that is being declared + initialized\n" +
                "//this type of identifier naming is allowed\n" +
                "\n" +
                "public String _1visNaam$ = \"Kishan\"; //instance variable (Kishan) that is being declared + initialized\n" +
                "public int leeftijd = 2_________1;\n" +
                "public double lengte = 200.699;\n" +
                "public float breedte = 2.69f;\n" +
                "{System.out.println( \"Vis kleur: \" + _$visKleur + \" , Vis naam: \" + _1visNaam$ + \" , Vis leeftijd: \" + leeftijd + \" , lengte: \"+ lengte + \" breedte: \"+ breedte);}\n" +
                "\n" +
                "}");
				
				  FishA fishA = new FishA(); // declaration and initialization of FishA and calling the constructor 

				
				
                menuInput();
                break;
            case 2:
                System.out.println("\nUw selectie was 'Show primitive types'\n");

                System.out.println("boolean _isTrue_ = false;\n" +
                        "    byte $1byteValue = 0;\n" +
                        "    short $_5shortValue = 21;\n" +
                        "    int intValue = 01234567;\n" +
                        "    long longValue = 1_000_000_000_0L, longValue1 = 10__00_00;\n" +
                        "    float floatValue = 10_190_0.0f;\n" +
                        "    double doubleValue = 10_10__10_10.00, doubleValue1 = 10_10_10_10.00d, doubleValue2 = floatValue;\n" +
                        "    char charValue = 109, charValue1 = 'N';\n" +
                        "    System.out.println(\"isTrue = \"+ _isTrue_ +\" ,byteValue = \"+ $1byteValue +\n" +
                        "            \" ,shortValue = \"+ $_5shortValue +\" ,intValue = \"+ intValue +\" ,longValue = \"+ longValue +\n" +
                        "            \" ,longValue1 = \"+longValue1 +\" ,floatValue = \"+ floatValue+\n" +
                        "            \" ,doubleValue = \"+ doubleValue +\" ,doubleValue1 = \"+ doubleValue1 +\n" +
                        "            \" ,doubleValue2 = \"+ doubleValue2 +\" ,charValue = \"+ charValue +\n" +
                        "            \" ,charValue1 = \" + charValue1);\n");
                fishB.primitives();
                menuInput();
                break;
            case 3:
                System.out.println("Uw selectie was 'Show example of working with var (inference type)'\n");
				System.out.println(" var shark = \"Shark\";\n" +
                        "    var var = \"Dolphin\";\n" +
                        "    var tentacles = 2.0;\n" +
                        "    System.out.println(shark + \" + \" + var +\" \"+ tentacles);\n");
				fishB.workingWithVar();
                menuInput();
                break;
            case 4:
                System.out.println("Uw selectie was 'Show an example of garbage collection eligibility'\n");
				System.out.println (" var string = new String(\"A fish\");\n" +
                        "    string = null;         //  ^ object is eligible for garbage collection\n" +
                        "    System.out.println(\"Stringvalue = \"+ string);\n");
				fishB.garbageCollector();
                menuInput();
                break;
            case 5:
                break;
            default:
                System.err.println("Kies a.u.b een valide menu optie\n");
                menuInput();
                break;
        }
    }


    public static void main(String[] args){
		
		var aqua = new Aquaria();
		aqua.menuInput();

    }
}