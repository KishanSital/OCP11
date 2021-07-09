package chapter19.serviceImpl;

import chapter19.models.FireArmModel;
import chapter19.repositories.FireArmsRepository;
import chapter19.services.DataFilesService;
import chapter19.services.FireArmsService;
import chapter19.utils.StringUtilsFireArmsServiceImplMessages;
import mypackage.utils.StringUtilsMyPackage;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static mypackage.serviceImpl.TriesValidationServiceImpl.triesValidation;


public class FireArmsServiceImpl implements FireArmsService {

    private final int lowestAmount = 0;
    private int availableAmountForSelectedFireArm = 0;
    private int amountToSell = 0;
    private String fireArmName;
    private int iteration = 0;
    private double totalSoldPrice = 0;
    private Long currentTimeStamp;
    public Supplier<Date> date;
    public Supplier<Long> transactionTimeStamp;
    private FireArmsRepository fireArmsRepository;
    private DataFilesService dataFilesService;
    private File receiptDirectory;


    public FireArmsServiceImpl() {
        super();
    }

    public FireArmsServiceImpl(FireArmsRepository fireArmsRepository, DataFilesService dataFilesService, File receiptDirectory) {
        this.fireArmsRepository = fireArmsRepository;
        this.dataFilesService = dataFilesService;
        this.receiptDirectory = receiptDirectory;

    }

    @Override
    public List<FireArmModel.FireArmSpecification> getFireArms() {
        return fireArmsRepository.getFireArmsData();
    }

    @Override
    public List<FireArmModel.FireArmSpecification> getSoldFireArms() {
        return fireArmsRepository.getSoldFireArmsData();
    }

    @Override
    public Map<Long, FireArmModel.FireArmSpecification> getAfterServiceList() {
        return fireArmsRepository.getAfterServiceData();
    }

    @Override
    public String updateFireArms() {
        UpdatingSoldFireArms();
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

    private String UpdatingStorageFireArms() {
        getFireArms().get(iteration).setStockAmount(availableAmountForSelectedFireArm - amountToSell);
        if ((Integer) getFireArms().get(iteration).getStockAmount() == lowestAmount) {
            getFireArms().remove(iteration);
        }
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
                + currentTimeStamp + "\n");
    }

    private void UpdatingSoldFireArms() {
        int iterationSold = 0;
        boolean listContainsSelectedFireArm = false;
        if (!getSoldFireArms().isEmpty()) {
            for (var fireArmsSold : getSoldFireArms()) {
                if (fireArmsSold.getFireArmName().equals(fireArmName)) {
                    listContainsSelectedFireArm = true;
                    break;
                }
                iterationSold++;
            }
        }
        if (listContainsSelectedFireArm) {
            UpdateExistingSoldFireArm(iterationSold);
        } else {
            addNewSoldItemToList();
        }
        totalSoldPrice += ((Double) getFireArms().get(iteration).getPricePerItem() * amountToSell);
        getAfterServiceList().put(createTransactionId(), addNewFireArm());
        createReceipt();
    }

    //creating receipts
    private void createReceipt() {
        try {
            File receiptLocation = new File(receiptDirectory, currentTimeStamp + ".txt");
            dataFilesService.createFile(receiptLocation);
            dataFilesService.writeDataUsingObjectOutputStream(getAfterServiceList().get(currentTimeStamp), receiptLocation);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FireArmModel.FireArmSpecification readReceipt(long transactionId) {
        File receiptLocation = new File(receiptDirectory, transactionId + ".txt");
        try {
            return dataFilesService.readFireArmUsingObjectInputStream(receiptLocation);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
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

    private boolean addNewSoldItemToList() {
        return getSoldFireArms().add(addNewFireArm());
    }

    private void UpdateExistingSoldFireArm(int iterationSold) {
        getSoldFireArms().get(iterationSold).setStockAmount
                ((Integer) getSoldFireArms().get(iterationSold).getStockAmount() + amountToSell);
    }


    public boolean checkIfSelectedAmountIsAvailable(int amountToSell, String fireArmName) {
        iteration = 0;
        for (var fireArm : getFireArms()) {
            if (fireArm.getFireArmName().equals(fireArmName)
                    && (Integer) (fireArm.getStockAmount()) >= amountToSell
                    && amountToSell > lowestAmount) {
                availableAmountForSelectedFireArm = (Integer) (getFireArms().get(iteration).getStockAmount());
                return true;
            }
            iteration++;
        }
        return false;
    }


    public boolean checkIfFireArmExists(String fireArmName) {
        for (int i = 0; i < getFireArms().size(); i++) {
            if (getFireArms().get(i).getFireArmName().equals(fireArmName)) {
                return true;
            }
        }
        return false;
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
    public double getTotalSoldPrice() {
        return totalSoldPrice;
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


}
