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
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/class")
public class ClassController {
    @Autowired
    private ClassRepository classRepository;
    private static final Logger LOGGER = LogManager.getLogger(ClassController.class);

    @GetMapping
    public BaseResponse<List<Class>> getAllClass() throws ApiException {
        BaseResponse<List<Class>> response = new BaseResponse<>();
        response.setData(this.classRepository.findAll());
        return response;
    }

    @GetMapping(value = "/{id}")
    public BaseResponse<Class> getDetailClass(@PathVariable long id) throws ApiException{
        BaseResponse<Class> response = new BaseResponse<>();
        response.setData(this.classRepository.getById(id));
        return  response;
    }

    @PostMapping
    public BaseResponse<String> createdClass(@RequestBody AddClassRequest request) throws ApiException
    {
        if (StringUtils.isEmpty(request.getName())){
            LOGGER.debug("Classname empty" );
            throw new ApiException(ERROR.INVALID_PARAM , "Tên của lớp không được để trống");
        }
        Class newClass = new Class();
        newClass.setName(request.getName());
        this.classRepository.save(newClass);
        return new BaseResponse<>();
    }

    @PutMapping(value = "{id}")
    public BaseResponse<String> updatedClass(@PathVariable long id,@RequestBody UpdateClassRequest request) throws ApiException
    {
        Class newClass = new Class();
        newClass = this.classRepository.getById(id);
        newClass.setName(request.getNameUpdate());
        this.classRepository.save(newClass);
        return new BaseResponse<>();
    }

    @DeleteMapping(value = "/{id}")
    public BaseResponse<String> deleteClass(@PathVariable long id) throws  ApiException
    {
       Class classDel = new Class();
       classDel =  this.classRepository.getById(id);
       this.classRepository.delete(classDel);
       return new BaseResponse<>(204,"Delete Success");
    }
}
