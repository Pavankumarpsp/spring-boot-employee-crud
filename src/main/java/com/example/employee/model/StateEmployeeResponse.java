package com.example.employee.model;

import java.util.List;

public class StateEmployeeResponse {

    private long totalEmployees;
    private List<Person> employees;

    public StateEmployeeResponse() {
    }

    public StateEmployeeResponse(
            long totalEmployees,
            List<Person> employees) {

        this.totalEmployees = totalEmployees;
        this.employees = employees;
    }

    public long getTotalEmployees() {
        return totalEmployees;
    }

    public void setTotalEmployees(long totalEmployees) {
        this.totalEmployees = totalEmployees;
    }

    public List<Person> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Person> employees) {
        this.employees = employees;
    }
}