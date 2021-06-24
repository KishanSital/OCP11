package chapter16.utils;


public enum StringUtilsLoggedInMenuMessages {
    WELCOME_MENU_MESSAGE(MyResourceBundle.rb.getString("WELCOME_MENU_MESSAGE")),
    SELL_FIREARM_MESSAGE(MyResourceBundle.rb.getString("SELL_FIREARM_MESSAGE")),
    DISPLAY_AVAILABLE_FIREARM_MESSAGE(MyResourceBundle.rb.getString("DISPLAY_AVAILABLE_FIREARM_MESSAGE")),
    DISPLAY_SOLD_FIREARM_MESSAGE(MyResourceBundle.rb.getString("DISPLAY_SOLD_FIREARM_MESSAGE")),
    REVIEW_DETAILS_TRANSACTION_MESSAGE(MyResourceBundle.rb.getString("REVIEW_DETAILS_TRANSACTION_MESSAGE")),
    DISPLAY_FULL_EXPANSION_PLAN(MyResourceBundle.rb.getString("DISPLAY_FULL_EXPANSION_PLAN")),
    DISPLAY_UPCOMING_PLANS(MyResourceBundle.rb.getString("DISPLAY_UPCOMING_PLANS")),
    ADD_A_PLAN_MESSAGE(MyResourceBundle.rb.getString("ADD_A_PLAN_MESSAGE")),
    MARK_UPCOMING_PLAN_TO_DONE_MESSAGE(MyResourceBundle.rb.getString("MARK_UPCOMING_PLAN_TO_DONE_MESSAGE")),
    VIEW_ALL_EXECUTED_PLANS_MESSAGE(MyResourceBundle.rb.getString("VIEW_ALL_EXECUTED_PLANS_MESSAGE")),
    LOG_OUT_AND_EXIT_MESSAGE(MyResourceBundle.rb.getString("LOG_OUT_AND_EXIT_MESSAGE")),
    TYPE(MyResourceBundle.rb.getString("TYPE"));
    private String message;
    private StringUtilsLoggedInMenuMessages(String message){
        this.message = message;
    }
    public String getMessage(){
        return message;
    }
    
}
