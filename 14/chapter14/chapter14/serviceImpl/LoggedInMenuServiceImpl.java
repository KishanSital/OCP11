package chapter14.serviceImpl;

import chapter14.views.*;
import mypackage.services.*;
import mypackage.utils.*;
import java.util.*;

import static mypackage.serviceImpl.TriesValidationServiceImpl.triesValidation;

public class LoggedInMenuServiceImpl implements MenuService{

    private Scanner scanner;
    private FireArmsView fireArmsView;
    private ExpansionView expansionView;
    private static List<String> menuOptionsList;
    private final int exitCode = 10;

    public LoggedInMenuServiceImpl(FireArmsView fireArmsView,
                                   ExpansionView expansionView){
        super();
        this.fireArmsView = fireArmsView;
        this.expansionView = expansionView;
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
                    fireArmsView.displayTransactionDetails();
                    break;
                case 5:
                    resetAllValidationServices();
                    expansionView.displayFullExpansionPlan();
                    break;
                case 6:
                    resetAllValidationServices();
                    expansionView.displayUpcomingPlan();
                    break;
                case 7:
                    resetAllValidationServices();
                    expansionView.addPlan();
                    break;
                case 8:
                    resetAllValidationServices();
                    expansionView.markDone();
                    break;
                case 9:
                    resetAllValidationServices();
                    expansionView.viewExecutedPlans();
                    break;
                case 10:
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
            menuOptionsList.add("to review details of a transaction");
            menuOptionsList.add("to display full expansion plan");
            menuOptionsList.add("to display the upcoming plan");
            menuOptionsList.add("to add a plan");
            menuOptionsList.add("to mark upcoming plan to done");
            menuOptionsList.add("to view all executed plans");
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
