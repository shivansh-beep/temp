package com.dxc.ams2.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.dxc.ams2.crud.AgentCrud;
import com.dxc.ams2.dbconnection.DBConnection;
import com.dxc.ams2.entity.Agent;
import com.dxc.ams2.entity.Appointment;
import com.dxc.ams2.entity.CustPolicy;
import com.dxc.ams2.entity.Customer;

public class AgentImpl implements AgentCrud {
	DBConnection db = new DBConnection();
	Connection c = db.getConnected();

	public void checkCOnnection() {
		if (c == null) {
			System.out.println("connection failed");
		} else if (c != null) {
			System.out.println("Succesfully Connected to Database");
		}
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
	public ArrayList<Appointment> appointmentDetails() {
		ArrayList<Appointment> al = new ArrayList<Appointment>();
		try {
			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery("select * from Appointment");
			while (rs.next()) {
				Appointment ap = new Appointment(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
				al.add(ap);
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
	public ArrayList<Customer> customertDetails(int CSNO) {
		ArrayList<Customer> al = new ArrayList<Customer>();
		try {
			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery("select * from customer where CSNO="+CSNO);
			while (rs.next()) {
				Customer cst = new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8));
				al.add(cst);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return al;
	}

	@Override
	public void newAppointment(Appointment ap) {
		try {
			PreparedStatement ps = c.prepareStatement("insert into appointment values(?,?,?,?)");

			ps.setString(1, ap.getAPID());
			ps.setString(2, ap.getAptDate());
			ps.setString(3, ap.getAptTime());
			ps.setString(4, ap.getCstName());
		
			int rs = ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean deleteAppointment(String APID) {
		boolean rs=false ;
		try {
			Statement st = c.createStatement();
		rs = st.execute("delete from appointment where APID='"+APID+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	@Override
	public void addCustomer(Customer cs) {
		try {
			PreparedStatement ps = c.prepareStatement("insert into Customer values(?,?,?,?,?,?,?,?)");

			ps.setString(1, cs.getCSNO());
			ps.setString(2, cs.getFname());
			ps.setString(3, cs.getLname());
			ps.setString(4, cs.getLoginname());
			ps.setString(5, cs.getPassword());
			ps.setString(6, cs.getEmailID());
			ps.setString(7, cs.getAddress());
			ps.setString(8, cs.getPhone());
			int rs = ps.executeUpdate();

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
