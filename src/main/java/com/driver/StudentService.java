package com.driver;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    StudentRepository stRepo;

    public void addStudent(Student student){
        stRepo.addStudent(student);
    }

    public void addTeacher(Teacher teacher){
        stRepo.addTeacher(teacher);
    }

    public Student getStudentByName(String name){
        return stRepo.getStudentByName(name);
    }

    public Teacher getTeacherByName(String name){
        return stRepo.getTeacherByName(name);
    }

    public List<String> getAllNames(){
        return stRepo.getAllStudents();
    }

    public void deleteAllTeachers(){
        stRepo.deleteAllTeachers();
    }

    public void deleteTeacherByName(String name){
        stRepo.deleteTeacherByName(name);
    }

    public void addStudentTeacherPair(String student, String teacher){
        stRepo.addStudentTeacherPair(student, teacher);
    }

    public List<String> getStudentsByTeacherName(String teacher){
        return stRepo.getStudentsByTeacherName(teacher);
    }
}
