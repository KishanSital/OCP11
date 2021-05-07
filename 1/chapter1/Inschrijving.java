package chapter1;
import chapter1.Class.ClassA;
import java.util.Scanner;
public class Inschrijving {
	
	public static void main(String[] args) /*main method*/ {
		Inschrijving newInschrijving = new Inschrijving(); // create new object of Inschrijving.java class with identifier newInschrijving as reference
		newInschrijving.menuInput(); // excecute method created in Inschrijving.java
		
	}
	
	public void menu() {
		System.out.println("*        Welkom te Unasat          *");
		System.out.println("1)Nieuwe inschrijving\n2)Show type of comments\n3)Show main method signature\n4)Show how to compile and run\n5)Show orders of element within a class\n6) Exit..");
		System.out.println("Maak uw keuze:");
	}
	
	public void TypeOfComments() {
		System.out.println("\n//chapter 1 demo   <- Single line comment\n /*\n*\n*Multi line comment\n*/\n/**\n*Javadoc multi-line comment\n*/");
		menuInput();
	}
	
	public void MainMethodSignature() {
		System.out.println("\npublic static void main(String[] args) {\n" +
			"System.out.println(args[0]);\n" +
			"System.out.println(args[1]);\n" +
		"}\n");
		menuInput();
	}
	
	public void CompileAClass() {
		System.out.println("\nSimple compile : javac (packaganame)/(classname).java  (packaganame)/(classname).java\n" +
			"Simple compile : javac  -d  (directoryname) (packaganame)/(classname).java  (packaganame)/(classname).java\n" +
			"Compile for specific class: javac -cp\n" +
			"\t\t\t     javac -classpath\n" +
			"\t\t\t    javac  --class-path \n" +
			"\t\t\t                      (classpath required for compilation) (packagename)/(classname).java\n" +
			"Run class after compilation: java -cp\n" +
			"\t\t\t        java -classpath\n" +
			"\t\t\t        java --class-path\n" +
			"                                                               (Classpath required) (packagename).(classname)\n" +
			"\t\t\tIf in the same directory of .class file, class can be run using (java (classname) (parameters if there are any required))\n" +
			"\n" +
			"Run class without compiling Single file: java  (packagename)/(classname.java)\n" +
			"\n" +
			"Creating a jar file, navigate to your destination folder (folder where the jar should be created) using cmd. jar -cvf (jarname).jar -C (path where compiled package with classes are stored) .\n" +
			"\n" +
		"Running the created jar       java -cp (path of the folder where the classes are compiled)  (jarname).jar (packagename).(classname) \n");
		menuInput();
	}
	
	public void ShowClassOrder(){
		System.out.println("Package structure; //package must be first non-comment\n" +
			"import java.util.;* //import must come after package\n//Specific import of classes will be used prior to a wildcard import when imported classes have the same name \n" +
			"import import java.util.*; \nimport java.sql.Date\n "+
			"public class Meerkat { // then comes the class\n" +
			"double weight; // instance fields and methods can go in either order\n" +
			"public void cat(){}\n" +
			"double height; // another field - they don't need to\n be together" +
		"}\n");
		menuInput();
	}
	
	public void menuInput() {
		Scanner menuOption = new Scanner(System.in);
		menu();
		switch (menuOption.nextInt()) {
			case 1:
			System.out.println("Uw selectie was 'Nieuwe inschrijving'");
			ClassA klas1; //declaration
			klas1 = new ClassA(); // initialization
			menuOption = new Scanner(System.in); // initialization of new Scanner Object
			System.out.println("Type de student naam ");
			String naam = menuOption.next();
			System.out.println("Type de student geboortedatum in ");
			String geboorteDatum = menuOption.next();
			klas1.student1(naam, geboorteDatum);
			menuInput();
			break;
			case 2:
			System.out.println("\nUw selectie was 'Show type of comments '\n");
			TypeOfComments();
			break;
			case 3:
			System.out.println("Uw selectie was 'Show main method signature'\n");
			MainMethodSignature();
			break;
			case 4:
			System.out.println("Uw selectie was 'Show how to compile and run'\n");
			CompileAClass();
			break;
			case 5:
			System.out.println("Uw selectie was 'Show orders of element within a class'\n");
			ShowClassOrder();
			break;
			case 6:
			break;
			default:
			System.err.println("Kies a.u.b een valide menu optie\n");
			menuInput();
			break;
		}
	}
}
