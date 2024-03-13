package DAO;

import java.sql.*;
import java.util.*;

import Database.Database_Connection;
import Model.Staff;

public class Staff_DAO {
	private static Connection connect = null;
	private static PreparedStatement ps = null;

	static String staff_idnum, staff_password, staff_name, staff_phonenum, staff_email, department_idnum, staff_level;
	
	public static Staff loginstaff(Staff login_staff) {
		staff_idnum = login_staff.getStaff_idnum();
		staff_password = login_staff.getStaff_password();
		
		try {
			connect = Database_Connection.getConnection();
			ps = connect.prepareStatement("SELECT * FROM staff WHERE staff_idnum = ?");
			ps.setString(1, staff_idnum);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				ps = connect.prepareStatement("SELECT * FROM staff WHERE staff_idnum = ? AND staff_password = ?");
				ps.setString(1, staff_idnum);
				ps.setString(2, staff_password);
				
				ResultSet sec_rs = ps.executeQuery();
				
				if(sec_rs.next()) {
					login_staff.setStaff_idnum(sec_rs.getString("staff_idnum"));
					login_staff.setStaff_name(sec_rs.getString("staff_name"));
					login_staff.setStaff_level(sec_rs.getString("staff_level"));
					login_staff.setValid_login("Successfully login");
				}
				else {
					login_staff.setValid_login("Wrong ID and password combination");
				}
			}
			else {
				login_staff.setValid_login("ID number not exist");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
		    try { if (ps != null) ps.close(); } catch (Exception e) {e.printStackTrace();};
		    try { if (connect != null) connect.close(); } catch (Exception e) {e.printStackTrace();};
		}
		return login_staff;
	}
	
	public String addstaff(Staff new_staff) {
		String status = null;
		
		staff_idnum = new_staff.getStaff_idnum();
		staff_password = new_staff.getStaff_password();
		staff_name = new_staff.getStaff_name();
		staff_phonenum = new_staff.getStaff_phonenum();
		staff_email = new_staff.getStaff_email();
		department_idnum = new_staff.getDepartment_idnum();
		staff_level = new_staff.getStaff_level();
		
		try {
			connect = Database_Connection.getConnection();
			ps = connect.prepareStatement("SELECT * FROM staff WHERE staff_idnum = ?");
			ps.setString(1, staff_idnum);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				status = "ID number already exist";
			}
			else {
				ps = connect.prepareStatement("INSERT INTO staff (staff_idnum, staff_password, staff_name, staff_phonenum, staff_email, department_idnum, staff_level) VALUES (?, ?, ?, ?, ?, ?, ?)");
				ps.setString(1, staff_idnum);
				ps.setString(2, staff_password);
				ps.setString(3, staff_name);
				ps.setString(4, staff_phonenum);
				ps.setString(5, staff_email);
				ps.setString(6, department_idnum);
				ps.setString(7, staff_level);
				
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
	
	public String updatestaff(Staff update_staff) {
		String status = null;
		
		staff_idnum = update_staff.getStaff_idnum();
		staff_password = update_staff.getStaff_password();
		staff_name = update_staff.getStaff_name();
		staff_phonenum = update_staff.getStaff_phonenum();
		staff_email = update_staff.getStaff_email();
		department_idnum = update_staff.getDepartment_idnum();
		staff_level = update_staff.getStaff_level();
		
		try {
			connect = Database_Connection.getConnection();
			ps = connect.prepareStatement("UPDATE staff SET staff_password = ?, staff_name = ?, staff_phonenum = ?, staff_email = ?, department_idnum = ?, staff_level = ? WHERE staff_idnum = ?");
			ps.setString(1, staff_password);
			ps.setString(2, staff_name);
			ps.setString(3, staff_phonenum);
			ps.setString(4, staff_email);
			ps.setString(5, department_idnum);
			ps.setString(6, staff_level);
			ps.setString(7, staff_idnum);
			
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
	
	public String deletestaff(String delete_idnum) {
		String status = null;
		try {
			connect = Database_Connection.getConnection();
			ps = connect.prepareStatement("DELETE FROM staff WHERE staff_idnum = ?");
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
	
	public static List<Staff> viewstafflist() {
		List<Staff> staff_list = new ArrayList<Staff>();
		
		try {
			connect = Database_Connection.getConnection();
			ps = connect.prepareStatement("SELECT * FROM staff S LEFT JOIN department D ON S.department_idnum = D.department_idnum");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Staff staff_info = new Staff();
				
				staff_info.setStaff_idnum(rs.getString("staff_idnum"));
				staff_info.setStaff_password(rs.getString("staff_password"));
				staff_info.setStaff_name(rs.getString("staff_name"));
				staff_info.setStaff_phonenum(rs.getString("staff_phonenum"));
				staff_info.setStaff_email(rs.getString("staff_email"));
				staff_info.setDepartment_idnum(rs.getString("department_idnum"));
				staff_info.setStaff_level(rs.getString("staff_level"));
				staff_info.setDepartment(Department_DAO.viewdepartment(rs.getString("department_idnum")));
				
				staff_list.add(staff_info);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
		    try { if (ps != null) ps.close(); } catch (Exception e) {e.printStackTrace();};
		    try { if (connect != null) connect.close(); } catch (Exception e) {e.printStackTrace();};
		}
		return staff_list;
	}
	
	public static Staff viewstaff(String view_idnum) {
		Staff staff_info = new Staff();

		try {
			connect = Database_Connection.getConnection();
			ps = connect.prepareStatement("SELECT * FROM staff WHERE staff_idnum = ?");
			ps.setString(1, view_idnum);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				staff_info.setStaff_idnum(rs.getString("staff_idnum"));
				staff_info.setStaff_password(rs.getString("staff_password"));
				staff_info.setStaff_name(rs.getString("staff_name"));
				staff_info.setStaff_phonenum(rs.getString("staff_phonenum"));
				staff_info.setStaff_email(rs.getString("staff_email"));
				staff_info.setDepartment_idnum(rs.getString("department_idnum"));
				staff_info.setStaff_level(rs.getString("staff_level"));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
		    try { if (ps != null) ps.close(); } catch (Exception e) {e.printStackTrace();};
		    try { if (connect != null) connect.close(); } catch (Exception e) {e.printStackTrace();};
		}
		return staff_info;
	}
}