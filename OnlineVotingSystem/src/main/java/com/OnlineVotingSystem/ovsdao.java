package com.OnlineVotingSystem;

//controller which is communicating with db

import java.sql.*;
public class ovsdao {

	
	Connection con=null;
	
	//method to get connection to db
	public void dbconnection()throws Exception {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/onlinevs","root","Manasi@06");
		
	}
	
	
	//method to register voter to db
	public int registervoters(voters v1)throws Exception {
		
		String query="insert into voters values(?,?,?,?,?,?)";
		
		PreparedStatement pst=con.prepareStatement(query);
		pst.setInt(1,v1.vno);
		pst.setString(2,v1.vname);
		pst.setString(3, v1.vplace);
		pst.setInt(4, v1.vmobile);
		pst.setInt(5, v1.vpassword);
		pst.setInt(6, v1.vvote);
		
	
		
		
		
		int res=pst.executeUpdate();
		return res;
	}
	
public int login(String uname,int pwd)throws Exception {
		
		//fetching the user details based on username
		String query="select * from voters where vname= '"+uname+"'";
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query);
		
		//checking wheter we user details or not
		if(rs.next()) {
			//fetching the password from db
			int password=rs.getInt(5);
			
			//matching the password
			if(password==pwd) {
				//login success
				return rs.getInt(1);
			}
			else {
				//bad password
				return 0;
			}
		}
		else {
			//unable to fetch user details
			return -1;
		}
		
		
		
	}
	
public int voting(int vvote,int vno)throws Exception {
	
	//fetching user details based on customer id
	String 	query2="select  * from voters where vno="+vno;
	
	Statement st=con.createStatement();
	
	ResultSet rs=st.executeQuery(query2);
	rs.next();
	
	//extracting account balance 
	int vote=rs.getInt(6);
	
	//updating 
			vvote+=vote;
			
			//storing the updated amount
			String query="update voters set vvote ="+vvote+" where vno="+vno;
			
			PreparedStatement pst=con.prepareStatement(query);
			
			pst.executeUpdate();
			//returning updated amount
					return vvote;
				
			
		}
	
public int changepwd(int currentpwd,int newpwd, int vno)throws Exception{
	//fetching user details
	String 	query2="select  * from voters where vno="+vno;
	
	Statement st=con.createStatement();
	
	ResultSet rs=st.executeQuery(query2);
	rs.next();
	//confirming existing password
	if(currentpwd==rs.getInt(5)) {
		//update new pwd in db
		String query="update voters set vpassword="+newpwd+" where vno="+vno;
		
			PreparedStatement pst=con.prepareStatement(query);
			pst.executeUpdate();
			return 1;
	}
	else {
		return 0;
	}
}

public int deleteAccount(int pwd, int vno)throws Exception{
	//fetching user details
	String 	query2="select  * from voters where vno="+vno;
	
	Statement st=con.createStatement();
	
	ResultSet rs=st.executeQuery(query2);
	rs.next();
	//confirming pwd
	if(pwd==rs.getInt(5)) {
	
		//delete the account
		String query="delete from voters where vno="+vno;
		
		PreparedStatement pst=con.prepareStatement(query);
			pst.executeUpdate();
		return 1;
	}
	else {
		return 0;
	}
}


public int vvote(int vvote, int res) {
	// TODO Auto-generated method stub
	return 0;
}


public int voting(int currentvote, int newvote, int res) {
	// TODO Auto-generated method stub
	return 0;
}


public int changevote(int currentvote, int newvote, int res) {
	// TODO Auto-generated method stub
	return 0;
}

















































}