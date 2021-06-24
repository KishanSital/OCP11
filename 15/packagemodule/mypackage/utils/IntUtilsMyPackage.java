package mypackage.utils;

public enum IntUtilsMyPackage {
    // enumeration constants are implicit public static final
    RETRY_LIMIT(Constants.RETRY_LIMIT_VALUE),
    NO_TRIES_LEFT(Constants.NO_TRIES_LEFT_VALUE),
    QUESTION_MESSAGE_INDEX(Constants.QUESTION_MESSAGE_INDEX_VALUE);


    private int intValue;

    private IntUtilsMyPackage(int intValue) {
        this.intValue = intValue;
    }

    public int getIntValue() {
        return intValue;
    }

    private static class Constants {
        static int RETRY_LIMIT_VALUE = 5;
        static int NO_TRIES_LEFT_VALUE = 0;
        static int QUESTION_MESSAGE_INDEX_VALUE = 0;
    }

}


