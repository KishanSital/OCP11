package mypackage.serviceImpl;


import mypackage.exceptions.*;
import mypackage.services.*;
import mypackage.utils.*;

import java.util.concurrent.atomic.AtomicInteger;

public final class TriesValidationServiceImpl implements TriesValidationService {
    //Atomic classes for our instance variables
    private static AtomicInteger triesLeftCounter, triedCounter;

    private TriesValidationServiceImpl() {
        super();
    }

    public static void resetTriesService() {
        triesLeftCounter = new AtomicInteger(5) ;
        triedCounter = new AtomicInteger(0);
    }
    
    public static void triesValidation() {

        triesLeftCounter.decrementAndGet();
        triedCounter.getAndIncrement();

        try {
            if (triedCounter.get() >= IntUtilsMyPackage.RETRY_LIMIT.getIntValue()) {
                System.out.println(StringUtilsMyPackage.MAXIMUM_TRIED_AMOUNT_MESSAGE.getStringValue());
                System.exit(0);
            } else {
                throw new TriesException("You have " + getTriesLeftCounter() + ((triesLeftCounter.get() > 1) ? " tries" : " try") + " left");
            }
        } catch (TriesException e) {
            System.out.println(e.getMessage());
        }

    }

    public static int getTriesLeftCounter() {
        return triesLeftCounter.get();
    }

}
