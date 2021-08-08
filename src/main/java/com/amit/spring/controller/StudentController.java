//package com.amit.spring.controller;
//
//import com.amit.spring.model.request.UpdateClassRequest;
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
//    @GetMapping
//    public BaseResponse<List<Student>> getAllStudent()  {
//        return this.studentService.getAllStudent();
//    }
//
//    @GetMapping(value = "/{id}")
//    public BaseResponse<Student> getDetailStudent(@PathVariable long id) {
//        return this.studentService.getDetailStudent(id);
//    }
//
//    @PostMapping
//    public BaseResponse<String> createdStudent(@RequestBody AddStudentRequest request)
//    {
//        return this.studentService.createdStudent(request);
//    }
//
//    @PutMapping(value = "{id}")
//    public BaseResponse<String> updatedClass(@PathVariable long id,@RequestBody UpdateClassRequest request) throws ApiException
//    {
//        return this.studentService.updatedClass(id,request);
//    }
//
//    @DeleteMapping(value = "/{id}")
//    public BaseResponse<String> deleteClass(@PathVariable lng id) throws  ApiException
//    {
//        return this.studentService.deleteClass(id);
//    }
//}
