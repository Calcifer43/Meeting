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
import java.util.List;

public class MeetingDetailsServlet extends HttpServlet {
    private MeetingService meetingService = new MeetingService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mid = req.getParameter("mid");
        String type = req.getParameter("type");
        Meeting meeting = meetingService.getMeetingDetailsByMeetingId(Integer.parseInt(mid));
        List<Employee> emps = meetingService.getEmps();
        List<MeetingParticipants> mps =meetingService.getMyMeetingParticipants(Integer.parseInt(mid));
        req.setAttribute("mps",mps);
        req.setAttribute("mt", meeting);
        req.setAttribute("emps", emps);
        req.setAttribute("type", type);
        req.getRequestDispatcher("/meetingdetails.jsp").forward(req, resp);
    }
}
