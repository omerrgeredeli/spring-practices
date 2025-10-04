package com.company.service;

import com.company.model.Employee;

import java.util.*;
import java.util.stream.Collectors;

public class ReportGenerator implements Reportable{
    private Map<String, List<Employee>> departmentMap = new HashMap<>();

    @Override
    public void generateReport(List<Employee> employees){
        if (employees == null || employees.isEmpty()) {
            System.out.println("No employees found to generate report.");
            return;
        }

        groupByDepartment(employees);
        printGeneralStats(employees);
        printDepartmentStats();
    }
    private void groupByDepartment(List<Employee> employees){
        departmentMap = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
    private void printGeneralStats(List<Employee> employees){
        double avgSalary = employees.stream()
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0);

        Optional<Employee> highestPaid = employees.stream()
                .max(Comparator.comparingDouble(Employee::getSalary));

        System.out.println("=== General Employee Statistics ===");
        System.out.println("Total Employees: " + employees.size());
        System.out.printf("Average Salary: %.2f%n", avgSalary);
        highestPaid.ifPresent(emp ->
                System.out.println("Highest Paid Employee: " + emp.getName() + " (" + emp.getSalary() + ")"));
        System.out.println();
    }
    private void printDepartmentStats(){
        System.out.println("=== Department Statistics ===");
        departmentMap.forEach((dept, empList) -> {
            double avgSalary = empList.stream()
                    .mapToDouble(Employee::getSalary)
                    .average()
                    .orElse(0);
            System.out.println("Department: " + dept);
            System.out.println("Employee Count: " + empList.size());
            System.out.printf("Average Salary: %.2f%n", avgSalary);
            System.out.println("---------------------------");
        });
    }
    }


