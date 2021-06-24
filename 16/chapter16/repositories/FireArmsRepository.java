package chapter16.repositories;

import chapter16.models.FireArmModel;
import chapter16.utils.StringUtilsFireArmsCategory;

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
        insertStandardFireArms();
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

    public void insertStandardFireArms() {
        getFireArmsData().add(new FireArmModel(1L,
                (StringUtilsFireArmsCategory.HAND_GUNS.getCategoryName()),
                "GLOCK-19",
                5, 325.00).new FireArmSpecification("9x19mm",
                "600g",
                "670g",
                "855g",
                30,
                "102mm",
                "28N"));

        getFireArmsData().add(new FireArmModel(2L,
                (StringUtilsFireArmsCategory.LONG_GUNS.getCategoryName()),
                "AK-47",
                5, 1288.46).new FireArmSpecification("7.62x39mm",
                "3.47 Kg",
                "3.90 Kg",
                "4.39 Kg",
                30,
                "369 mm",
                "17.7 N"));
    }
}
