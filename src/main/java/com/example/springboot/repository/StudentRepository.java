package com.example.springboot.repository;

import com.example.springboot.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
    hem pagination&sorting hem de temel crud islemlerini gerceklestirmesi icin
    PagingAndSortingRepository interface'ini kalitan JpaRepository interface'i kullanildi.
 */

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    Page<Student> findByClassroomId(Integer classroomId, Pageable pageable);
}

