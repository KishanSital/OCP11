package chapter15.views;

import chapter15.services.StudentService;
import mypackage.services.MenuService;
import mypackage.utils.IntUtilsMyPackage;
import mypackage.utils.StringUtilsMyPackage;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static mypackage.serviceImpl.TriesValidationServiceImpl.triesValidation;

public class MenuView implements MenuService {

    private static final int exitCode = 9;
    private static List<String> menuOptionsList;
    private Scanner scanner;
    private StudentService studentService;
    private StudentView studentView;


    public MenuView(StudentService studentService){
        this();
        this.studentService = studentService;
        this.studentView = new StudentView(studentService);
    }

    public MenuView() {
        super();
        init();
    }

    @Override
    public void init() {
        scanner = new Scanner(System.in);
        resetAllValidationServices();
    }

    @Override
    public void displayMenu() {
        int choiceEntry = 0;
        do {
            menuOptions();
            try {
                choiceEntry = scanner.nextInt();
            } catch (InputMismatchException e) {
                scanner.next();
                resetAllValidationServices();
                System.out.println(StringUtilsMyPackage.INVALID_NUMBER_MESSAGE.getStringValue());
                continue;
            }
            switch (choiceEntry) {
                case 1:
                    resetAllValidationServices();
                    studentView.calculateStatisticsOfScores();
                    break;
                case 2:
                    resetAllValidationServices();
                    studentView.addStudent();
                    break;
                case 3:
                    resetAllValidationServices();
                    studentView.count();
                    break;
                case 4:
                    resetAllValidationServices();
                    studentView.minMax();
                    break;
                case 5:
                    resetAllValidationServices();
                    studentView.studentsWithMatchingLetter();
                    break;
                case 6:
                    resetAllValidationServices();
                    studentView.findAnyStudent();
                    break;
                case 7:
                    resetAllValidationServices();
                    studentView.displayStudentNameWithLength();
                    break;
                case 8:
                    resetAllValidationServices();
                    studentView.filteringStudents();
                    break;
                case 9:
                    resetAllValidationServices();
                    System.out.println(StringUtilsMyPackage.LOGGED_OUT_MESSAGE.getStringValue());
                    System.exit(0);
                    break;
                default:
                    triesValidation();
                    System.out.println(StringUtilsMyPackage.STARTING_MENU_OPTIONS_MESSAGE.getStringValue()
                            + exitCode + "\n");
            }
        } while (choiceEntry != exitCode);
    }

    @Override
    public void menuOptions() {
        menuOptionsList = new ArrayList<>();
        menuOptionsList.add("Welcome to the student data manipulation system");
        menuOptionsList.add("to display the statistics of the scores of a student");
        menuOptionsList.add("to add a student to your class");
        menuOptionsList.add("to count the students in your class");
        menuOptionsList.add("to display student with the smallest or largest name");
        menuOptionsList.add("to check whether there are students whose name start with the same letter");
        menuOptionsList.add("to retrieve possibly the first or a random student");
        menuOptionsList.add("to display all students with their name and length of their names");
        menuOptionsList.add("to keep only one student if duplicates are available, sort all the students by their name,\nfilter out a student, skip a few students and limit the amount of students you'd like to see ");
        menuOptionsList.add("to exit the application ");
        printOutMenuOptions();
    }

    @Override
    public void printOutMenuOptions() {
        for (int i = 0; i < menuOptionsList.size(); i++) {
            if (i == IntUtilsMyPackage.QUESTION_MESSAGE_INDEX.getIntValue()) {
                System.out.println(menuOptionsList.get(i));
                continue;
            } else {
                System.out.println("Type " + i + " " + menuOptionsList.get(i));
            }
        }
    }

}
