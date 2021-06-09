package chapter14.views;

import chapter14.models.*;
import chapter14.serviceImpl.*;
import mypackage.utils.*;
import static mypackage.serviceImpl.TriesValidationServiceImpl.*;

import java.util.Scanner;

import static chapter14.utils.StringUtilsExpansionPlansServiceImplMessages.*;
public class ExpansionView extends ExpansionServiceImpl {

    private final Scanner scanner = new Scanner(System.in);

    public void displayFullExpansionPlan() {
        if (getTodoExpansionList().isEmpty()){
            System.out.println(NO_EXPANSION_PLANS_MESSAGE.getMessage());
        } else {
            getTodoExpansionList().forEach(e -> System.out.println( "\nExpansion plan:\nstart date: "
                    + e.getStartDate()
                    + "\nplan: "
                    + e.getPlan()
                    +"\n"));
        }
    }

    @Override
    public void displayUpcomingPlan() {
        if (displayUpcomingPlanProcess()){
            System.out.println("Expansion plan:\nstart date: "
                    + getTodoExpansionList().peek().getStartDate()
                    + "\nplan: "
                    +  getTodoExpansionList().peek().getPlan()
                    + "\n");
        }
    }

private boolean displayUpcomingPlanProcess() {
        if (getTodoExpansionList().peek() == null){
            System.out.println(NO_EXPANSION_PLANS_MESSAGE.getMessage());
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void addPlan() {
        ExpansionModel expansionModel = new ExpansionModel();
        System.out.println(INSERT_PLAN_MESSAGE.getMessage());
        expansionModel.setPlan(scanner.next());
        if(!addPlan(expansionModel)){
            System.out.println(SOMETHING_WENT_WRONG_DURING_PLAN_INSERT_MESSAGE.getMessage());
        } else {
            System.out.println(StringUtilsMyPackage.OPERATION_SUCCESSFUL_MESSAGE.getStringValue());
        }
    }

    @Override
    public void markDone() {
        if (displayUpcomingPlanProcess()) {
            System.out.println("Are you sure that you'd like to mark " +
                               getTodoExpansionList().peek().getPlan() + " to done ?");
            updatePlansLists();
        }
    }

    private void updatePlansLists() {
        boolean isInputValid;
        resetTriesService();
        do {
            System.out.println("Type y for yes or n for no");
            if (scanner.next().equalsIgnoreCase("y")) {
                isInputValid = true;
                markPlanToDone();
                System.out.println(StringUtilsMyPackage.OPERATION_SUCCESSFUL_MESSAGE.getStringValue());
            } else if (scanner.next().equalsIgnoreCase("n")) {
                break;
            } else {
                triesValidation();
                System.out.println("Please enter an expected value");
                isInputValid = false;
            }
        }while (!isInputValid);
    }

    @Override
    public void viewExecutedPlans() {
        System.out.println("All the executed plans");
        getDoneExpansionList().forEach(System.out::println);
    }
}
