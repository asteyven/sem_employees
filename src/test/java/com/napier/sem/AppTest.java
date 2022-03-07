package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    @Test
    void printEmployeesTestNull()
    {
        EmployeeQueries.printEmployees(null);
    }

    @Test
    void printEmployeesTestEmpty()
    {
        ArrayList<Employee> employess = new ArrayList<Employee>();
        EmployeeQueries.printEmployees(employess);
    }

    @Test
    void printSalariesTestContainsNull()
    {
        ArrayList<Employee> employees = new ArrayList<Employee>();
        employees.add(null);
        EmployeeQueries.printEmployees(employees);
    }

    @Test
    void printSalaries()
    {
        ArrayList<Employee> employees = new ArrayList<Employee>();
        Employee emp = new Employee();
        emp.emp_no = 1;
        emp.first_name = "Kevin";
        emp.last_name = "Sim";
        emp.title = "Lecturer";
        emp.salary = 1000000000;
        employees.add(emp);
        EmployeeQueries.printEmployees(employees);
    }

}