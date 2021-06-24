package chapter16.services;

import chapter16.models.ExpansionModel;
import chapter16.annotations.Service;

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
