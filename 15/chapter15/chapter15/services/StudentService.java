package chapter15.services;

import chapter15.models.StudentModel;
import mypackage.annotations.*;
import mypackage.services.MenuService;

import java.util.Collection;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

@Service({"interface for student"})
public interface StudentService {

    List<StudentModel> getStudentList();
    void average(List<Double> scores);
    <K> Stream<K> toStream (Collection<K> studentList);
    void findAnyStudent();
    void findMinMax(String[] entryOptions, boolean isChoiceCorrect, String scannerEntry);
    void displayStudentNamesWithLength();
    void filteringStudents(String studentName,long skipStudents, long studentAmount);
    void studentsWithMatchingLetter(List<String> studNamesList, Predicate<String> studentPredicate);
    DoubleSummaryStatistics calculateSummaryStatistics(List<Double> scoresList);

}
