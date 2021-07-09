package chapter18.serviceImpl;


import chapter18.models.*;
import chapter18.repositories.*;
import chapter18.services.*;
import chapter18.utils.*;
import mypackage.utils.*;

import java.util.*;

import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

import java.util.function.*;

import static mypackage.serviceImpl.TriesValidationServiceImpl.*;


public class FireArmsServiceImpl implements FireArmsService {

    private final int lowestAmount = 0;
    private int availableAmountForSelectedFireArm = 0;
    private int amountToSell = 0;
    private String fireArmName;
    // atomic integer class
    private AtomicInteger iteration = new AtomicInteger(0);
    private double totalSoldPrice = 0;
    private Long currentTimeStamp;
    public Supplier<Date> date;
    public Supplier<Long> transactionTimeStamp;
    private FireArmsRepository fireArmsRepository;
    private ExecutorService shootingService;
    private ExecutorService cleaningService;
    private int person = 0;


    public FireArmsServiceImpl() {
        super();
    }

    public FireArmsServiceImpl(FireArmsRepository fireArmsRepository) {
        this.fireArmsRepository = fireArmsRepository;
    }

    //synchronized collections
    @Override
    public List<FireArmModel.FireArmSpecification> getFireArms() {
        return Collections.synchronizedList(fireArmsRepository.getFireArmsData());
    }

    @Override
    public List<FireArmModel.FireArmSpecification> getSoldFireArms() {
        return  Collections.synchronizedList(fireArmsRepository.getSoldFireArmsData());
    }

    @Override
    public Map<Long, FireArmModel.FireArmSpecification> getAfterServiceList() {
        return Collections.synchronizedMap(fireArmsRepository.getAfterServiceData());
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
        getFireArms().get(iteration.get()).setStockAmount(availableAmountForSelectedFireArm - amountToSell);
        if ((Integer) getFireArms().get(iteration.get()).getStockAmount() == lowestAmount) {
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
                + currentTimeStamp);
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
        totalSoldPrice += ((Double) getFireArms().get(iteration.get()).getPricePerItem() * amountToSell);
        getAfterServiceList().put(createTransactionId(), addNewFireArm());
    }

    private FireArmModel.FireArmSpecification addNewFireArm() {
        return new FireArmModel
                (getFireArms().get(iteration.get()).getFireArmId(),
                        getFireArms().get(iteration.get()).getFireArmCategory(),
                        getFireArms().get(iteration.get()).getFireArmName(),
                        amountToSell,
                        getFireArms().get(iteration.get()).getPricePerItem())
                .new FireArmSpecification
                (getFireArms().get(iteration.get()).getCaliber(),
                        getFireArms().get(iteration.get()).getWeightWithoutMagazine(),
                        getFireArms().get(iteration.get()).getWeightWithEmptyMagazine(),
                        getFireArms().get(iteration.get()).getWeightWithLoadedMagazine(),
                        getFireArms().get(iteration.get()).getMagazineCapacity(),
                        getFireArms().get(iteration.get()).getBarrelLength(),
                        getFireArms().get(iteration.get()).getTriggerPull());
    }

    private boolean addNewSoldItemToList() {
        return getSoldFireArms().add(addNewFireArm());
    }

    private void UpdateExistingSoldFireArm(int iterationSold) {
        getSoldFireArms().get(iterationSold).setStockAmount
                ((Integer) getSoldFireArms().get(iterationSold).getStockAmount() + amountToSell);
    }


    public boolean checkIfSelectedAmountIsAvailable(int amountToSell, String fireArmName) {
        iteration = new AtomicInteger(0);
        for (var fireArm : getFireArms()) {
            if (fireArm.getFireArmName().equals(fireArmName)
                    && (Integer) (fireArm.getStockAmount()) >= amountToSell
                    && amountToSell > lowestAmount) {
                availableAmountForSelectedFireArm = (Integer) (getFireArms().get(iteration.get()).getStockAmount());
                return true;
            }
            iteration.getAndIncrement();
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

    @Override
    public void testGuns(int amountOfPersons) {
        // creation of a thread pool with a fixed amount of threads
        try {
            shootingService = Executors.newFixedThreadPool(amountOfPersons);
            for (int shotsFired = 0; shotsFired < amountOfPersons; shotsFired++) {
                shootingService.submit(this::fireThoseGuns);
            }
        } finally {
            shootingService.shutdown();
            changingZombiePoster();
        }

    }


    private void changingZombiePoster() {
        while (!shootingService.isTerminated()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        person = 0;
    }

    // people have to wait till there's an available spot at the shooting range (our monitor)
    private synchronized void fireThoseGuns() {
        System.out.println("person " + ++person + " has now fired on the zombie target");

    }

    private void cleanBarrel() {
        System.out.println("Cleaned barrel");
    }

    private void cleanStock() {
        System.out.println("Cleaned stock");
    }

    private void storingFireArm() {
        System.out.println("storing firearm");
    }

    private void performCleaningTask(CyclicBarrier barrelCyclicBarrier, CyclicBarrier stockCyclicBarrier) {
        try {
            cleanBarrel();
            barrelCyclicBarrier.await();
            cleanStock();
            stockCyclicBarrier.await();
            storingFireArm();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void startCleaning(int gunsCount, int workersCount) {
        cleaningService = null;
        try {
            cleaningService = Executors.newFixedThreadPool(workersCount);
            var barrelCyclicBarrier = new CyclicBarrier(gunsCount);
            var stockCyclicBarrier = new CyclicBarrier(gunsCount, () -> System.out.println("All guns have been cleaned"));
            for (int countingGuns = 0; countingGuns < gunsCount; countingGuns++) {
                cleaningService.submit(() -> performCleaningTask(barrelCyclicBarrier, stockCyclicBarrier));
            }
        } finally {
            cleaningService.shutdown();
            placeCleanedGunsInStorage();

        }

    }

    private void placeCleanedGunsInStorage() {
        while (!cleaningService.isTerminated()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}



