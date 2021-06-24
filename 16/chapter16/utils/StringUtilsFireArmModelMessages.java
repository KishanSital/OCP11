package chapter16.utils;

public enum StringUtilsFireArmModelMessages {
    FIREARM_MESSAGE(MyResourceBundle.rb.getString("FIREARM_MESSAGE")),
    FIREARM_ID_MESSAGE(MyResourceBundle.rb.getString("FIREARM_ID_MESSAGE")),
    FIREARM_CATEGORY_MESSAGE(MyResourceBundle.rb.getString("FIREARM_CATEGORY_MESSAGE")),
    FIREARM_NAME_MESSAGE(MyResourceBundle.rb.getString("FIREARM_NAME_MESSAGE")),
    FIREARM_AMOUNT_MESSAGE(MyResourceBundle.rb.getString("FIREARM_AMOUNT_MESSAGE")),
    PRICE_PER_ITEM_MESSAGE(MyResourceBundle.rb.getString("PRICE_PER_ITEM_MESSAGE")),
    FIREARM_SPECIFICATION_MESSAGE(MyResourceBundle.rb.getString("FIREARM_SPECIFICATION_MESSAGE")),
    CALIBER_MESSAGE(MyResourceBundle.rb.getString("CALIBER_MESSAGE")),
    WEIGHT_WITHOUT_MAGAZINE_MESSAGE(MyResourceBundle.rb.getString("WEIGHT_WITHOUT_MAGAZINE_MESSAGE")),
    WEIGHT_WITH_EMPTY_MAGAZINE_MESSAGE(MyResourceBundle.rb.getString("WEIGHT_WITH_EMPTY_MAGAZINE_MESSAGE")),
    WEIGHT_WITH_LOADED_MAGAZINE_MESSAGE(MyResourceBundle.rb.getString("WEIGHT_WITH_LOADED_MAGAZINE_MESSAGE")),
    MAGAZINE_CAPACITY_MESSAGE(MyResourceBundle.rb.getString("MAGAZINE_CAPACITY_MESSAGE")),
    BARREL_LENGTH_MESSAGE(MyResourceBundle.rb.getString("BARREL_LENGTH_MESSAGE")),
    TRIGGER_PULL_MESSAGE(MyResourceBundle.rb.getString("TRIGGER_PULL_MESSAGE"));
    private String message;
    private StringUtilsFireArmModelMessages(String message){
        this.message = message;
    }
    public String getMessage(){
        return message;
    }


}
