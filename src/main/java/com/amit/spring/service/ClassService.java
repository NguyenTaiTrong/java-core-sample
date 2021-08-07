package com.amit.spring.service;

import com.amit.spring.aop.IncorrectFileExtensionException;
import com.amit.spring.model.Class;
import com.amit.spring.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(rollbackFor = IncorrectFileExtensionException.class)
public class ClassService {
    @Autowired
    private ClassRepository classRepository;

    public Class getClassByName(String name) throws IncorrectFileExtensionException
    {
        List<Class> listClass = classRepository.findAll();
        for (Class aClass:listClass) {
            if(aClass.getName().equals(name))
            {
                return aClass;
            }
        }
        return null;
    }
}
