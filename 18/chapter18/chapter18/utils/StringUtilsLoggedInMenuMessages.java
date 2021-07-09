package chapter18.utils;

public enum StringUtilsLoggedInMenuMessages {
    WELCOME_MENU_MESSAGE("---------------------------------------------------\nWelcome to your firearms seller application\n---------------------------------------------------"),
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
    TYPE("Type"),
    FIRE_THOSE_GUNS_MESSAGE("to test out those guns"),
    CLEAN_THOSE_GUNS_MESSAGE("to clean the firearms"),VIEW_PLANS_IN_PARALLEL_ORDER("to view all expansion plans in a parallel order");


    private String message;
    private StringUtilsLoggedInMenuMessages(String message){
        this.message = message;
    }
    public String getMessage(){
        return message;
    }
    
}
