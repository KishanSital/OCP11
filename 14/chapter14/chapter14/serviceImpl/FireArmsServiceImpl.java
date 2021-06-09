package chapter14.serviceImpl;

import chapter14.models.*;
import chapter14.repositories.*;
import chapter14.services.*;
import chapter14.utils.*;
import mypackage.utils.*;

import java.util.*;
import java.util.function.*;

public abstract class FireArmsServiceImpl extends FireArmsRepository implements FireArmsService {

    private final int lowestAmount = 0;
    private int availableAmountForSelectedFireArm = 0;
    protected int amountToSell = 0;
    protected String fireArmName;
    private int iteration = 0;
    private double totalSoldPrice = 0;
    private Long currentTimeStamp;
    public Supplier<Date> date;
    public Supplier<Long> transactionTimeStamp;
    // instance method reference on parameter
    public Consumer<FireArmModel.FireArmSpecification> printOutFireArm= System.out::println;
    //static method reference
    public Consumer<List<FireArmModel.FireArmSpecification>> sortList = Collections::sort;

    public FireArmsServiceImpl (){
        super();
    }


    @Override
    public List<FireArmModel.FireArmSpecification> getFireArms() {
        return (List<FireArmModel.FireArmSpecification>) fireArmsList;
    }

    @Override
    public List<FireArmModel.FireArmSpecification> getSoldFireArms() {
        return (List<FireArmModel.FireArmSpecification>) soldFireArmsList;
    }
    @Override
    public Map<Long, FireArmModel.FireArmSpecification> getAfterServiceList(){
        return (Map<Long, FireArmModel.FireArmSpecification>) afterSaleServiceList;
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



    protected void updateFireArms() {
        UpdatingSoldFireArms();
        UpdatingStorageFireArms();
    }

    private void UpdatingStorageFireArms() {
        getFireArms().get(iteration).setStockAmount(availableAmountForSelectedFireArm - amountToSell);
        if ( (Integer) getFireArms().get(iteration).getStockAmount() == lowestAmount){
            getFireArms().remove(iteration);
        }
        transactionInfo();
    }

    @Override
    public long createTransactionId() {
        //constructor reference
        date = Date::new;
        // instance method reference on an a particular object
        transactionTimeStamp = date.get()::getTime;
        currentTimeStamp = transactionTimeStamp.get();
        return currentTimeStamp;
    }

    private void transactionInfo() {
        System.out.println(StringUtilsMyPackage.OPERATION_SUCCESSFUL_MESSAGE.getStringValue()
                + StringUtilsFireArmsServiceImplMessages.TRANSACTION_TIME_STAMP.getMessage()
                + currentTimeStamp);
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
        totalSoldPrice+=((Double)getFireArms().get(iteration).getPricePerItem() * amountToSell);
        getAfterServiceList().put(createTransactionId(), addNewFireArm());
    }

    private FireArmModel.FireArmSpecification addNewFireArm() {
        return new FireArmModel
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
                        getFireArms().get(iteration).getTriggerPull());
    }

    private void addNewSoldItemToList() {
        getSoldFireArms().add(addNewFireArm());
    }
    private void UpdateExistingSoldFireArm(int iterationSold) {
        getSoldFireArms().get(iterationSold).setStockAmount
        ((Integer)getSoldFireArms().get(iterationSold).getStockAmount()+amountToSell);
    }


    protected boolean checkIfSelectedAmountIsAvailable(int amountToSell, String fireArmName) {
         iteration = 0;
        for (var fireArm: getFireArms()) {
           if (fireArm.getFireArmName().equals(fireArmName)
               && (Integer)(fireArm.getStockAmount()) >= amountToSell
               && amountToSell > lowestAmount){
               availableAmountForSelectedFireArm = (Integer)(getFireArms().get(iteration).getStockAmount());
                return true;
            }
            iteration++;
        }
        return false;
    }


    protected boolean checkIfFireArmExists(String fireArmName) {
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
            sortList.accept(getFireArms());
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
            sortList.accept(getSoldFireArms());
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
