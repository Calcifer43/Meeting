package org.sang.servlet;

import org.sang.bean.Employee;
import org.sang.bean.Meeting;
import org.sang.bean.MeetingParticipants;
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

@WebServlet(name = "ApproveInputServlet")
public class ApproveInputServlet extends HttpServlet {
    private MeetingParticipantsService meetingParticipantsService = new MeetingParticipantsService();
    private MeetingService meetingService = new MeetingService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int empid = ((Employee) req.getSession().getAttribute("loginUser")).getEmployeeid();
        List<Meeting> mts = meetingService.getMyBookingMeeting(empid);
        List<MeetingParticipants> mts2=new ArrayList<MeetingParticipants>();
        for(Meeting m : mts){
            List<MeetingParticipants> list = meetingParticipantsService.getStateApproveaccount2(m.getMeetingid(),3);
            for(MeetingParticipants mp:list){
                mts2.add(mp);
            }
        }

        req.setAttribute("mts2",mts2);

        req.getRequestDispatcher("/approveinput.jsp").forward(req, resp);
    }
}
