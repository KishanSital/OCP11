package chapter10.services;

import java.util.*;
import chapter10.interfaces.*;
import static chapter10.constants.Constants.*;
import static chapter10.services.TriesValidationService.*;
import chapter6_7.*;

public class LoggedInMenuService implements MenuInterface {
    private List<String> loggedInMenuOptions;
    private int exitCode = 5;
    private Scanner scanChoice;
    private FileServices fileServices;
    private String directory;

    public LoggedInMenuService(String directory) {
        super();
        this.directory = directory;
        init();
    }

    @Override
    public void init() {
        scanChoice = new Scanner(System.in);
        fileServices = new FileServices(directory);
    }

    @Override
    public void resetAllValidationServices() {
        resetTriesService();
    }

    @Override
    public void displayMenu() {
        int choiceEntry = 0;
        do {
            menuOptions();

            try{
                choiceEntry = scanChoice.nextInt();
            }catch (InputMismatchException e) {
                scanChoice.next();
                triesValidation();
                System.out.println(INVALID_NUMBER_MESSAGE);
                continue;
            }

            switch (choiceEntry) {
                case 1:
                    resetAllValidationServices();
                    fileServices.createAFile();
                    break;
                case 2:
                    resetAllValidationServices();
                    fileServices.writeToFile();
                    break;
                case 3:
                    resetAllValidationServices();
                    fileServices.readFromFile();
                    break;
                case 4:
                    resetAllValidationServices();
                    fileServices.deleteFile();
                    break;
                case 5:
                    resetAllValidationServices();
                    System.out.println(LOGGED_OUT_MESSAGE);
                    break;
				case 6:
                    resetAllValidationServices();
                    System.out.println(LOGGED_OUT_MESSAGE);
					App.main("");
                    break;
                default:
                    triesValidation();
                    System.out.println(STARTING_MENU_OPTIONS_MESSAGE + exitCode + "\n");
            }
        } while (choiceEntry != exitCode);
    }


    @Override
    public void menuOptions() {
        loggedInMenuOptions = new ArrayList<>();
        loggedInMenuOptions.add("What would you like to do ?");
        loggedInMenuOptions.add("to create a file");
        loggedInMenuOptions.add("to write to a file");
        loggedInMenuOptions.add("to read from a file");
        loggedInMenuOptions.add("to delete a file");
        loggedInMenuOptions.add("to Log out and exit the program");
		loggedInMenuOptions.add("to navigate to chapter 6,7,8 and 9");
        printOutMenuOptions();
    }

    private void printOutMenuOptions() {
        for (int i = 0; i < loggedInMenuOptions.size(); i++) {
            if (i == QUESTION_MESSAGE_INDEX) {
                System.out.println(loggedInMenuOptions.get(i));
                continue;
            } else {
                System.out.println("Type " + i + " " + loggedInMenuOptions.get(i));
            }
        }
    }

}
