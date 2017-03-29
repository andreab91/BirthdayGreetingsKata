package it.xpug.kata.birthday_greetings;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrea on 29/03/17.
 */
public class EmployeeFileDataSource implements IEmployeeDataSource {

    private List<Employee> employees = new ArrayList<Employee>();

    public EmployeeFileDataSource(String fileName) throws IOException, ParseException {
        readEmployees(fileName);
    }

    private void readEmployees(String fileName) throws IOException, ParseException {
        BufferedReader in = new BufferedReader(new FileReader(fileName));
        String str = "";
        str = in.readLine(); // skip header
        while ((str = in.readLine()) != null) {
            String[] employeeData = str.split(", ");
            Employee employee = new Employee(employeeData[1], employeeData[0], employeeData[2], employeeData[3]);
            employees.add(employee);
        }
    }

    public List<Employee> getEmployeesBornToday(DateWrapper date) {
        List<Employee> employeesBornToday = new ArrayList<Employee>();
        for(Employee employee: employees) {
            if(employee.isBirthday(date)) {
                employeesBornToday.add(employee);
            }
        }
        return employeesBornToday;
    }
}
