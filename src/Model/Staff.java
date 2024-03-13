package Model;

public class Staff {
	private String staff_idnum;
	private String staff_password;
	private String staff_name;
	private String staff_phonenum;
	private String staff_email;
	private String department_idnum;
	private String staff_level;
	private String valid_login;
	private Department department;
	
	public String getStaff_idnum() {
		return staff_idnum;
	}
	public void setStaff_idnum(String staff_idnum) {
		this.staff_idnum = staff_idnum;
	}
	public String getStaff_password() {
		return staff_password;
	}
	public void setStaff_password(String staff_password) {
		this.staff_password = staff_password;
	}
	public String getStaff_name() {
		return staff_name;
	}
	public void setStaff_name(String staff_name) {
		this.staff_name = staff_name;
	}
	public String getStaff_phonenum() {
		return staff_phonenum;
	}
	public void setStaff_phonenum(String staff_phonenum) {
		this.staff_phonenum = staff_phonenum;
	}
	public String getStaff_email() {
		return staff_email;
	}
	public void setStaff_email(String staff_email) {
		this.staff_email = staff_email;
	}
	public String getDepartment_idnum() {
		return department_idnum;
	}
	public void setDepartment_idnum(String department_idnum) {
		this.department_idnum = department_idnum;
	}
	public String getStaff_level() {
		return staff_level;
	}
	public void setStaff_level(String staff_level) {
		this.staff_level = staff_level;
	}
	public String isValid_login() {
		return valid_login;
	}
	public void setValid_login(String valid_login) {
		this.valid_login = valid_login;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
}