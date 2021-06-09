package chapter14.services;

import chapter14.models.*;
import mypackage.annotations.*;
import java.util.*;

@Service
public interface ExpansionService {
    void insertStandardExpansionPlans();
    Queue<ExpansionModel> getTodoExpansionList();
    Set<ExpansionModel> getDoneExpansionList();
    void displayUpcomingPlan();
    void addPlan();
    void markDone();
    void viewExecutedPlans();
}
