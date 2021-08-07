//package com.amit.spring.controller;
//
//import com.amit.spring.model.Class;
//import com.amit.spring.model.Student;
//import com.amit.spring.model.request.AddClassRequest;
//import com.amit.spring.model.request.AddStudentRequest;
//import com.amit.spring.model.response.BaseResponse;
//import com.amit.spring.model.utils.ApiException;
//import com.amit.spring.service.StudentService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/v1/student")
//public class StudentController {
//    @Autowired
//    private StudentService studentService;
//
//    @PostMapping(value = "/class/{id}")
//    public BaseResponse<String> createdClass(@PathVariable int id, @RequestBody AddStudentRequest request) throws ApiException
//    {
//        return studentService.createdStudent(id,request);
//    }
//
//    @GetMapping
//    public BaseResponse<List<Student>> getAllStudent() throws ApiException{
//        return studentService.getAllStudent();
//    }
//
//    @GetMapping(value = "/{id}")
//    public BaseResponse<Student> getDetailStudent(@PathVariable int id) throws ApiException{
//        return studentService.getDetailStudent(id);
//    }
//
//}
