package org.sang.servlet;

import org.sang.bean.MeetingRoom;
import org.sang.service.MeetingRoomService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookMeetingServlet extends HttpServlet {
    private MeetingRoomService meetingRoomService = new MeetingRoomService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<MeetingRoom> list = meetingRoomService.getAllMeetingRoom();
        req.setAttribute("mrs", list);
        req.getRequestDispatcher("/bookmeeting.jsp").forward(req, resp);
    }
}
