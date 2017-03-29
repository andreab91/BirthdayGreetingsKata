package it.xpug.kata.birthday_greetings;

import java.util.List;

/**
 * Created by andrea on 29/03/17.
 */
public interface IEmployeeDataSource {
    List<Employee> getEmployeesBornToday(DateWrapper dateWrapper);
}
