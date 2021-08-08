package com.amit.spring.service;

import com.amit.spring.aop.IncorrectFileExtensionException;
import com.amit.spring.controller.ClassController;
import com.amit.spring.model.Class;
import com.amit.spring.model.request.AddClassRequest;
import com.amit.spring.model.request.UpdateClassRequest;
import com.amit.spring.model.response.BaseResponse;
import com.amit.spring.model.utils.ApiException;
import com.amit.spring.model.utils.ERROR;
import com.amit.spring.repository.ClassRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = IncorrectFileExtensionException.class)
public class ClassService {
    private static final Logger LOGGER = LogManager.getLogger(ClassService.class);

    @Autowired
    private ClassRepository classRepository;

    public BaseResponse<List<Class>> getAllClass()
    {
        BaseResponse<List<Class>> response = new BaseResponse<>();
        response.setData(this.classRepository.findAll());
        return response;
    }

    public  BaseResponse<Class> getClassById(long id)
    {
        BaseResponse<Class> response = new BaseResponse<>();
        response.setData(this.classRepository.getById(id));
        return  response;
    }

    public BaseResponse<String> createdClass(AddClassRequest request)
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

    public BaseResponse<String> updatedClass(long id,UpdateClassRequest request)
    {
        Class newClass = new Class();
        newClass = this.classRepository.getById(id);
        newClass.setName(request.getNameUpdate());
        this.classRepository.save(newClass);
        return new BaseResponse<>();
    }

    public BaseResponse<String> deleteClass(long id)
    {
        Class classDel = new Class();
        classDel =  this.classRepository.getById(id);
        this.classRepository.delete(classDel);
        return new BaseResponse<>(204,"Delete Success");
    }

    public BaseResponse<List<Class>> getClassByName(String name) throws IncorrectFileExtensionException
    {
        BaseResponse<List<Class>> response = new BaseResponse<>();
        response.setData(this.classRepository.searchByClassLike(name));
        return response;
    }
}
