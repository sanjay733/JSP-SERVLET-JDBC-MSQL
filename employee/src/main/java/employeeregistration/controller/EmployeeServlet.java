package employeeregistration.controller;
import java.util.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import employeeregistration.dao.operations;
import employeeregistration.model.Employee;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
@WebServlet("/register")
public class EmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final operations ops = new operations();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            process(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            process(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            process(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equals("save")) {
            save(request, response);
        } else if (action.equals("delete")) {
            del(request, response);
        }
        else if(action.equals("update")) {
        	upd(request, response);
        }
        else if(action.equals("search")) {
        	search(request,response);
        }
        get(request,response);
        
        
    }

    private void save(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastName");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        String contact = request.getParameter("contact");

        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setUserName(username);
        employee.setPassword(password);
        employee.setContact(contact);
        employee.setAddress(address);

        ops.insert(employee);
        get(request, response);
    }

    private void get(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
        String tableView = request.getParameter("tableView");
        List<Employee> data = ops.display();
      // System.out.println("hello  "+request.getParameter("firstname")+"    "+tableView);
        if (tableView != null) {
            if ("normal".equals(tableView)) {
                data.removeIf(employee -> employee.getFirstName() == null);
            } else if ("asc".equals(tableView)) {
                Collections.sort(data, Comparator.comparing(Employee::getFirstName));
            } else if ("desc".equals(tableView)) {
                Collections.sort(data, Comparator.comparing(Employee::getFirstName).reversed());
            }
            else if("fir10".equals(tableView)) {
            	 if (data.size() > 10) {
                     data.subList(10, data.size()).clear();
                 }
            }
            else if("last10".equals(tableView)) {
            	 if (data.size() > 10) {
                     data.subList(0, data.size() - 10).clear();
                 }
            }
        }

        request.setAttribute("data", data);
        request.getRequestDispatcher("employeeregister.jsp").forward(request, response);
    }
    
    
    private void del(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
        String name = request.getParameter("firstname");
        ops.delete(name);
        get(request, response);
    }
    
    
    public void upd(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
    	 String firstName = request.getParameter("firstname");
    	    String lastName = request.getParameter("lastname");
    	    String username = request.getParameter("username");
    	    String password = request.getParameter("password");
    	    String address = request.getParameter("address");
    	    String contact = request.getParameter("contact");

    	    ops.update(firstName, lastName, username, password, address, contact);
    	    get(request, response);
    	}
    
    
    public void search(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
        String firstName = request.getParameter("firstname");

        List<Employee> searchResult = ops.search(firstName);
        request.setAttribute("data", searchResult);
        request.getRequestDispatcher("employeeregister.jsp").forward(request, response);
    }
}
