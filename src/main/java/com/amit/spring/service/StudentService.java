package com.amit.spring.service;

import com.amit.spring.controller.StudentController;
import com.amit.spring.model.Class;
import com.amit.spring.model.Student;
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
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ClassRepository classRepository;
    private static final Logger LOGGER = LogManager.getLogger(StudentController.class);

    public BaseResponse<List<Student>> getAllStudent() throws ApiException {
        BaseResponse<List<Student>> response = new BaseResponse<>();
        response.setData(this.studentRepository.findAll());
        return response;
    }

    public BaseResponse<Student> getDetailStudent(long id) throws ApiException{
        BaseResponse<Student> response = new BaseResponse<>();
        response.setData(this.studentRepository.getById(id));
        return  response;
    }

    public BaseResponse<String> createdStudent(AddStudentRequest request) throws ApiException
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

    public BaseResponse<String> updatedClass(long id, UpdateClassRequest request) throws ApiException
    {
        Student newStudent = new Student();
        newStudent = this.studentRepository.getById(id);
        newStudent.setName(request.getNameUpdate());
        this.studentRepository.save(newStudent);
        return new BaseResponse<>();
    }

    public BaseResponse<String> deleteClass(long id) throws  ApiException
    {
        Student studentDel = new Student();
        studentDel =  this.studentRepository.getById(id);
        this.studentRepository.delete(studentDel);
        return new BaseResponse<>(204,"Delete Success");
    }
}
