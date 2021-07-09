package chapter21.serviceImpl;


import chapter21.models.FireArmModel;
import chapter21.services.FireArmsRepositoryService;
import chapter21.services.FireArmsService;
import chapter21.utils.StringUtilsFireArmsServiceImplMessages;
import mypackage.utils.StringUtilsMyPackage;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static mypackage.serviceImpl.TriesValidationServiceImpl.triesValidation;


public final class FireArmsServiceImpl implements FireArmsService {

    private final int lowestAmount = 0;
    private int availableAmountForSelectedFireArm = 0;
    private int amountToSell = 0;
    private String fireArmName;
    private Long currentTimeStamp;
    public Supplier<Date> date;
    public Supplier<Long> transactionTimeStamp;
    private FireArmsRepositoryService fireArmsRepositoryService;

    private FireArmModel.FireArmSpecification foundFireArm;


    public FireArmsServiceImpl() {
        super();
    }

    public FireArmsServiceImpl(FireArmsRepositoryService fireArmsRepositoryService) {

        if (fireArmsRepositoryService == null){
            throw new NullPointerException("Please provide an implementation of FireArmsRepositoryService ");
        }
        this.fireArmsRepositoryService = fireArmsRepositoryService;
    }

    @Override
    public List<FireArmModel<Long, String, Integer, Double>.FireArmSpecification> getFireArms() {
        return fireArmsRepositoryService.getFireArmsData();
    }

    @Override
    public List<FireArmModel<Long, String, Integer, Double>.FireArmSpecification>  getSoldFireArms() {
        return fireArmsRepositoryService.getSoldFireArmsData();
    }

    @Override
    public Map<Long,FireArmModel<Long, String, Integer, Double>.FireArmSpecification> getAfterServiceList() {
        return fireArmsRepositoryService.getAfterServiceData();
    }

    @Override
    public String updateFireArms() throws SQLException {
        insertingSoldFireArms();
        return UpdatingStorageFireArms();
    }

    @Override
    public String getFireArmName() {
        return fireArmName;
    }

    @Override
    public void setFireArmName(String fireArmName) {
        this.fireArmName = fireArmName;
    }

    private String UpdatingStorageFireArms() throws SQLException{
        int availableAmount = availableAmountForSelectedFireArm - amountToSell;
        fireArmsRepositoryService.updateStockAmount(foundFireArm, availableAmount);
        return transactionInfo();
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

    private String transactionInfo() {
        return (StringUtilsMyPackage.OPERATION_SUCCESSFUL_MESSAGE.getStringValue()
                + StringUtilsFireArmsServiceImplMessages.TRANSACTION_TIME_STAMP.getMessage()
                + currentTimeStamp);
    }

    private void insertingSoldFireArms() throws SQLException{
        fireArmsRepositoryService.insertSoldFireArm(addNewFireArm(),createTransactionId());
    }

    private FireArmModel.FireArmSpecification addNewFireArm() {
        return new FireArmModel
                (foundFireArm.getFireArmId(),
                        foundFireArm.getFireArmCategory(),
                        foundFireArm.getFireArmName(),
                        amountToSell,
                        foundFireArm.getPricePerItem())
                .new FireArmSpecification
                (foundFireArm.getCaliber(),
                        foundFireArm.getWeightWithoutMagazine(),
                        foundFireArm.getWeightWithEmptyMagazine(),
                        foundFireArm.getWeightWithLoadedMagazine(),
                        foundFireArm.getMagazineCapacity(),
                        foundFireArm.getBarrelLength(),
                        foundFireArm.getTriggerPull());
    }



    public boolean checkIfSelectedAmountIsAvailable(int amountToSell) {
        if ((Integer) foundFireArm.getStockAmount() >= amountToSell && amountToSell > lowestAmount) {
            availableAmountForSelectedFireArm = (Integer) foundFireArm.getStockAmount();
            return true;
        } else {
            return false;
        }
    }


    public boolean checkIfFireArmExists(String fireArmName) throws SQLException {
        if ((foundFireArm = fireArmsRepositoryService.findFireArm(fireArmName)) != null) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public void setAmountToSell(int amountToSell) {
        this.amountToSell = amountToSell;
    }

    @Override
    public int getAmountToSell() {
        return amountToSell;
    }

    @Override
    public boolean keyExistenceValidation(long key) {
        boolean isKeyPresent;
        if (getAfterServiceList().containsKey(key)) {
            isKeyPresent = true;
        } else {
            triesValidation();
            isKeyPresent = false;
        }
        return isKeyPresent;
    }

    @Override
    public int[] insertFireArms(FireArmModel<Long, String, Integer, Double>.FireArmSpecification... fireArms) throws SQLException {
       return fireArmsRepositoryService.insertFireArms(fireArms);
    }


}
