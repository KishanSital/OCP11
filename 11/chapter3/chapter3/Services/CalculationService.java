package chapter3.Services;

import chapter3.Entities.*;
import chapter3.App;

public class CalculationService {
	private int parameter1;
	private double parameter2;
	private long parameter3;
	
	public CalculationService(){
	}
	public void calculation (int menuItemSelected, 
							 int[] menuItems,
							 ParametersEntity parametersEntity) {
							 
		parameter1 = parametersEntity.getParameter1();
		parameter2 = parametersEntity.getParameter2();
		parameter3 = parametersEntity.getParameter3();
		
		{ //block
			// ternary operator
			System.out.println((menuItemSelected != menuItems[0]) ?
				((menuItemSelected != menuItems[1]) ?
					((menuItemSelected != menuItems[2]) ? 
						("parameter1 += parameter2\n" + parameter1 + " += " + parameter2 +"\n"+ "The value of parameter1 now is " + (parameter1+= parameter2) +"\n prameter1 += parameter3\n" + parameter1 +" += " + parameter3 +"\n" + "The value of parameter1 is now "+ (parameter1 += parameter3)): // operation using compound operator
					"var answer = parameter1 * parameter2 / parameter3\n"+parameter1 +" * "+ parameter2 + " / " + parameter3 +"\nSystem.out.println(answer)\n" + (parameter1 * parameter2 / parameter3)) : //type promotion will take  place and var will automatically take double as its type
				("var answer = parameter1++ +(parameter1-- %  parameter2)+ --parameter3\n" +parameter1 + "++ +(" + parameter1 + "-- %" + parameter2 + ")" + "+ --" + parameter3 + "\nvar answer = " + (parameter1++ + (parameter1-- % parameter2) + --parameter3))) : // operator precedence, post increment, post decrement , modulus operator, pre decrement 
				"var answer = (int) ( parameter1 + parameter2 * parameter3)\n" + parameter1 + " + " + parameter2 + " * " + parameter3 + " = " + (int) (parameter1 + parameter2 * parameter3) + "\n answer = " + (int) (parameter1 + parameter2 * parameter3)); // Binary operation, operator precedence with type promotion to double and casting to int
				{ //scope block starts here
					
					//String [] args = new String[1];
					//App app = new App();
					App.main(null);
					
				} //scope ends here
				
			 
		}
	}
	
	
}
