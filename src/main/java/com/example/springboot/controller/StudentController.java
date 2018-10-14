package com.example.springboot.controller;

import com.example.springboot.model.Student;
import com.example.springboot.repository.ClassroomRepository;
import com.example.springboot.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {
    /*
    Bu classta student entity'sine yapılacak crud islemlerinde classroom bilgisi de dahil olacağı için
    classRoomRepository objesi Autowired ile inject edildi.
     */
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ClassroomRepository classroomRepository;

    @GetMapping("/classrooms/{classroomId}/getStudents")
    public Page<Student> getAllStudentsByClassroomId(@PathVariable(value = "classroomId") Integer classroomId,
                                                     Pageable pageable) {
        return studentRepository.findByClassroomId(classroomId, pageable);
    }

    @PostMapping("/classrooms/{classroomId}/addStudent")
    public Student addStudent(@PathVariable (value = "classroomId") Integer classroomId,
                              @RequestBody Student student) {
        //verilen classroomId her bir student'ın classroom bilgisine save ediliyor.
        return classroomRepository.findById(classroomId).map(classroom -> {
            student.setClassroom(classroom);
            return studentRepository.save(student);
        }).orElseThrow(() -> new RuntimeException("ClassroomId " + classroomId + " not found"));
    }
}
