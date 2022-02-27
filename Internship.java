import java.util.*;
import java.lang.*;
import java.sql.*;
import java.io.*;

class Internship
{
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;

	InputStreamReader isr = new InputStreamReader(System.in);
	BufferedReader br = new BufferedReader(isr);


		try
		{
		Class.forName("com.mysql.cj.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/javadb?user=root&password=Pass@123");
		}
		catch(Exception ex)
		{
			System.out.println("Error : "+ex.getMessage());
				
	}
	public void insert()
	{
		int stud_no;
		String stud_name;
		String stud_dob;
      		String stud_doj;

		try
		{
			System.out.print("Student Number : ");
			stud_no=Integer.parseInt(br.readLine());
			System.out.print("Student Name : ");
			stud_name=br.readLine();
			System.out.print("DOB : ");
			stud_dob=br.readLine();
			System.out.print("DOJ : ");
			stud_doj=br.readLine();	
			connections();	
			pst=con.prepareStatement("insert into student values(?,?,?,?);");
			pst.setInt(1,stud_no);
			pst.setString(2,stud_name);
			pst.setString(3,stud_dob);
			pst.setString(4,stud_doj);
			int cnt=pst.executeUpdate();
			if(cnt>0)
			{
				System.out.println("Insertion ");
			}
                        else
			{
				System.out.println("Insertion Failed");
			}
		}
		catch(Exception e)
		{
			System.out.println("Error : "+e.getMessage());
		}
	}
	public void delete()
	{
		int studentNo;

		try
		{
			System.out.print("Enter Student Number : ");
			studentNo=Integer.parseInt(br.readLine());
			Class.forName("com.mysql.cj.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/javadb?user=root&password=Pass@123");
			pst=con.prepareStatement("delete from student where studentNo = ?;");
			pst.setInt(1,studentNo);
			int status=pst.executeUpdate();
			if(status>0)
			{
				System.out.println(" Deleted ");
			}
			else
			{
				System.out.println("Deletion failed");
			}
		}
		catch(Exception e)
		{
			System.out.println("Error : "+e.getMessage());
		}
	}
	public void update()
	{
		int stud_no;
		String stud_doj;
		
		try
		{
			System.out.print("Enter Student Id : ");
			stud_no=Integer.parseInt(br.readLine());
			System.out.print("Enter Date of Joining : ");
			stud_doj=br.readLine();
			
			Class.forName("com.mysql.cj.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/javadb?user=root&password=Pass@123");
			pst=con.prepareStatement("update student set studentDOJ=? where studentNo=?;");
			pst.setString(1,stud_doj);
			pst.setInt(2,stud_no);
			int status=pst.executeUpdate();
			if(status>0)
				System.out.println("Updation Successful");
			else
				System.out.println("Updation Failed");
		}
		catch(Exception e)
		{
			System.out.println("Error : "+e.getMessage());
		}

	}
	public void select()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/javadb?user=root&password=Pass@123");
			pst=con.prepareStatement("select * from student");
			rs=pst.executeQuery();
			while(rs.next())
			{
				System.out.print(rs.getInt("studentNo")+"|");
				System.out.print(rs.getString("studentName")+"|");
				System.out.print(rs.getString("studentDOB")+"|");
				System.out.print(rs.getString("studentDOJ")+"|");
				System.out.println("\n");
			}
		}
		catch(Exception e)
		{
			System.out.println("Error : "+e.getMessage());
		}
	}
	public void search()
	{
		try
		{
			int stuNo;
			System.out.print("Enter Student Number : ");
			stuNo=Integer.parseInt(br.readLine());
		        Class.forName("com.mysql.cj.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/javadb?user=root&password=Pass@123");
			pst=con.prepareStatement("select * from student where studentNo =?");
			pst.setInt(1,stuNo);
			rs=pst.executeQuery();
			while(rs.next())
			{
				System.out.print(rs.getInt("studentNo")+" | ");
				System.out.print(rs.getString("studentName")+" | ");
				System.out.print(rs.getString("studentDOB")+" | ");
				System.out.print(rs.getString("studentDOJ")+" | ");
			}
		}
		catch(Exception e)
		{
			System.out.println("Error : "+e.getMessage());
		}
	}
	

	public static void main(String args[])
	{
		int n;
		 Internship obj = new Internship();
		Scanner sc = new Scanner(System.in);
		
			System.out.println("\n");
			System.out.println("Student Opretion....");
			System.out.println("1. Insert Record.");
			System.out.println("2. Delete Record.");	
			System.out.println("3. Update Record.");
			System.out.println("4. Select Record.");
			System.out.println("5. Search Record.");	
			System.out.println("6. Exit.");
				
			
			System.out.print("\nEnter Choice : ");
			n=sc.nextInt();
			System.out.println();
			switch(n)
			{

				case 1 : obj.insert();
					break;
				case 2 : obj.delete();
					break;
				case 3 : obj.update();
					break;
				case 4 : obj.select();
					break;
				case 5 : obj.search();
					break;
				case 6 : System.exit(0);
					break;
				default : System.out.print("Enter Valid Choice.");
				
			}
			
		

		
	}
}