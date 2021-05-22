package chapter5.services;

import chapter5.constants.*;

import java.util.*;

public class MathService{

    private Scanner scanner;
    private TriesValidationService triesValidationService;
    private List <String> mathMenuOptions;
    private int exitCode = 5, firstChoice = 1, secondChoice = 2;


    public MathService (TriesValidationService triesValidationService){

        this.triesValidationService = triesValidationService;

        init();
    }

    private void init(){
        scanner = new Scanner(System.in);
        resetAllValidationServices();

    }
    private void resetAllValidationServices(){
        triesValidationService.resetTriesService();

    }

    public void mathMenuNavigator(){
        int choiceEntry = 0;

        do {
            mathMenuOptions();
			
			if (scanner.hasNextInt()){
				choiceEntry = scanner.nextInt();
			} 
			else {
				scanner.next();
				System.out.println("Please type a valid number \n");
				triesValidationService.triesValidation();

				continue;
			}
            switch(choiceEntry)
            {
                case 1:
                    resetAllValidationServices();
                    minMax();
                    break;
                case 2:
                    resetAllValidationServices();
                    round();
                    break;
                case 3:
                    resetAllValidationServices();
                    pow();
                    break;
                case 4:
                    resetAllValidationServices();
					random();
                    break;
				case 5:
                    resetAllValidationServices();
					System.out.println("Thank you and goodbye\n");
                    break;
                case 6:
                    System.exit(0);
                default:
                    System.out.println("Choice must be a value between 1 and 6.\n");
                    triesValidationService.triesValidation();
            }

        } while (choiceEntry != exitCode);

    }


	private void random(){
	
        int startingCount = 1;

        System.out.println("Type the amount of random digits you want to generate");
        int mountOfValues = scanner.nextInt();
        System.out.println("Now type the lowest value the numbers can be");
        int lowestValue = scanner.nextInt();
        System.out.println("Provide the highest value the numbers can be");
        int highestValue = scanner.nextInt();

        System.out.println("The generated numbers are \n");

        double value;
		String leftAlignFormat = " | %-20s | %-40s | \n ";

		System.out.printf("  +--------------------+----------------------------------------+ \n");
        System.out.printf( leftAlignFormat , "Amount counter", "Generated values");
		System.out.printf("  +--------------------+----------------------------------------+ \n");
        for ( ;startingCount <= mountOfValues; startingCount++){
           
		       value= ((Math.random() * highestValue));
			   System.out.printf(leftAlignFormat,startingCount,value);
        }
		System.out.printf("  +--------------------+----------------------------------------+ \n");
    }

    private void minMax(){
        int inputChoice;
        double firstNumber;
        double secondNumber;

        System.out.println("Type a number it can be a decimal value");
        firstNumber = scanner.nextDouble();

        System.out.println("Type a second number it can also be a decimal value");
        secondNumber = scanner.nextDouble();


        System.out.println("So tell me, what would you like to do ?");
        System.out.println("Type 1 to compare these two numbers and retrieve the smaller one");
        System.out.println("Type 2 to compare these two numbers and retrieve the bigger one");
        System.out.println("Type any other number to exit..");

        inputChoice = scanner.nextInt();

        if (inputChoice == firstChoice){
            retrieveSmallerNumber(firstNumber, 
								  secondNumber);

        } else if (inputChoice == secondChoice){
            retrieveBiggerNumber(firstNumber,
								secondNumber);
        }


    }

    private void round(){

        System.out.println("Type a dcimal number that you'd like to round");
        double toRoundNumber = scanner.nextDouble();
        System.out.println("The result was "+ Math.round (toRoundNumber) + "\n");

    }

    private void pow(){

        double firstNumber;
        double secondNumber;

        System.out.println("Type the first number you'd like to place in the expression");
        firstNumber = scanner.nextDouble();

        System.out.println("Type the second number, which will be the exponent of "
                + firstNumber);
        secondNumber = scanner.nextDouble();

        System.out.println("The result of "
							+ firstNumber
							+ " to the power of "
							+ secondNumber + " is "
							+ Math.pow(firstNumber,
							secondNumber)
							+ "\n");

    }

    private void retrieveSmallerNumber (double firstNumber,
                                        double secondNumber){

        System.out.println("The result was " + Math.min(firstNumber,
														secondNumber) + "\n");

    }

    private void retrieveBiggerNumber (double firstNumber,
                                       double secondNumber){

        System.out.println("The result was " + Math.max(firstNumber,
														secondNumber) + "\n");
    }


    private void mathMenuOptions(){
        mathMenuOptions = new ArrayList<>();
        mathMenuOptions.add("What would you like to do ?");
        mathMenuOptions.add("to guess which of the given numbers are bigger or smaller than the other");
        mathMenuOptions.add("to round a given decimal value");
        mathMenuOptions.add("to work with exponents");
		mathMenuOptions.add("to generate random numbers");
        mathMenuOptions.add("to navigate back");
        mathMenuOptions.add("to shut down the program...");
        First:   for (int i = 0 ; i < mathMenuOptions.size(); i++){
            if (i == Constants.QUESTION_MESSAGE_INDEX){
                System.out.println(mathMenuOptions.get(i));
                continue First;
            } else {
                System.out.println("Type " + i + " " + mathMenuOptions.get(i));
            }
        }
    }


}
