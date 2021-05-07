package chapter5.services;

import chapter5.constants.*;

import java.util.*;

public class StringService {

    private String chosenNameString;
    private ListService listService;
    private Scanner scanner;
    private TriesValidationService triesValidationService;
    private List <String> stringMenuOptions;
    private int exitCode = 11, startingIndexNumber,
               endingIndexNumber, firstChoice = 1, secondChoice = 2;

    public StringService(String chosenName,
                         ListService listService) {
        this.chosenNameString = chosenName;
        this.listService = listService;
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

    public void StringMenuNavigator(){
        int choiceEntry = 0;
        do {
            stringMenuOptions();
            	
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
                    subString();
                    break;
                case 2:
                    resetAllValidationServices();
                    toLowerCase();
                    break;
                case 3:
                    resetAllValidationServices();
                    toUpperCase();
                    break;
                case 4:
                    resetAllValidationServices();
                    equals();
                    break;
                case 5:
                    resetAllValidationServices();
                    equalsIgnoreCase();
                    break;
                case 6:
                    resetAllValidationServices();
                    startsWith();
                    break;
                case 7:
                    resetAllValidationServices();
                    endsWith();
                    break;
                case 8:
                    resetAllValidationServices();
                    replace();
                    break;
                case 9:
                    resetAllValidationServices();
                    contains();
                    break;
                case 10:
                    resetAllValidationServices();
                    System.out.println(chosenNameString);
                    break;
                case 11:
                    resetAllValidationServices();
                    System.out.println("Thank you and goodbye\n");
                    break;
                case 12:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Choice must be a value between 1 and 12.\n");
                    triesValidationService.triesValidation();
            }
        } while (choiceEntry != exitCode);
    }

    private void contains() {
        System.out.println("Type a letter or word that you'd like to use to check if "
                          +" (" + chosenNameString+ ") does contains\n"
                          + "keep in mind that this operation is case sensitive");

        String enteredCharSequence = scanner.next();
        System.out.println( "The result was " + ( (chosenNameString.contains(enteredCharSequence)) ?
                                                  "true, which means that "+ chosenNameString
                                                + " does consist of the letter or word"+ enteredCharSequence
                                                : "false, which means that "+ chosenNameString
                                                + " does not consist of the letter or word" + enteredCharSequence));


    }

    private int getMaxIndexNumber (String name){
       return (name.length() -1);
    }

    private int getMinimumIndexNumber (String name){
        return (0);
    }

    private void replace() {
        resetAllValidationServices();
        boolean isContain = false;
        do {
            System.out.println("Choose and type the letter or word that you'd like to replace within "
                                + " (" + chosenNameString + ")\n");
            String selectedCharSequence = scanner.next();

            if (chosenNameString.contains(selectedCharSequence)) {
                isContain = true;
                System.out.println("Now type the replacement letter or word that should be placed instead of "
                                     + selectedCharSequence);
                String replacementCharSequence = scanner.next();
                System.out.println(chosenNameString + " has been changed to "
                                                    + chosenNameString.replace(selectedCharSequence,
                                                    replacementCharSequence) + "\n");
            } else {
                triesValidationService.triesValidation();
                System.out.println("Chosen letter or word was not found, "
                                    +"please try again\n");

            }
        } while (!isContain);


    }

    private void endsWith() {
        System.out.println("Type the letter or word that you think "
                            + " (" + chosenNameString + ") ends with\n");
        String selectedCharSequence = scanner.next();
        System.out.println("The result was " + ( (chosenNameString.endsWith(selectedCharSequence)) ?
                            " true, so it does end with "+ selectedCharSequence+"\n"
                           :" false, so it does not end with "+ selectedCharSequence));
    }

    private void startsWith() {
        System.out.println("Type the letter or word that you think "
                + " (" + chosenNameString + ") starts with\n");
        String selectedCharSequence = scanner.next();
        System.out.println("The result was " + ( (chosenNameString.startsWith(selectedCharSequence)) ?
                " true, so it does start with "+ selectedCharSequence+"\n"
                :" false, so it does not start with "+ selectedCharSequence));
    }

    private void equalsIgnoreCase() {
        System.out.println("Type another String value you'd like to compare " + chosenNameString
                          +" with you don't need to worry about the case sensitivity\n");
        String newStringValue = scanner.next();
        System.out.println("\nThe result was " + chosenNameString.equalsIgnoreCase(newStringValue) +
                ((chosenNameString.equalsIgnoreCase(newStringValue)) ?
                        " the sequence of the characters do match\n":
                        " the sequence of the characters do not match \n"));
    }

    private void equals() {
        System.out.println("Type another String value you'd like to compare " + chosenNameString +" with\n");
        String newStringValue = scanner.next();
        System.out.println("\nThe result was " + chosenNameString.equals(newStringValue) +
                          ((chosenNameString.equals(newStringValue)) ?
                                  " the sequence of the characters do match \n":
                                  " the sequence of the characters do not match \n"));
    }

    private void toUpperCase() {
        System.out.println( "\nThe uppercase value of "+ chosenNameString + " is "
                            + chosenNameString.toUpperCase() +"\n");
    }
    private void toLowerCase() {
        System.out.println( "\nthe lowercase value of "+ chosenNameString + " is "
                            + chosenNameString.toLowerCase() + "\n");
    }


    private void workWithOnlyOneIndex(){
            boolean firstIndex;
            do {
                System.out.println("Insert the index number, it must not be smaller than "
                        + getMinimumIndexNumber(chosenNameString) + " or larger than "
                        + chosenNameString.length());
                startingIndexNumber = scanner.nextInt();

                if (startingIndexNumber < getMinimumIndexNumber(chosenNameString)
                        || startingIndexNumber > chosenNameString.length()){
                    firstIndex = false;
                    triesValidationService.triesValidation();

                } else {
                    firstIndex = true;
                }
            } while (!firstIndex);

            resetAllValidationServices();

            System.out.println("Your subString result was "
                    +chosenNameString.substring(startingIndexNumber)
                    + (chosenNameString.substring(startingIndexNumber).isEmpty() ?
                    " was empty":
                    " ")
                    + "\n");

    }

    private void workWithTwoIndexes (){

            boolean firstIndex;
            do {
                System.out.println("Insert the starting index number, it must not be smaller than "
                        + getMinimumIndexNumber(chosenNameString) + " or larger than "
                        + chosenNameString.length());

                startingIndexNumber = scanner.nextInt();

                if ( startingIndexNumber < getMinimumIndexNumber(chosenNameString)
                        || startingIndexNumber > chosenNameString.length()){
                    triesValidationService.triesValidation();
                    firstIndex = false;
                } else {
                    firstIndex = true;
                }
            } while (!firstIndex);


            resetAllValidationServices();

            boolean secondIndex;

            do {
                System.out.println("Insert the ending index number, it must not be smaller than "
                        + startingIndexNumber +" or larger than "
                        + chosenNameString.length());

                endingIndexNumber = scanner.nextInt();

                if (endingIndexNumber < startingIndexNumber
                        || endingIndexNumber > chosenNameString.length()){
                    secondIndex = false;
                    triesValidationService.triesValidation();
                } else {
                    secondIndex = true;
                }
            } while (!secondIndex);

            resetAllValidationServices();

            System.out.println( "Your subString result was "
                    + chosenNameString.substring(startingIndexNumber,
                    endingIndexNumber)
                    + ((chosenNameString.substring(startingIndexNumber,
                    endingIndexNumber)).isEmpty() ?"empty":" ")
                    + "\n");

    }

    private void subString() {
        System.out.println("Type 1 to only work with a starting index");
        System.out.println("Type 2 to work with both a starting and ending index");
        System.out.println("Type a different number to exit..");

        int choice = scanner.nextInt();

        if (choice == firstChoice){
            workWithOnlyOneIndex();
        } else if ( choice == secondChoice){
            workWithTwoIndexes();
        }

    }


    private void stringMenuOptions(){
        stringMenuOptions = new ArrayList<>();
        stringMenuOptions.add("What would you like to do with the String  " + "("+ chosenNameString+") ?");
        stringMenuOptions.add("to retrieve a subString");
        stringMenuOptions.add("to convert the name to lowercase");
        stringMenuOptions.add("to convert the name to uppercase");
        stringMenuOptions.add("to use the equals() method on the name with another String");
        stringMenuOptions.add("to use the equalsIgnoreCase() method on the name with another String");
        stringMenuOptions.add("to check if the name starts with a given String");
        stringMenuOptions.add("to check if the name ends with a given String");
        stringMenuOptions.add("to replace a letter or word from the name");
        stringMenuOptions.add("to check if the name contains a given letter or word");
        stringMenuOptions.add("to view the name");
        stringMenuOptions.add("to navigate back");
        stringMenuOptions.add("to shut down the program...");
        First:   for (int i = 0 ; i < stringMenuOptions.size(); i++){
            if (i == Constants.QUESTION_MESSAGE_INDEX){
                System.out.println(stringMenuOptions.get(i));
                continue First;
            } else {
                System.out.println("Type " + i + " " + stringMenuOptions.get(i));
            }
        }

    }


}
