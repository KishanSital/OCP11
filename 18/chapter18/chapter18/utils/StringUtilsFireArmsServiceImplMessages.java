package chapter18.utils;


public enum StringUtilsFireArmsServiceImplMessages {
    INSERT_NAME_MESSAGE("Enter the name of the firearm you'd like to sell"),
    FIREARM_NOT_EXISTENT_MESSAGE("This firearm may be out of stock or it does not exist, please type another name from the list"),
    INSERT_AMOUNT_MESSAGE("Enter the amount you'd like to sell"),
    SELECTED_AMOUNT_NOT_AVAILABLE_MESSAGE("The selected amount is not available, please try again"),
    IN_STOCK_MESSAGE("The firearms which are in stock are"),
    OUT_OF_STOCK_MESSAGE("Sorry we're out of stock"),
    SOLD_FIREARM_MESSAGE("The sold firearms are"),
    NOTHING_SOLD_MESSAGE("Nothing has been sold"),
    TRANSACTION_TIME_STAMP("Please note this down, your Transaction ID: "),
    INSERT_TRANSACTION_ID("Please enter an existent Transaction id"),
    TRANSACTION_NOT_EXISTENT_MESSAGE("This transaction does not exist"),
    TOTAL_SOLD_PRICE_MESSAGE("Total sold price");


    private String message;
    private StringUtilsFireArmsServiceImplMessages(String message){
    this.message = message;
    }

    public String getMessage(){
        return message;
    }

}
