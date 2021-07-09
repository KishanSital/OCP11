package chapter18.views;

import chapter18.models.*;
import chapter18.services.*;
import chapter18.utils.*;

import java.text.*;
import java.util.*;
import java.util.function.*;

import static mypackage.serviceImpl.TriesValidationServiceImpl.resetTriesService;
import static mypackage.serviceImpl.TriesValidationServiceImpl.triesValidation;


public class FireArmsView {

    private final Scanner scanner = new Scanner(System.in);
    private FireArmsService fireArmsService;
    // instance method reference on parameter
    public Consumer<FireArmModel.FireArmSpecification> printOutFireArm = System.out::println;
    //static method reference
    public Consumer<List<FireArmModel.FireArmSpecification>> sortList = Collections::sort;


    public FireArmsView(FireArmsService fireArmsService) {
        this.fireArmsService = fireArmsService;
    }

    public void sellFireArms() {
        if (fireArmsService.getFireArms().isEmpty()) {
            System.out.println(StringUtilsFireArmsServiceImplMessages.OUT_OF_STOCK_MESSAGE.getMessage());
        } else {
            testName();
            testAmount();
            System.out.println(fireArmsService.updateFireArms());
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
            if (!isKeyPresent) {
                System.out.println(StringUtilsFireArmsServiceImplMessages.TRANSACTION_NOT_EXISTENT_MESSAGE.getMessage());
            } else {
                System.out.println(fireArmsService.getAfterServiceList().get(key));
            }
        } while (!isKeyPresent);
    }


    private void testName() {
        resetTriesService();
        boolean isFireArmExistent;
        do {
            System.out.println(StringUtilsFireArmsServiceImplMessages.INSERT_NAME_MESSAGE.getMessage());
            fireArmsService.setFireArmName(scanner.next());
            isFireArmExistent = fireArmsService.checkIfFireArmExists(fireArmsService.getFireArmName());
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
            isAmountAvailable = fireArmsService.checkIfSelectedAmountIsAvailable(fireArmsService.getAmountToSell(),
                    fireArmsService.getFireArmName());
            if (!isAmountAvailable) {
                triesValidation();
                System.out.println(StringUtilsFireArmsServiceImplMessages.SELECTED_AMOUNT_NOT_AVAILABLE_MESSAGE.getMessage());
            }
        } while (!isAmountAvailable);
    }

    public void displayAvailableFireArms() {
        if (!fireArmsService.getFireArms().isEmpty()) {
            sortList.accept(fireArmsService.getFireArms());
            System.out.println(StringUtilsFireArmsServiceImplMessages.IN_STOCK_MESSAGE.getMessage());
            for (var fireArm : fireArmsService.getFireArms()) {
                printOutFireArm.accept(fireArm);
            }
        } else {
            System.out.println(StringUtilsFireArmsServiceImplMessages.OUT_OF_STOCK_MESSAGE.getMessage());
        }
    }

    //NumberFormat
    public void displaySoldFireArms() {
        if (!fireArmsService.getSoldFireArms().isEmpty()) {
            sortList.accept(fireArmsService.getSoldFireArms());
            System.out.println(StringUtilsFireArmsServiceImplMessages.SOLD_FIREARM_MESSAGE.getMessage());
            for (var fireArm : fireArmsService.getSoldFireArms()) {
                printOutFireArm.accept(fireArm);
            }
            System.out.println(StringUtilsFireArmsServiceImplMessages.TOTAL_SOLD_PRICE_MESSAGE.getMessage() + "= " + NumberFormat.getCurrencyInstance().format(fireArmsService.getTotalSoldPrice()));// NumberFormat
        } else {
            System.out.println(StringUtilsFireArmsServiceImplMessages.NOTHING_SOLD_MESSAGE.getMessage());
        }

    }

    public void testGuns() {
        System.out.println("Enter the amount of people that need to fire a single shot on a single zombie target");
        int person = scanner.nextInt();
        fireArmsService.testGuns(person);

    }

    public void cleanTheGuns() {
        System.out.println("Enter the amount of guns that need to be cleaned in a parallel fashion");
        int gunsCount = scanner.nextInt();
        System.out.println("How many people will we use for this task\nKeep in mind that one person may only work on a single firearm");
        int workersCount = scanner.nextInt();
        fireArmsService.startCleaning(gunsCount, workersCount);
    }
}
