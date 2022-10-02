package com.quiz.project;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class MainTest {
	
	public void storeTable() throws SQLException {
		
		Connection con=null;
		PreparedStatement ps1=null;
		
	Scanner scanner = new Scanner(System.in);
	System.out.println("Enter Id");
	String StudnetId=scanner.next();
	System.out.println("Enter Name");
//	System.out.println();
	String Name=scanner.next();
	
	Questions questions=new Questions();
	int count = questions.startQuiz();
	
	try {
		//Load driver class
		Class.forName("com.mysql.jdbc.Driver");
		//Get connection
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject", "root", "Root");
	    ps1 = con.prepareStatement("INSERT into result(StudentId,Name,Score,Grade)VALUES(?,?,?,?)");
		
		ps1.setString(1, StudnetId);
		ps1.setString(2, Name);
		ps1.setLong(3, count);
		
		if(count>=8 && count<=10) {
			ps1.setString(4, "A");
		}else if(count>=6 && count<=7) {
			ps1.setString(4, "B");
		}else if(count==5) {
			ps1.setString(4, "C");
		}else {
			ps1.setString(4, "Fail");
		}
		
		System.out.println();
		int x = ps1.executeUpdate();
		System.out.println("Record inserted "+x);
		System.out.println();
		
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		con.close();
		ps1.close();
	}
	}
	
	public static void getTable() throws SQLException {
		
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			//Load driver class
			Class.forName("com.mysql.jdbc.Driver");
			//Establish connection
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject", "root", "Root");
			ps= con.prepareStatement("SELECT* FROM result ORDER BY score DESC LIMIT 0,100");
		    rs = ps.executeQuery();
			System.out.println("Class result table");
			
			while(rs.next()) {
				
				System.out.println("SudentId = "+rs.getInt(1));
				System.out.println("Name = "+rs.getString(2));
				System.out.println("Score = "+rs.getString(3));
				System.out.println("Grade = "+rs.getString(4));
				System.out.println();
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			con.close();
			ps.close();
			rs.close();	
		}
	}
	
	public static void getIdDetails() {
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter StudentId for details");
		System.out.println();
		String Id = scanner.next();
	
		Connection con=null; 
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			//Load driver class
			Class.forName("com.mysql.jdbc.Driver");
			//Establish connection
		    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject", "root", "Root");
			ps = con.prepareStatement("SELECT * FROM result WHERE StudentId=?");
			ps.setString(1, Id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				System.out.println("Student details: ");
				System.out.println("StudentId= "+rs.getInt(1));
				System.out.println("Name= "+rs.getString(2));
				System.out.println("Score= "+rs.getString(3));
				System.out.println("Grade= "+rs.getString(4));
				
			}
			con.close();
			ps.close();
			rs.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws SQLException {
		
		MainTest mainTest = new MainTest();
		Scanner scanner=new Scanner(System.in);
		System.out.println("Enter number of students");
		int num = scanner.nextInt();
		
		for(int z=1;z<=num;z++) {//for number of students
			mainTest.storeTable();
		}
		getTable();//Calling getTable method
		
		getIdDetails();//Calling getIdDetails method
	}
	}