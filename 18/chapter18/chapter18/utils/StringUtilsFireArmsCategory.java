package chapter18.utils;


public enum StringUtilsFireArmsCategory {
    HAND_GUNS("Hand guns"),
    LONG_GUNS("Long guns");
    private String categoryName;
    private StringUtilsFireArmsCategory (String categoryName){
        this.categoryName = categoryName;
    }
    public String getCategoryName(){
        return categoryName;
    }


}
