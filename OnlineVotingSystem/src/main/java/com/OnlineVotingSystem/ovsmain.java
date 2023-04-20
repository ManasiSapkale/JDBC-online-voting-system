package com.OnlineVotingSystem;

import java.util.*;

public class ovsmain {



	public static void main(String[] args) throws Exception {
	
		Scanner bs=new Scanner(System.in);
		ovsdao dao=new ovsdao();
		voters v1=new voters();
		
		System.out.println("\t\t\t********Welcome to Online Voting System***********");
		
		System.out.println("Press 1 for Registration \nPress 2 for Login");
		int op=bs.nextInt();
		
		switch(op) {
		
		case 1->{
			System.out.println("Enter voter no");
			int vno=bs.nextInt();
			bs.nextLine();
			System.out.println("Enter voter Name");
			String vname=bs.nextLine();
			System.out.println("Enter voter place");
			String vplace=bs.nextLine();
		
			System.out.println("Enter voter mobile");
			int vmobile=bs.nextInt();
			System.out.println("Enter voter password");
			int vpassword=bs.nextInt();
			
			
			
			
			v1.vno=vno;
			v1.vname=vname;
			v1.vplace=vplace;
			v1.vmobile=vmobile;
			v1.vpassword=vpassword;
			int vvote = 0;
			v1.vvote=vvote;
			
			
			
			//getting connection to db
			dao.dbconnection();
			//inserting user details into db
			int res=dao.registervoters(v1);
			
			//if insertion is success response is 1 otherwise 0
			if(res==1) {
				System.out.println("account creation successful");
			}
			else {
				System.out.println("Something went wrong");
			}


		}
		case 2->{
			System.out.println("Welcome to Login Page");
			//reading username and password for login
			System.out.println("Enter Username");
			bs.nextLine();
			String uname=bs.nextLine();
			System.out.println("Enter Password");
			int pwd=bs.nextInt();
			
			
			//connecting to db
			dao.dbconnection();
			
			//login method
			int res=dao.login(uname, pwd);
			//handling the response
			if(res==0) {
				System.out.println("username/password are incorrect");
			}
			else if(res==-1) {
				System.out.println("Unable to find the details");
			}
			else {
				System.out.println("Login successful");
				System.out.println("Press 1 for voting\nPress 2 for change password\nPress 3 for Delete Acc");
				int ops=bs.nextInt();
				
				switch(ops) {
				
				case 1->{
					System.out.println("Enter amount to votes");
					int vvote=bs.nextInt();
					int vote=dao.voting(vvote,res );
					System.out.println("voting successful\\n Available vote is :"+vote);
					
			}
				
				case 2->{
					System.out.println("Enter current password");
					int currentpwd=bs.nextInt();
					System.out.println("Enter new password");
					int newpwd=bs.nextInt();
					
					int status=dao.changepwd(currentpwd, newpwd, res);
					if(status==1) {
						System.out.println("Password changed...");
					}
					else {
						System.out.println("Something went wrong");
					}
					
			}
			case 3->{
					System.out.println("Enter password to delete");
					int pass=bs.nextInt();
					int status=dao.deleteAccount(pass, res);
					if(status==1) {
						System.out.println("Your account is deleted\n Good Bye!....");
					}
					else {
						System.out.println("Something went wrong");
					}
					
					
					
					
					
			}
			
			
			}
		
		
		
		
		}
		
	}
	
	
	
	}
	
	
	
	bs.close();
	
	
}

}