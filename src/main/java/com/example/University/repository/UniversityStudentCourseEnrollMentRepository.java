package com.example.University.repository;

import com.example.University.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversityStudentCourseEnrollMentRepository extends JpaRepository<Customer,Long> {
}
