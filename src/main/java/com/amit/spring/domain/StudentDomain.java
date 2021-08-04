package com.amit.spring.domain;

import com.amit.spring.model.Class;
import com.amit.spring.model.Student;
import com.amit.spring.model.request.AddStudentRequest;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class StudentDomain {
    private Map<Integer, Student> cacheStudentById = new HashMap<>();
    private static int IDD = 0;

    private synchronized int getIDD(){
        IDD ++;
        return IDD;
    }
    public List<Student> getAllStudent(){
        return new ArrayList<>(cacheStudentById.values());
    }
    public Student findId(Integer id){
        return cacheStudentById.get(id);
    }

    public void createdStudentOfClass(Class aClass,String name){
        Student newStudent = new Student();
        newStudent.setId(this.getIDD());
        newStudent.setName(name);
        newStudent.setAClass(aClass);
        cacheStudentById.put(newStudent.getId(), newStudent);
    }

}
