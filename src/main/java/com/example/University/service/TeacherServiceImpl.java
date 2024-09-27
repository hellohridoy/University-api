package com.example.University.service;

import com.example.University.Dao.TeacherDao;
import com.example.University.Dto.SubjectDto;
import com.example.University.entity.Teacher;
import com.example.University.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;
    private final TeacherDao teacherDao;
    @Override
    public Teacher saveTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher updateTeacher(Long id, Teacher teacher) {
        Optional<Teacher> existingTeacher = teacherRepository.findById(id);
        if (existingTeacher.isPresent()) {
            Teacher updatedTeacher = existingTeacher.get();
            updatedTeacher.setName(teacher.getName());
            updatedTeacher.setEmail(teacher.getEmail());
            updatedTeacher.setPhoneNumber(teacher.getPhoneNumber());
            updatedTeacher.setDepartment(teacher.getDepartment());
            updatedTeacher.setSpecialization(teacher.getSpecialization());
            updatedTeacher.setHireDate(teacher.getHireDate());
            updatedTeacher.setSalary(teacher.getSalary());
            updatedTeacher.setBiography(teacher.getBiography());
            updatedTeacher.setProfilePicture(teacher.getProfilePicture());
            updatedTeacher.setQualifications(teacher.getQualifications());
            return teacherRepository.save(updatedTeacher);
        } else {
            throw new RuntimeException("Teacher not found with id: " + id);
        }
    }

    @Override
    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public Teacher getTeacherById(Long id) {
        return teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + id));
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    public Double getAllTeachersMonthlyPaidByUniversity() {
        double totalMonthlyPayment = teacherDao.findTotalMonthlySalaryByUniversity();
        System.out.println("Total monthly salary paid by the university: " + totalMonthlyPayment);
        return totalMonthlyPayment;
    }

    @Override
    public List<SubjectDto> getAllSubject() {

        return teacherDao.getAllUniqueSubjects();
    }
}
