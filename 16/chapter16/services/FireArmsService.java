package chapter16.services;

import chapter16.models.FireArmModel;
import chapter16.annotations.Service;

import java.util.List;
import java.util.Map;

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
}
