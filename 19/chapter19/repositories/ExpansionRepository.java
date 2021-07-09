package chapter19.repositories;

import chapter19.models.ExpansionModel;

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
    }

    public Queue<ExpansionModel> getTodoExpansionData() {
        return (Queue<ExpansionModel>) todoExpansionList;
    }

    public Set<ExpansionModel> getDoneExpansionData() {
        return (Set<ExpansionModel>) doneExpansionList;
    }

}
