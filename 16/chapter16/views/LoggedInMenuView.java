package chapter16.views;

import chapter16.utils.MyResourceBundle;
import chapter16.utils.StringUtilsLoggedInMenuMessages;
import chapter16.services.*;
import chapter16.utils.*;
import java.util.*;

import static chapter16.serviceImpl.TriesValidationServiceImpl.triesValidation;

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

    public static void chooseLanguage(){
        Scanner languageScanner = new Scanner(System.in);
        System.out.println("Choose your country/kies je land:");
        System.out.println("Type n voor/for Nederland/nederlands\nType e voor/for USA/english");
        String enteredChoice = languageScanner.next();
        if(enteredChoice.equals("n")){
            Locale.setDefault(new Locale.Builder().setLanguage("nl").setRegion("DE").build());
            MyResourceBundle.localChanger(new Locale("nl","DE"));
        } else if (enteredChoice.equals("e")) {
            Locale.setDefault(new Locale.Builder().setLanguage("en").setRegion("US").build());
            MyResourceBundle.localChanger(new Locale("en","US"));
        }
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
