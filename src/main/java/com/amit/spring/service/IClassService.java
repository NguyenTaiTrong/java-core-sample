package com.amit.spring.service;

import com.amit.spring.model.Class;
import com.amit.spring.model.request.AddClassRequest;
import com.amit.spring.model.request.UpdateClassRequest;
import com.amit.spring.model.response.BaseResponse;

import java.util.List;

public interface IClassService {
    BaseResponse<Class> createdClass(AddClassRequest request);
    BaseResponse<Class> getClassById(long id);
    BaseResponse<String> deleteClassById(long id);
    BaseResponse<List<Class>> getAll();
    BaseResponse<Class> updatedClass(UpdateClassRequest request, long id);
}
