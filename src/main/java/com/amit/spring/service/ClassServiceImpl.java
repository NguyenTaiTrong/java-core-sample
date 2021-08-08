package com.amit.spring.service;

import com.amit.spring.model.Class;
import com.amit.spring.model.request.AddClassRequest;
import com.amit.spring.model.request.UpdateClassRequest;
import com.amit.spring.model.response.BaseResponse;
import com.amit.spring.model.utils.ApiException;
import com.amit.spring.model.utils.ERROR;
import com.amit.spring.queue.MessagePublisher;
import com.amit.spring.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassServiceImpl implements IClassService {

    public static int id = 0;

    @Autowired
    ClassRepository classRepository;

    @Autowired
    MessagePublisher messagePublisher;

    @Autowired
    RedisTemplate<Integer, Object> redisTemplate;

    @Override
    public BaseResponse<Class> createdClass(AddClassRequest request) {
        BaseResponse<Class> response = new BaseResponse<>();

        Class aClass = new Class();
        aClass.setName(request.getName());
        aClass.setDescription(request.getDescription());
        aClass.setId(this.id + 1);
        classRepository.save(aClass);

        response.setData(aClass);
        this.id += 1;
        messagePublisher.publish("You created class:"+  request.getName());

        return response;
    }

    @Override
    public BaseResponse<Class> getClassById(long id) {
        Class aClass = classRepository.findById(id).get();
        if (aClass == null)
        {
            return new BaseResponse<>(409,"Class name is exist");
        }

        BaseResponse<Class> response = new BaseResponse<>();
        messagePublisher.publish("Get class detail by Id :"+  id);
        if (aClass != null)
        {
            response.setData(aClass);
        }
        return response;
    }

    @Override
    public BaseResponse<String> deleteClassById(long id) {
        Class aClass = classRepository.findById(id).get();
        if (aClass == null)
        {
            return new BaseResponse<>(404,"Class not found");
        }
        classRepository.deleteById(id);
        BaseResponse<String> response = new BaseResponse<>();
        return response;
    }

    @Override
    public BaseResponse<List<Class>> getAll() {
        Iterable<Class> aClass = classRepository.findAll();
        BaseResponse<List<Class>> response = new BaseResponse<>();
        response.setData((List<Class>) aClass);
        return response;
    }

    @Override
    public BaseResponse<Class> updatedClass(UpdateClassRequest request,long id) {
        BaseResponse<Class> response = new BaseResponse<>();
        Class aClass = classRepository.findById(id).get();
        if (aClass == null)
        {
            return new BaseResponse<>(404,"Class not found");
        }

        aClass.setName(request.getNameUpdate());
        classRepository.save(aClass);
        response.setData(aClass);
        return response;
    }
}
