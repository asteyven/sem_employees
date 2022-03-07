package com.napier.sem;


import java.sql.*;
import java.util.ArrayList;

public class App {

    /**
     * Connection to MySQL database.
     */
    private Connection con = null;


    public static void main(String[] args) {
        // Create new Application and connect to database
        App a = new App();

        if (args.length < 1) {
            a.connect("localhost:33060", 30000);
        } else {
            a.connect(args[0], Integer.parseInt(args[1]));
        }

        Department dept = a.getDepartment("Development");
        ArrayList<Employee> employees = a.getSalariesByDepartment(dept);

//        employees = a.getAllSalaries();
        // Print salary report
        EmployeeQueries.printEmployees(employees);

        System.out.println(employees.size());

        // Disconnect from database
        a.disconnect();
    }

    /**
     * Connect to the MySQL database.
     *
     * @param location Use db:3306 for docker and localhost:33060 for local or Integration Tests
     * @param delay    set to zero for local if db already running else 30000
     */
    public void connect(String location, int delay) {
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                // Wait a bit for db to start
                Thread.sleep(delay);
                // Connect to database
                //Added allowPublicKeyRetrieval=true to get Integration Tests to work. Possibly due to accessing from another class?
                con = DriverManager.getConnection("jdbc:mysql://"
                                + location
                                + "/employees?allowPublicKeyRetrieval=true&useSSL=false",
                        "root", "example");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect() {
        if (con != null) {
            try {
                // Close connection
                con.close();
            } catch (Exception e) {
                System.out.println("Error closing connection to database");
            }
        }
    }

    /**
     * Get Single Employee
     * @param ID
     * @return
     */
    public Employee getEmployee(int ID) {
        String sql = EmployeeQueries.getEmployeeSql(ID);
        return getEmployees(sql).get(0);
    }

    /**
     * Get List of Employees
     * @param strSelect
     * @return
     */
    public ArrayList<Employee> getEmployees(String strSelect) {
        ArrayList<Employee> employees = new ArrayList<>();
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            ResultSet rset = stmt.executeQuery(strSelect);

            while (rset.next()) {
                Employee emp = new Employee();
                emp.emp_no = rset.getInt("employees.emp_no");
                emp.first_name = rset.getString("employees.first_name");
                emp.last_name = rset.getString("employees.last_name");
                emp.title = rset.getString("titles.title");
                emp.salary = rset.getInt("salaries.salary");
                emp.dept_name = rset.getString("departments.dept_name");
                emp.manager = rset.getInt("dept_manager.emp_no");
                employees.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return employees;
    }

    /**
     * Gets all the current employees and salaries.
     *
     * @return A list of all employees and salaries, or null if there is an error.
     */
    public ArrayList<Employee> getAllSalaries() {
            // Create string for SQL statement
            String strSelect = EmployeeQueries.getAllSalariesSql();
            // Execute SQL statement
           return getEmployees(strSelect);
    }

    //TODO BELOW

    /**
     * Senior Engineer
     * Staff
     * Engineer
     * Senior Staff
     * Assistant Engineer
     * Technique Leader
     * Manager
     */
    public ArrayList<Employee> getSalariesByRole(String role) {

        String strSelect = "SELECT employees.emp_no, employees.first_name, employees.last_name, salaries.salary"
                + " FROM employees, salaries, titles"
                + " WHERE employees.emp_no = salaries.emp_no"
                + "  AND employees.emp_no = titles.emp_no"
                + "  AND salaries.to_date = '9999-01-01'"
                + "  AND titles.to_date = '9999-01-01'"
                + "  AND titles.title = '" + role + "'"
                + " ORDER BY employees.emp_no ASC";
            return getEmployees(strSelect);
    }

    /**
     * Customer Service
     * Development
     * Finance
     * Human Resources
     * Marketing
     * Production
     * Quality Management
     * Research
     * Sales
     *
     * @param dept_name
     * @return
     */
    public Department getDepartment(String dept_name) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT * FROM departments, dept_manager, employees" +
                    " WHERE dept_name = '" + dept_name + "'" +
                    "  AND  departments.dept_no = dept_manager.dept_no" +
                    "  AND dept_manager.to_date = '9999-01-01'" +
                    "  AND employees.emp_no = dept_manager.emp_no";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract employee information
            ArrayList<Employee> employees = new ArrayList<Employee>();
            if (rset.next()) {
                Employee manager = new Employee();
                manager.emp_no = rset.getInt("employees.emp_no");
                manager.first_name = rset.getString("employees.first_name");
                manager.last_name = rset.getString("employees.last_name");
                String dept_no = rset.getString("departments.dept_no");
                Department department = new Department(dept_no, dept_name, manager);
                return department;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get salary details");
            return null;
        }
        return null;
    }

    public ArrayList<Employee> getSalariesByDepartment(Department dept) {
            // Create string for SQL statement
            String strSelect = "SELECT employees.emp_no, employees.first_name, employees.last_name, salaries.salary" +
                    " FROM employees, salaries, dept_emp, departments" +
                    " WHERE employees.emp_no = salaries.emp_no" +
                    "  AND employees.emp_no = dept_emp.emp_no" +
                    "  AND dept_emp.dept_no = departments.dept_no" +
                    "  AND salaries.to_date = '9999-01-01'" +
                    "  AND departments.dept_no = '" + dept.getDept_no() + "'" +
                    " ORDER BY employees.emp_no ASC";
            return getEmployees(strSelect);
    }
}