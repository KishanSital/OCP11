package chapter18.repositories;

import chapter18.models.ExpansionModel;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;

public class ExpansionRepository {

    //usage of concurrent collections classes
    // Constructor method reference
    private static final Supplier<Queue<?>> todoExpansion = ConcurrentLinkedQueue::new; //unbounded wildcard
    private static final Supplier<Set<?>> doneExpansion = ConcurrentSkipListSet::new;//unbounded wildcard
    public Queue<?> todoExpansionList = todoExpansion.get(); // unbounded wildcard
    public Set<?> doneExpansionList = doneExpansion.get(); //unbounded wildcard


    public ExpansionRepository() {
        super();
    }

    public Queue<ExpansionModel> getTodoExpansionData() {
        return (Queue<ExpansionModel>) todoExpansionList;
    }

    public Set<ExpansionModel> getDoneExpansionData() {
        return (Set<ExpansionModel>) doneExpansionList;
    }

}
