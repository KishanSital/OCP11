package chapter3.Services;

import chapter3.Entities.*;

import java.util.*;
import chapter2.*;

public class MenuService {

	private Scanner calcMenu;
	private int menuItems [], menuItemSelected, parameter1, aquariaCode = 5, exitCode = 6,recursionCounter, triesLeftCounter = 5; // assignment operator
	private double parameter2;
	private long parameter3;
	private CalculationService calculationService;
	private ParametersEntity parametersEntity;
	private static final int RECURSION_LIMIT = 5;

	public MenuService(){

	}
	
	public MenuService (CalculationService calculationService,ParametersEntity parametersEntity){
		this.calculationService = calculationService;
		this.parametersEntity = parametersEntity;
	}

	private  void displayMenuOptions(){
		System.out.println("*      Welcome to your java logic calculations example app   *");
		System.out.println(" type 1 for an example of operator precedence and type promotion and casting ");
		System.out.println(" type 2 for an example of post- increment and pre-decrement with modulus, using parenthesis for operator precedence ");
		System.out.println(" type 3 for an example of numeric promotion");
		System.out.println(" type 4 for an example of compound operations");
		System.out.println(" type 5 to navigate to chapter2 ");
		System.out.println(" type 6 to exit...");
	}

	public void Menu(){
		menuItems = new int [4];
		menuItems[0] = 1;
		menuItems[1] = 2;
		menuItems[2] = 3;
		menuItems[3] = 4;
		calcMenu = new Scanner(System.in);
		
		displayMenuOptions();
		
		menuItemSelected = calcMenu.nextInt();
		navigationToConnectedChapterChecker();
	}

	private void invalidSelectionChecker(){
		if (Arrays.stream(menuItems).noneMatch(i -> i == menuItemSelected)) { // checks if the given input does not match my array element		
			--triesLeftCounter; //pre-decrement operator
			recursionCounter++; //post-increment operator
			
			if (recursionCounter >= RECURSION_LIMIT){  //relational operator
				System.out.println("Invalid menu selection limit has been reached, please restart the application");
				System.exit(0);
			} else {
				System.out.println("\n \tPlease choose a valid option");
				System.out.println("\t     You have " + triesLeftCounter +  ((triesLeftCounter == 1) ? " try": " tries" )  + " left\n");  // ternary operator
				Menu();// recursion
			}
		}    
		}
	
	
	private void navigationToConnectedChapterChecker(){
		if (menuItemSelected == aquariaCode){  // relational operator
			System.out.println("\nThank you and goodbye.");
			//System.exit(0); // to terminate JVM
			Aquaria.main(null);
		}else if(menuItemSelected == exitCode){
		
		return;
		
		}else {
			invalidSelectionChecker();
			System.out.println(" please pass 3 Number parameters ");
			System.out.println(" parameter1 (int)");
			parameter1 = calcMenu.nextInt();
			System.out.println(" parameter2 (double)");
			parameter2 = calcMenu.nextDouble();
			System.out.println(" parameter3 (long)");
			parameter3 = calcMenu.nextLong();
			parametersEntity = new ParametersEntity(parameter1, parameter2, parameter3);
			System.out.println("\nint parameter1 = " + parameter1 + "\ndouble parameter2 = " + parameter2 + "\nlong parameter3 = " + parameter3 + "\n");
			calculationService.calculation(menuItemSelected, menuItems, parametersEntity);
		}
	}
	
}
