package com.amit.spring.model;


import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.*;

@Data
@RedisHash("class")
public class Class {
    @Id
    private int id;
    private String name;
    private String description;
}