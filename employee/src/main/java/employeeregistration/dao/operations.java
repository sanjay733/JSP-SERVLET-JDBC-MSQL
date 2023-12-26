package employeeregistration.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import employeeregistration.model.Employee;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class operations {

    public void insert(Employee employee) {
    	  String query = "INSERT INTO employee ( first_name, last_name, username, password, address, contact) VALUES ( ?, ?, ?, ?, ?, ?)";
          try {Connection conn = BBConnection.getConnection();
               PreparedStatement statement = conn.prepareStatement(query);
//              statement.setLong(1, employee.getId());
              statement.setString(1, employee.getFirstName());
              statement.setString(2, employee.getLastName());
              statement.setString(3, employee.getUserName());
              statement.setString(4, employee.getPassword());
              statement.setString(5, employee.getAddress());
              statement.setString(6, employee.getContact());

              statement.executeUpdate();
          } catch (SQLException | ClassNotFoundException e) {
              e.printStackTrace();
          }
      }
    public List<Employee> display() {
        String query = "SELECT * FROM employee";
        List<Employee> employeeList = new ArrayList<>();

        try (Connection conn = BBConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setLastName(resultSet.getString("last_name"));
                employee.setUserName(resultSet.getString("username"));
                employee.setPassword(resultSet.getString("password"));
                employee.setAddress(resultSet.getString("address"));
                employee.setContact(resultSet.getString("contact"));
                employeeList.add(employee);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return employeeList;
    }
    

    public void delete(String name) {
        String query = "DELETE FROM employee WHERE first_name=?";
        try (Connection conn = BBConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {

            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public void update(String firstName, String lastName, String username, String password, String address, String contact) {
        String query = "UPDATE employee " +
                "SET " +
                "    last_name = ?, " +
                "    username = ?, " +
                "    password = ?, " +
                "    address = ?, " +
                "    contact = ? " +
                "WHERE " +
                "    first_name = ?";
        
        try (Connection conn = BBConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {

            preparedStatement.setString(1, lastName);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, address);
            preparedStatement.setString(5, contact);
            preparedStatement.setString(6, firstName); // Matched with the WHERE clause
            
            preparedStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public List<Employee> search(String name) {
        String query = "SELECT * FROM employee WHERE first_name=?";
        List<Employee> resultList = new ArrayList<>();

        try (Connection conn = BBConnection.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {

            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setLastName(resultSet.getString("last_name"));
                employee.setUserName(resultSet.getString("username"));
                employee.setPassword(resultSet.getString("password"));
                employee.setAddress(resultSet.getString("address"));
                employee.setContact(resultSet.getString("contact"));

                resultList.add(employee);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resultList;
    }
}
