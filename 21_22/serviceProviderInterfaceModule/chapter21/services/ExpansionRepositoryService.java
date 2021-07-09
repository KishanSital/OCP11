package chapter21.services;

import chapter21.models.ExpansionModel;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Queue;
import java.util.Set;

public interface ExpansionRepositoryService {

    Queue<ExpansionModel> getTodoExpansionData();

    int[] insertExpansionPlans(ExpansionModel<String, Date>... expansionPlans) throws SQLException;

    ExpansionModel<String, LocalDate> markPlanDone() throws SQLException;

    ExpansionModel<String, LocalDate> retrieveTheNextPlan();

    Set<ExpansionModel> getExecutedExpansionPlanData();

    String createTables();

    String deleteTables();
}
