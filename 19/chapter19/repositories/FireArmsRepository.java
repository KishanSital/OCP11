package chapter19.repositories;

import chapter19.models.FireArmModel;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Supplier;

public class FireArmsRepository {

    // Constructor method reference
    private static final Supplier<List<?>> fireArms = ArrayList::new; // unbounded wildcard
    private static final Supplier<Map<?, FireArmModel.FireArmSpecification>> afterSaleService = TreeMap::new;
    private List<?> fireArmsList = fireArms.get();// unbounded wildcard
    private List<?> soldFireArmsList = fireArms.get(); // wildcard with a lower bounded
    private Map<?, ? extends FireArmModel.FireArmSpecification> afterSaleServiceList = afterSaleService.get();// unbounded and lower bound
    //                              unbounded

    public FireArmsRepository() {
        super();
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
