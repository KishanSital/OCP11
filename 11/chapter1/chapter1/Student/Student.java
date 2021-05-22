//chapter 1 demo
package chapter1.Student;
// ^ this is the package name

import java.lang.*;
import java.time.LocalDate;
/* ^
	this
	import
	is
	redundant
*/

/**
	* this is a Javadoc multiple-line comment
	*
	* @author Kishan Sital
*/
public class Student /*classname*/ {
	// declaration of instance variables
	public int studentNummer;
	public String voornaam;
	public LocalDate inschrijfDatum;
	public String geboorteDatum;
	
	public void printNewlyCreatedStudent() {
		System.out.println("\n*            Student succesvol ingeschreven             *");
		System.out.println("\nStudentNummer: " + studentNummer + "\nNaam: " + voornaam + "\nInschrijfdatum: " + inschrijfDatum + "\nGeboortedatum:" + geboorteDatum +"\n");
	}
}
