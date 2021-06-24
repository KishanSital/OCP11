package chapter17.views;

import chapter17.models.ExpansionModel;
import chapter17.services.ExpansionService;
import mypackage.utils.StringUtilsMyPackage;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;
import java.util.function.Function;

import static chapter17.utils.StringUtilsExpansionPlansServiceImplMessages.*;
import static mypackage.serviceImpl.TriesValidationServiceImpl.*;


public class ExpansionView {

    //DateTimeFormatter
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(PATTERN.getMessage());
    private final Scanner scanner = new Scanner(System.in);
    private ExpansionService expansionService;
    private Function<ZonedDateTime, String> formatter = x -> dateTimeFormatter.withLocale(Locale.getDefault()).format(x);

    public ExpansionView(ExpansionService expansionService) {
        this.expansionService = expansionService;
    }

    public void displayFullExpansionPlan() {
        if (expansionService.getTodoExpansionList().isEmpty()) {
            System.out.println(NO_EXPANSION_PLANS_MESSAGE.getMessage());
        } else {
            expansionService.getTodoExpansionList().forEach(e -> System.out.println("\n" + EXPANSION_PLAN_MESSAGE.getMessage() + ":\n" + START_DATE_MESSAGE.getMessage() + ": "
                    + formatter.apply((ZonedDateTime) e.getStartDate())
                    + "\n" + PLAN_MESSAGE.getMessage() + ": "
                    + e.getPlan()
                    + "\n"));
        }
    }

    public void displayUpcomingPlan() {
        if (expansionService.displayUpcomingPlanProcess()) {
            System.out.println("\n" + EXPANSION_PLAN_MESSAGE.getMessage() + ":\n" + START_DATE_MESSAGE.getMessage() + ": "
                    + formatter.apply((ZonedDateTime) expansionService.getTodoExpansionList().peek().getStartDate())
                    + "\n" + PLAN_MESSAGE.getMessage() + ": "
                    + expansionService.getTodoExpansionList().peek().getPlan()
                    + "\n");
        } else {
            System.out.println(NO_EXPANSION_PLANS_MESSAGE.getMessage());
        }
    }

    public void addPlan() {
        ExpansionModel expansionModel = new ExpansionModel();
        System.out.println(INSERT_PLAN_MESSAGE.getMessage());
        expansionModel.setPlan(scanner.next());
        if (!expansionService.addPlan(expansionModel)) {
            System.out.println(SOMETHING_WENT_WRONG_DURING_PLAN_INSERT_MESSAGE.getMessage());
        } else {
            System.out.println(StringUtilsMyPackage.OPERATION_SUCCESSFUL_MESSAGE.getStringValue());
        }
    }

    public void markDone() {
        if (expansionService.displayUpcomingPlanProcess()) {
            System.out.println(ARE_YOU_SURE_MESSAGE.getMessage() + " " +
                    expansionService.getTodoExpansionList().peek().getPlan() + " " + TO_DONE_MESSAGE.getMessage() + " ?");
            updatePlansLists();
        }
    }

    private void updatePlansLists() {
        boolean isInputValid;
        resetTriesService();
        do {
            System.out.println(TYPE_YES_NO_MESSAGE.getMessage());
            if (scanner.next().equalsIgnoreCase("y")) {
                isInputValid = true;
                expansionService.markPlanToDone();
                System.out.println(StringUtilsMyPackage.OPERATION_SUCCESSFUL_MESSAGE.getStringValue());
            } else if (scanner.next().equalsIgnoreCase("n")) {
                break;
            } else {
                triesValidation();
                System.out.println(ENTER_AN_EXPECTED_VALUE_MESSAGE.getMessage());
                isInputValid = false;
            }
        } while (!isInputValid);
    }

    public void viewExecutedPlans() {

        if (expansionService.getDoneExpansionList().isEmpty()) {
            System.out.println(NO_EXPANSION_PLANS_MESSAGE.getMessage());
        } else {
            System.out.println(ALL_EXECUTED_PLANS_MESSAGE.getMessage());
            expansionService.getDoneExpansionList().forEach(e -> System.out.println("\n" + EXPANSION_PLAN_MESSAGE.getMessage() + ":\n" + START_DATE_MESSAGE.getMessage() + ": "
                    + formatter.apply((ZonedDateTime) e.getStartDate())
                    + "\n" + PLAN_MESSAGE.getMessage() + ": "
                    + e.getPlan()
                    + "\n" + END_DATE_MESSAGE.getMessage() + ": "
                    + formatter.apply((ZonedDateTime) e.getEndDate())
                    + "\n"));
        }
    }
}
