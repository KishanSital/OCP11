package chapter5.services;

import chapter5.constants.*;

import java.util.*;
public class SecondMenuService {

    private TriesValidationService triesValidationService; 
    private ListService listService;
    private Scanner scanner;
    private List <String> secondMenuOptions;
    private  int exitCode = 4;
    private String chosenName;


    public SecondMenuService( TriesValidationService triesValidationService,
                              ListService listService){
        this.listService = listService;
        this.triesValidationService = triesValidationService;

        init();
    }

    public void init(){
        this.scanner = new Scanner(System.in);
		resetAllValidationServices();
    }

    public void resetAllValidationServices(){
        triesValidationService.resetTriesService();
    }

    public String chooseAName(){
        boolean isNameValid;

        listService.viewAllNames();
        System.out.println("Choose a name wisely");
		
        triesValidationService.resetTriesService();

        do {
            isNameValid = listService.checkIfNamesListContainsSpecificName();
            if (isNameValid == false){
                triesValidationService.triesValidation();
            }
        } while (isNameValid == false);
		
       return chosenName = listService.getChosenName();
    }



    public void secondMenuNavigator(){
        chooseAName();
        int choiceEntry = 0;

        do {
            secondMenuOptions();
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
                    StringService stringService = new StringService(chosenName,
                                                                    listService);
                    stringService.StringMenuNavigator();
                    break;
                case 2:
                    resetAllValidationServices();
                    StringBuilderService stringBuilderService = new StringBuilderService(chosenName,
                                                                                         listService);
                    stringBuilderService.StringBuilderMenuNavigator();
                    break;
                case 3:
                    resetAllValidationServices();
                    chooseAName();
                    break;
                case 4:
                    resetAllValidationServices();
                    System.out.println("Thank you and goodbye");
                    break;
                case 5:
                    resetAllValidationServices();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Choice must be a value between 1 and 5.\n");
                    triesValidationService.triesValidation();
            }
        } while (choiceEntry != exitCode);
    }

    private void secondMenuOptions(){

       secondMenuOptions = new ArrayList<>();
       secondMenuOptions.add("What would you like to do with the chosen name " + "("+ chosenName+") ?");
       secondMenuOptions.add("to work with the String data type");
       secondMenuOptions.add("to work with the StringBuilder data type");
       secondMenuOptions.add("to choose another name from the list");
       secondMenuOptions.add("to navigate back to the main menu");
       secondMenuOptions.add("to shut down the program...");
        First:   for (int i = 0 ; i < secondMenuOptions.size(); i++){
            if (i == Constants.QUESTION_MESSAGE_INDEX){
                System.out.println(secondMenuOptions.get(i));
                continue First;
            } else {
                System.out.println("Type " + i + " " + secondMenuOptions.get(i));
            }
        }

    }
}
