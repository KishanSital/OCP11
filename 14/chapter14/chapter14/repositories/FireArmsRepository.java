package chapter14.repositories;
import chapter14.models.*;
import java.util.*;
import java.util.function.*;

public class FireArmsRepository {

    protected List <?> fireArmsList = fireArms.get();// unbounded wildcard
    protected List<?> soldFireArmsList = fireArms.get(); //  unbounded wildcard
    protected Map<?,? extends FireArmModel.FireArmSpecification> afterSaleServiceList = afterSaleService.get();// unbounded and upper bound

    // Constructor method reference
    private static final Supplier<List<?>> fireArms = ArrayList::new; // unbounded wildcard
    private static final Supplier<Map<?,FireArmModel.FireArmSpecification>> afterSaleService = TreeMap::new;
    //                              unbounded
    protected FireArmsRepository (){
    }
}
