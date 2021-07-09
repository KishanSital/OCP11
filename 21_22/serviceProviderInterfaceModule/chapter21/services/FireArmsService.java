package chapter21.services;

import chapter21.models.FireArmModel;
import mypackage.annotations.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Service
public interface FireArmsService {
    List<FireArmModel<Long, String, Integer, Double>.FireArmSpecification>  getFireArms();

    List<FireArmModel<Long, String, Integer, Double>.FireArmSpecification> getSoldFireArms();

    long createTransactionId();

    Map<Long, FireArmModel<Long, String, Integer, Double>.FireArmSpecification> getAfterServiceList();

    String updateFireArms() throws SQLException;

    String getFireArmName();

    void setFireArmName(String fireArmName);

    boolean  checkIfSelectedAmountIsAvailable(int amountToSell) ;

    boolean checkIfFireArmExists(String fireArmName) throws SQLException;

    int getAmountToSell();

    void setAmountToSell(int amountToSell);

    boolean keyExistenceValidation(long key);

     int[] insertFireArms(FireArmModel<Long, String, Integer, Double>.FireArmSpecification... fireArms) throws SQLException;

}
