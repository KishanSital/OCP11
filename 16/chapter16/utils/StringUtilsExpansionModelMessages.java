package chapter16.utils;

public enum StringUtilsExpansionModelMessages {
    EXPANSION_PLAN_MESSAGE(MyResourceBundle.rb.getString("EXPANSION_PLAN_MESSAGE")),
    PLAN_MESSAGE(MyResourceBundle.rb.getString("PLAN_MESSAGE")),
    START_DATE_MESSAGE(MyResourceBundle.rb.getString("START_DATE_MESSAGE")),
    END_DATE_MESSAGE(MyResourceBundle.rb.getString("END_DATE_MESSAGE"));

    private String message;

    private StringUtilsExpansionModelMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
