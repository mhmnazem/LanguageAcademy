package com.languageacademy.register.service.impl;

import com.languageacademy.register.domain.Student;
import com.languageacademy.register.repository.StudentRepository;
import com.languageacademy.register.service.StudentService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Student}.
 */
@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private final Logger log = LoggerFactory.getLogger(StudentServiceImpl.class);

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student save(Student student) {
        log.debug("Request to save Student : {}", student);
        return studentRepository.save(student);
    }

    @Override
    public Student update(Student student) {
        log.debug("Request to update Student : {}", student);
        return studentRepository.save(student);
    }

    @Override
    public Optional<Student> partialUpdate(Student student) {
        log.debug("Request to partially update Student : {}", student);

        return studentRepository
            .findById(student.getId())
            .map(existingStudent -> {
                if (student.getFirstName() != null) {
                    existingStudent.setFirstName(student.getFirstName());
                }
                if (student.getLastName() != null) {
                    existingStudent.setLastName(student.getLastName());
                }
                if (student.getNationalNumber() != null) {
                    existingStudent.setNationalNumber(student.getNationalNumber());
                }
                if (student.getBirthCertificateId() != null) {
                    existingStudent.setBirthCertificateId(student.getBirthCertificateId());
                }
                if (student.getGender() != null) {
                    existingStudent.setGender(student.getGender());
                }
                if (student.getDateOfBirth() != null) {
                    existingStudent.setDateOfBirth(student.getDateOfBirth());
                }
                if (student.getPlaceOfBirth() != null) {
                    existingStudent.setPlaceOfBirth(student.getPlaceOfBirth());
                }
                if (student.getPhoneNumber() != null) {
                    existingStudent.setPhoneNumber(student.getPhoneNumber());
                }
                if (student.getMobileNumber() != null) {
                    existingStudent.setMobileNumber(student.getMobileNumber());
                }

                return existingStudent;
            })
            .map(studentRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Student> findAll() {
        log.debug("Request to get all Students");
        return studentRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Student> findOne(Long id) {
        log.debug("Request to get Student : {}", id);
        return studentRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Student : {}", id);
        studentRepository.deleteById(id);
    }
}
