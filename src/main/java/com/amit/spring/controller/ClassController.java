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
import com.amit.spring.service.ClassService;
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
    private ClassService classService;

    @GetMapping
    public BaseResponse<List<Class>> getAllClass() throws ApiException {
        return this.classService.getAllClass();
    }

    @GetMapping(value = "/{id}")
    public BaseResponse<Class> getDetailClass(@PathVariable long id) throws ApiException{
        return this.classService.getClassById(id);
    }

    @PostMapping
    public BaseResponse<String> createdClass(@RequestBody AddClassRequest request) throws ApiException
    {
        return this.classService.createdClass(request);
    }

    @PutMapping(value = "{id}")
    public BaseResponse<String> updatedClass(@PathVariable long id,@RequestBody UpdateClassRequest request) throws ApiException
    {
        return  this.classService.updatedClass(id,request);
    }

    @DeleteMapping(value = "/{id}")
    public BaseResponse<String> deleteClass(@PathVariable long id) throws  ApiException
    {
       return this.classService.deleteClass(id);
    }
    @GetMapping(value = "filter")
    public BaseResponse<List<Class>> filterClass(@RequestParam(value = "name") String name) {
        return this.classService.getClassByName(name);
    }

}
