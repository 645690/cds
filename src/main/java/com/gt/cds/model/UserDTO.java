package com.gt.cds.model;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class UserDTO {

    private String name;

    private BigDecimal salary;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}
