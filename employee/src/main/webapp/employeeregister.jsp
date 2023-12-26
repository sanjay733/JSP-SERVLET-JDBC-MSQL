<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="employeeregistration.model.Employee" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Employee Registration and List</title>
    <style>
   body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 80%;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        h1 {
            text-align: center;
            color: #333;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            border: 1px solid #ccc;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        form {
            display: flex;
            flex-direction: column;
            align-items: center;
            margin-top: 20px;
        }
        label {
            margin-bottom: 5px;
            font-weight: bold;
        }
        input[type="text"],
        input[type="password"] {
            width: 250px;
            padding: 6px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        input[type="submit"] {
            padding: 8px 16px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        input[type="submit"]:hover {
            background-color: #0056b3;
        }    </style>
</head>
<body>
    <div class="container">
        <h1>Employee Register Form</h1>

     <form action="EmployeeServlet" method="post">
    <label for="action">Action:</label>
    <select id="actionInput" name="action">
        <option value="save">Save</option>
        <option value="search">Search</option>
        <option value="delete">Delete</option>
        <option value="update">Update</option>
    </select><br>
    <label for="tableView">tableView:</label>
   <select id="tableView" name="tableView"> 
    <option value="normal">Normal</option>
    <option value="asc">Ascending</option>
    <option value="dec">Descending</option>
        <option value="fir10">First-10</option>
        <option value="last10">Last-10</option>
    
</select><br>
    <label for="firstname">First Name:</label>
    <input type="text" name="firstname" />
    <label for="lastName">Last Name:</label>
    <input type="text" name="lastname" />
    <label for="username">Username:</label>
    <input type="text" name="username" />
    <label for="password">Password:</label>
    <input type="password" name="password" />
    <label for="address">Address:</label>
    <input type="text" name="address" />
    <label for="contact">Contact No:</label>
    <input type="text" name="contact" />
    <input type="submit" value="Submit" />
</form>

     <div class="pagination">
    <label for="page">Page:</label>
    <select id="page" name="page">
        <% 
        
            int defaultPageSize = 15;
            int numberOfPages = (int) Math.ceil(90 / (double) defaultPageSize);
            
            for (int i = 1; i <= numberOfPages; i++) {
        %>
            <option value="<%= i %>"><%= i %></option>
        <% 
            }
        %>
    </select>
</div>

        <h1>Employee List</h1>
        <table>
    <table>
            <thead>
                <tr>
                    <th>First Name</th>
                                        <th>Last Name</th>
                                        <th>User Name</th>
                                        <th>Password</th>
                                        <th>Address</th>
                                        <th>Contact</th>
                    
                </tr>
            </thead>
            <tbody>
                <% 
                    List<Employee> employees = (List<Employee>)request.getAttribute("data");
                    if (employees != null) {
                        for (Employee employee : employees) {
                %>
                <tr>
                    <td><%= employee.getFirstName() %></td>
                                        <td><%= employee.getLastName() %></td>
                                        <td><%= employee.getUserName() %></td>
                                        <td><%= employee.getPassword() %></td>
                                        <td><%= employee.getAddress() %></td>
                                        <td><%= employee.getContact() %></td>
                    
                </tr>
                <%      
                        }
                    } else {
                %>
                <tr>
                    <td colspan="1">No data available</td>
                </tr>
                <% 
                    }
                %>
            </tbody>        </table>
    </div>
</body>
</html>

