package chapter21.services;

import chapter21.models.ExpansionModel;
import mypackage.annotations.Service;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Queue;
import java.util.Set;

@Service
public interface ExpansionService {
    Queue<ExpansionModel> getTodoExpansionPlanQueue();

    Set<ExpansionModel> getDoneExpansionPlanSet();

    boolean addPlan(ExpansionModel expansionModel);

    ExpansionModel markPlanToDone();

    boolean displayUpcomingPlanProcess();

    ExpansionModel<String, LocalDate> getUpcomingPlan();

    String createTables();

    String deleteTables();

    int[] insertExpansionPlans(ExpansionModel<String, Date>... expansionPlans) throws SQLException;
}
