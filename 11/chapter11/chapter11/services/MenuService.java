package chapter11.services;

import java.util.*;
import static chapter10.constants.Constants.*;
import static chapter10.services.TriesValidationService.*;
import chapter10.*;
import chapter10.interfaces.*;

public class MenuService implements MenuInterface {
    private List<String> menuOptionsList;
    private int exitCode = 8;
    private Scanner scanChoice;
	private static final String WELCOME_MESSAGE = "*\t Welcome to Kishan's progression viewer\t*";

    public MenuService() {
        super();
        init();
    }

    public void menu() {

    }

    @Override
    public void menuOptions() {
        menuOptionsList = new ArrayList<>();
        menuOptionsList.add("What would you like to do ?");
        menuOptionsList.add("to navigate to chapter1");
        menuOptionsList.add("to navigate to chapter2");
        menuOptionsList.add("to navigate to chapter3");
        menuOptionsList.add("to navigate to chapter4");
        menuOptionsList.add("to navigate to chapter5");
        menuOptionsList.add("to navigate to chapter6_7_8_9");
        menuOptionsList.add("to navigate to chapter10");
        menuOptionsList.add("to Log out and exit the program");
        printOutMenuOptions();
    }

    private void printOutMenuOptions() {
        for (int i = 0; i < menuOptionsList.size(); i++) {
            if (i == QUESTION_MESSAGE_INDEX) {
                System.out.println(menuOptionsList.get(i));
                continue;
            } else {
                System.out.println("Type " + i + " " + menuOptionsList.get(i));
            }
        }
    }

    @Override
    public void resetAllValidationServices() {
        resetTriesService();
    }

    @Override
    public void displayMenu() {
		System.out.println(WELCOME_MESSAGE);
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
                    chapter1.Inschrijving.main(null);
                    break;
                case 2:
                    resetAllValidationServices();
                    chapter2.Aquaria.main(null);
                    break;
                case 3:
                    resetAllValidationServices();
                    chapter3.App.main(null);
                    break;
                case 4:
                    resetAllValidationServices();
                    chapter4.App.main("");
                    break;
                case 5:
                    resetAllValidationServices();
                    chapter5.App.main("");
                    break;
                case 6:
                    resetAllValidationServices();
                    chapter6_7.App.main("");
                    break;
                case 7:
                    resetAllValidationServices();
                    App.main("");
                    break;
                case 8:
                    resetAllValidationServices();
                    System.out.println(LOGGED_OUT_MESSAGE);
                    System.exit(0);
                    break;
                default:
                    triesValidation();
                    System.out.println(STARTING_MENU_OPTIONS_MESSAGE + exitCode + "\n");
            }
        } while (choiceEntry != exitCode);
    }

    @Override
    public void init() {
        scanChoice = new Scanner(System.in);
		resetAllValidationServices();
    }
}
