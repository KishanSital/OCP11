package chapter10_11.constants;


public class Constants {

    public final static int RETRY_LIMIT;
    public final static int NO_TRIES_LEFT;
    public final static int QUESTION_MESSAGE_INDEX;
    public final static String WELCOME_MESSAGE;
    public final static String MAXIMUM_TRIED_AMOUNT_MESSAGE;
    public final static String INVALID_CREDENTIALS_MESSAGE;
    public final static String PROVIDE_USERNAME_MESSAGE;
    public final static String PROVIDE_PASSWORD_MESSAGE;
    public final static String LOGIN_SUCCESS_MESSAGE;
    public final static String INVALID_NUMBER_MESSAGE;
    public final static String CLOSING_LOGIN_EXCEPTION;
    public final static String CLOSING_TRIES_EXCEPTION;
    public final static String LOGGED_OUT_MESSAGE;
    public final static String STARTING_MENU_OPTIONS_MESSAGE;
    public final static String ENTER_THE_FILE_NAME_FOR_WRITING_PURPOSES_MESSAGE;
    public final static String TYPE_SOMETHING_TO_WRITE_TO_FILE_MESSAGE;
    public final static String WRITING_WAS_SUCCESSFUL_MESSAGE;
    public final static String SOMETHING_WENT_WRONG_WHILE_TRYING_TO_WRITE_TO_FILE_MESSAGE;
    public final static String DISPLAYING_FILES_IN_CURRENT_DIRECTORY_MESSAGE;
    public final static String ENTER_THE_FILE_NAME_FOR_READING_PURPOSES_MESSAGE;
    public final static String SOMETHING_WENT_WRONG_WHILE_TRYING_TO_READ_FROM_FILE_MESSAGE;
    public final static String ENTER_THE_FILE_NAME_FOR_DELETING_PURPOSES_MESSAGE;
    public final static String FILE_DELETED_SUCCESSFULLY_MESSAGE;
    public final static String FAILED_TO_DELETE_FILE_MESSAGE;
    public final static String SOMETHING_WENT_WRONG_WHILE_DELETING_FILE_MESSAGE;
    public final static String FILE_CREATED_SUCCESSFULLY_MESSAGE;
    public final static String FILE_ALREADY_EXISTS_MESSAGE;
    public final static String FILE_CREATION_ERROR_OCCURRED_MESSAGE;
    public final static String TYPE_THE_NAME_OF_THE_NEW_FILE_MESSAGE;
    public final static String ENTER_THE_PATH_MESSAGE;
    public final static String INVALID_DIRECTORY_SELECTION;


    static {
        MAXIMUM_TRIED_AMOUNT_MESSAGE = "Maximum amount of permitted tries have been reached, please restart the application\n";
        RETRY_LIMIT = 5;
        NO_TRIES_LEFT = 0;
        QUESTION_MESSAGE_INDEX = 0;
        INVALID_CREDENTIALS_MESSAGE = "Credentials were invalid, please try again\n";
        PROVIDE_USERNAME_MESSAGE = "Please enter your Username";
        PROVIDE_PASSWORD_MESSAGE = "Please enter your Password";
        LOGIN_SUCCESS_MESSAGE = "Login was successful\n";
        INVALID_NUMBER_MESSAGE = "Please type a valid number\n";
        CLOSING_LOGIN_EXCEPTION = "Try block with resource management is closing loginException";
        CLOSING_TRIES_EXCEPTION = "Try block with resource management is closing TriesException";
        LOGGED_OUT_MESSAGE = "You've been logged out successfully\n";
        STARTING_MENU_OPTIONS_MESSAGE = "Choice must be between 1 and\n";
        ENTER_THE_FILE_NAME_FOR_WRITING_PURPOSES_MESSAGE = "Enter the file name you'd like to write to\nYou could also create a new file and write immediately to it";
        TYPE_SOMETHING_TO_WRITE_TO_FILE_MESSAGE = "Type the message you'd like to store in the file";
        WRITING_WAS_SUCCESSFUL_MESSAGE = "Message successfully added to the file\n";
        SOMETHING_WENT_WRONG_WHILE_TRYING_TO_WRITE_TO_FILE_MESSAGE = "An error occurred while trying to write to the file";
        DISPLAYING_FILES_IN_CURRENT_DIRECTORY_MESSAGE = "\nDisplaying files that are available in current directory\n";
        ENTER_THE_FILE_NAME_FOR_READING_PURPOSES_MESSAGE = "Type the file name which you'd like to read";
        SOMETHING_WENT_WRONG_WHILE_TRYING_TO_READ_FROM_FILE_MESSAGE = "An error occurred while trying to read the file";
        ENTER_THE_FILE_NAME_FOR_DELETING_PURPOSES_MESSAGE = "Enter the name of the file which you'd like to delete";
        FILE_DELETED_SUCCESSFULLY_MESSAGE = " has been deleted successfully\n";
        FAILED_TO_DELETE_FILE_MESSAGE = "Failed to delete the file";
        SOMETHING_WENT_WRONG_WHILE_DELETING_FILE_MESSAGE = "An error occurred while deleting the file";
        FILE_CREATED_SUCCESSFULLY_MESSAGE = " has been created successfully\n";
        FILE_ALREADY_EXISTS_MESSAGE = "This file already exists";
        FILE_CREATION_ERROR_OCCURRED_MESSAGE = "An error occurred while creating this file";
        TYPE_THE_NAME_OF_THE_NEW_FILE_MESSAGE = "Type the name of the new file you'd like to create";
        ENTER_THE_PATH_MESSAGE = "\nType a valid path from your device which we'll be working with\nFor example  C:\\\\Users\\\\KishanSital\\\\Downloads\\\\ \n";
        INVALID_DIRECTORY_SELECTION = "Previous given path was not correct, please enter a valid path\n";
        WELCOME_MESSAGE = "db   d8b   db d88888b db       .o88b.  .d88b.  .88b  d88. d88888b   d888888b  .d88b.    db    db  .d88b.  db    db d8888b.   d88888b d888888b db      d88888b   .88b  d88.  .d8b.  d8b   db  .d8b.   d888b  d88888b d8888b. \n" +
                "88   I8I   88 88'     88      d8P  Y8 .8P  Y8. 88'YbdP`88 88'       `~~88~~' .8P  Y8.   `8b  d8' .8P  Y8. 88    88 88  `8D   88'       `88'   88      88'       88'YbdP`88 d8' `8b 888o  88 d8' `8b 88' Y8b 88'     88  `8D \n" +
                "88   I8I   88 88ooooo 88      8P      88    88 88  88  88 88ooooo      88    88    88    `8bd8'  88    88 88    88 88oobY'   88ooo      88    88      88ooooo   88  88  88 88ooo88 88V8o 88 88ooo88 88      88ooooo 88oobY' \n" +
                "Y8   I8I   88 88~~~~~ 88      8b      88    88 88  88  88 88~~~~~      88    88    88      88    88    88 88    88 88`8b     88~~~      88    88      88~~~~~   88  88  88 88~~~88 88 V8o88 88~~~88 88  ooo 88~~~~~ 88`8b   \n" +
                "`8b d8'8b d8' 88.     88booo. Y8b  d8 `8b  d8' 88  88  88 88.          88    `8b  d8'      88    `8b  d8' 88b  d88 88 `88.   88        .88.   88booo. 88.       88  88  88 88   88 88  V888 88   88 88. ~8~ 88.     88 `88. \n" +
                " `8b8' `8d8'  Y88888P Y88888P  `Y88P'  `Y88P'  YP  YP  YP Y88888P      YP     `Y88P'       YP     `Y88P'  ~Y8888P' 88   YD   YP      Y888888P Y88888P Y88888P   YP  YP  YP YP   YP VP   V8P YP   YP  Y888P  Y88888P 88   YD \n" +
                "                                                                                                                                                                                                                            \n" +
                "                                                                                                                                                                                                                            ";
    }
    private Constants() {
        super();
    }

    public static final void displayWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
    }
}
