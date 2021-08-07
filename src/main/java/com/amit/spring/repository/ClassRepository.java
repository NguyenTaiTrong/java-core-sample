package com.amit.spring.repository;

import com.amit.spring.model.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends JpaRepository<Class,Long> {
    @Query("SELECT c FROM Class c WHERE c.name = 'bad'")
    Class findClassBad();
}
