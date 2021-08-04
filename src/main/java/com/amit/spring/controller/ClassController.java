package com.amit.spring.controller;

import com.amit.spring.model.Class;
import com.amit.spring.model.request.AddClassRequest;
import com.amit.spring.model.request.UpdateClassRequest;
import com.amit.spring.model.response.BaseResponse;
import com.amit.spring.model.utils.ApiException;
import com.amit.spring.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.invoke.MethodHandles;
import java.util.List;

@RestController
@RequestMapping("/v1/class")
public class ClassController {
    @Autowired
    private ClassService classService;

// list
    @GetMapping
    public BaseResponse<List<Class>> getAllClass() throws ApiException{
        return classService.getAllClass();
    }

// tim kiem theo ten
    @GetMapping(value = "/filter")
    public BaseResponse<Class> getClass(@RequestParam String name) throws ApiException{
        return classService.getClassByName(name);
    }
// tim kiem theo id
     @GetMapping(value = "/{id}")
     public BaseResponse<Class> getDetailClass(@PathVariable int id) throws ApiException{
         return classService.getDetailClass(id);
     }
// tao
    @PostMapping
    public BaseResponse<String> createdClass(@RequestBody AddClassRequest request) throws ApiException
    {
        return classService.createdClass(request);
    }

// sưa
     @PutMapping(value = "/{id}")
     public BaseResponse<String> updateClass(@PathVariable int id,@RequestBody UpdateClassRequest request) throws ApiException{
         return classService.updateClass(id,request);
     }

// xoa
    @DeleteMapping(value = "/{id}&&{name}")
    public BaseResponse<String> deleteClass(@PathVariable int id,@PathVariable String name) throws  ApiException
    {
        return  classService.deleteClass(id,name);
    }

// danh sach sinh vien
//    @GetMapping(value = "/{id}/students")
//    public BaseResponse<Class> getStudentsOfClass(@PathVariable int id,@RequestParam String name) throws ApiException{
//
//    }
// tong diem so
}
