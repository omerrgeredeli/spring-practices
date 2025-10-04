package com.company.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Department {
    private String name;
    private List<Employee> employees = new ArrayList<>();
    public Department(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public List<Employee> getEmployees(){
        return employees;
    }

    public void addEmployee(Employee e){
        employees.add(e);
    }
    public double calculateAverageSalary(){
        double avgSalary = 0;
        for(Employee e : employees){
            avgSalary += avgSalary + e.getSalary();
        }
        return avgSalary / employees.size();
    }
    public Optional<Employee> findTopEarner(){
        double max = employees.get(0).getSalary(); // ilk elemanı başlangıç kabul ediyoruz

        for (int i = 1; i < employees.size(); i++) {
            if (employees.get(i).getSalary() > max) {
                max = employees.get(i).getSalary();
            }
        }

        System.out.println("Maksimum değer: " + max);

        return Optional.empty();
    }

}
