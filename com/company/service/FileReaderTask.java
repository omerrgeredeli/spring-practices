package com.company.service;

import com.company.exception.InvalidDataFormatException;
import com.company.model.Employee;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class FileReaderTask implements Runnable{
    private final String filePath;
    private final List<Employee> sharedList;
    private final ReentrantLock lock;
    public FileReaderTask(String filePath, List<Employee> sharedList, ReentrantLock lock){
        this.filePath = filePath;
        this.sharedList = sharedList;
        this.lock = lock;
    }
    @Override
    public void run(){
        System.out.println("Runnable is working " + Thread.currentThread().getName());
    }
    private List<Employee> readFromFile(String filePath) throws IOException, InvalidDataFormatException{

        Path path = Paths.get(filePath);

        if (!Files.exists(path)) {
            throw new FileNotFoundException("File not found: " + filePath);
        }

        List<String> lines = Files.readAllLines(path);

        List<Employee> employees = new ArrayList<>();

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i).trim();

            if (line.isEmpty()) continue; // boş satırları atla

            String[] parts = line.split(",");

            if (parts.length != 4) {
                throw new InvalidDataFormatException("Invalid format at line " + (i + 1) + ": " + line);
            }

            int id = Integer.parseInt(parts[0].trim());
            String name = parts[1].trim();
            String department = parts[2].trim();
            double salary;

            try {
                salary = Double.parseDouble(parts[3].trim());
            } catch (NumberFormatException e) {
                throw new InvalidDataFormatException("Invalid salary at line " + (i + 1) + ": " + parts[3]);
            }

            employees.add(new Employee(id,name, department, salary));
        }

        return employees;
    }
    }


