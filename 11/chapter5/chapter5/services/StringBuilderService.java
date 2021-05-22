package chapter5.services;

import chapter5.constants.Constants;

import java.util.*;

public class StringBuilderService {

    private StringBuilder chosenNameSB;
    private ListService listService;
    private Scanner scanner;
    private TriesValidationService triesValidationService;
    private List<String> stringBuilderMenuOptions;
    private int exitCode = 7;
    private int smallestIndexNumber = 0;

    public StringBuilderService(String chosenName,
                                ListService listService) {

        this.chosenNameSB = new StringBuilder(chosenName);
        this.listService =  listService;
        init();
    }

    private void init(){
        scanner = new Scanner(System.in);
        triesValidationService = new TriesValidationService();
        resetAllValidationServices();
    }

    private void resetAllValidationServices(){
        triesValidationService.resetTriesService();

    }

    public void StringBuilderMenuNavigator(){
        int choiceEntry = 0;
        do {
            StringBuilderMenuOptions();
			
			if (scanner.hasNextInt()){
				choiceEntry = scanner.nextInt();
				} 
			else {
				scanner.next();
				System.out.println("Please type a valid number \n");
				triesValidationService.triesValidation();

				continue;
			}
            switch (choiceEntry)
            {
                case 1:
                    resetAllValidationServices();
                    append();
                    break;
                case 2:
                    resetAllValidationServices();
                    insert();
                    break;
                case 3:
                    resetAllValidationServices();
                    delete();
                    break;
                case 4:
                    resetAllValidationServices();
                    deleteCharAt();
                    break;
                case 5:
                    resetAllValidationServices();
                    replace();
                    break;
                case 6:
                    resetAllValidationServices();
                    reverse();
                    break;
                case 7:
                    resetAllValidationServices();
                    System.out.println("Thank you and goodbye\n");
                    break;
                case 8:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Choice must be a value between 1 and 8.\n");
                    triesValidationService.triesValidation();
            }
        } while (choiceEntry != exitCode);
    }

    private void replace() {
        boolean isValidIndex;
        int startingIndexNumber;
        int endingIndexNumber;

        resetAllValidationServices();

        do {
            System.out.println("Type the index number where you'd like to start the replacement from");
            startingIndexNumber = scanner.nextInt();
            if (startingIndexNumber < smallestIndexNumber || startingIndexNumber > getChosenNameSBLength()) {
                triesValidationService.triesValidation();
                System.out.println("Please type a positive integer between -1 and " + (getChosenNameSBLength() + 1));
                isValidIndex = false;
            } else {
                isValidIndex = true;
            }
        } while (!isValidIndex);

        resetAllValidationServices();

        do {
            System.out.println("Type the index number where you'd like to end the replacement");
            endingIndexNumber = scanner.nextInt();
            if (endingIndexNumber < startingIndexNumber || endingIndexNumber > getChosenNameSBLength()) {
                triesValidationService.triesValidation();
                System.out.println("Please type an integer between "
                        + startingIndexNumber +" and "
                        + (getChosenNameSBLength() + 1));
                isValidIndex = false;
            } else {
                isValidIndex = true;
            }
        } while (!isValidIndex);

        resetAllValidationServices();

        System.out.println("Now type the replacement text");
        System.out.println("The value is now "
                          + chosenNameSB.replace(startingIndexNumber,
                            endingIndexNumber, scanner.next())
                          + "\n");


    }

    private void reverse() {
        System.out.println("The value is reversed and is now " + chosenNameSB.reverse() +"\n");
    }

    private void deleteCharAt() {
		boolean isValidIndex;
		int indexToRemove;
	    resetAllValidationServices();
		
		   do {		   
		     System.out.println("Type the index number of the character that you'd like to delete from "+ chosenNameSB);
			 indexToRemove = scanner.nextInt();
			 
            if (indexToRemove < smallestIndexNumber || indexToRemove > getChosenNameSBLength() -1) {
                triesValidationService.triesValidation();
                System.out.println("Please type a positive integer between -1 and " + (getChosenNameSBLength()));
                isValidIndex = false;
            } else {
                isValidIndex = true;
            }
        } while (!isValidIndex);

        resetAllValidationServices();
        chosenNameSB.deleteCharAt(indexToRemove);
        System.out.println("The value is now "+ chosenNameSB +"\n");
    }

    private void delete() {

        boolean isValidIndex;
        int startingIndexNumber;
        int endingIndexNumber;

        resetAllValidationServices();

        do {
            System.out.println("Type the index number where you'd like to start deleting from");
            startingIndexNumber = scanner.nextInt();
            if (startingIndexNumber < smallestIndexNumber || startingIndexNumber > getChosenNameSBLength()) {
                triesValidationService.triesValidation();
                System.out.println("Please type a positive integer between -1 and " + (getChosenNameSBLength() + 1));
                isValidIndex = false;
            } else {
                isValidIndex = true;
            }
        } while (!isValidIndex);

        resetAllValidationServices();

        do {
            System.out.println("Type the index number where you'd like to end deleting");
            endingIndexNumber = scanner.nextInt();
            if (endingIndexNumber < startingIndexNumber || endingIndexNumber > getChosenNameSBLength()) {
                triesValidationService.triesValidation();
                System.out.println("Please type an integer between "
                                    + startingIndexNumber +" and "
                                    + (getChosenNameSBLength() + 1));
                isValidIndex = false;
            } else {
                isValidIndex = true;
            }
        } while (!isValidIndex);

        resetAllValidationServices();
        chosenNameSB.delete(startingIndexNumber,endingIndexNumber);
        System.out.println("The new value is now "+ chosenNameSB +"\n");

    }

    private int getChosenNameSBLength(){
       return chosenNameSB.length();
    }

    private void insert() {
        int indexNumber;
        resetAllValidationServices();
        boolean isValidIndex;
        do {
            System.out.println("Type the index number where you'd like to insert a value");
            indexNumber = scanner.nextInt();
            if (indexNumber > getChosenNameSBLength() || indexNumber < smallestIndexNumber) {
                isValidIndex = false;
                triesValidationService.triesValidation();
                System.out.println("Please type a positive integer between -1 and " + (getChosenNameSBLength() + 1));
            } else {
                isValidIndex = true;
            }
        } while (!isValidIndex);
        System.out.println("Type the value you'd like to insert");
        StringBuilder insertValue = new StringBuilder(scanner.next());
        chosenNameSB.insert(indexNumber, insertValue);
        System.out.println("The value is now " + chosenNameSB + "\n");

    }

    private void append() {
        System.out.println("Type something you'd like to append to "+ chosenNameSB);
        StringBuilder toAppendValue = new StringBuilder( scanner.next());
        chosenNameSB.append(toAppendValue);
        System.out.println("The value is now " + chosenNameSB + "\n");
    }


    private void StringBuilderMenuOptions() {
        stringBuilderMenuOptions = new ArrayList<String>();

        stringBuilderMenuOptions.add("What would you like to do with the StringBuilder value ("+ chosenNameSB +") ?\n" +
                                     "Keep in mind that these changes will persist !");
        stringBuilderMenuOptions.add("to append something");
        stringBuilderMenuOptions.add("to insert something at a specific location");
        stringBuilderMenuOptions.add("to delete something");
        stringBuilderMenuOptions.add("to delete a character at a specific location");
        stringBuilderMenuOptions.add("to replace something");
        stringBuilderMenuOptions.add("to reverse it");
        stringBuilderMenuOptions.add("to navigate to the previous menu");
        stringBuilderMenuOptions.add("to shut down the program...");
        First:   for (int i = 0 ; i < stringBuilderMenuOptions.size(); i++){
            if (i == Constants.QUESTION_MESSAGE_INDEX){
                System.out.println(stringBuilderMenuOptions.get(i));
                continue First;
            } else {
                System.out.println("Type " + i + " " + stringBuilderMenuOptions.get(i));
            }
        }
    }

}
