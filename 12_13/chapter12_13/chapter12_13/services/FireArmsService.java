package chapter12_13.services;

import chapter12_13.models.*;
import mypackage.annotations.*;
import java.util.List;

@Service
public interface FireArmsService {
     List<FireArmModel.FireArmSpecification> getFireArms();
     List<FireArmModel.FireArmSpecification> getSoldFireArms();
     void insertStandardFireArms();
     void sellFireArms();
     void displayAvailableFireArms();
     void displaySoldFireArms();

}
