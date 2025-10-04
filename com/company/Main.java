package com.company;

import com.company.model.Employee;
import com.company.service.SalaryProcessor;
import com.company.util.ExceptionHandler;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.company.util.FileUtil.writeReport;

public class Main {
    public static void main(String[] args) {

            // 1️⃣ Test dosyalarını hazırla
            try {
                writeReport("employees1.csv",
                        "1,John,IT,5000\n" +
                                "2,Alice,HR,6000\n" +
                                "3,Bob,IT,5500");

                writeReport("employees2.csv",
                        "4,Eve,Finance,7000\n" +
                                "5,Charlie,HR,6200\n" +
                                "6,Dave,Finance,7100");

            } catch (IOException e) {
                ExceptionHandler.handle(e);
                return;
            }

            // 2️⃣ Dosya yollarını listele
            List<String> filePaths = Arrays.asList("employees1.csv", "employees2.csv");

            // 3️⃣ SalaryProcessor ile dosyaları oku ve rapor üret
            SalaryProcessor processor = new SalaryProcessor(filePaths);

            try {
                processor.processFilesConcurrently();
            } catch (InterruptedException | ExecutionException e) {
                ExceptionHandler.handle(e);
            } catch (Exception e) {
                // Genel exception guard
                ExceptionHandler.handle(e);
            }

            // 4️⃣ Okunan Employee listesini konsola yazdır (isteğe bağlı)
            List<Employee> allEmployees = processor.getAllEmployees();
            System.out.println("=== All Employees ===");
            allEmployees.forEach(System.out::println);
        }

    }

