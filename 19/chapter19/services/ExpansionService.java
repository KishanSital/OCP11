package chapter19.services;

import chapter19.models.ExpansionModel;
import mypackage.annotations.Service;

import java.util.Queue;
import java.util.Set;

@Service
public interface ExpansionService {
    Queue<ExpansionModel> getTodoExpansionList();

    Set<ExpansionModel> getDoneExpansionList();

    boolean addPlan(ExpansionModel expansionModel);

    ExpansionModel markPlanToDone();

    boolean displayUpcomingPlanProcess();
}
