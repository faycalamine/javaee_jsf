package my_package;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
public class StudentDbUtil {
	

	
	public static DataSource getDataSource() throws NamingException{
		String jndi="java:comp/env/jdbc/studentdb" ;
		Context context = new InitialContext();
		DataSource dataSource = (DataSource)
		context.lookup(jndi);
		return dataSource;
		}

	public static List<Student> getStudents() throws SQLException, NamingException
	{
		List<Student> liste= new ArrayList<Student>();
		Connection myConn=null;
		Statement myStmt = null;
		ResultSet myRs= null;	
		try {
			myConn = getDataSource().getConnection();
			myStmt= myConn.createStatement();
			String sql= "select * from student";
			myRs = myStmt.executeQuery(sql);
			while(myRs.next()){
			int myid = myRs.getInt("id");
			String myfirstname = myRs.getString("first_name");
			String mylastname = myRs.getString("last_name");
			String myemail = myRs.getString("email");
			Student tempstudent= new Student(myid,myfirstname,mylastname,myemail);
			liste.add(tempstudent);
			}
			return liste;
			} finally{
			close(myConn,myStmt,myRs);
			}
	}
	public static void addStudent(Student student)
	{
		Connection myConn=null;
		java.sql.PreparedStatement myStmt = null;
		ResultSet myRs= null;
		try {
		myConn = getDataSource().getConnection();
		String sql = "INSERT INTO student VALUES (?,?,?,?)";
		myStmt = myConn.prepareStatement(sql);
		int myid = student.getId();
		String myfirstname = student.getFirst_name();
		String mylastname =  student.getLast_name();
		String myemail = student.getEmail();
		myStmt.setInt(1, myid);
		myStmt.setString(2, myfirstname);
		myStmt.setString(3, mylastname);
		myStmt.setString(4, myemail);
		myStmt.execute();
		}
		catch(Exception e){
		System.out.println(e.getMessage());
		}
		finally{
		close(myConn,myStmt,myRs);
		}
	}
	public static Student fetchStudent(int id)
	{
		Connection myConn=null;
		Statement myStmt = null;
		ResultSet myRs= null;
		Student student=null;
		try {
		myConn = getDataSource().getConnection();
		myStmt= myConn.createStatement();
		String sql= "select * from student where id="+id;
		myRs = myStmt.executeQuery(sql);
		while(myRs.next()){
		int myid = myRs.getInt("id");
		String myfirstname = myRs.getString("first_name");
		String mylastname =  myRs.getString("last_name");
		String myemail = myRs.getString("email");
		student = new Student(myid,myfirstname,mylastname,myemail);
		}
		return student;
		}catch(Exception e){
		System.out.println(e.getMessage());
		return null;
		} finally{
		close(myConn,myStmt,myRs);
		}
	}
	public static void updateStudent(Student student)
	{
		Connection myConn=null;
		java.sql.PreparedStatement myStmt = null;
		try {
		myConn = getDataSource().getConnection();
		String sql = "update student set first_name=?, last_name=?, email=? where id=?";
		myStmt = myConn.prepareStatement(sql);
		myStmt.setString(1, student.getFirst_name());
		myStmt.setString(2,student.getLast_name());
		myStmt.setString(3,student.getEmail());
		myStmt.setInt(4,student.getId());
		myStmt.execute();
		}
		catch(Exception e){
		System.out.println(e.getMessage());
		}
		finally{
		close(myConn,myStmt,null);
		}
	}
	public static void deleteStudent(int id)
	{
		Connection myConn=null;
		Statement myStmt = null;
		try {
		myConn = getDataSource().getConnection();
		myStmt= myConn.createStatement();
		String sql= "delete from student where id="+id;
		myStmt.execute(sql);
		}catch(Exception e){
		System.out.println(e.getMessage());
		} finally{ close(myConn,myStmt,null); }
	}
	private static void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		try{
		if(myStmt!=null)
		myStmt.close();
		if(myRs!=null)
		myRs.close();
		if(myConn!=null)
		myConn.close();
		}catch(Exception e){
		System.out.println(e.getMessage());
		}
		}
}
