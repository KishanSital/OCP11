package chapter14.views;

import chapter14.serviceImpl.*;
import chapter14.utils.*;
import java.util.*;
import static mypackage.serviceImpl.TriesValidationServiceImpl.*;

public class FireArmsView extends FireArmsServiceImpl {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void sellFireArms() {
        if (getFireArms().isEmpty()) {
            System.out.println(StringUtilsFireArmsServiceImplMessages.OUT_OF_STOCK_MESSAGE.getMessage());
        } else {
            testName();
            testAmount();
            updateFireArms();
        }
    }

    @Override
    public void displayTransactionDetails() {
        if (getAfterServiceList().isEmpty()) {
            System.out.println(StringUtilsFireArmsServiceImplMessages.NOTHING_SOLD_MESSAGE.getMessage());
        } else {
            displayTransactionDetailsProcess();
        }
    }

    private void displayTransactionDetailsProcess() {
        long key;
        resetTriesService();
        boolean isKeyPresent;
        do {
            System.out.println(StringUtilsFireArmsServiceImplMessages.INSERT_TRANSACTION_ID.getMessage());
            try {
                key = scanner.nextLong();
            } catch (InputMismatchException e) {
                triesValidation();
                System.out.println(StringUtilsFireArmsServiceImplMessages.TRANSACTION_NOT_EXISTENT_MESSAGE.getMessage());
                isKeyPresent = false;
                scanner.next();
                continue;
            }
            isKeyPresent = keyExistenceValidation(key);
        } while (!isKeyPresent);
    }

    private boolean keyExistenceValidation(long key) {
        boolean isKeyPresent;
        if (getAfterServiceList().containsKey(key)) {
            isKeyPresent = true;
            printOutFireArm.accept(getAfterServiceList().get(key));
        } else {
            triesValidation();
            isKeyPresent = false;
            System.out.println(StringUtilsFireArmsServiceImplMessages.TRANSACTION_NOT_EXISTENT_MESSAGE.getMessage());
        }
        return isKeyPresent;
    }

    private void testName() {
        resetTriesService();
        boolean isFireArmExistent;
        do {
            System.out.println(StringUtilsFireArmsServiceImplMessages.INSERT_NAME_MESSAGE.getMessage());
            fireArmName = scanner.next();
            isFireArmExistent = checkIfFireArmExists(fireArmName);
            if (!isFireArmExistent) {
                triesValidation();
                System.out.println(StringUtilsFireArmsServiceImplMessages.FIREARM_NOT_EXISTENT_MESSAGE.getMessage());
            }
        } while (!isFireArmExistent);
    }

    private void testAmount() {
        resetTriesService();
        boolean isAmountAvailable;
        do {
            System.out.println(StringUtilsFireArmsServiceImplMessages.INSERT_AMOUNT_MESSAGE.getMessage());
            amountToSell = scanner.nextInt();
            isAmountAvailable = checkIfSelectedAmountIsAvailable(amountToSell, fireArmName);
            if (!isAmountAvailable) {
                triesValidation();
                System.out.println(StringUtilsFireArmsServiceImplMessages.SELECTED_AMOUNT_NOT_AVAILABLE_MESSAGE.getMessage());
            }
        } while (!isAmountAvailable);
    }
}
