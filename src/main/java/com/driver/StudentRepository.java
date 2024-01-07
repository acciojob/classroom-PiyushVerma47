package com.driver;
import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {
    HashMap<String, Student> studentMap = new HashMap<>();
    HashMap<String, Teacher> teacherMap = new HashMap<>();
    HashMap<String, List<String>> teacherStudentMap = new HashMap<>();

    public void addStudent(Student student){
        String stName = student.getName();
        studentMap.put(stName,student);
    }

    public void addTeacher(Teacher teacher){
        String tName = teacher.getName();
        teacherMap.put(tName,teacher);
    }

    public void addStudentTeacherPair(String studentName , String teacherName){
        List<String> list;
        if(teacherStudentMap.containsKey(teacherName)){
            list = teacherStudentMap.get(teacherName);
        }
        else list = new ArrayList<>();
        list.add(studentName);
        teacherStudentMap.put(teacherName,list);
    }

    public Student getStudentByName(String studentName){
        if(studentMap.containsKey(studentName))
            return studentMap.get(studentName);
        return null;
    }

    public Teacher getTeacherByName(String teacherName){
        if(teacherMap.containsKey(teacherName))
            return teacherMap.get(teacherName);
        return null;
    }

    public List<String> getStudentsByTeacherName(String teacherName){
        if(teacherStudentMap.containsKey(teacherName)){
            return teacherStudentMap.get(teacherName);
        }
        return new ArrayList<>();
    }

    public List<String> getAllStudents(){
        List<String> list = new ArrayList<>();
        for(String studentName : studentMap.keySet()){
            list.add(studentName);
        }
        return list;
    }

    public void deleteTeacherByName(String teacherName){
        if(teacherStudentMap.containsKey(teacherName)){
            List<String> list = teacherStudentMap.get(teacherName);
            for(String studentName : list){
                if(studentMap.containsKey(studentName))
                    studentMap.remove(studentName);
            }
        }
        teacherMap.remove(teacherName);
        teacherStudentMap.remove(teacherName);
    }

//    public void deleteAllTeachers(){
//        for(String teacherName : teacherMap.keySet()){
//            deleteTeacherByName(teacherName);
//        }
//    }

    public void deleteAllTeachers(){
        List<String> teacherList = new ArrayList<>();
        for(String teacherName : teacherMap.keySet()){
            teacherList.add(teacherName);
        }
        for(String teacherName : teacherList){
            if(teacherStudentMap.containsKey(teacherName)){
                List<String> studentList = teacherStudentMap.get(teacherName);
                for(String studentName : studentList){
                    if(studentMap.containsKey(studentName))
                        studentMap.remove(studentName);
                }
            }
            teacherMap.remove(teacherName);
            teacherStudentMap.remove(teacherName);
        }
    }
}
