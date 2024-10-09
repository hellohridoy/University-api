package com.example.University.service;

import com.example.University.Dao.StudentDao;
import com.example.University.Dto.StudentInfoBySubjectsDto;
import com.example.University.Dto.StudentResultDto;
import com.example.University.entity.Student;
import com.example.University.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;
    private final StudentDao studentDao;

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student getStudentById(Long id) {
       return studentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<StudentResultDto> getAllStudentResults(String searchParams) {
        return studentDao.getAllStudentsWithSubject(searchParams);
    }

    @Override
    public List<StudentInfoBySubjectsDto> getStudentBySubjects(String searchParams) {
        return studentDao.getStudentInfoBySubject(searchParams);
    }
}
