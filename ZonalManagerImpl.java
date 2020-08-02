package com.dxc.ams2.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.dxc.ams2.crud.ZonalManagerCrud;
import com.dxc.ams2.dbconnection.DBConnection;
import com.dxc.ams2.entity.Agent;
import com.dxc.ams2.entity.Branch;
import com.dxc.ams2.entity.CustPolicy;
import com.dxc.ams2.entity.Manager;
import com.dxc.ams2.entity.ZonalManager;

public class ZonalManagerImpl implements ZonalManagerCrud {

	DBConnection db = new DBConnection();
	Connection c = db.getConnected();

//	public void checkConnection() {
//	
//	}
	@Override
	public void addManager(Manager m) {

		DBConnection db = new DBConnection();
		Connection c = db.getConnected();
		if (c == null) {
			System.out.println("connection failed");
		} else if (c != null) {
			System.out.println("Succesfully Connected to Database");
		}

		try {
			PreparedStatement ps = c.prepareStatement("insert into manager values(?,?,?,?,?,?)");

			ps.setString(1, m.getMgno());
			ps.setString(2, m.getFirstname());
			ps.setString(3, m.getLastname());
			ps.setString(4, m.getLoginname());
			ps.setString(5, m.getPassword());
			ps.setString(6, m.getEmailID());
			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<ZonalManager> listZonalManagers() {

		if (c == null) {
			System.out.println("connection failed");
		} else if (c != null) {
			System.out.println("Succesfully Connected to Database");
		}

		ArrayList<ZonalManager> al = new ArrayList<ZonalManager>();
		try {
			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery("select * from zonalmanager");
			while (rs.next()) {
				ZonalManager zm = new ZonalManager(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5));
				al.add(zm);
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
//		try {
//			c.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return al;
	}

	@Override
	public void addBranch(Branch b) {
		try {
			PreparedStatement ps = c.prepareStatement("insert into branch values(?,?,?,?,?)");

			ps.setString(1, b.getBRNO());
			ps.setString(2, b.getBrName());
			ps.setString(3, b.getBrAddress());
			ps.setString(4, b.getZMGNO());
			ps.setString(5, b.getManager());
			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public boolean replaceManager(String BRNO, String manager) {
		boolean rs=false ;
		try {
			Statement st = c.createStatement();
		rs = st.execute("update branch set manager='"+manager+"' where BRNO='"+BRNO+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	@Override
	public void logOut() {
		// TODO Auto-generated method stub
    System.exit(0);
	}
}
