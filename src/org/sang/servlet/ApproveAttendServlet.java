package org.sang.servlet;

import org.sang.bean.Employee;
import org.sang.bean.Meeting;
import org.sang.bean.MeetingParticipants;
import org.sang.service.EmployeeService;
import org.sang.service.MeetingParticipantsService;
import org.sang.service.MeetingService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ApproveAttendServlet")
public class ApproveAttendServlet extends HttpServlet {
    private MeetingParticipantsService meetingParticipantsService = new MeetingParticipantsService();
    private MeetingService meetingService = new MeetingService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int empid = ((Employee) req.getSession().getAttribute("loginUser")).getEmployeeid();
        List<Meeting> mts = meetingService.getMyMeeting(empid);
        List<Meeting> mts2 = new ArrayList<>();
        for(Meeting m : mts){
            if(meetingParticipantsService.isP(m.getMeetingid(),empid))mts2.add(m);
        }
        List<MeetingParticipants> list = meetingParticipantsService.getStateApproveaccount(empid,2);

        req.setAttribute("list", list);
        req.setAttribute("mts2",mts2);

        req.getRequestDispatcher("/approveattend.jsp").forward(req, resp);
    }
}
