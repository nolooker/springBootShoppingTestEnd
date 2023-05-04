package com.shopping.controller;

import com.shopping.entity.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeTestController {
    @GetMapping("/employee")
    public Employee test(){
        Employee bean = new Employee();
        bean.setAge(20);
        bean.setId("yusin");
        bean.setName("김유신");
        return bean ;
    }
}
