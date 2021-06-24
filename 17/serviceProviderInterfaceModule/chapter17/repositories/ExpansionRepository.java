package chapter17.repositories;

import chapter17.models.ExpansionModel;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.function.Supplier;

public class ExpansionRepository {

    // Constructor method reference
    private static final Supplier<Queue<?>> todoExpansion = LinkedList::new; //unbounded wildcard
    private static final Supplier<Set<?>> doneExpansion = TreeSet::new;//unbounded wildcard
    public Queue<?> todoExpansionList = todoExpansion.get(); // unbounded wildcard
    public Set<?> doneExpansionList = doneExpansion.get(); //unbounded wildcard


    public ExpansionRepository() {
        super();
        insertStandardExpansionPlans();
    }

    public Queue<ExpansionModel> getTodoExpansionData() {
        return (Queue<ExpansionModel>) todoExpansionList;
    }

    public Set<ExpansionModel> getDoneExpansionData() {
        return (Set<ExpansionModel>) doneExpansionList;
    }

    //Usage of ZoneDateTime
    private void insertStandardExpansionPlans() {
        var zoneId = TimeZone.getDefault().toZoneId();
        getTodoExpansionData().offer(new ExpansionModel(ZonedDateTime.of(LocalDateTime.of(2017, Month.JANUARY, 01, 00,00),zoneId),
                "Expand storage area/vergroot opslag plaats",
                null));
        getTodoExpansionData().offer(new ExpansionModel(ZonedDateTime.of(LocalDateTime.of(2018, Month.JANUARY, 01, 00,00),zoneId),
                "Expand variety of firearms/verscheidenheid aan vuurwapens uitbreiden",
                null));
        getTodoExpansionData().offer(new ExpansionModel(ZonedDateTime.of(LocalDateTime.of(2019,Month.JANUARY,01,00,00),zoneId),
                "Employ new storage workers/nieuwe opslagmedewerkers in dienst nemen",
                null));
    }
}
