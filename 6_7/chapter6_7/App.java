package chapter6_7;


import chapter6_7.constants.*;
import chapter6_7.entities.*;
import chapter6_7.entities.*;
import chapter6_7.repositories.*;
import chapter6_7.repositories.*;
import chapter6_7.services.*;
import chapter6_7.services.*;

public class App {
	
    public static void main (String ... args){

        Constants.displayWelcomeMessage();
        MenuService menuService = new MenuService();
        menuService.menuNavigator();
		
	}
}
