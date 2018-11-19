package my_package;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped

public class Student {
	

	private  int id;
	private  String first_name;
	private  String last_name;
	private  String email;
	
	public Student(int id1, String first_name1, String last_name1, String email1) {
		id = id1;
		first_name = first_name1;
		last_name = last_name1;
		email = email1;
		
	}
	public Student()
	{
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
