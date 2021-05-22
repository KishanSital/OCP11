package chapter10.services;

import static chapter10.constants.Constants.*;
import chapter10.exceptions.*;
import static chapter10.constants.Constants.*;

public class TriesValidationService {

    private static int triesLeftCounter, triedCounter;

    private TriesValidationService() {
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
            if (triedCounter >= RETRY_LIMIT) {
                System.out.println(MAXIMUM_TRIED_AMOUNT_MESSAGE);
                System.exit(0);
            } else {
                throw new TriesException("You have " + getTriesLeftCounter() + ((triesLeftCounter > 1) ? " tries" : " try") + " left");
            }
        } catch (TriesException e) {
            System.out.println(e.getMessage());
        } finally {
            // will never be reached if line 28 is executed
        }

    }

    public static int getTriesLeftCounter() {
        return triesLeftCounter;
    }

}
