package services;

import models.Employee;

import java.util.List;
import java.util.Optional;

public interface IEmployeeService {

    Optional findEmployee(Integer id);

    List<Employee> findAll();

    Optional findByName(String name);

    void deleteEmployee(Employee employee);

    boolean Add(Employee employee);

    boolean Update(Employee employee);
}