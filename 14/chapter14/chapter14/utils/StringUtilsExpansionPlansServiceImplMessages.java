package chapter14.utils;

public enum StringUtilsExpansionPlansServiceImplMessages {
    NO_EXPANSION_PLANS_MESSAGE("There are no more plans available"),
    PATTERN("yyyy-MM-dd"),
    INSERT_PLAN_MESSAGE("Type your plan"),
    SOMETHING_WENT_WRONG_DURING_PLAN_INSERT_MESSAGE("Something went wrong, please try again later");


    private String message;
    private StringUtilsExpansionPlansServiceImplMessages(String message){
    this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
