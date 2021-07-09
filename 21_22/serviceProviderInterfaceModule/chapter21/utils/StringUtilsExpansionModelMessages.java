package chapter21.utils;

public enum StringUtilsExpansionModelMessages {
    EXPANSION_PLAN_MESSAGE("Expansion plan"),
    PLAN_MESSAGE("plan"),
    START_DATE_MESSAGE("start date"),
    END_DATE_MESSAGE("end date");

    private String message;

    private StringUtilsExpansionModelMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
