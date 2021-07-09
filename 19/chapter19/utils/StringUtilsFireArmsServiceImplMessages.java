package chapter19.utils;


public enum StringUtilsFireArmsServiceImplMessages {
    INSERT_NAME_MESSAGE(MyResourceBundle.rb.getString("INSERT_NAME_MESSAGE")),
    FIREARM_NOT_EXISTENT_MESSAGE(MyResourceBundle.rb.getString("FIREARM_NOT_EXISTENT_MESSAGE")),
    INSERT_AMOUNT_MESSAGE(MyResourceBundle.rb.getString("INSERT_AMOUNT_MESSAGE")),
    SELECTED_AMOUNT_NOT_AVAILABLE_MESSAGE(MyResourceBundle.rb.getString("SELECTED_AMOUNT_NOT_AVAILABLE_MESSAGE")),
    IN_STOCK_MESSAGE(MyResourceBundle.rb.getString("IN_STOCK_MESSAGE")),
    OUT_OF_STOCK_MESSAGE(MyResourceBundle.rb.getString("OUT_OF_STOCK_MESSAGE")),
    SOLD_FIREARM_MESSAGE(MyResourceBundle.rb.getString("SOLD_FIREARM_MESSAGE")),
    NOTHING_SOLD_MESSAGE(MyResourceBundle.rb.getString("NOTHING_SOLD_MESSAGE")),
    TRANSACTION_TIME_STAMP(MyResourceBundle.rb.getString("TRANSACTION_TIME_STAMP")),
    INSERT_TRANSACTION_ID(MyResourceBundle.rb.getString("INSERT_TRANSACTION_ID")),
    TRANSACTION_NOT_EXISTENT_MESSAGE(MyResourceBundle.rb.getString("TRANSACTION_NOT_EXISTENT_MESSAGE")),
    TOTAL_SOLD_PRICE_MESSAGE(MyResourceBundle.rb.getString("TOTAL_SOLD_PRICE_MESSAGE"));


    private String message;
    private StringUtilsFireArmsServiceImplMessages(String message){
    this.message = message;
    }

    public String getMessage(){
        return message;
    }

}
