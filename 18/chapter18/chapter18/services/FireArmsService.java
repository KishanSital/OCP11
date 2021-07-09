package chapter18.services;

import chapter18.models.*;
import mypackage.annotations.*;
import java.util.*;

@Service
public interface FireArmsService {
    List<FireArmModel.FireArmSpecification> getFireArms();

    List<FireArmModel.FireArmSpecification> getSoldFireArms();

    long createTransactionId();

    Map<Long, FireArmModel.FireArmSpecification> getAfterServiceList();

    String updateFireArms();

    String getFireArmName();

    void setFireArmName(String fireArmName);

    boolean checkIfSelectedAmountIsAvailable(int amountToSell, String fireArmName);

    boolean checkIfFireArmExists(String fireArmName);

    int getAmountToSell();

    void setAmountToSell(int amountToSell);

    double getTotalSoldPrice();

    boolean keyExistenceValidation(long key);

    void testGuns(int amountOfPersons);

    void startCleaning(int gunsCount, int workersCount);
}
