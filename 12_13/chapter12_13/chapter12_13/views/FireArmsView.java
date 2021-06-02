package chapter12_13.views;

import chapter12_13.serviceImpl.*;
import chapter12_13.utils.*;
import mypackage.utils.*;

import java.util.Scanner;

import static mypackage.serviceImpl.TriesValidationServiceImpl.*;

public class FireArmsView extends FireArmsServiceImpl {

    private Scanner scanner = new Scanner(System.in);

    @Override
    public void sellFireArms() {
        if (getFireArms().isEmpty()){
            System.out.println(StringUtilsFireArmsServiceImplMessages.OUT_OF_STOCK_MESSAGE.getMessage());
        }else {
            testName();
            testAmount();
            updateFireArms();
            System.out.println(StringUtilsMyPackage.OPERATION_SUCCESSFUL_MESSAGE.getStringValue());
        }
    }
    private void testName() {
        resetTriesService();
        boolean isFireArmExistent;
        do {
            System.out.println(StringUtilsFireArmsServiceImplMessages.INSERT_NAME_MESSAGE.getMessage());
            fireArmName = scanner.next();
            isFireArmExistent = checkIfFireArmExists(fireArmName);
            if (!isFireArmExistent){
                triesValidation();
                System.out.println(StringUtilsFireArmsServiceImplMessages.FIREARM_NOT_EXISTENT_MESSAGE.getMessage());
            }
        } while(!isFireArmExistent);
    }
    private void testAmount() {
        resetTriesService();
        boolean isAmountAvailable;
        do {
            System.out.println(StringUtilsFireArmsServiceImplMessages.INSERT_AMOUNT_MESSAGE.getMessage());
            amountToSell = scanner.nextInt();
            isAmountAvailable = checkIfSelectedAmountIsAvailable(amountToSell, fireArmName);
            if (!isAmountAvailable){
                triesValidation();
                System.out.println(StringUtilsFireArmsServiceImplMessages.SELECTED_AMOUNT_NOT_AVAILABLE_MESSAGE.getMessage());
            }
        } while (!isAmountAvailable);
    }
}
