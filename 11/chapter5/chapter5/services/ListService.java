package chapter5.services;

import chapter5.entities.*;

import java.util.*;

public class ListService {

    private List <String> namesList = new ArrayList<String>();
    private NamesEntity namesEntity;
    private Scanner scanner;
    private int removeUsingIndex = 1;
    private int removeUsingValue = 2;
    private String chosenName;
    private TriesValidationService triesForInvalidChoiceSelection;
    private TriesValidationService triesForInvalidIndexSelection;
    private TriesValidationService triesForInvalidNameSelection;
    private int selection;


    public ListService(NamesEntity namesEntity,
                       TriesValidationService triesValidationService){
        this.namesEntity = namesEntity;
        this.triesForInvalidChoiceSelection = triesValidationService;
        init();
    }

    public void init(){
        scanner = new Scanner(System.in);
        triesForInvalidIndexSelection = new TriesValidationService();
        triesForInvalidNameSelection = new TriesValidationService();
        resetAllTriesValidationServices();

    }

    public void resetAllTriesValidationServices(){
        triesForInvalidChoiceSelection.resetTriesService();
        triesForInvalidIndexSelection.resetTriesService();
        triesForInvalidNameSelection.resetTriesService();

    }

    public void insertNames (){
        namesList.add(namesEntity.getName());
        namesList.add( ("Gutenberg"));
        namesList.add(("Bonaparte"));
        namesList.add(("Luther"));
        namesList.add(("Marx"));
        namesList.add(("Caesar"));
        namesList.add(("Buddha"));
        namesList.add(("Tesla"));
        namesList.add(("Hitler"));

    }

    public void sortNameList(){
       Collections.sort(namesList);
        System.out.println("List has been sorted\n");
    }

    public void printOutNames(){
        insertNames();
        System.out.println("We've added your name amongst many legendary people");
        for (int i = 0; i< namesList.size(); i++){
            System.out.println("Index number: " + i + ", name: " + namesList.get(i));
        }
        System.out.println("\n");
    }

    public void viewAllNames(){
        if (namesList.isEmpty()){
            System.out.println("List was empty\n");
            return;
        }
        for (int i = 0; i< namesList.size(); i++){
            System.out.println("Index number: " + i + ", name: " + namesList.get(i));
        }
        System.out.println("\n");
    }

    public void getNamesListSize(){
        System.out.println("The size of the list is " + namesList.size());
        System.out.println("\n");

    }
    public void removeName() {
        System.out.println("How would you like to remove a name ?");
        System.out.println("Type 1 to remove using the index number");
        System.out.println("Type 2 to remove using the value");
        System.out.println(" ");
        selection = scanner.nextInt();

        if (selection == removeUsingIndex){

            System.out.println("Type the index number");
            int indexNumber = scanner.nextInt();
            try {
                namesList.remove(indexNumber);
				System.out.println("Name removed successfully\n");

            } catch (IndexOutOfBoundsException ioe){
                triesForInvalidIndexSelection.triesValidation();
                System.out.println("Please type a valid index number\n");
                removeName();
            }

        } else if (selection == removeUsingValue){

            System.out.println("Type the specific name");
            String specificName = scanner.next();
            if (namesList.remove(specificName)){
			
			System.out.println("Name removed successfully\n");

            } else {
                triesForInvalidNameSelection.triesValidation();
                System.out.println("Please type a valid name \n");
                removeName();
            }
        } else {
            triesForInvalidChoiceSelection.triesValidation();
            System.out.println("Please choose a valid option\n");
            removeName();
        }

    }


    public void insertNewName() {
        System.out.println("Type the name");
        String newName = scanner.next();
        if (namesList.add(newName)){
            System.out.println("New name added successfully");
        } else {
            System.out.println("Something went wrong");
        }
        System.out.println("\n");

    }

    public void changeSpecificNameFromList(){
        System.out.println("Type the index number of the name you want to change");
        int indexNumber = scanner.nextInt();
        System.out.println("Type the name you'd like to place instead of " + namesList.get(indexNumber));
        String changeName = scanner.next();
        namesList.set(indexNumber,changeName);
        System.out.println("Index number " + indexNumber + " from the list has been changed with " + changeName);
        System.out.println("\n");

    }


    public void clearNamesList(){
        namesList.clear();
        System.out.println("All names have been removed from the list");
        System.out.println("\n");

    }

    public boolean checkIfNamesListContainsSpecificName(){
        System.out.println("Type the name");
        chosenName = scanner.next();
        if(namesList.contains(chosenName)){
            System.out.println("List DOES contain "+ chosenName);
            System.out.println("\n");
            return true;
        } else {
            System.out.println("List DOES NOT contain "+ chosenName);
            System.out.println("\n");
            return false;
        }
    }

    public List<String> getNamesList (){
        return namesList;
    }

    public String getChosenName(){
        return chosenName;
    }
}
