package com.dxc.ams2.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.dxc.ams2.crud.ManagerCrud;
import com.dxc.ams2.dbconnection.DBConnection;
import com.dxc.ams2.entity.Agent;
import com.dxc.ams2.entity.CustPolicy;
import com.dxc.ams2.entity.Manager;

public class ManagerImpl implements ManagerCrud {

	DBConnection db = new DBConnection();
	Connection c = db.getConnected();

	public void checkConnection() {
		if (c == null) {
			System.out.println("connection failed");
		} else if (c != null) {
			System.out.println("Succesfully Connected to Database");
		}
	}

	@Override
	public ArrayList<Manager> listManagers() {
		ArrayList<Manager> al = new ArrayList<Manager>();
		try {
			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery("select * from manager");
			while (rs.next()) {
				Manager m = new Manager(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6));
				al.add(m);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		try {
//			c.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return al;
	}

	@Override
	public ArrayList<Agent> listAgents() {
		ArrayList<Agent> al = new ArrayList<Agent>();
		try {
			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery("select * from agent");
			while (rs.next()) {
				Agent a = new Agent(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10),
						rs.getInt(11), rs.getString(12), rs.getInt(13));
				al.add(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		try {
//			c.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return al;
	}

	@Override
	public String agentPerformance(int AGNO) {
		Agent a = null;
		try {
			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery("select * from agent where AGNO='" + AGNO + "'");

			a = new Agent(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getInt(11),
					rs.getString(12), rs.getInt(13));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (((a.getpSold() * 100) / a.getTarget()) >= 50 || ((a.getpSold() * 100) / a.getTarget()) <= 75) {
			return "average";
		} else if (((a.getpSold() * 100) / a.getTarget()) >= 75) {
			return "Good";
		} else {
			return "chances of improvement";
		}
	}

	@Override
	public ArrayList<CustPolicy> policyDetail() {
		ArrayList<CustPolicy> al = new ArrayList<CustPolicy>();
		try {
			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery("select * from custpolicy");
			while (rs.next()) {
				CustPolicy cp = new CustPolicy(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7));
				al.add(cp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return al;
	}

	@Override
	public void addAgent(Agent a) {
		try {
			PreparedStatement ps = c.prepareStatement("insert into agent values(?,?,?,?,?,?,?,?,?,?,?,?,?)");

			ps.setString(1, a.getAGNO());
			ps.setString(2, a.getFname());
			ps.setString(3, a.getLname());
			ps.setString(4, a.getLoginname());
			ps.setString(5, a.getPassword());
			ps.setString(6, a.getpType());
			ps.setString(7, a.getBRNO());
			ps.setString(8, a.getEmail());
			ps.setString(9, a.getAddress());
			ps.setString(10, a.getPhoneNo());
			ps.setInt(11, a.getTarget());
			ps.setString(12, a.getTargetDate());
			ps.setInt(13, a.getpSold());
			int rs = ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void setTarget(String AGNO, String target) {
		boolean rs = false;
		try {
			Statement st = c.createStatement();
			rs = st.execute("update agent set target=" + target + " where AGNO=" + AGNO);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void logOut() {
		// TODO Auto-generated method stub
		System.exit(0);
	}

}
