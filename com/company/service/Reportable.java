package com.company.service;

import com.company.model.Employee;

import java.util.List;

public interface Reportable {
    void generateReport(List<Employee> employees);

}
