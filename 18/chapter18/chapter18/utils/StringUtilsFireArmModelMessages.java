package chapter18.utils;

public enum StringUtilsFireArmModelMessages {
    FIREARM_MESSAGE("Firearm"),
    FIREARM_ID_MESSAGE("firearm id"),
    FIREARM_CATEGORY_MESSAGE("firearm category"),
    FIREARM_NAME_MESSAGE("firearm name"),
    FIREARM_AMOUNT_MESSAGE("amount"),
    PRICE_PER_ITEM_MESSAGE("price per item"),
    FIREARM_SPECIFICATION_MESSAGE("Firearm specification"),
    CALIBER_MESSAGE("caliber"),
    WEIGHT_WITHOUT_MAGAZINE_MESSAGE("weight without magazine"),
    WEIGHT_WITH_EMPTY_MAGAZINE_MESSAGE("weight with empty magazine"),
    WEIGHT_WITH_LOADED_MAGAZINE_MESSAGE("weight with loaded magazine"),
    MAGAZINE_CAPACITY_MESSAGE("magazine capacity"),
    BARREL_LENGTH_MESSAGE("barrel length"),
    TRIGGER_PULL_MESSAGE("trigger pull");
    private String message;
    private StringUtilsFireArmModelMessages(String message){
        this.message = message;
    }
    public String getMessage(){
        return message;
    }


}
