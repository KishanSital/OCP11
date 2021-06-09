package chapter14.repositories;

import java.util.*;
import java.util.function.*;

public class ExpansionRepository {

    protected Queue<?> todoExpansionList = todoExpansion.get(); // unbounded wildcard
    protected Set<?> doneExpansionList = doneExpansion.get(); //unbounded wildcard

    // Constructor method reference
    private static final Supplier<Queue<?>> todoExpansion = LinkedList::new; //unbounded wildcard
    private static final Supplier<Set<?>>  doneExpansion = TreeSet::new;//unbounded wildcard

    protected ExpansionRepository() {
        super();
    }
}
