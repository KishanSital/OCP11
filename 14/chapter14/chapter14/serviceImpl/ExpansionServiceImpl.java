package chapter14.serviceImpl;

import chapter14.models.*;
import chapter14.repositories.*;
import chapter14.services.*;
import java.text.*;
import java.util.*;
import java.util.function.*;
import static chapter14.utils.StringUtilsExpansionPlansServiceImplMessages.*;

public abstract class ExpansionServiceImpl extends ExpansionRepository implements ExpansionService {
    protected static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(PATTERN.getMessage());
    private Function<Date, String> formatter = simpleDateFormat::format;

    public ExpansionServiceImpl() {
        super();
    }

    @Override
    public void insertStandardExpansionPlans() {
        getTodoExpansionList().offer(new ExpansionModel("2017-01-01",
                                                      "Expand storage area",
                                                      null));
        getTodoExpansionList().offer(new ExpansionModel("2018-01-01",
                                                      "Expand variety of firearms",
                                                      null));
        getTodoExpansionList().offer(new ExpansionModel("2019-01-01",
                                                      "Employ new storage workers ",
                                                      null));
    }

    @Override
    public Queue<ExpansionModel> getTodoExpansionList() {
        return (Queue<ExpansionModel>) todoExpansionList;
    }

    @Override
    public Set<ExpansionModel> getDoneExpansionList() {
        return (Set<ExpansionModel>) doneExpansionList;
    }

    public boolean addPlan(ExpansionModel expansionModel) {
        expansionModel.setStartDate(formatter.apply(new Date()));
        return getTodoExpansionList().offer(expansionModel);
    }

    protected void markPlanToDone() {
        getDoneExpansionList().add(new ExpansionModel(getTodoExpansionList().peek().getStartDate(),
                                                      getTodoExpansionList().peek().getPlan(),
                                                      formatter.apply(new Date())));
        getTodoExpansionList().poll();
    }
}
