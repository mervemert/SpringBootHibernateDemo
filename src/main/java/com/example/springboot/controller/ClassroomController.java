package com.example.springboot.controller;

import com.example.springboot.model.Classroom;
import com.example.springboot.repository.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClassroomController {

    @Autowired
    private ClassroomRepository classroomRepository;

    //@GetMapping annotation'ı @RequestMapping'in özellestirilmis bir versiyonudur.
    //@RequestMapping(method = RequestMethod.GET) yerine HTTP GET isteklerini handle etmesi icin kullanildi.
    //client tarafından tum classroomları görmek icin istek yapıldığında bu metot çalışır.
    @GetMapping("/getClassrooms")
    public Page<Classroom> getAllStudents(Pageable pageable) {
        return classroomRepository.findAll(pageable);
    }

    //@PostMapping annotation'ı da @RequestMapping'in özellestirilmis bir versiyonudur.
    //@RequestMapping(method = RequestMethod.POST) yerine HTTP POST isteklerini handle etmesi icin kullanildi.
    //addClassRoom metodu client tarafından gonderilen bilgileri POST ile alarak db'deki ilgili tabloya kaydeder.
    @PostMapping("/addClassroom")
    public Classroom addClassRoom(@RequestBody Classroom classroom) {
        return classroomRepository.save(classroom);
    }

}
