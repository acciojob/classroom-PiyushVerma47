package com.driver;
import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {
    HashMap<String, Student> studentMap = new HashMap<>();
    HashMap<String, Teacher> teacherMap = new HashMap<>();
    HashMap<Teacher, List<Student>> teacherStudentMap = new HashMap<>();
    public void addStudent(Student student){
        studentMap.put(student.getName(),student);
    }

    public void addTeacher(Teacher teacher){
        teacherMap.put(teacher.getName(), teacher);
    }


    public Student getStudentByName(String name) {
        return studentMap.get(name);
    }

    public Teacher getTeacherByName(String name) {
        return teacherMap.get(name);
    }

    public List<String> getAllStudents(){
        List<String> names = new ArrayList<>();
        for(String name : studentMap.keySet()){
            names.add(name);
        }
        return names;
    }

    public void deleteAllTeachers(){
        List<String> teacherNames = new ArrayList<>();
        for(String str : teacherMap.keySet()){
            teacherNames.add(str);
        }
        for(String teacher : teacherNames){
            List<Student> studentNames = teacherStudentMap.get(teacher);
            for(Student student : studentNames){
                String name = student.getName();
                if(studentMap.containsKey(name))
                studentMap.remove(name);
            }
        }
        teacherMap.clear();
        teacherStudentMap.clear();
    }

    public void deleteTeacherByName(String name){
        List<Student> studentNames = teacherStudentMap.get(name);
        for(Student s : studentNames){
            studentMap.remove(s.getName());
        }
        teacherMap.remove(name);
        teacherStudentMap.remove(getTeacherByName(name));
    }

    public void addStudentTeacherPair(String student, String teacher){
        Teacher tempTeacher = getTeacherByName(teacher);
        Student student1 = getStudentByName(student);
        List<Student> tempList = teacherStudentMap.getOrDefault(tempTeacher, new ArrayList<Student>());
        tempList.add(student1);
        teacherStudentMap.put(tempTeacher, tempList);
    }

    public List<String> getStudentsByTeacherName(String teacherName){
        Teacher teacher = getTeacherByName(teacherName);
        List<Student> list = teacherStudentMap.get(teacher);

        List<String> ans = new ArrayList<>();
        for(Student student : list){
            ans.add(student.getName());
        }
        return ans;
    }
}
