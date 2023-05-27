//package mk.ukim.finki.vtor_kolokvium;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class OperationNotAllowedException extends Exception{
    public OperationNotAllowedException( String message ) {
        super(message);
    }
}

class GraduateLog{
    static GraduateLog instance;
    StringBuilder sb;

    private GraduateLog(){
        sb = new StringBuilder();
    }

    public static GraduateLog getInstance(){
        if (instance == null) {
            instance = new GraduateLog();
        }
        return instance;
    }

    @Override
    public String toString(){
        return sb.toString();
    }
}
class Term{
    Map<String,Integer> courses = new HashMap<>();

    public void addGrade(String course,int grade){
        courses.put(course,grade);
    }
    public float getAverageGrade(){
        return (float) courses.values().stream().mapToInt(value -> value).average().getAsDouble();
    }

    public void detailedReport(StringBuilder sb,List<String> courseList){
        sb.append("Courses for term: ").append(courses.size()).append('\n');
        sb.append("Average grade for term: ").append(String.format("%.2f",getAverageGrade()));
        courseList.addAll(courses.keySet());
    }
}
class Student{
    String id;
    int yearsOfStudies;
    int passedCourses = 0;
    Map<Integer,Term> terms = new LinkedHashMap<>();

    public Student( String id, int yearsOfStudies ) {
        this.id=id;
        this.yearsOfStudies=yearsOfStudies;
    }

    public int getPassedCourses() {
        return passedCourses;
    }

    public Student addGrade( int term, String course, int grade) throws OperationNotAllowedException {
        if(yearsOfStudies==3 && term>6)
            throw new OperationNotAllowedException(String.format("Term %d is not allowed for student with ID %s",term,id));
        Term t = terms.get(term);
        if(t.courses.size()==3)
            throw new OperationNotAllowedException(String.format("Student %s already has 3 grades in term %d",id,term));
        t.addGrade(course,grade);
        if(grade>5) passedCourses++;
        if(yearsOfStudies == 3)
            if((int)terms.values().stream().filter(t1 -> t.courses.size()==3).count()==6)
                return this;
        if(yearsOfStudies == 4)
            if((int)terms.values().stream().filter(t1 -> t.courses.size()==3).count()==8)
                return this;
        return null;
    }

    public float getAverageGrades() {
        if(passedCourses==0) return 5.0F;
        return (float)terms.values().stream().mapToDouble(Term::getAverageGrade).sum()/terms.size();
    }

    public String detailedReport( StringBuilder sb ) {
        List<String> courses = new ArrayList<>();

        for ( Map.Entry<Integer,Term> term : terms.entrySet()) {
            sb.append("Term ").append(term.getKey()).append('\n');
            term.getValue().detailedReport(sb,courses);
        }
        sb.append("Average grade: ").append(getAverageGrades());
        courses.sort(Comparator.naturalOrder());
        sb.append("Courses attended: ");
        for ( String course: courses ) {
            sb.append(course).append(", ");
        }
        return sb.append('\n').toString();
    }
    public void shortReport(){
        System.out.printf("Student %s Courses passed: %d Average grade: %.2f%n",id,passedCourses,getAverageGrades());
    }
}

class Course{
    int studentCount = 0;
    float averageGrade = 0;
    String name;

    public Course( String name ) {
        this.name=name;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public void incrementStudentCount() {
        this.studentCount++;
    }

    public float getAverageGrade() {
        return averageGrade/studentCount;
    }

    public void addAverageGrade( float averageGrade ) {
        this.averageGrade+=averageGrade;
    }
}
class Faculty {

    Map<String,Student> students = new HashMap<>();
    Map<String,Course> courses = new HashMap<>();
    GraduateLog graduateLog;

    public Faculty() {
        graduateLog = GraduateLog.getInstance();
    }

    void addStudent(String id, int yearsOfStudies) {
        students.put(id,new Student(id,yearsOfStudies));
    }

    void addGradeToStudent(String studentId, int term, String courseName, int grade) throws OperationNotAllowedException {
        Course course = courses.putIfAbsent(courseName,new Course(courseName));
        if(!(course==null)) {
            course.incrementStudentCount();
            course.addAverageGrade(grade);
        }
        Student student = students.get(studentId);
        Student remove = student.addGrade(term,courseName,grade);
        if(remove==null) {
            graduateLog.sb.append(
                    String.format(
                            "Student with ID %s graduated with average grade %.2f in %d years%n",
                            studentId, student.getAverageGrades(), student.yearsOfStudies)
            );
            students.remove(studentId);
        }
    }

    String getFacultyLogs() {
        return graduateLog.toString();
    }

    String getDetailedReportForStudent(String id) {
        Student student = students.get(id);
        StringBuilder sb = new StringBuilder();
        return student.detailedReport(sb);
    }

    void printFirstNStudents(int n) {
        List<Student> studentList =new ArrayList<>(students.values().stream().collect(Collectors.toList()));
        studentList.sort(Comparator.comparing(Student::getPassedCourses)
                .reversed()
                .thenComparing(Student::getAverageGrades));
        studentList.stream().limit(n).forEach(Student::shortReport);
    }

    void printCourses() {
        List<Course> courseList=new ArrayList<>(courses.values());
        courseList.sort(Comparator.comparing(Course::getStudentCount).thenComparing(Course::getAverageGrade));
        for ( Course course : courseList ) {
            System.out.printf("%s %d %.2f%n",course.name,course.getStudentCount(),course.getAverageGrade());
        }
    }
}

public class FacultyTest {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCase = sc.nextInt();

        if (testCase == 1) {
            System.out.println("TESTING addStudent AND printFirstNStudents");
            Faculty faculty = new Faculty();
            for (int i = 0; i < 10; i++) {
                faculty.addStudent("student" + i, (i % 2 == 0) ? 3 : 4);
            }
            faculty.printFirstNStudents(10);

        } else if (testCase == 2) {
            System.out.println("TESTING addGrade and exception");
            Faculty faculty = new Faculty();
            faculty.addStudent("123", 3);
            faculty.addStudent("1234", 4);
            try {
                faculty.addGradeToStudent("123", 7, "NP", 10);
            } catch (OperationNotAllowedException e) {
                System.out.println(e.getMessage());
            }
            try {
                faculty.addGradeToStudent("1234", 9, "NP", 8);
            } catch (OperationNotAllowedException e) {
                System.out.println(e.getMessage());
            }
        } else if (testCase == 3) {
            System.out.println("TESTING addGrade and exception");
            Faculty faculty = new Faculty();
            faculty.addStudent("123", 3);
            faculty.addStudent("1234", 4);
            for (int i = 0; i < 4; i++) {
                try {
                    faculty.addGradeToStudent("123", 1, "course" + i, 10);
                } catch (OperationNotAllowedException e) {
                    System.out.println(e.getMessage());
                }
            }
            for (int i = 0; i < 4; i++) {
                try {
                    faculty.addGradeToStudent("1234", 1, "course" + i, 10);
                } catch (OperationNotAllowedException e) {
                    System.out.println(e.getMessage());
                }
            }
        } else if (testCase == 4) {
            System.out.println("Testing addGrade for graduation");
            Faculty faculty = new Faculty();
            faculty.addStudent("123", 3);
            faculty.addStudent("1234", 4);
            int counter = 1;
            for (int i = 1; i <= 6; i++) {
                for (int j = 1; j <= 3; j++) {
                    try {
                        faculty.addGradeToStudent("123", i, "course" + counter, (i % 2 == 0) ? 7 : 8);
                    } catch (OperationNotAllowedException e) {
                        System.out.println(e.getMessage());
                    }
                    ++counter;
                }
            }
            counter = 1;
            for (int i = 1; i <= 8; i++) {
                for (int j = 1; j <= 3; j++) {
                    try {
                        faculty.addGradeToStudent("1234", i, "course" + counter, (j % 2 == 0) ? 7 : 10);
                    } catch (OperationNotAllowedException e) {
                        System.out.println(e.getMessage());
                    }
                    ++counter;
                }
            }
            System.out.println("LOGS");
            System.out.println(faculty.getFacultyLogs());
            System.out.println("PRINT STUDENTS (there shouldn't be anything after this line!");
            faculty.printFirstNStudents(2);
        } else if (testCase == 5 || testCase == 6 || testCase == 7) {
            System.out.println("Testing addGrade and printFirstNStudents (not graduated student)");
            Faculty faculty = new Faculty();
            for (int i = 1; i <= 10; i++) {
                faculty.addStudent("student" + i, ((i % 2) == 1 ? 3 : 4));
                int courseCounter = 1;
                for (int j = 1; j < ((i % 2 == 1) ? 6 : 8); j++) {
                    for (int k = 1; k <= ((j % 2 == 1) ? 3 : 2); k++) {
                        try {
                            faculty.addGradeToStudent("student" + i, j, ("course" + courseCounter), i % 5 + 6);
                        } catch (OperationNotAllowedException e) {
                            System.out.println(e.getMessage());
                        }
                        ++courseCounter;
                    }
                }
            }
            if (testCase == 5)
                faculty.printFirstNStudents(10);
            else if (testCase == 6)
                faculty.printFirstNStudents(3);
            else
                faculty.printFirstNStudents(20);
        } else if (testCase == 8 || testCase == 9) {
            System.out.println("TESTING DETAILED REPORT");
            Faculty faculty = new Faculty();
            faculty.addStudent("student1", ((testCase == 8) ? 3 : 4));
            int grade = 6;
            int counterCounter = 1;
            for (int i = 1; i < ((testCase == 8) ? 6 : 8); i++) {
                for (int j = 1; j < 3; j++) {
                    try {
                        faculty.addGradeToStudent("student1", i, "course" + counterCounter, grade);
                    } catch (OperationNotAllowedException e) {
                        e.printStackTrace();
                    }
                    grade++;
                    if (grade == 10)
                        grade = 5;
                    ++counterCounter;
                }
            }
            System.out.println(faculty.getDetailedReportForStudent("student1"));
        } else if (testCase==10) {
            System.out.println("TESTING PRINT COURSES");
            Faculty faculty = new Faculty();
            for (int i = 1; i <= 10; i++) {
                faculty.addStudent("student" + i, ((i % 2) == 1 ? 3 : 4));
                int courseCounter = 1;
                for (int j = 1; j < ((i % 2 == 1) ? 6 : 8); j++) {
                    for (int k = 1; k <= ((j % 2 == 1) ? 3 : 2); k++) {
                        int grade = sc.nextInt();
                        try {
                            faculty.addGradeToStudent("student" + i, j, ("course" + courseCounter), grade);
                        } catch (OperationNotAllowedException e) {
                            System.out.println(e.getMessage());
                        }
                        ++courseCounter;
                    }
                }
            }
            faculty.printCourses();
        } else if (testCase==11) {
            System.out.println("INTEGRATION TEST");
            Faculty faculty = new Faculty();
            for (int i = 1; i <= 10; i++) {
                faculty.addStudent("student" + i, ((i % 2) == 1 ? 3 : 4));
                int courseCounter = 1;
                for (int j = 1; j <= ((i % 2 == 1) ? 6 : 8); j++) {
                    for (int k = 1; k <= ((j % 2 == 1) ? 2 : 3); k++) {
                        int grade = sc.nextInt();
                        try {
                            faculty.addGradeToStudent("student" + i, j, ("course" + courseCounter), grade);
                        } catch (OperationNotAllowedException e) {
                            System.out.println(e.getMessage());
                        }
                        ++courseCounter;
                    }
                }

            }

            for (int i=11;i<15;i++) {
                faculty.addStudent("student" + i, ((i % 2) == 1 ? 3 : 4));
                int courseCounter = 1;
                for (int j = 1; j <= ((i % 2 == 1) ? 6 : 8); j++) {
                    for (int k = 1; k <= 3; k++) {
                        int grade = sc.nextInt();
                        try {
                            faculty.addGradeToStudent("student" + i, j, ("course" + courseCounter), grade);
                        } catch (OperationNotAllowedException e) {
                            System.out.println(e.getMessage());
                        }
                        ++courseCounter;
                    }
                }
            }
            System.out.println("LOGS");
            System.out.println(faculty.getFacultyLogs());
            System.out.println("DETAILED REPORT FOR STUDENT");
            System.out.println(faculty.getDetailedReportForStudent("student2"));
            try {
                System.out.println(faculty.getDetailedReportForStudent("student11"));
                System.out.println("The graduated students should be deleted!!!");
            } catch (NullPointerException e) {
                System.out.println("The graduated students are really deleted");
            }
            System.out.println("FIRST N STUDENTS");
            faculty.printFirstNStudents(10);
            System.out.println("COURSES");
            faculty.printCourses();
        }
    }
}
