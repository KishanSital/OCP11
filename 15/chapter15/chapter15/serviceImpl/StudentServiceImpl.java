package chapter15.serviceImpl;

import chapter15.models.StudentModel;
import chapter15.repositories.StudentRepository;
import chapter15.services.StudentService;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

import static mypackage.serviceImpl.TriesValidationServiceImpl.triesValidation;

public class StudentServiceImpl extends StudentRepository<StudentModel> implements StudentService {

    private BiFunction<Double, Integer, Double> calculateAverage = (d1, d2) -> (d1 / d2);
    private Consumer<String> println = System.out::println;
    private Consumer<String> print = System.out::print;
    private final int minimum = 0;

    public StudentServiceImpl() {
        super();
        insertStandardStudents();
    }

    private void insertStandardStudents(){
        getStudentList().add(new StudentModel("Erika"));
        getStudentList().add(new StudentModel("Erika"));
        getStudentList().add(new StudentModel("Neculai"));
        getStudentList().add(new StudentModel("Fionola"));
        getStudentList().add(new StudentModel("Harald"));
        getStudentList().add(new StudentModel("Todorka"));
        getStudentList().add(new StudentModel("Valto"));
        getStudentList().add(new StudentModel("kishan"));
        getStudentList().add(new StudentModel("kishan"));
        getStudentList().add(new StudentModel("krishan"));
    }

    @Override
    public List<StudentModel> getStudentList() {
        return studentList;
    }

    // convert a list to stream
    @Override
    public  <K>Stream<K> toStream (Collection<K> list){
        if (list == null ||list.isEmpty()){
            return Stream.empty();
        }
        return list.stream();
    }

    /**
     * Use calculateSummaryStatistics instead
     */
    @Deprecated
    private Optional<Double> calculateAverageResult(List<Double> scoresList) {
        Double total = 0.00;
        if (scoresList == null || scoresList.isEmpty()) {
            return Optional.empty();
        } else {
            for (Double totalScore : scoresList) {
                total += totalScore;
            }
            return Optional.of(calculateAverage.apply(total, scoresList.size()));
        }
    }

    @Override
    // usage of conversion of a List to stream, mapping it to a primitive stream
    // (DoubleStream using the toDoubleFunction)
    // using the DoubleSummaryStatistics method on it
    public DoubleSummaryStatistics calculateSummaryStatistics(List<Double> scoresList) {
     return toStream(scoresList).mapToDouble(s -> s).boxed().mapToDouble(k->k).summaryStatistics();
    }

    @Override
    // using the noneMatch, allMatch , anyMatch methods on a stream
    public void studentsWithMatchingLetter(List<String> studNamesList, Predicate<String> studentPredicate) {
        if (toStream(studNamesList).noneMatch(studentPredicate)){
            println.accept("None of the students name start with the same letter or word");
        } else if (toStream(studNamesList).allMatch(studentPredicate)) {
            println.accept("All the students name start with the same letter or word");
        } else if (toStream(studNamesList).anyMatch(studentPredicate)) {
            println.accept("There are students whose name start with the same letter or word");
        } else {
            println.accept("Something went wrong");
        }
    }

    // conversion of List containing StudentModel objects to an ArrayList
    // with String objects that contain the name of the students
    private ArrayList<String> allStudentNames(List<StudentModel> studentList){
        ArrayList<String> studentNames = new ArrayList<>();
        if(studentList != null || !studentList.isEmpty()) {
            studentList.forEach(s -> studentNames.add(s.getStudentName().toString()));
        }
        return studentNames;
    }

    @Override
    // using isPresent method from Optional
    public void average(List<Double> scores) {
        if (calculateAverageResult(scores).isPresent()) {
            println.accept("Student's average rounded by one point decimal : " + Math.round(calculateAverageResult(scores).get()) + "\n");
        } else {
            System.out.println("Come back when you're ready");
        }
    }

    @Override
    //Using min and max method on a stream
    public void findMinMax(String[] entryOptions, boolean isChoiceCorrect, String scannerEntry) {
        if (isChoiceCorrect)
            if (scannerEntry.equals(entryOptions[minimum])) {
                Optional<StudentModel> studentMinimumOptional = toStream(getStudentList()).min(Comparator.comparingInt(s -> s.getStudentName().toString().length()));
                if (studentMinimumOptional == null || studentMinimumOptional.isEmpty()) {
                    System.out.println("No student with the shortest name found\n");
                } else {
                    println.accept("The student with the shortest name is: " + studentMinimumOptional.get().toString() + "\n");
                }
            } else {
                Optional<StudentModel> studentMaxOptional = toStream(getStudentList()).max(Comparator.comparingInt(s -> s.getStudentName().toString().length()));
                if (studentMaxOptional == null || studentMaxOptional.isEmpty()) {
                    System.out.println("No student with the longest name found\n");
                } else {
                    println.accept("The student with the longest name is: " + studentMaxOptional.get().toString() + "\n");
                }
            }
        if (!isChoiceCorrect) {
            triesValidation();
        }
    }

    @Override
    //Using findAny, isEmpty method on a stream
    public void findAnyStudent() {
        if (toStream(getStudentList()) != null && !toStream(getStudentList()).findAny().isEmpty()) {
            println.accept("The retrieved student is: "+ toStream(getStudentList()).findAny().get().toString());
        } else {
            println.accept("There were no students available");
        }
    }

    @Override
    //Different ways to display students name and their name length
    public void displayStudentNamesWithLength(){
        if (toStream(getStudentList()) != null && toStream(getStudentList()).findFirst().isPresent()) {
            usageOfReduce();
            usageOfCollectWithTreeSet();
            usageOfCollectWithCollectors();
            collectingIntoMapWithMergeFunctionForDuplicates();
            collectingIntoTreeMapWithMergeFunctionForDuplicatesAndMapFactory();
            collectingUsingGroupingWithATreeMap();
            collectingUsingPartitioning();
            collectingUsingPartitioningAndCollector();
        } else {
            println.accept("There were no students available");
        }
    }

    private void collectingUsingPartitioningAndCollector() {
        println.accept("Collecting using partitioning to partition the students into two groups depending on whether the length of their names are bigger than 6 and mapping them to a StudentModel object");
        Map<Boolean, Set<StudentModel>> studentMapWithPartitioningAndMapping = toStream(allStudentNames(getStudentList())).collect(Collectors.partitioningBy(s-> s.length() > 6 ,  Collectors.mapping(s-> new StudentModel(s),Collectors.toSet())));
        studentMapWithPartitioningAndMapping.forEach((k,v) ->  println.accept("Length: "+k + ", Name:" +v));
        println.accept("");
    }

    private void collectingUsingPartitioning() {
        println.accept("Collecting using partitioning to partition the students into two groups depending on whether the length of their names are bigger than 6");
        Map<Boolean, Set<String>> studentMapWithPartitioning = toStream(allStudentNames(getStudentList())).collect(Collectors.partitioningBy(s-> s.length() > 6 ,  Collectors.toSet()));
        studentMapWithPartitioning.forEach((k,v) ->  println.accept("Length: "+k + ", Name:" +v));
        println.accept("");
    }

    private void collectingUsingGroupingWithATreeMap() {
        println.accept("Collecting using grouping with a TreeMap (contains a Set of the student names which are grouped to the length of their names)");
        TreeMap<Integer, Set<String>> studentTreeMapWithGrouping = toStream(allStudentNames(getStudentList())).collect(Collectors.groupingBy(s -> s.length(), TreeMap::new, Collectors.toSet()));
        studentTreeMapWithGrouping.forEach((k,v) ->  println.accept("Length: "+k + ", Name:" +v));
        println.accept("");
    }

    private void collectingIntoTreeMapWithMergeFunctionForDuplicatesAndMapFactory() {
        println.accept("Collecting into TreeMap<Length,Name> (with merge function for duplicates and mapFactory)");
        TreeMap<Integer, String> studentTreeMap = toStream(allStudentNames(getStudentList())).collect(Collectors.toMap(String::length, v-> v, (s1, s2)-> s1 +","+ s2,TreeMap::new));
        studentTreeMap.forEach((k,v) -> println.accept("Length: "+k + ", Name:" +v));
        println.accept("");
    }

    private void collectingIntoMapWithMergeFunctionForDuplicates() {
        println.accept("Collecting into Map<Length,Name> (with merge function for duplicates)");
        Map<Integer, String> studentMap = toStream(allStudentNames(getStudentList())).collect(Collectors.toMap(String::length, v-> v, (s1, s2)-> s1 +","+ s2));
        studentMap.forEach((k,v) -> println.accept("Length: "+k + ", Name:" +v));
        println.accept("");
    }

    private void usageOfCollectWithCollectors() {
        println.accept("Using collect,Collectors with Set");
        Set<String> studentSet = toStream(allStudentNames(getStudentList())).collect(Collectors.toSet());
        print.accept("All the students names and their name length: ");
        studentSet.forEach(s -> System.out.print("| Name: "+ s + ", Length: "+s.length()));
        println.accept("");
    }

    private void usageOfCollectWithTreeSet() {
        println.accept("Using collect with TreeSet");
        TreeSet<String> studentTreeSet = toStream(getStudentList()).collect(TreeSet::new, (i, s) -> i.add("Name: "+s.getStudentName().toString()+",Length: "+s.getStudentName().toString().length()+"|"), TreeSet::addAll);
        print.accept("All the students names and their name length: ");
        studentTreeSet.forEach(System.out::print);
        println.accept("");
    }

    private void usageOfReduce() {
        println.accept("Using reduce");
        String studentString = toStream(getStudentList()).reduce("", (i,s)-> i+"Name: "+s.getStudentName().toString()+",Length: "+s.getStudentName().toString().length()+"|", (a,b) -> a + b);
        println.accept("All the students names and their name length: " +studentString);
        println.accept("");
    }

    @Override
    //usage of filter, distinct, limit, skip, sorted, peek , count on a stream
    public void filteringStudents(String studentName, long skipStudents, long studentAmount) {
        removingDuplicates();
        sortingStudents();
        filteringOutStudent(studentName);
        skippingAndLimitingStudent(studentName, skipStudents, studentAmount);
        countingStudents(studentName, skipStudents, studentAmount);
        // studentStream(allStudentNames(getStudentList())).distinct().peek(s-> println.accept(" The student report now contains: " +s)).sorted().filter( s-> s.equals(studentName)).peek(s->println.accept("2 The student report now contains: "+ s)).skip(skipStudents).peek(s-> println.accept("3 The student report now contains: ")).limit(studentAmount).peek(s -> println.accept("4 The student report now contains : "+ s)).forEach(println::accept);
    }

    private void countingStudents(String studentName, long skipStudents, long studentAmount) {
        println.accept("Counting...");
        long countingStudentsInReport = toStream(allStudentNames(getStudentList())).distinct().sorted().filter(s -> !s.equals(studentName)).skip(skipStudents).limit(studentAmount).count();
        println.accept("There are "+ countingStudentsInReport+" students now in this report\n");
    }

    private void skippingAndLimitingStudent(String studentName, long skipStudents, long studentAmount) {
        println.accept("Skipping and limiting...");
        String reportAfterSkippingAndLimiting = toStream(allStudentNames(getStudentList())).distinct().sorted().filter(s -> !s.equals(studentName)).skip(skipStudents).limit(studentAmount).reduce("After skipping "+skipStudents+" and limiting the report to "+studentAmount+" the report now contains: ",(i,s)-> i+" "+s,(a,b)->a+b);
        println.accept(reportAfterSkippingAndLimiting );
    }

    private void filteringOutStudent(String studentName) {
        println.accept("Filtering out student...");
        String reportAfterFiltering = toStream(allStudentNames(getStudentList())).distinct().sorted().filter(s -> !s.equals(studentName)).peek(System.out::println).reduce("After filtering out "+studentName+" the report now contains: ",(i,s)-> i+" "+s,(a,b)->a+b);
        println.accept(reportAfterFiltering);
    }

    private void sortingStudents() {
        println.accept("Sorting ...");
        String reportAfterSort = toStream(allStudentNames(getStudentList())).distinct().sorted().reduce("After sorting the students on their names the report now contains: ",(i,s)-> i+" "+s,(a,b)->a+b);
        println.accept(reportAfterSort);
    }

    private void removingDuplicates() {
        println.accept("Removing duplicates...");
        String reportAfterDistinct = toStream(allStudentNames(getStudentList())).distinct().reduce("After removing the duplicates the student report now contains: ",(i,s) -> i+" "+s,(a,b) -> a + b);
        println.accept(reportAfterDistinct);
    }


}
