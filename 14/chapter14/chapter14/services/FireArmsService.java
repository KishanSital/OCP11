package chapter14.services;

import chapter14.models.FireArmModel;
import mypackage.annotations.Service;
import java.util.*;

@Service
public interface FireArmsService {
     List<FireArmModel.FireArmSpecification> getFireArms();
     List<FireArmModel.FireArmSpecification> getSoldFireArms();
     void insertStandardFireArms();
     void sellFireArms();
     void displayAvailableFireArms();
     void displaySoldFireArms();
     long createTransactionId();
     void displayTransactionDetails();
     Map<Long, FireArmModel.FireArmSpecification> getAfterServiceList();

}
