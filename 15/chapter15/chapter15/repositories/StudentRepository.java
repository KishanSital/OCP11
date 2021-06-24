package chapter15.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;


public abstract class StudentRepository<T> {
    protected final Supplier<List<T>> studentListSupplier = ArrayList::new;
    protected final List<T> studentList = studentListSupplier.get();

}
