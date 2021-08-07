package com.amit.spring.controller;

import com.amit.spring.model.Class;
import com.amit.spring.model.Student;
import com.amit.spring.model.request.AddClassRequest;
import com.amit.spring.model.request.AddStudentRequest;
import com.amit.spring.model.request.UpdateClassRequest;
import com.amit.spring.model.response.BaseResponse;
import com.amit.spring.model.utils.ApiException;
import com.amit.spring.model.utils.ERROR;
import com.amit.spring.repository.ClassRepository;
import com.amit.spring.repository.StudentRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/student")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ClassRepository classRepository;
    private static final Logger LOGGER = LogManager.getLogger(StudentController.class);

    @GetMapping
    public BaseResponse<List<Student>> getAllStudent() throws ApiException {
        BaseResponse<List<Student>> response = new BaseResponse<>();
        response.setData(this.studentRepository.findAll());
        return response;
    }

    @GetMapping(value = "/{id}")
    public BaseResponse<Student> getDetailStudent(@PathVariable long id) throws ApiException{
        BaseResponse<Student> response = new BaseResponse<>();
        response.setData(this.studentRepository.getById(id));
        return  response;
    }

    @PostMapping
    public BaseResponse<String> createdStudent(@RequestBody AddStudentRequest request) throws ApiException
    {
        if (StringUtils.isEmpty(request.getName())){
            LOGGER.debug("StudentName empty" );
            throw new ApiException(ERROR.INVALID_PARAM , "Tên của lớp không được để trống");
        }
        Class aClass = this.classRepository.getById(request.getClassId());
        Student newStudent = new Student();
        newStudent.setName(request.getName());
        newStudent.setaClass(aClass);

        this.studentRepository.save(newStudent);
        return new BaseResponse<>(201,"Add student successfully");
    }

    @PutMapping(value = "{id}")
    public BaseResponse<String> updatedClass(@PathVariable long id,@RequestBody UpdateClassRequest request) throws ApiException
    {
        Student newStudent = new Student();
        newStudent = this.studentRepository.getById(id);
        newStudent.setName(request.getNameUpdate());
        this.studentRepository.save(newStudent);
        return new BaseResponse<>();
    }

    @DeleteMapping(value = "/{id}")
    public BaseResponse<String> deleteClass(@PathVariable long id) throws  ApiException
    {
        Student studentDel = new Student();
        studentDel =  this.studentRepository.getById(id);
        this.studentRepository.delete(studentDel);
        return new BaseResponse<>(204,"Delete Success");
    }
}
