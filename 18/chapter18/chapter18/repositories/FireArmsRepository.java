package chapter18.repositories;

import chapter18.models.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;

public class FireArmsRepository {

    //usage of concurrent collection classes
    // Constructor method reference
    private static final Supplier<List<?>> fireArms = CopyOnWriteArrayList::new; // unbounded wildcard
    private static final Supplier<Map<?, FireArmModel.FireArmSpecification>> afterSaleService = ConcurrentSkipListMap::new;
    private List<?> fireArmsList = fireArms.get();// unbounded wildcard
    private List<?> soldFireArmsList = fireArms.get(); // wildcard with a lower bounded
    private Map<?, ? extends FireArmModel.FireArmSpecification> afterSaleServiceList = afterSaleService.get();// unbounded and lower bound
    //                              unbounded

    public FireArmsRepository() {
        super();
        //insertStandardFireArms();
    }

    public List<FireArmModel.FireArmSpecification> getFireArmsData() {
        return (List<FireArmModel.FireArmSpecification>) fireArmsList;
    }

    public List<FireArmModel.FireArmSpecification> getSoldFireArmsData() {
        return (List<FireArmModel.FireArmSpecification>) soldFireArmsList;
    }

    public Map<Long, FireArmModel.FireArmSpecification> getAfterServiceData() {
        return (Map<Long, FireArmModel.FireArmSpecification>) afterSaleServiceList;
    }

}
