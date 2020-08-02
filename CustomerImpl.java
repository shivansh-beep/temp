package com.dxc.ams2.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.dxc.ams2.crud.CustomerCrud;
import com.dxc.ams2.dbconnection.DBConnection;
import com.dxc.ams2.entity.Agent;
import com.dxc.ams2.entity.CustPolicy;
import com.dxc.ams2.entity.Customer;

public class CustomerImpl implements CustomerCrud {
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
	public ArrayList<Customer> customertInfo(int CSNO) {
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

	public boolean changePassword(int CSNO,String oldPassword,String newPassword) {
		boolean rs=false ;
		try {
			Statement st = c.createStatement();
		rs = st.execute("update customer set password='"+newPassword+"' where CSNO='"+CSNO +"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
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
	public void logOut() {
		// TODO Auto-generated method stub
   System.exit(0);
	}

}
