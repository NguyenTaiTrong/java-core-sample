package com.amit.spring.controller;


import com.amit.spring.model.Class;
import com.amit.spring.model.request.AddClassRequest;
import com.amit.spring.model.request.UpdateClassRequest;
import com.amit.spring.model.response.BaseResponse;
import com.amit.spring.model.utils.ApiException;
import com.amit.spring.service.IClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/class")
public class ClassController {
    @Autowired
    IClassService classService;

    @GetMapping
    public BaseResponse<List<Class>> getAllClass() throws ApiException{
        return classService.getAll();
    }

    @GetMapping("/{id}")
    public BaseResponse<Class> getClass(@PathVariable long id) throws ApiException{
        return classService.getClassById(id);
    }

    @PostMapping()
    public BaseResponse<Class> createdClass(@RequestBody AddClassRequest request) throws ApiException {
        return classService.createdClass(request);
    }

    @DeleteMapping("/{id}")
    public BaseResponse<String> deleleClass(@PathVariable long id) throws ApiException{
        return classService.deleteClassById(id);
    }

    @PutMapping("/{id}")
    public BaseResponse<Class> updatedClass(@PathVariable long id, @RequestBody UpdateClassRequest request) throws ApiException{
        return classService.updatedClass(request,id);
    }

}
