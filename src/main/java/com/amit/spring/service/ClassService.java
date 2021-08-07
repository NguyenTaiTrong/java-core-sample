//package com.amit.spring.service;
//
//import com.amit.spring.domain.ClassDomain;
//import com.amit.spring.model.Class;
//import com.amit.spring.model.request.AddClassRequest;
//import com.amit.spring.model.request.UpdateClassRequest;
//import com.amit.spring.model.response.BaseResponse;
//import com.amit.spring.model.utils.ApiException;
//import com.amit.spring.model.utils.ERROR;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//
//import java.util.List;
//
//@Service
//public class ClassService {
//    private static final Logger LOGGER = LogManager.getLogger(ClassService.class);
//
//    @Autowired
//    ClassDomain classDomain;
//
//    public BaseResponse<List<Class>> getAllClass(){
//        BaseResponse<List<Class>> response = new BaseResponse<>();
//        response.setData(classDomain.getAllClass());
//        return response;
//    }
//
//    public BaseResponse<Class> getDetailClass(int id){
//        BaseResponse<Class> response = new BaseResponse<>();
//        response.setData(classDomain.findId(id));
//        return response;
//    }
//
//    public BaseResponse<Class> getClassByName(String name){
//        BaseResponse<Class> response = new BaseResponse<>();
//        response.setData(classDomain.findByName(name));
//        return response;
//    }
//
//    public BaseResponse<String> createdClass(AddClassRequest request) throws ApiException{
//
//        if (StringUtils.isBlank(request.getName())){
//            LOGGER.debug("Classname blank" );
//            throw new ApiException(ERROR.INVALID_PARAM , "Tên của lớp không được để trống");
//        }
//        if (classDomain.findByName(request.getName()) != null){
//            LOGGER.debug("Classname {} existed" , request.getName());
//            throw new ApiException(ERROR.CLASS_NAME_EXIST);
//        }
//        classDomain.createdClass(request.getName());
//        return new BaseResponse<>();
//    }
//
//    public BaseResponse<String> updateClass(int id,UpdateClassRequest request) throws  ApiException{
//        classDomain.updateClass(id,request.getNameUpdate());
//        return new BaseResponse<>();
//    }
//
//    public BaseResponse<String> deleteClass(int id, String name) throws  ApiException{
//        classDomain.deleteClass(id,name);
//        return  new BaseResponse<>();
//    }
//
//
//
//
//}
