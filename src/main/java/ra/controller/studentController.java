package ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.model.entity.Student;
import ra.model.service.StudentService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("api/student")
public class studentController {
    @Autowired
    private StudentService studentService;
    @GetMapping
    public List<Student> getAllStudent(){
    return studentService.fillAll();
    }

   @GetMapping("/{studentId}")
       public Student getStudentById(@PathVariable("studentId") int studentId){
        return studentService.findById(studentId);
       }

    @PostMapping
    public  Student createStudent(@RequestBody Student student){
        return studentService.saveOrUpdate(student);
    }

    @PutMapping("/{studentId}")
    public Student updateStudent(@PathVariable("studentId") int studentId , @RequestBody Student student){
        Student updateStudent = studentService.findById(studentId);
        updateStudent.setStudentName(student.getStudentName());
        updateStudent.setStudentAge(student.getStudentAge());
        updateStudent.setStudentStatus(student.getStudentStatus());
        //Cap nhat
        return studentService.saveOrUpdate(updateStudent);
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<?> deleteStudent(@PathVariable("studentId") int studentId){
        try{
            studentService.delete(studentId);
            return ResponseEntity.ok("Da xoa thanh cong");
        }catch (Exception e ){
            return ResponseEntity.ok("Chua xoa duoc");
        }
    }

    @GetMapping("/search")
    public List<Student> searchByNameOrId(@RequestParam("studentName") String studentName){
        return studentService.searchByName(studentName);
    }
    
    @GetMapping("/sortByName")
    public ResponseEntity<List<Student>> sortByStudentName(@RequestParam("direction")String direction){
        List<Student> listStudent = studentService.sortStudentByStudentName(direction);
        return  new ResponseEntity<>(listStudent, HttpStatus.OK);
    }

    @GetMapping("/getPagging")
    public ResponseEntity<Map<String,Object>> getPagging(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "2") int size){
        Pageable pageable = PageRequest.of(page,size);
        Page<Student> pageStudent = studentService.getPagging(pageable);
        Map<String,Object> data = new HashMap<>();
        data.put("student",pageStudent.getContent());
        data.put("total",pageStudent.getSize());
        data.put("totalItems",pageStudent.getTotalElements());
        data.put("totalPages",pageStudent.getTotalPages());
        return  new ResponseEntity<>(data,HttpStatus.OK);
    }


}
