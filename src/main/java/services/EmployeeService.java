package services;

import models.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeService implements IEmployeeService {

    @Override
    public Optional findEmployee(Integer id) {

        Optional result = Optional.empty();

        try (Connection conn = ConnectionManager.getConnection()) {
            final String SQL_SELECT = "SELECT * FROM employee WHERE id = ?;";
            PreparedStatement statement = conn.prepareStatement(SQL_SELECT);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = Optional.of(
                        new Employee(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getString("email"),
                                resultSet.getDouble("salary")));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return result;
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<>();

        try (Connection conn = ConnectionManager.getConnection()) {
            final String SQL_SELECT = "SELECT * FROM employee;";
            PreparedStatement statement = conn.prepareStatement(SQL_SELECT);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                employees.add(new Employee(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getDouble("salary")));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return employees;
    }

    @Override
    public Optional findByName(String name) {
        Optional result = Optional.empty();

        try (Connection conn = ConnectionManager.getConnection()) {
            final String SQL_SELECT = "SELECT * FROM employee WHERE name = ?;";
            PreparedStatement statement = conn.prepareStatement(SQL_SELECT);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = Optional.of(
                        new Employee(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getString("email"),
                                resultSet.getDouble("salary")));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return result;
    }

    @Override
    public void deleteEmployee(Employee employee) {
        try (Connection conn = ConnectionManager.getConnection()) {
            final String SQL_DELETE = "DELETE FROM employee WHERE id = ?;";
            PreparedStatement statement = conn.prepareStatement(SQL_DELETE);
            statement.setInt(1, employee.getId());
            statement.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public boolean Add(Employee employee) {
        try (Connection conn = ConnectionManager.getConnection()) {
            final String SQL_INSERT = "INSERT INTO employee(name, email, salary) VALUES(?,?,?)";
            PreparedStatement statement = conn.prepareStatement(SQL_INSERT);
            statement.setString(1, employee.getName());
            statement.setString(2, employee.getEmail());
            statement.setDouble(3, employee.getSalary());

            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean Update(Employee employee) {
        try (Connection conn = ConnectionManager.getConnection()) {
            final String SQL_UPDATE = "UPDATE employee SET name = ?, email = ?, salary = ? WHERE id = ?;";
            PreparedStatement statement = conn.prepareStatement(SQL_UPDATE);
            statement.setString(1, employee.getName());
            statement.setString(2, employee.getEmail());
            statement.setDouble(3, employee.getSalary());
            statement.setInt(4, (employee.getId()));

            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}