package chapter19.utils;


public enum StringUtilsExpansionPlansServiceImplMessages {
    NO_EXPANSION_PLANS_MESSAGE(MyResourceBundle.rb.getString("NO_EXPANSION_PLANS_MESSAGE")),
    PATTERN(MyResourceBundle.rb.getString("PATTERN")),
    INSERT_PLAN_MESSAGE(MyResourceBundle.rb.getString("INSERT_PLAN_MESSAGE")),
    SOMETHING_WENT_WRONG_DURING_PLAN_INSERT_MESSAGE(MyResourceBundle.rb.getString("SOMETHING_WENT_WRONG_DURING_PLAN_INSERT_MESSAGE")),
    EXPANSION_PLAN_MESSAGE(MyResourceBundle.rb.getString("EXPANSION_PLAN_MESSAGE")),
    START_DATE_MESSAGE(MyResourceBundle.rb.getString("START_DATE_MESSAGE")),
    PLAN_MESSAGE(MyResourceBundle.rb.getString("PLAN_MESSAGE")),
    ARE_YOU_SURE_MESSAGE(MyResourceBundle.rb.getString("ARE_YOU_SURE_MESSAGE")),
    TO_DONE_MESSAGE(MyResourceBundle.rb.getString("TO_DONE_MESSAGE")),
    TYPE_YES_NO_MESSAGE(MyResourceBundle.rb.getString("TYPE_YES_NO_MESSAGE")),
    ENTER_AN_EXPECTED_VALUE_MESSAGE(MyResourceBundle.rb.getString("ENTER_AN_EXPECTED_VALUE_MESSAGE")),
    ALL_EXECUTED_PLANS_MESSAGE(MyResourceBundle.rb.getString("ALL_EXECUTED_PLANS_MESSAGE")),
    END_DATE_MESSAGE(MyResourceBundle.rb.getString("END_DATE_MESSAGE"));


    private String message;
    private StringUtilsExpansionPlansServiceImplMessages(String message){
    this.message = message;
    }

    public String getMessage(){
        return message;
    }


}
