package ra.model.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ra.model.entity.Student;
import ra.model.repository.StudentRepository;
import ra.model.service.StudentService;

import java.util.List;

@Service
public class StudentImp implements StudentService {
    @Autowired
    private StudentRepository studentRepository;


    @Override
    public List<Student> fillAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findById(int studentId) {
        return studentRepository.findById(studentId).get();
    }

    @Override
    public Student saveOrUpdate(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void delete(int studentId) {
        studentRepository.deleteById(studentId);
    }

    @Override
    public List<Student> searchByName(String studentName) {
        return studentRepository.findByStudentNameContainingIgnoreCase(studentName);
    }

    @Override
    public List<Student> sortStudentByStudentName(String direction) {
        if (direction.equals("asc")) {
            return studentRepository.findAll(Sort.by("studentName").ascending());
        } else {
            return studentRepository.findAll(Sort.by("studentName").descending());
        }
    }

    @Override
    public Page<Student> getPagging(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }
}
