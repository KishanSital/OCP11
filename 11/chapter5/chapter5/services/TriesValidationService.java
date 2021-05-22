package chapter5.services;

import chapter5.constants.*;

public class TriesValidationService {
	
    private int triesLeftCounter , triedCounter;
	
    public TriesValidationService(){
		
	}
	
    public void resetTriesService (){
        triesLeftCounter = 5;
        triedCounter = 0;
	}
	
    public void triesValidation(){
        --triesLeftCounter;
        triedCounter ++ ;
        if ( triedCounter >= Constants.RETRY_LIMIT){
            System.out.println("Limit has been reached, please restart the application");
            System.exit(0);
			} else {
            System.out.println("You have "+ triesLeftCounter + ( (triesLeftCounter > 1) ? " tries":" try") + " left");
		}
	}
	
    public int getTriesLeftCounter(){
        return triesLeftCounter;
	}
	
}
