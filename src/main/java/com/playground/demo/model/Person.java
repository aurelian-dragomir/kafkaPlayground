package com.playground.demo.model;

import lombok.Data;
import lombok.experimental.Accessors;

//@Accessors(fluent = true)
@Data
public class Person {
    private Long id;
    private String name;
}