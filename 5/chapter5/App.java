package chapter5;

import chapter5.constants.*;
import chapter5.services.*;
import chapter5.entities.*;


import java.util.*;

public class App {

    public static void main(String... args) {

        Constants.displayWelcomeMessage();

        Scanner scanner = new Scanner(System.in);
        NamesEntity name = new NamesEntity();
        System.out.println("Please enter your name...");
        name.setName(scanner.next());

		
        TriesValidationService triesValidationService = new TriesValidationService();
		
		MathService mathService = new MathService(triesValidationService);
		
        ListService listService =  new ListService(name,
                                                   triesValidationService);

        MenuService menuService = new MenuService(triesValidationService,
                                                  listService,
												  mathService);

        menuService.menuNavigator();



    }
}