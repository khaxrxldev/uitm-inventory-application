package DAO;

import java.sql.*;
import java.util.*;

import Database.Database_Connection;
import Model.Department;

public class Department_DAO {
	private static Connection connect = null;
	private static PreparedStatement ps = null;
	
	String department_idnum, department_name, location_idnum;
	
	public String adddepartment(Department new_department) {
		String status = null;
		
		department_idnum = new_department.getDepartment_idnum();
		department_name = new_department.getDepartment_name();
		location_idnum = new_department.getLocation_idnum();
		
		try {
			connect = Database_Connection.getConnection();
			ps = connect.prepareStatement("SELECT * FROM department WHERE department_idnum = ?");
			ps.setString(1, department_idnum);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				status = "ID number already exist";
			}
			else {
				ps = connect.prepareStatement("INSERT INTO department (department_idnum, department_name, location_idnum) VALUES (?, ?, ?)");
				ps.setString(1, department_idnum);
				ps.setString(2, department_name);
				ps.setString(3, location_idnum);
				
				ps.execute();
				status = "Successfully added";
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			status = "Unsuccessfully added";
		}
		finally {
		    try { if (ps != null) ps.close(); } catch (Exception e) {e.printStackTrace();};
		    try { if (connect != null) connect.close(); } catch (Exception e) {e.printStackTrace();};
		}
		return status;
	}
	
	public String updatedepartment(Department update_department) {
		String status = null;
		
		department_idnum = update_department.getDepartment_idnum();
		department_name = update_department.getDepartment_name();
		location_idnum = update_department.getLocation_idnum();
		
		try {
			connect = Database_Connection.getConnection();
			ps = connect.prepareStatement("UPDATE department SET department_name = ?, location_idnum = ? WHERE department_idnum = ?");
			ps.setString(1, department_name);
			ps.setString(2, location_idnum);
			ps.setString(3, department_idnum);
			
			ps.execute();
			status = "Successfully updated";
		}
		catch(Exception e) {
			e.printStackTrace();
			status = "Unsuccessfully updated";
		}
		finally {
		    try { if (ps != null) ps.close(); } catch (Exception e) {e.printStackTrace();};
		    try { if (connect != null) connect.close(); } catch (Exception e) {e.printStackTrace();};
		}
		return status;
	}
	
	public String deletedepartment(String delete_idnum) {
		String status = null;
		
		try {
			connect = Database_Connection.getConnection();
			ps = connect.prepareStatement("DELETE FROM department WHERE department_idnum = ?");
			ps.setString(1, delete_idnum);
			
			ps.execute();
			status = "Successfully deleted";
		}
		catch(Exception e) {
			e.printStackTrace();
			status = "Unsuccessfully deleted";
		}
		finally {
		    try { if (ps != null) ps.close(); } catch (Exception e) {e.printStackTrace();};
		    try { if (connect != null) connect.close(); } catch (Exception e) {e.printStackTrace();};
		}
		return status;
	}
	
	public static List<Department> viewdepartmentlist() {
		List<Department> department_list = new ArrayList<Department>();
		
		try {
			connect = Database_Connection.getConnection();
			ps = connect.prepareStatement("SELECT * FROM department D JOIN location L ON D.location_idnum = L.location_idnum");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Department department_info = new Department();
				
				department_info.setDepartment_idnum(rs.getString("department_idnum"));
				department_info.setDepartment_name(rs.getString("department_name"));
				department_info.setLocation_idnum(rs.getString("location_idnum"));
				department_info.setLocation(Location_DAO.viewlocation(rs.getString("location_idnum")));
				
				department_list.add(department_info);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
		    try { if (ps != null) ps.close(); } catch (Exception e) {e.printStackTrace();};
		    try { if (connect != null) connect.close(); } catch (Exception e) {e.printStackTrace();};
		}
		return department_list;
	}
	
	public static Department viewdepartment(String view_idnum) {
		Department department_info = new Department();
		
		try {
			connect = Database_Connection.getConnection();
			ps = connect.prepareStatement("SELECT * FROM department WHERE department_idnum = ?");
			ps.setString(1, view_idnum);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				department_info.setDepartment_idnum(rs.getString("department_idnum"));
				department_info.setDepartment_name(rs.getString("department_name"));
				department_info.setLocation_idnum(rs.getString("location_idnum"));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
		    try { if (ps != null) ps.close(); } catch (Exception e) {e.printStackTrace();};
		    try { if (connect != null) connect.close(); } catch (Exception e) {e.printStackTrace();};
		}
		return department_info;
	}
}