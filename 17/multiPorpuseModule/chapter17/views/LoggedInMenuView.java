package chapter17.views;

import chapter17.utils.StringUtilsLoggedInMenuMessages;
import mypackage.services.MenuService;
import mypackage.utils.IntUtilsMyPackage;
import mypackage.utils.StringUtilsMyPackage;

import java.util.*;

import static mypackage.serviceImpl.TriesValidationServiceImpl.triesValidation;

public class LoggedInMenuView implements MenuService{

    private Scanner scanner;
    private FireArmsView fireArmsView;
    private ExpansionView expansionView;
    private static List<String> menuOptionsList;
    private final int exitCode = 10;

    public LoggedInMenuView(FireArmsView fireArmsView,
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
        //chooseLanguage();
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
            menuOptionsList.add(StringUtilsLoggedInMenuMessages.WELCOME_MENU_MESSAGE.getMessage());
            menuOptionsList.add(StringUtilsLoggedInMenuMessages.SELL_FIREARM_MESSAGE.getMessage());
            menuOptionsList.add(StringUtilsLoggedInMenuMessages.DISPLAY_AVAILABLE_FIREARM_MESSAGE.getMessage());
            menuOptionsList.add(StringUtilsLoggedInMenuMessages.DISPLAY_SOLD_FIREARM_MESSAGE.getMessage());
            menuOptionsList.add(StringUtilsLoggedInMenuMessages.REVIEW_DETAILS_TRANSACTION_MESSAGE.getMessage());
            menuOptionsList.add(StringUtilsLoggedInMenuMessages.DISPLAY_FULL_EXPANSION_PLAN.getMessage());
            menuOptionsList.add(StringUtilsLoggedInMenuMessages.DISPLAY_UPCOMING_PLANS.getMessage());
            menuOptionsList.add(StringUtilsLoggedInMenuMessages.ADD_A_PLAN_MESSAGE.getMessage());
            menuOptionsList.add(StringUtilsLoggedInMenuMessages.MARK_UPCOMING_PLAN_TO_DONE_MESSAGE.getMessage());
            menuOptionsList.add(StringUtilsLoggedInMenuMessages.VIEW_ALL_EXECUTED_PLANS_MESSAGE.getMessage());
            menuOptionsList.add(StringUtilsLoggedInMenuMessages.LOG_OUT_AND_EXIT_MESSAGE.getMessage());
            printOutMenuOptions();
        }

        private static void printOutMenuOptions() {
            for (int i = 0; i < menuOptionsList.size(); i++) {
                if (i == IntUtilsMyPackage.QUESTION_MESSAGE_INDEX.getIntValue()) {
                    System.out.println(menuOptionsList.get(i));
                    continue;
                } else {
                    System.out.println(StringUtilsLoggedInMenuMessages.TYPE.getMessage()+" " + i + " " + menuOptionsList.get(i));
                }
            }
        }
    }
}
