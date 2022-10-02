package com.quiz.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Questions {
	
public int startQuiz() throws SQLException{
		
		int count=0;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			//Load driver class
			Class.forName("com.mysql.jdbc.Driver");
			//Establish connection
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject","root","Root");
		    ps = con.prepareStatement("SELECT Question,a,b,C,d,Answer FROM quiz ORDER BY RAND() LIMIT 10");
			rs = ps.executeQuery();
			System.out.println("QUiz started...");
			
			int i=1;
			while(rs.next() && i<=10){
		
				System.out.println();
				System.out.println("Question "+i+". "+rs.getString(1));
				System.out.println("a)"+rs.getString(2));
				System.out.println("b) "+rs.getString(3));
				System.out.println("c) "+rs.getString(4));
				System.out.println("d) "+rs.getString(5));	
				System.out.println();
				i++;
				
				String ans=rs.getString(6);
				//User input
				Scanner scanner=new Scanner(System.in);
				System.out.println("Enter the option ");
				String option=scanner.next();
			
			if(ans.equals(option)) {
				count=count+1;
			}
	   }
			System.out.println("Quiz Score= "+count);
		
		}catch(Exception e) {
		   e.printStackTrace();
		}finally {
			con.close();
			ps.close();
			rs.close();
		}
		return count;
   }    
}
