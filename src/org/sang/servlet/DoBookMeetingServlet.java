package org.sang.servlet;

import org.sang.bean.Employee;
import org.sang.bean.Meeting;
import org.sang.bean.MeetingParticipants;
import org.sang.service.MeetingService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class DoBookMeetingServlet extends HttpServlet {
    private MeetingService meetingService = new MeetingService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String meetingname = req.getParameter("meetingname");
        String numberofparticipants = req.getParameter("numberofparticipants");
        String starttime = req.getParameter("starttime");
        String endtime = req.getParameter("endtime");
        String hotelname = req.getParameter("hotelname");
        String roomid = req.getParameter("roomid");
        String description = req.getParameter("description");
        String[] selSelectedEmployees = req.getParameterValues("selSelectedEmployees");
        StringBuffer whatis = new StringBuffer();
        String employeename = req.getParameter("employeename");if(employeename.equals("yes"))whatis.append("employeename ");
        String departmentname = req.getParameter("departmentname");if(departmentname.equals("yes"))whatis.append("departmentname ");
        String noid = req.getParameter("noid");if(noid.equals("yes"))whatis.append("noid ");
        String phone = req.getParameter("phone");if(phone.equals("yes"))whatis.append("phone ");
        String time = req.getParameter("time");if(time.equals("yes"))whatis.append("time ");
        String sex = req.getParameter("sex");if(sex.equals("yes"))whatis.append("sex ");
        String room = req.getParameter("room");if(room.equals("yes"))whatis.append("room ");

        //获取当前登录的用户对象
        Employee loginUser = (Employee) req.getSession().getAttribute("loginUser");
        Meeting meeting = new Meeting(hotelname,meetingname, Integer.parseInt(roomid), loginUser.getEmployeeid(), Integer.parseInt(numberofparticipants), Timestamp.valueOf(starttime), Timestamp.valueOf(endtime), new Timestamp(System.currentTimeMillis()), description);
        meeting.setWhatis(whatis.toString());
        meetingService.insert(meeting, selSelectedEmployees);
        resp.sendRedirect(req.getContextPath() + "/sucess.jsp");
    }
}

