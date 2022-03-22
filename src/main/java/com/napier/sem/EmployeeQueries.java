package com.napier.sem;

import java.util.ArrayList;

public class EmployeeQueries {

    static String basicQuery = "SELECT employees.emp_no, employees.first_name, employees.last_name, " +
            "titles.title, salaries.salary, departments.dept_name, dept_manager.emp_no FROM " +
            "employees, salaries, titles, departments, dept_emp, dept_manager WHERE employees" +
            ".emp_no = salaries.emp_no AND salaries.to_date = '9999-01-01' AND titles.emp_no " +
            "= employees.emp_no AND titles.to_date = '9999-01-01' AND dept_emp.emp_no = " +
            "employees.emp_no AND dept_emp.to_date = '9999-01-01' AND departments.dept_no = " +
            "dept_emp.dept_no AND dept_manager.dept_no = dept_emp.dept_no AND dept_manager" +
            ".to_date = '9999-01-01' ";

    /**
     * All current employees ordered by emp_no (limited to 500)
     * @return
     */
    public static String getAllSalariesSql() {
        String strSelect = basicQuery + "ORDER BY employees.emp_no ASC Limit 500";
        return strSelect;
    }

    /**
     * Single current Employee based on emp_no
     * @param emp_no
     * @return
     */
    public static String getEmployeeSql(int emp_no) {
        String strSelect = basicQuery + "AND employees.emp_no = '" + emp_no + "'";
        return strSelect;
    }

    public static String getAllSalariesByRoleSql(String role) {
        String strSelect = basicQuery + "AND titles.title = '" + role + "'";
        return strSelect;
    }
}
