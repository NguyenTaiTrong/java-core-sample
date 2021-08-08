package com.amit.spring.repository;

import com.amit.spring.model.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassRepository extends JpaRepository<Class,Long> {
    @Query("SELECT c FROM Class c WHERE c.name LIKE %:name%")
    List<Class> searchByClassLike(@Param("name") String name);
}
