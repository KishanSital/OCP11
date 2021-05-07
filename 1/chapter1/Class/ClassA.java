//chapter 1 demo
package chapter1.Class;
//^ this is the package

import chapter1.Student.Student;

import java.time.LocalDate;
import java.util.Random;
//^ the imports for Student.java

public class ClassA {
    public void student1(String studentNaam, String geboorteDatum) {
        Student student;  //instance of Student
        student = new Student(); // declararation of Student
        Random studentNum = new Random(); //declaration of a new Random object
        //genereer random getallen van 0-99999
        int limitRandNum = 99999; // delcaration and initialization on one line

        java.time.LocalDate inschrijf_datum = LocalDate.now(); // delcaration and initialization on one line

//( reference student fields to newly created student object )
        student.voornaam = studentNaam;
        student.studentNummer = studentNum.nextInt(limitRandNum);
        student.inschrijfDatum = inschrijf_datum;
        student.geboorteDatum = geboorteDatum;

        student.printNewlyCreatedStudent();


    }

}
