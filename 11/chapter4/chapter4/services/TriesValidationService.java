package chapter4.services;

import chapter4.constants.*;

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
            System.out.println("Invalid limit has been reached, please restart the application");
            System.exit(0);
			} else {
            System.out.println("You have "+ getTriesLeftCounter() + ( (getTriesLeftCounter() > 1) ? " tries":" try") + " left");
		}
	}
	
    public int getTriesLeftCounter(){
        return triesLeftCounter;
	}
	
}
