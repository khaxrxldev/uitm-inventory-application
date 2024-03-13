package Model;

public class Equipment_Type {
	private String eqtype_idnum;
	private String eqtype_name;
	private String eqtype_category;
	private String department_idnum;
	private int eqtype_total;
	private Department department;
	
	public String getEqtype_idnum() {
		return eqtype_idnum;
	}
	public void setEqtype_idnum(String eqtype_idnum) {
		this.eqtype_idnum = eqtype_idnum;
	}
	public String getEqtype_name() {
		return eqtype_name;
	}
	public void setEqtype_name(String eqtype_name) {
		this.eqtype_name = eqtype_name;
	}
	public String getEqtype_category() {
		return eqtype_category;
	}
	public void setEqtype_category(String eqtype_category) {
		this.eqtype_category = eqtype_category;
	}
	public String getDepartment_idnum() {
		return department_idnum;
	}
	public void setDepartment_idnum(String department_idnum) {
		this.department_idnum = department_idnum;
	}
	public int getEqtype_total() {
		return eqtype_total;
	}
	public void setEqtype_total(int eqtype_total) {
		this.eqtype_total = eqtype_total;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
}