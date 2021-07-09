package chapter21.utils;


public enum StringUtilsLoggedInMenuMessages {
    WELCOME_MENU_MESSAGE("Welcome to your firearms seller application"),
    SELL_FIREARM_MESSAGE("to sell a firearm"),
    DISPLAY_AVAILABLE_FIREARM_MESSAGE("to display the available firearms"),
    DISPLAY_SOLD_FIREARM_MESSAGE("to display the sold firearms"),
    REVIEW_DETAILS_TRANSACTION_MESSAGE("to review details of a transaction"),
    DISPLAY_FULL_EXPANSION_PLAN("to display full expansion plan"),
    DISPLAY_UPCOMING_PLANS("to display the upcoming plan"),
    ADD_A_PLAN_MESSAGE("to add a plan"),
    MARK_UPCOMING_PLAN_TO_DONE_MESSAGE("to mark upcoming plan to done"),
    VIEW_ALL_EXECUTED_PLANS_MESSAGE("to view all executed plans"),
    LOG_OUT_AND_EXIT_MESSAGE("to Log out and exit the program"),
    TYPE("Type");
    private String message;
    private StringUtilsLoggedInMenuMessages(String message){
        this.message = message;
    }
    public String getMessage(){
        return message;
    }
    
}
