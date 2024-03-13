package Model;

public class Department {
	private String department_idnum;
	private String department_name;
	private String location_idnum;
	private Location location;
	
	public String getDepartment_idnum() {
		return department_idnum;
	}
	public void setDepartment_idnum(String department_idnum) {
		this.department_idnum = department_idnum;
	}
	public String getDepartment_name() {
		return department_name;
	}
	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}
	public String getLocation_idnum() {
		return location_idnum;
	}
	public void setLocation_idnum(String location_idnum) {
		this.location_idnum = location_idnum;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
}