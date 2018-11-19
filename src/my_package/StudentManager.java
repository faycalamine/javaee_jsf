package my_package;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.naming.NamingException;

@ManagedBean
@SessionScoped

public class StudentManager {

	 private List<Student> studiants=new ArrayList<Student>();
	
public List<Student> getStudiants() {
		return studiants;
	}

public void loadStudents() throws SQLException, NamingException
	{
		studiants = StudentDbUtil.getStudents();
	}
	/*public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
	*/
	public void removeStudent(Student st)
	{
		StudentDbUtil.deleteStudent(st.getId());
	}
	public void modifyStudent(Student st)
	{
		StudentDbUtil.updateStudent(st);
	}
	public void addStudent(Student st)
	{
		StudentDbUtil.addStudent(st);
	}
	
	
}
