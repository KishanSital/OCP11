package chapter16.utils;


public enum StringUtilsFireArmsCategory {
    HAND_GUNS(MyResourceBundle.rb.getString("HAND_GUNS")),
    LONG_GUNS(MyResourceBundle.rb.getString("LONG_GUNS"));
    private String categoryName;
    private StringUtilsFireArmsCategory (String categoryName){
        this.categoryName = categoryName;
    }
    public String getCategoryName(){
        return categoryName;
    }


}
