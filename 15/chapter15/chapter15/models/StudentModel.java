package chapter15.models;

public class StudentModel <S extends Comparable<S>> implements Comparable<StudentModel<S>> {

    private S studentName;

    public StudentModel(S studentName) {
        this.studentName = studentName;
    }

    public S getStudentName() {
        return studentName;
    }

    public void setStudentName(S studentName) {
        this.studentName = studentName;
    }

    @Override
    public String toString() {
        return (String)studentName;
    }

    @Override
    public int compareTo(StudentModel<S> studentModel) {
        return this.studentName.compareTo(studentModel.getStudentName());
    }
}
