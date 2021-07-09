package chapter18.services;

import chapter18.models.*;
import mypackage.annotations.*;

import java.util.*;
import java.util.stream.*;

@Service
public interface ExpansionService {
    Queue<ExpansionModel> getTodoExpansionList();

    Set<ExpansionModel> getDoneExpansionList();

    boolean addPlan(ExpansionModel expansionModel);

    ExpansionModel markPlanToDone();

    boolean displayUpcomingPlanProcess();

    void shootingPlanner();

    <K> Stream<K> toParallelStream(Collection<K> list);
}
