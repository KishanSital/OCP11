package chapter21.services;

import chapter21.models.FireArmModel;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface FireArmsRepositoryService {
    List<FireArmModel<Long, String, Integer, Double>.FireArmSpecification> getFireArmsData();

    List<FireArmModel<Long, String, Integer, Double>.FireArmSpecification> getSoldFireArmsData();

    Map<Long, FireArmModel<Long, String, Integer, Double>.FireArmSpecification> getAfterServiceData();

    int[] insertFireArms(FireArmModel<Long, String, Integer, Double>.FireArmSpecification... fireArms) throws SQLException;

    FireArmModel<Long, String, Integer, Double>.FireArmSpecification findFireArm(String fireArmName) throws SQLException;

    int insertSoldFireArm(FireArmModel<Long, String, Integer, Double>.FireArmSpecification fireArm, Long transactionId) throws SQLException;

    int updateStockAmount(FireArmModel<Long, String, Integer, Double>.FireArmSpecification fireArm,
                          int availableAmount) throws SQLException;
}
