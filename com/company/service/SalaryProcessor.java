package com.company.service;

import com.company.exception.InvalidDataFormatException;
import com.company.model.Employee;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class SalaryProcessor {
    private final List<String> filePaths;
    private final List<Employee> allEmployees = new ArrayList<>();
    private final ReentrantLock lock = new ReentrantLock();
    private ExecutorService executor; // artık field olarak tanımlı

    public SalaryProcessor(List<String> filePaths){
        this.filePaths = filePaths;
    }

    public void processFilesConcurrently() throws InterruptedException, ExecutionException {
        // 1) Thread pool başlat
        startThreads();

        List<Future<List<Employee>>> futures = new ArrayList<>();

        // 2) Her dosya için Callable görevi oluştur ve submit et
        for (String path : filePaths) {
            Callable<List<Employee>> task = () -> {
                try {
                    return readEmployeesFromFile(path);
                } catch (IOException | InvalidDataFormatException e) {
                    System.err.println("Error processing file '" + path + "': " + e.getMessage());
                    return Collections.emptyList();
                }
            };
            futures.add(executor.submit(task));
        }

        // 3) Future'ların sonuçlarını topla ve birleştir
        for (Future<List<Employee>> future : futures) {
            List<Employee> employees = future.get();
            mergeResults(employees);
        }

        // 4) Thread pool'u kapat
        executor.shutdown();
        if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
            executor.shutdownNow();
        }

        // 5) Nihai rapor oluştur
        ReportGenerator reportService = new ReportGenerator();
        reportService.generateReport(getAllEmployees());
    }

    private void startThreads() {
        int poolSize = Math.max(1, Math.min(filePaths.size(), Runtime.getRuntime().availableProcessors()));
        executor = Executors.newFixedThreadPool(poolSize); // artık field initialize ediliyor
        System.out.println("Starting thread pool with " + poolSize + " threads.");
    }

    private void mergeResults(List<Employee> employees) {
        if (employees == null || employees.isEmpty()) return;
        synchronized (allEmployees) {
            allEmployees.addAll(employees);
        }
    }

    public List<Employee> getAllEmployees() {
        synchronized (allEmployees) {
            return Collections.unmodifiableList(new ArrayList<>(allEmployees));
        }
    }

    private List<Employee> readEmployeesFromFile(String filePath) throws IOException, InvalidDataFormatException {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            throw new IOException("File not found: " + filePath);
        }

        List<String> lines = Files.readAllLines(path);
        List<Employee> result = new ArrayList<>();

        for (int i = 0; i < lines.size(); i++) {
            String raw = lines.get(i).trim();
            if (raw.isEmpty()) continue;

            String[] parts = raw.split(",");
            if (parts.length != 4) {
                throw new InvalidDataFormatException("Invalid column count at line " + (i + 1) + " in " + filePath);
            }

            int id = Integer.parseInt(parts[0].trim());
            String name = parts[1].trim();
            String department = parts[2].trim();
            double salary;
            try {
                salary = Double.parseDouble(parts[3].trim());
            } catch (NumberFormatException e) {
                throw new InvalidDataFormatException("Invalid salary number at line " + (i + 1) + " in " + filePath);
            }

            result.add(new Employee(id, name, department, salary));
        }

        return result;
    }
}
