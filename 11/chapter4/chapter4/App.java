package chapter4;

import chapter4.entities.*;
import chapter4.services.*;
import chapter4.constants.*;
import chapter4.repositories.*;


public class App {
	
    public static void main (String ... args){
		
        // inserting the standard user
        UserEntity userEntity = new UserEntity();
        UserRepository userRepository = new UserRepository(userEntity);
        userRepository.insertStandardUser();
		
        // insert mail for standard user
        MailEntity mailEntity = new MailEntity();
        MailRepository mailRepository = new MailRepository(userRepository,
														   mailEntity);
        mailRepository.insertStandard2dMails();
		
		
        Constants.displayWelcomeMessage();
		
        TriesValidationService triesValidationService = new TriesValidationService();
        MenuService menuService = new MenuService(triesValidationService);
        menuService.menuNavigator(userRepository,
								  mailRepository);
		
	}
}
