package org.sang.dao;

import org.sang.bean.Employee;
import org.sang.bean.MeetingParticipants;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MeetingParticipantsDao {
    public int updateMepStatusById(int mid,int empid, int status,String[] whatis) {
        Connection con = null;
        PreparedStatement ps = null;
        String[] m = {"employeename","departmentname","noid","phone","time","sex","room"};
        try {
            con = DBUtils.getConnection();
            StringBuffer sql = new StringBuffer("UPDATE meetingparticipants set state=?");

            for (int i=0;i<7;i++){
                if(whatis[i]!=null){
                    sql.append(" , "+m[i]+"='"+whatis[i]+"'");

                }
            }
            sql.append(" WHERE employeeid=? and meetingid=?");
            ps = con.prepareStatement(sql.toString());
            ps.setInt(1, status);
            ps.setInt(2, empid);
            ps.setInt(3,mid);
            return ps.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(ps);
            DBUtils.close(con);
        }
        return -1;
    }
    public List<MeetingParticipants> getStateApproveaccount2(int mid,int state) {
        List<MeetingParticipants> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement("SELECT * FROM meetingparticipants WHERE state=? AND meetingid=? ");
            ps.setInt(1,state);
            ps.setInt(2,mid);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new  MeetingParticipants(mid,rs.getInt("employeeid"),rs.getTimestamp("time"),rs.getString("room"),rs.getString("employeename"),rs.getString("departmentname"),rs.getString("noid"),rs.getString("phone"),rs.getString("sex"),rs.getInt("state")));
            }
            return list;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(rs);
            DBUtils.close(ps);
            DBUtils.close(con);
        }
        return list;
    }
    public List<MeetingParticipants> getStateApproveaccount(int empid,int state) {
        List<MeetingParticipants> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement("SELECT * FROM meetingparticipants WHERE state=? AND employeeid=? ");
            ps.setInt(1,state);
            ps.setInt(2,empid);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new  MeetingParticipants(rs.getInt("meetingid"),empid,rs.getTimestamp("time"),rs.getString("room"),rs.getString("employeename"),rs.getString("departmentname"),rs.getString("noid"),rs.getString("phone"),rs.getString("sex"),rs.getInt("state")));
            }
            return list;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(rs);
            DBUtils.close(ps);
            DBUtils.close(con);
        }
        return list;
    }

    public void insert(int meetingid, String[] empids) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtils.getConnection();
            System.out.println("-----s");
            ps = con.prepareStatement("insert into meetingparticipants (meetingid,employeeid,state) values (?,?,?);");
            for (String empid : empids) {
                System.out.println(empid);
                ps.setInt(1, meetingid);
                ps.setInt(2, Integer.parseInt(empid));
                ps.setInt(3,2);
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(ps);
            DBUtils.close(con);
        }
    }
    public void insert(int meetingid, int empid) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement("insert into meetingparticipants (meetingid,employeeid,state) values (?,?,?);");
            ps.setInt(1, meetingid);
            ps.setInt(2, empid);
            ps.setInt(3,3);
            ps.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(ps);
            DBUtils.close(con);
        }
    }
    public void insert(int meetingid, int empid, Timestamp time, String room, String employeename, String departmentname, String noid, String phone, String sex) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement("insert into meetingparticipants (meetingid,empid,time,room,employeename,departmentname,noid,phone,sex,state) values (?,?,?,?,?,?,?,?,?,?);");
            ps.setInt(1, meetingid);
            ps.setInt(2, empid);
            ps.setTimestamp(3,time);
            ps.setString(4,room);
            ps.setString(5,employeename);
            ps.setString(6,departmentname);
            ps.setString(7,noid);
            ps.setString(8,phone);
            ps.setString(9,sex);
            ps.setInt(10,3);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(ps);
            DBUtils.close(con);
        }
    }
    public boolean isP(int mid,int emid){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement("SELECT * FROM meetingparticipants WHERE meetingid=? AND employeeid=? ");
            ps.setInt(1,mid);
            ps.setInt(2,emid);
            rs = ps.executeQuery();
            if(rs.next()) {
                return   2==rs.getInt("state");
            }
            return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(rs);
            DBUtils.close(ps);
            DBUtils.close(con);
        }
        return true;
    }
    public int c;
    public boolean isP3(int mid,int emid){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            ps = con.prepareStatement("SELECT * FROM meetingparticipants WHERE meetingid=? AND employeeid=? ");
            ps.setInt(1,mid);
            ps.setInt(2,emid);
            rs = ps.executeQuery();
            if(rs.next()) {
                return   3==rs.getInt("state");
            }
            return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(rs);
            DBUtils.close(ps);
            DBUtils.close(con);
        }
        return true;
    }
    public List<MeetingParticipants> getMyMeetParticipants(int mid) {
        List<MeetingParticipants> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

            con = DBUtils.getConnection();
            ps = con.prepareStatement("SELECT * FROM meetingparticipants WHERE state=1 AND meetingid=?");
            ps.setInt(1,mid);
            rs = ps.executeQuery();
            while (rs.next()) {
                if(rs.getInt("state")==1)list.add(new MeetingParticipants(mid,rs.getInt("employeeid"),rs.getTimestamp("time"),rs.getString("room"),rs.getString("employeename"),rs.getString("departmentname"),rs.getString("noid"),rs.getString("phone"),rs.getString("sex"),rs.getInt("state")));
            }

                return list;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(rs);
            DBUtils.close(ps);
            DBUtils.close(con);
        }
        return list;
    }

}
