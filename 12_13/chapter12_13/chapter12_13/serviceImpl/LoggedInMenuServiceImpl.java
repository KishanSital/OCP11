package chapter12_13.serviceImpl;

import chapter12_13.views.FireArmsView;
import mypackage.services.*;
import mypackage.utils.*;
import java.util.*;
import static mypackage.serviceImpl.TriesValidationServiceImpl.*;

public class LoggedInMenuServiceImpl implements MenuService{

    private Scanner scanner;
    private FireArmsView fireArmsView;
    private static List<String> menuOptionsList;
    private final int exitCode = 4;

    public LoggedInMenuServiceImpl(FireArmsView fireArmsView){
        super();
        this.fireArmsView = fireArmsView;
        init();
    }

    @Override
    public void init(){
        scanner = new Scanner(System.in);
        resetAllValidationServices();
    }

    @Override
    public void displayMenu() {
        int choiceEntry = 0;
        do {
            NestedStaticMenuOptions.menuOptions();
            try{
                choiceEntry = scanner.nextInt();
            }catch (InputMismatchException e) {
                scanner.next();
                triesValidation();
                System.out.println(StringUtilsMyPackage.INVALID_NUMBER_MESSAGE.getStringValue());
                continue;
            }
            switch (choiceEntry) {
                case 1:
                    resetAllValidationServices();
                    fireArmsView.sellFireArms();
                    break;
                case 2:
                    resetAllValidationServices();
                    fireArmsView.displayAvailableFireArms();
                    break;
                case 3:
                    resetAllValidationServices();
                    fireArmsView.displaySoldFireArms();
                    break;
                case 4:
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

    private static class NestedStaticMenuOptions  {
        public static void menuOptions() {
            menuOptionsList = new ArrayList<>();
            menuOptionsList.add("Welcome to your firearms seller application");
            menuOptionsList.add("to sell a firearm");
            menuOptionsList.add("to display the available firearms");
            menuOptionsList.add("to display the sold firearms");
            menuOptionsList.add("to Log out and exit the program");
            printOutMenuOptions();
        }

        private static void printOutMenuOptions() {
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
}
