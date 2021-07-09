package chapter21.views;

import chapter21.models.FireArmModel;
import chapter21.services.FireArmsService;
import chapter21.utils.StringUtilsFireArmsServiceImplMessages;

import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

import static mypackage.serviceImpl.TriesValidationServiceImpl.resetTriesService;
import static mypackage.serviceImpl.TriesValidationServiceImpl.triesValidation;


public final class FireArmsView implements Cloneable{

    private Scanner scanner = new Scanner(System.in);
    private FireArmsService fireArmsService;
    // instance method reference on parameter
    private Consumer<FireArmModel<Long,String,Integer,Double>.FireArmSpecification> printOutFireArm= System.out::println;
    //static method reference
    private Consumer<List<FireArmModel<Long,String,Integer,Double>.FireArmSpecification>> sortList = Collections::sort;

    public FireArmsView(FireArmsService fireArmsService) {

        if (fireArmsService == null){
            throw new NullPointerException("Please provide an implementation of FireArmsService");
        }
        this.fireArmsService = fireArmsService;
    }


    public Object clone() throws CloneNotSupportedException {
        FireArmsView fireArmsView = (FireArmsView) super.clone();
        return fireArmsView;
    }

    public void sellFireArms() {
        if (fireArmsService.getFireArms().isEmpty()) {
            System.out.println(StringUtilsFireArmsServiceImplMessages.OUT_OF_STOCK_MESSAGE.getMessage());
        } else {
            testName();
            testAmount();
            try {
                System.out.println(fireArmsService.updateFireArms());
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
    }

    public void displayTransactionDetails() {
        if (fireArmsService.getAfterServiceList().isEmpty()) {
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
                isKeyPresent = false;
                scanner.next();
                continue;
            }
            isKeyPresent = fireArmsService.keyExistenceValidation(key);
            if (!isKeyPresent){
                System.out.println(StringUtilsFireArmsServiceImplMessages.TRANSACTION_NOT_EXISTENT_MESSAGE.getMessage());
            } else {
                System.out.println(fireArmsService.getAfterServiceList().get(key));
            }
        } while (!isKeyPresent);
    }


    private void testName() {
        resetTriesService();
        boolean isFireArmExistent = false;
        do {
            System.out.println(StringUtilsFireArmsServiceImplMessages.INSERT_NAME_MESSAGE.getMessage());
            fireArmsService.setFireArmName(scanner.next());
            try {
                isFireArmExistent = fireArmsService.checkIfFireArmExists(fireArmsService.getFireArmName());
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
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
            fireArmsService.setAmountToSell(scanner.nextInt());
            isAmountAvailable = fireArmsService.checkIfSelectedAmountIsAvailable(fireArmsService.getAmountToSell());
            if (!isAmountAvailable) {
                triesValidation();
                System.out.println(StringUtilsFireArmsServiceImplMessages.SELECTED_AMOUNT_NOT_AVAILABLE_MESSAGE.getMessage());
            }
        } while (!isAmountAvailable);
    }

    public void displayAvailableFireArms() {
        if (!fireArmsService.getFireArms().isEmpty()){
            sortList.accept(fireArmsService.getFireArms());
            System.out.println(StringUtilsFireArmsServiceImplMessages.IN_STOCK_MESSAGE.getMessage());
            for (var fireArm:fireArmsService.getFireArms()){
                printOutFireArm.accept(fireArm);
            }
        } else {
            System.out.println(StringUtilsFireArmsServiceImplMessages.OUT_OF_STOCK_MESSAGE.getMessage());
        }
    }

    //NumberFormat
    public void displaySoldFireArms() {
        if (!fireArmsService.getSoldFireArms().isEmpty()){
            sortList.accept(fireArmsService.getSoldFireArms());
            System.out.println(StringUtilsFireArmsServiceImplMessages.SOLD_FIREARM_MESSAGE.getMessage());
            for (var fireArm: fireArmsService.getSoldFireArms()) {
                printOutFireArm.accept(fireArm);
            }
        } else {
            System.out.println(StringUtilsFireArmsServiceImplMessages.NOTHING_SOLD_MESSAGE.getMessage());
        }

    }
}
