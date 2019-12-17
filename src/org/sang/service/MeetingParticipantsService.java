package org.sang.service;

import org.sang.bean.Employee;
import org.sang.bean.MeetingParticipants;
import org.sang.dao.MeetingParticipantsDao;

import java.util.List;

public class MeetingParticipantsService {
    public MeetingParticipantsService() {
    }

    private MeetingParticipantsDao meetingParticipantsdao = new MeetingParticipantsDao();
    public List<MeetingParticipants> getStateApproveaccount(int empid,int state) {
        return meetingParticipantsdao.getStateApproveaccount(empid,state);
    }
    public List<MeetingParticipants> getStateApproveaccount2(int mid,int state) {
        return meetingParticipantsdao.getStateApproveaccount2(mid,state);
    }

    public int updateMepStatusById(int mid,int empid, int status,String[] whatis) {
        return meetingParticipantsdao.updateMepStatusById(mid,empid,status,whatis);
    }
    public boolean isP(int mid,int emid){
        return meetingParticipantsdao.isP(mid,emid);
    }
    public boolean isP3(int mid,int emid){
        return meetingParticipantsdao.isP3(mid,emid);
    }

    public void insert(int meetingid, int empid) {
        meetingParticipantsdao.insert(meetingid,empid);
    }
}
