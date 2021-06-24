package chapter15.views;

import chapter15.models.*;
import chapter15.services.*;
import mypackage.utils.*;

import java.util.*;
import java.util.function.*;

public class StudentView{
    private static final Consumer<String> println = System.out::println;
    private static final Consumer<StudentModel> printStudent = System.out::println;
    private final int exitCode = 2;
    private Scanner scanner;
    private Supplier<List<Double>> studentScoreListSupplier = ArrayList::new;
    private List<Double> studentScoreList;
    private StudentService studentService;

    public StudentView(StudentService studentService) {
        this.studentService = studentService;
        init();
    }

    public void init() {
        scanner = new Scanner(System.in);

    }

    /**
     * use calculateStatisticsOfScores instead
     */
    @Deprecated
    public void calculateAverageScore() {
        Double scannerValue;
        studentScoreList = studentScoreListSupplier.get();
        println.accept("Calculate the average of the student score\n");
        boolean isEnough = false;
        do {
            println.accept("Enter the student score\nType any letter when you're done");
            try {
                scannerValue = scanner.nextDouble();
                studentScoreList.add(scannerValue);
                println.accept("Score added successfully");
            } catch (Exception e) {
                scanner.next();
                isEnough = true;

                studentService.average(studentScoreList);
            }
        } while (!isEnough);

    }

    //usage of the getMax, getMin, getAverage, getCount on a DoubleSummaryStatistics object
    public void calculateStatisticsOfScores() {
        Double scannerValue;
        studentScoreList = studentScoreListSupplier.get();
        println.accept("Display the statistics of all the scores for this student\n");
        boolean isEnough = false;
        do {
            println.accept("Enter the student score\nType any letter when you're done");
            try {
                scannerValue = scanner.nextDouble();
                studentScoreList.add(scannerValue);
                println.accept("Score added successfully");
            } catch (Exception e) {
                scanner.next();
                isEnough = true;

               println.accept("Student's highest score: "+studentService.calculateSummaryStatistics(studentScoreList).getMax());
               println.accept("Student's lowest score: "+studentService.calculateSummaryStatistics(studentScoreList).getMin());
               println.accept("Student's average score: "+studentService.calculateSummaryStatistics(studentScoreList).getAverage());
               println.accept("Student's tests taken count: "+studentService.calculateSummaryStatistics(studentScoreList).getCount()+"\n");
            }
        } while (!isEnough);

    }

    public void addStudent() {
        println.accept("Type a student name");
        if (studentService.getStudentList().add(new StudentModel(scanner.next()))) {
            println.accept(StringUtilsMyPackage.OPERATION_SUCCESSFUL_MESSAGE.getStringValue());
            println.accept("The list contains the following students:");
            studentService.getStudentList().forEach(printStudent::accept);
        } else {
            println.accept(StringUtilsMyPackage.OPERATION_FAILED_MESSAGE.getStringValue());
        }
    }

    public void count() {
        println.accept("The total students in your class count: " + studentService.toStream(studentService.getStudentList()).count() + "\n");
    }

    public void minMax() {
        String[] entryOptions = {"min", "max", "x"};
        boolean isChoiceCorrect = false;
        String scannerEntry;
        do {
            println.accept("Type min to retrieve student with the shortest name\nType max to retrieve student with the longest name\nType x to exit");
            scannerEntry = scanner.next();
            for (int i = 0; i < entryOptions.length; i++) {
                if (scannerEntry.equals(entryOptions[i])) isChoiceCorrect = true;
            }
            if (scannerEntry.equals(entryOptions[exitCode])) break;
            studentService.findMinMax(entryOptions, isChoiceCorrect, scannerEntry);
        } while (!isChoiceCorrect);
    }

    public void studentsWithMatchingLetter() {
        println.accept("Enter the starting letter or word");
        String namesThatStartWith= scanner.next();
        List<String> studNamesList = new ArrayList<>();
        Predicate<String> studentPredicate = s -> s.startsWith(namesThatStartWith);
        for (StudentModel studentModel : studentService.getStudentList()) {
            studNamesList.add(studentModel.getStudentName().toString().trim());
        }

        println.accept("List of the students:");
        studentService.toStream(studentService.getStudentList()).forEach(System.out::println);
        println.accept("");

        studentService.studentsWithMatchingLetter(studNamesList, studentPredicate);
    }


    public void findAnyStudent() {
        studentService.findAnyStudent();
    }

    public void displayStudentNameWithLength() {
        studentService.displayStudentNamesWithLength();
    }

    public void filteringStudents() {
        String studentName;
        long skipStudents;
        long studentAmount;
        studentService.getStudentList().forEach(printStudent::accept);
        println.accept("Type the name of the student you'd like to remove from this report");
        studentName = scanner.next();
        println.accept("Type the amount of students you'd like to skip");
        skipStudents = scanner.nextLong();
        println.accept("Type the amount of students you'd like to see");
        studentAmount = scanner.nextLong();
        studentService.filteringStudents(studentName,skipStudents,studentAmount);
    }
}
