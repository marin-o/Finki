package mk.ukim.finki.wp.kol2022.g2.service.impl;

import mk.ukim.finki.wp.kol2022.g2.model.Course;
import mk.ukim.finki.wp.kol2022.g2.model.Student;
import mk.ukim.finki.wp.kol2022.g2.model.StudentType;
import mk.ukim.finki.wp.kol2022.g2.model.exceptions.InvalidCourseIdException;
import mk.ukim.finki.wp.kol2022.g2.model.exceptions.InvalidStudentEmailException;
import mk.ukim.finki.wp.kol2022.g2.model.exceptions.InvalidStudentIdException;
import mk.ukim.finki.wp.kol2022.g2.repository.CourseRepository;
import mk.ukim.finki.wp.kol2022.g2.repository.StudentRepository;
import mk.ukim.finki.wp.kol2022.g2.service.StudentService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService, UserDetailsService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final PasswordEncoder passwordEncoder;

    public StudentServiceImpl(StudentRepository studentRepository, CourseRepository courseRepository, PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<Student> listAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findById(Long id) {
        return studentRepository.findById(id).orElseThrow(InvalidStudentIdException::new);
    }

    @Override
    public Student create(String name, String email, String password, StudentType type, List<Long> courseId, LocalDate enrollmentDate) {
        List<Course> courses = courseRepository.findAllById(courseId);
        return studentRepository.save(
                new Student(name,email, passwordEncoder.encode(password), type,courses,enrollmentDate)
        );

    }

    @Override
    public Student update(Long id, String name, String email, String password, StudentType type, List<Long> coursesId, LocalDate enrollmentDate) {
        List<Course> courses = courseRepository.findAllById(coursesId);
        Student s = findById(id);
        s.setName(name);
        s.setEmail(email);
        if(password != null)
            s.setPassword(passwordEncoder.encode(password));
        s.setType(type);
        s.setCourses(courses);
        s.setEnrollmentDate(enrollmentDate);
        return studentRepository.save(s);
    }

    @Override
    public Student delete(Long id) {
        Student s = findById(id);
        studentRepository.delete(s);
        return s;
    }

//    @Override
//    public List<Student> filter(Long courseId, Integer yearsOfStudying) {
//        Course c = courseId == null ? null : courseRepository.findById(courseId).orElseThrow(InvalidCourseIdException::new);
//        LocalDate date = yearsOfStudying == null ? null : LocalDate.now().minusYears(yearsOfStudying);
//        if(c != null && date != null){
//            return studentRepository.findAllByCoursesContainingAndEnrollmentDateBefore(c,date);
//        }
//        if(c == null){
//            return studentRepository.findAllByEnrollmentDateBefore(date);
//        }
//        return studentRepository.findAllByCoursesContaining(c);
//
//    }

    @Override
    public List<Student> filter(Long courseId, Integer yearsOfStudying) {
        LocalDate dateBefore = LocalDate.now();
        if(courseId != null && yearsOfStudying != null){
            dateBefore = dateBefore.minusYears(yearsOfStudying);
            Course c = courseRepository.findById(courseId).orElseThrow(InvalidCourseIdException::new);
            return studentRepository.findAllByCoursesContainingAndEnrollmentDateBefore(c,dateBefore);
        }
        if(courseId == null && yearsOfStudying!=null){
            dateBefore = dateBefore.minusYears(yearsOfStudying);
            return studentRepository.findAllByEnrollmentDateBefore(dateBefore);
        }
        if(courseId != null) {
            Course c = courseRepository.findById(courseId).orElseThrow(InvalidCourseIdException::new);
            return studentRepository.findAllByCoursesContaining(c);
        }
        return studentRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Student s = studentRepository.findByEmail(email).orElseThrow(InvalidStudentEmailException::new);

        return new User(
                s.getEmail(),
                s.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + s.getType()))
        );
    }
}
