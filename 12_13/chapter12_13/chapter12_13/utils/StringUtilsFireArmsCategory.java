package chapter12_13.utils;

public enum StringUtilsFireArmsCategory {
    HAND_GUNS("Handguns"),
    LONG_GUNS("Long guns");
    private String categoryName;
    private StringUtilsFireArmsCategory (String categoryName){
        this.categoryName = categoryName;
    }
    public String getCategoryName(){
        return categoryName;
    }
}
