//package com.amit.spring.service;
//
//import com.amit.spring.model.Class;
//import com.amit.spring.model.Student;
//import com.amit.spring.model.request.AddStudentRequest;
//import com.amit.spring.model.response.BaseResponse;
//import com.amit.spring.model.utils.ApiException;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//
//import java.util.List;
//
//@Service
//public class StudentService extends ClassService {
//    private static final Logger LOGGER = LogManager.getLogger(StudentService.class);
//    @Autowired
//    StudentDomain studentDomain;
//
//    public BaseResponse<String> createdStudent(int id,AddStudentRequest request) throws ApiException {
//        Class studentOfClass = getDetailClass(id).getData();
//        studentDomain.createdStudentOfClass(studentOfClass,request.getName());
//        return  new BaseResponse<>();
//    }
//
//    public BaseResponse<List<Student>> getAllStudent(){
//        BaseResponse<List<Student>> response = new BaseResponse<>();
//        response.setData(studentDomain.getAllStudent());
//        return response;
//    }
//
//    public BaseResponse<Student> getDetailStudent(int id){
//        BaseResponse<Student> response = new BaseResponse<>();
//        response.setData(studentDomain.findId(id));
//        return response;
//    }
//}
