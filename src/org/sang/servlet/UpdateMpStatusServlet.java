package org.sang.servlet;

import org.sang.bean.Employee;
import org.sang.service.EmployeeService;
import org.sang.service.MeetingParticipantsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet(name = "UpdateMpStatusServlet")
public class UpdateMpStatusServlet extends HttpServlet {
    private MeetingParticipantsService meetingParticipantsService = new MeetingParticipantsService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Enumeration<String> parametersname = req.getParameterNames();
        String[] whatis = new String[7];
        whatis[0]=req.getParameter("employeename");
        whatis[1]=req.getParameter("departmentname");
        whatis[2]=req.getParameter("noid");
        whatis[3]=req.getParameter("phone");
        whatis[4]=req.getParameter("time");
        whatis[5]=req.getParameter("sex");
        whatis[6]=req.getParameter("room");

        String status = req.getParameter("status");
        String mid = req.getParameter("mid");
        int loginEmpId = ((Employee) req.getSession().getAttribute("loginUser")).getEmployeeid();
        if(req.getParameter("empid")!=null){
            loginEmpId=Integer.parseInt(req.getParameter("empid"));
        }
        if(req.getParameter("type")!=null){
            meetingParticipantsService.insert(Integer.parseInt(mid),loginEmpId);;
        }
        int i = meetingParticipantsService.updateMepStatusById(Integer.parseInt(mid),loginEmpId,Integer.parseInt(status),whatis);
        if (i == 1) {
            resp.sendRedirect(req.getContextPath() + "/approveattend");
        } else {
            req.setAttribute("error", "审批失败");
            req.getRequestDispatcher("/approveattend").forward(req, resp);
        }
    }
}
