package mypackage.serviceImpl;


import mypackage.exceptions.*;
import mypackage.services.*;
import mypackage.utils.*;

public class TriesValidationServiceImpl implements TriesValidationService {
    private static int triesLeftCounter, triedCounter;

    private TriesValidationServiceImpl() {
        super();
    }

    public static void resetTriesService() {
        triesLeftCounter = 5;
        triedCounter = 0;
    }
    
    public static void triesValidation() {
        --triesLeftCounter;
        triedCounter++;

        try {
            if (triedCounter >= IntUtilsMyPackage.RETRY_LIMIT.getIntValue()) {
                System.out.println(StringUtilsMyPackage.MAXIMUM_TRIED_AMOUNT_MESSAGE.getStringValue());
                System.exit(0);
            } else {
                throw new TriesException("You have " + getTriesLeftCounter() + ((triesLeftCounter > 1) ? " tries" : " try") + " left");
            }
        } catch (TriesException e) {
            System.out.println(e.getMessage());
        }

    }

    public static int getTriesLeftCounter() {
        return triesLeftCounter;
    }

}
