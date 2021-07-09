package chapter18.views;

import chapter18.utils.*;
import mypackage.services.*;
import mypackage.utils.*;

import java.time.*;

import java.time.format.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;

import static chapter18.utils.StringUtilsExpansionPlansServiceImplMessages.*;
import static mypackage.serviceImpl.TriesValidationServiceImpl.*;

public class LoggedInMenuView implements MenuService {

    private Scanner scanner;
    private FireArmsView fireArmsView;
    private ExpansionView expansionView;
    private static List<String> menuOptionsList;
    private final int exitCode = 13;
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(PATTERN.getMessage());
    private Function<ZonedDateTime, String> formatter = x -> dateTimeFormatter.withLocale(Locale.getDefault()).format(x);
    private ScheduledExecutorService scheduledExecutorService;
    //Runnable task to print out ZonedDateTime
    private Runnable displayZonedDateTimeTask = () -> System.out.println(formatter.apply(ZonedDateTime.of(LocalDateTime.of(LocalDate.now(),
            LocalTime.now()),
            TimeZone.getDefault().toZoneId())));
    private ScheduledFuture<?> displayZonedDateTimeFuture;


    public LoggedInMenuView(FireArmsView fireArmsView,
                            ExpansionView expansionView) {
        super();
        this.fireArmsView = fireArmsView;
        this.expansionView = expansionView;
        init();
    }

    @Override
    public void init() {
        scanner = new Scanner(System.in);
        resetAllValidationServices();
    }

    @Override
    public void displayMenu() {
        schedulerThread();
        expansionView.automaticShootingPlanner();
        int choiceEntry = 0;
        do {
            NestedStaticMenuOptions.menuOptions();
            System.out.println("---------------------------------------------------");
            try {
                choiceEntry = scanner.nextInt();
            } catch (InputMismatchException e) {
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
                    fireArmsView.testGuns();
                    break;
                case 11:
                    resetAllValidationServices();
                    fireArmsView.cleanTheGuns();
                    break;
                case 12:
                    resetAllValidationServices();
                    expansionView.viewPlansUsingParallelStream();
                    break;
                case 13:
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

    private void schedulerThread() {
        // creating a new scheduled thread pool with 10 threads
        scheduledExecutorService = Executors.newScheduledThreadPool(10);
        //scheduling a task to occur every minute after the previous fixed scheduled task has completed
        displayZonedDateTimeFuture = scheduledExecutorService.scheduleWithFixedDelay(displayZonedDateTimeTask, 0, 1, TimeUnit.MINUTES);
    }

    private static class NestedStaticMenuOptions {
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
            menuOptionsList.add(StringUtilsLoggedInMenuMessages.FIRE_THOSE_GUNS_MESSAGE.getMessage());
            menuOptionsList.add(StringUtilsLoggedInMenuMessages.CLEAN_THOSE_GUNS_MESSAGE.getMessage());
            menuOptionsList.add(StringUtilsLoggedInMenuMessages.VIEW_PLANS_IN_PARALLEL_ORDER.getMessage());
            menuOptionsList.add(StringUtilsLoggedInMenuMessages.LOG_OUT_AND_EXIT_MESSAGE.getMessage());
            printOutMenuOptions();
        }

        private static void printOutMenuOptions() {
            for (int i = 0; i < menuOptionsList.size(); i++) {
                if (i == IntUtilsMyPackage.QUESTION_MESSAGE_INDEX.getIntValue()) {
                    System.out.println(menuOptionsList.get(i));
                    continue;
                } else {
                    System.out.println(StringUtilsLoggedInMenuMessages.TYPE.getMessage() + " " + i + " " + menuOptionsList.get(i));
                }
            }
        }
    }
}
