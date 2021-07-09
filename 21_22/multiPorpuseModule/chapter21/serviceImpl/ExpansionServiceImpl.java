package chapter21.serviceImpl;

import chapter21.models.ExpansionModel;
import chapter21.services.ExpansionRepositoryService;
import chapter21.services.ExpansionService;


import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Queue;
import java.util.Set;

import java.util.function.Function;

import static chapter21.utils.StringUtilsExpansionPlansServiceImplMessages.PATTERN;


public final class ExpansionServiceImpl implements ExpansionService {
    // DateTimeFormatter
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(PATTERN.getMessage());
    private Function<LocalDate, String> formatter = x -> dateTimeFormatter.withLocale(Locale.getDefault()).format(x);
    private ExpansionRepositoryService expansionRepositoryService;

    public ExpansionServiceImpl() {
        super();
    }

    public ExpansionServiceImpl(ExpansionRepositoryService expansionRepositoryService) {
        if (expansionRepositoryService == null) {
            throw new NullPointerException("Please provide an implementation of ExpansionRepositoryService");
        }
        this.expansionRepositoryService = expansionRepositoryService;
    }

    @Override
    public Queue<ExpansionModel> getTodoExpansionPlanQueue() {
        return expansionRepositoryService.getTodoExpansionData();
    }

    @Override
    public Set<ExpansionModel> getDoneExpansionPlanSet() {
        return expansionRepositoryService.getExecutedExpansionPlanData();
    }

    @Override
    public ExpansionModel<String, LocalDate> getUpcomingPlan() {
        return expansionRepositoryService.retrieveTheNextPlan();
    }

    @Override
    public String createTables() {
        return expansionRepositoryService.createTables();
    }

    @Override
    public String deleteTables() {
        return expansionRepositoryService.deleteTables();
    }

    @Override
    public int[] insertExpansionPlans(ExpansionModel<String, Date>... expansionPlans) throws SQLException {
        return expansionRepositoryService.insertExpansionPlans(expansionPlans);
    }

    @Override
    public boolean addPlan(ExpansionModel expansionModel) {
        try {
            expansionRepositoryService.insertExpansionPlans(expansionModel);
        } catch (SQLException sqlException) {
            return false;
        }
        return true;
    }

    @Override
    public ExpansionModel markPlanToDone() {
        try {
            return expansionRepositoryService.markPlanDone();
        } catch (SQLException sqlException) {
            return null;
        }
    }

    @Override
    public boolean displayUpcomingPlanProcess() {
        if (expansionRepositoryService.retrieveTheNextPlan() == null) {
            return false;
        } else {
            return true;
        }
    }
}
