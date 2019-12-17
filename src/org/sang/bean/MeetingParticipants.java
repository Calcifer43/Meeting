package org.sang.bean;

import java.sql.DatabaseMetaData;
import java.sql.Timestamp;

public class MeetingParticipants {
    private int meetingid;
    private int employeeid;
    private Timestamp time;
    private String room;
    private String employeename;
    private String departmentname;
    private String noid;
    private String phone;
    private String sex;
    private int state;

    public MeetingParticipants() {
    }

    public MeetingParticipants(int meetingid, int employeeid, Timestamp time, String room, String employeename, String departmentname, String noid, String phone, String sex, int state) {
        this.meetingid = meetingid;
        this.employeeid = employeeid;
        this.time = time;
        this.room = room;
        this.employeename = employeename;
        this.departmentname = departmentname;
        this.noid = noid;
        this.phone = phone;
        this.sex = sex;
        this.state = state;
    }

    public int getMeetingid() {
        return meetingid;
    }

    public void setMeetingid(int meetingid) {
        this.meetingid = meetingid;
    }

    public int getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(int employeeid) {
        this.employeeid = employeeid;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getEmployeename() {
        return employeename;
    }

    public void setEmployeename(String employeename) {
        this.employeename = employeename;
    }

    public String getDepartmentname() {
        return departmentname;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
    }

    public String getNoid() {
        return noid;
    }

    public void setNoid(String noid) {
        this.noid = noid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
