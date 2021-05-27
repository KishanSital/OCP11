package chapter12_13.serviceImpl;

import chapter12_13.models.*;
import chapter12_13.repositories.*;
import chapter12_13.services.*;
import chapter12_13.utils.*;
import mypackage.utils.*;

import static mypackage.serviceImpl.TriesValidationServiceImpl.*;

import java.util.*;
import java.util.function.*;

public class FireArmsServiceImpl extends FireArmsRepository implements FireArmsService {

    private Scanner scanner = new Scanner(System.in);
    private final int lowestAmount = 0;
    private int availableAmountForSelectedFireArm = 0;
    private int amountToSell = 0;
    private int iteration = 0;
    private String fireArmName;
    private double totalSoldPrice = 0;
    private Consumer<FireArmModel.FireArmSpecification> printOutFireArm = fireArm -> System.out.println("Firearm:\n" +
            "firearm id = " + fireArm.getFireArmId() + "\n" +
            "firearm category = " + fireArm.getFireArmCategory() + "\n" +
            "firearm name = " + fireArm.getFireArmName() + "\n" +
            "amount = " +  fireArm.getStockAmount() +"\n"+
            "price per item  = $" +  fireArm.getPricePerItem() +"\n"+
            "Firearm specification:\n" +
            "caliber = " + fireArm.getCaliber() + "\n" +
            "weight without magazine = " + fireArm.getWeightWithoutMagazine() + "\n" +
            "weight with empty magazine = " + fireArm.getWeightWithEmptyMagazine() + "\n" +
            "weight with loaded magazine = " + fireArm.getWeightWithLoadedMagazine() + "\n" +
            "magazine capacity = " + fireArm.getMagazineCapacity() + "\n"+
            "barrel length = " + fireArm.getBarrelLength() + "\n" +
            "trigger pull = " + fireArm.getTriggerPull() + "\n");



    public FireArmsServiceImpl (){
        super();
    }

    @Override
    public List<FireArmModel.FireArmSpecification> getFireArms() {
        return fireArms;
    }

    @Override
    public List<FireArmModel.FireArmSpecification> getSoldFireArms() {
        return soldFireArms;
    }


    @Override
    public void insertStandardFireArms() {
        getFireArms().add(new FireArmModel(1L,
                (StringUtilsFireArmsCategory.HAND_GUNS.getCategoryName()),
                "GLOCK-19",
                5, 325.00).new FireArmSpecification("9x19mm",
                "600g",
                "670g",
                "855g",
                30,
                "102mm",
                "28N"));

        getFireArms().add(new FireArmModel(2L,
                (StringUtilsFireArmsCategory.LONG_GUNS.getCategoryName()),
                "AK-47",
                5,1288.46).new FireArmSpecification("7.62x39mm",
                "3.47 Kg",
                "3.90 Kg",
                "4.39 Kg",
                30,
                "369 mm",
                "17.7 N"));
    }

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

    private void updateFireArms() {
        UpdatingSoldFireArms();
        UpdatingStorageFireArms();
    }

    private void UpdatingStorageFireArms() {
        getFireArms().get(iteration).setStockAmount(availableAmountForSelectedFireArm - amountToSell);
        if (getFireArms().get(iteration).getStockAmount() == lowestAmount){
            getFireArms().remove(iteration);
        }
    }

    private void UpdatingSoldFireArms() {
        int iterationSold = 0;
        boolean listContainsSelectedFireArm = false;
        if (!getSoldFireArms().isEmpty()){
            for (var fireArmsSold:getSoldFireArms()){
                if (fireArmsSold.getFireArmName().equals(fireArmName)){
                    listContainsSelectedFireArm = true;
                    break;
                }
                iterationSold++;
            }
        }
        if (listContainsSelectedFireArm){
            UpdateExistingSoldFireArm(iterationSold);
        } else{
            addNewSoldItemToList();
        }
        totalSoldPrice+=(getFireArms().get(iteration).getPricePerItem() * amountToSell);
    }

    private void addNewSoldItemToList() {
        getSoldFireArms().add(new FireArmModel
                (getFireArms().get(iteration).getFireArmId(),
                getFireArms().get(iteration).getFireArmCategory(),
                getFireArms().get(iteration).getFireArmName(),
                amountToSell,
                getFireArms().get(iteration).getPricePerItem())
                .new FireArmSpecification
                (getFireArms().get(iteration).getCaliber(),
                getFireArms().get(iteration).getWeightWithoutMagazine(),
                getFireArms().get(iteration).getWeightWithEmptyMagazine(),
                getFireArms().get(iteration).getWeightWithLoadedMagazine(),
                getFireArms().get(iteration).getMagazineCapacity(),
                getFireArms().get(iteration).getBarrelLength(),
                getFireArms().get(iteration).getTriggerPull())
        );
    }
    private void UpdateExistingSoldFireArm(int iterationSold) {
        getSoldFireArms().get(iterationSold).setStockAmount
        (getSoldFireArms().get(iterationSold).getStockAmount()+amountToSell);
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

    private boolean checkIfSelectedAmountIsAvailable(int amountToSell, String fireArmName) {
         iteration = 0;
        for (var fireArm: getFireArms()) {
           if (fireArm.getFireArmName().equals(fireArmName)
               && fireArm.getStockAmount() >= amountToSell
               && amountToSell > lowestAmount){
               availableAmountForSelectedFireArm = getFireArms().get(iteration).getStockAmount();
                return true;
            }
            iteration++;
        }
        return false;
    }


    private boolean checkIfFireArmExists(String fireArmName) {
        for (int i = 0; i < getFireArms().size(); i++){
            if (getFireArms().get(i).getFireArmName().equals(fireArmName)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void displayAvailableFireArms() {
        if (!getFireArms().isEmpty()){
            System.out.println(StringUtilsFireArmsServiceImplMessages.IN_STOCK_MESSAGE.getMessage());
            for (var fireArm:getFireArms()){
                printOutFireArm.accept(fireArm);
            }
        } else {
            System.out.println(StringUtilsFireArmsServiceImplMessages.OUT_OF_STOCK_MESSAGE.getMessage());
        }
    }

    @Override
    public void displaySoldFireArms() {
        if (!getSoldFireArms().isEmpty()){
            System.out.println(StringUtilsFireArmsServiceImplMessages.SOLD_FIREARM_MESSAGE.getMessage());
            for (var fireArm: getSoldFireArms()) {
                printOutFireArm.accept(fireArm);
            }
            System.out.println("Total sold price = $" + totalSoldPrice);
        } else {
            System.out.println(StringUtilsFireArmsServiceImplMessages.NOTHING_SOLD_MESSAGE.getMessage());
        }

    }

}
