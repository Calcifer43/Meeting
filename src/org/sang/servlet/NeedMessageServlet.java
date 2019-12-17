package org.sang.servlet;

import org.sang.bean.Employee;
import org.sang.bean.Meeting;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

@WebServlet(name = "NeedMessageServlet")
public class NeedMessageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String meetingname = req.getParameter("meetingname");
        String numberofparticipants = req.getParameter("numberofparticipants");
        String starttime = req.getParameter("starttime");
        String endtime = req.getParameter("endtime");
        String hotelname = req.getParameter("hotelname");
        String roomid = req.getParameter("roomid");
        String description = req.getParameter("description");
        String[] selSelectedEmployees = req.getParameterValues("selSelectedEmployees");
        //获取当前登录的用户对象
        Employee loginUser = (Employee) req.getSession().getAttribute("loginUser");
        Meeting meeting = new Meeting(hotelname,meetingname, Integer.parseInt(roomid), loginUser.getEmployeeid(), Integer.parseInt(numberofparticipants), Timestamp.valueOf(starttime), Timestamp.valueOf(endtime), new Timestamp(System.currentTimeMillis()), description);
        req.setAttribute("meeting",meeting);
        req.setAttribute("selSelectedEmployees",selSelectedEmployees);
        resp.sendRedirect(req.getContextPath() + "/needmessage.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
