package org.sang.servlet;

import org.sang.bean.Meeting;
import org.sang.service.MeetingService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CheackServlet")
public class CheackServlet extends HttpServlet {
    private MeetingService meetingService = new MeetingService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String meetingid = req.getParameter("meetingid");

        Meeting meeting = meetingService.getMeetingDetailsByMeetingId(Integer.parseInt(meetingid));
        req.setAttribute("mt",meeting);        String type = req.getParameter("type");
        req.setAttribute("type",type);
        req.getRequestDispatcher("/meetingdetails.jsp").forward(req, resp);
    }
}
