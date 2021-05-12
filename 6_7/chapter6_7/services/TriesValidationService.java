package chapter6_7.services;

import chapter6_7.constants.*;

public class TriesValidationService {
	
    private static int triesLeftCounter  , triedCounter ;
	
    private TriesValidationService(){
	super();	
	}
	
    public static void resetTriesService(){
        triesLeftCounter = 5;
        triedCounter = 0;
	}
	
    public static void triesValidation(){
        --triesLeftCounter;
        triedCounter ++ ;
        if ( triedCounter >= Constants.RETRY_LIMIT){
            System.out.println("Max tries have been reached, please restart the application");
            System.exit(0);
			} else {
            System.out.println("You have "+ triesLeftCounter + ( (triesLeftCounter > 1) ? " tries":" try") + " left");
		}
	}
	
    public static int getTriesLeftCounter(){
        return triesLeftCounter;
	}
	
}
