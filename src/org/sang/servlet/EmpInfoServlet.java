package org.sang.servlet;

import org.sang.bean.Employee;
import org.sang.dao.EmployeeDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EmpInfoServlet")
public class EmpInfoServlet extends HttpServlet {
    private EmployeeDao employeeDao = new EmployeeDao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String empid = request.getParameter("empid");
        Employee employee = employeeDao.getEmpById(Integer.parseInt(empid));
        request.setAttribute("e",employee);
        request.getRequestDispatcher("/empinfo.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
