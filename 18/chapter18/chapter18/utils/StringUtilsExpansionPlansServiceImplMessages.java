package chapter18.utils;


public enum StringUtilsExpansionPlansServiceImplMessages {
    NO_EXPANSION_PLANS_MESSAGE(" There are no plans available"),
    PATTERN("MMMM dd, yyyy 'at' hh:mm a 'zone' z"),
    INSERT_PLAN_MESSAGE("Type your plan"),
    SOMETHING_WENT_WRONG_DURING_PLAN_INSERT_MESSAGE("Something went wrong, please try again later"),
    EXPANSION_PLAN_MESSAGE(" Expansion plan"),
    START_DATE_MESSAGE("start date"),
    PLAN_MESSAGE("to add a plan"),
    ARE_YOU_SURE_MESSAGE("Are you sure that you'd like to mark"),
    TO_DONE_MESSAGE("to done"),
    TYPE_YES_NO_MESSAGE(" Type y for yes or n for no"),
    ENTER_AN_EXPECTED_VALUE_MESSAGE("Please enter an expected value"),
    ALL_EXECUTED_PLANS_MESSAGE("All the executed plans"),
    END_DATE_MESSAGE("end date");


    private String message;
    private StringUtilsExpansionPlansServiceImplMessages(String message){
    this.message = message;
    }

    public String getMessage(){
        return message;
    }


}
