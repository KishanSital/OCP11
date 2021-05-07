package chapter5.services;

import chapter5.constants.*;

import java.util.*;

public class MenuService {
	
    private List <String> menuOptions;
    private TriesValidationService triesValidationService;
    private int exitCode = 11;
    private Scanner scanner;
    private ListService listService;
	private SecondMenuService secondMenuService;
	private MathService mathService;

    public MenuService(TriesValidationService triesValidationService,
					   ListService listService,
					   MathService mathService){
        this.triesValidationService = triesValidationService;
        this.listService = listService;
		this.mathService = mathService;
		init();
	}

	private void init(){
		secondMenuService = new SecondMenuService(triesValidationService,
												  listService);
		scanner = new Scanner(System.in);
	    mathService = new MathService(triesValidationService);							  
		resetAllValidationServices();
		
		listService.printOutNames();


	}

	private void resetAllValidationServices(){
		triesValidationService.resetTriesService();

	}

    public void menuNavigator(){
        int choiceEntry = 0;
        do {
            menuOptions();
			
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
					listService.getNamesListSize();
				break;
				case 2:
					resetAllValidationServices();
					listService.insertNewName();
				break;
				case 3:
					resetAllValidationServices();
					listService.removeName();
				break;
				case 4:
					resetAllValidationServices();
					listService.checkIfNamesListContainsSpecificName();
				break;
				case 5:
					resetAllValidationServices();
					listService.clearNamesList();
				break;
				case 6:
					resetAllValidationServices();
					listService.changeSpecificNameFromList();
				break;
				case 7:
					resetAllValidationServices();
					secondMenuService.secondMenuNavigator();
				break;
				case 8:
					resetAllValidationServices();
					listService.viewAllNames();
				break;
				case 9:
					resetAllValidationServices();
					listService.sortNameList();
				break;
				case 10:
					resetAllValidationServices();
					mathService.mathMenuNavigator();
				break;
				case 11:
				System.out.println("Thank you and goodbye");
				System.exit(0);
				break;
				default:
				System.out.println("Choice must be a value between 1 and 11.\n");
				triesValidationService.triesValidation();
				
			}
		} while (choiceEntry != exitCode);
	}




	private void menuOptions(){
		menuOptions = new ArrayList<>();
		menuOptions.add("What would you like to do with the list ?");
		menuOptions.add("to get the size of the list");
		menuOptions.add("to add a new name");
		menuOptions.add("to remove a name");
		menuOptions.add("to check if the list contains a specific name");
		menuOptions.add("to clear the list");
		menuOptions.add("to change a specific value from the list");
		menuOptions.add("to perform other activities with a specific name from the list");
		menuOptions.add("to view the list");
		menuOptions.add("to sort the list");
		menuOptions.add("to perform some math operations");
		menuOptions.add("to close the app..");
		First:   for (int i = 0 ; i < menuOptions.size(); i++){
            if (i == Constants.QUESTION_MESSAGE_INDEX){
				System.out.println(menuOptions.get(i));
				continue First;
				} else {
				System.out.println("Type " + i + " " + menuOptions.get(i));
			}
		}
		
	}
	
	
}
