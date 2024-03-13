package DAO;

import java.sql.*;
import java.sql.Date;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

import Database.Database_Connection;
import Model.Complaint;

public class Complaint_DAO {
	private static Connection connect = null;
	private static PreparedStatement ps = null;
	
	String complaint_id, complaint_name, complaint_phonenum, complaint_status, complaint_detail, location_idnum, complaint_staff;
	Date complaint_date;
	
	public String addcomplaint(Complaint new_complaint) {
		String status = null;
		
		complaint_name = new_complaint.getComplaint_name();
		complaint_phonenum = new_complaint.getComplaint_phonenum();
		complaint_status = new_complaint.getComplaint_status();
		complaint_date = new_complaint.getComplaint_date();
		complaint_detail = new_complaint.getComplaint_detail();
		location_idnum = new_complaint.getLocation_idnum();
		
		try {
			connect = Database_Connection.getConnection();
			ps = connect.prepareStatement("INSERT INTO complaint (complaint_name, complaint_phonenum, complaint_status, complaint_date, complaint_detail, location_idnum) VALUES (?, ?, ?, ?, ?, ?)");
			ps.setString(1, complaint_name);
			ps.setString(2, complaint_phonenum);
			ps.setString(3, complaint_status);
			ps.setDate(4, complaint_date);
			ps.setString(5, complaint_detail);
			ps.setString(6, location_idnum);
			
			ps.execute();
			status = "Successfully added";
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
	
	public String updatecomplaint(Complaint update_complaint) {
		String status = null;
		
		complaint_id = update_complaint.getComplaint_id();
		complaint_status = update_complaint.getComplaint_status();
		complaint_detail = update_complaint.getComplaint_detail();
		complaint_staff = update_complaint.getComplaint_staff();
		
		try {
			connect = Database_Connection.getConnection();
			ps = connect.prepareStatement("UPDATE complaint SET complaint_status = ?, complaint_detail = ?, complaint_staff = ? WHERE complaint_id = ?");
			ps.setString(1, complaint_status);
			ps.setString(2, complaint_detail);
			ps.setString(3, complaint_staff);
			ps.setInt(4, Integer.parseInt(complaint_id));
			
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
	
	public String deletecomplaint(String delete_idnum) {
		String status = null;
		
		try {
			connect = Database_Connection.getConnection();
			ps = connect.prepareStatement("DELETE FROM complaint WHERE complaint_id = ?");
			ps.setInt(1, Integer.parseInt(delete_idnum));
			
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
	
	public static List<Complaint> viewcomplaintlist() {
		List<Complaint> complaint_list = new ArrayList<Complaint>();
		
		try {
			connect = Database_Connection.getConnection();
			ps = connect.prepareStatement("SELECT * FROM complaint ORDER BY complaint_status DESC");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Complaint complaint_info = new Complaint();
				
				complaint_info.setComplaint_id(rs.getString("complaint_id"));
				complaint_info.setComplaint_name(rs.getString("complaint_name"));
				complaint_info.setComplaint_phonenum(rs.getString("complaint_phonenum"));
				complaint_info.setComplaint_status(rs.getString("complaint_status"));
				complaint_info.setComplaint_date(rs.getDate("complaint_date"));
				
				if(rs.getDate("complaint_date") != null) {
					long millis = System.currentTimeMillis();  
					java.sql.Date datetoday = new java.sql.Date(millis);
					
					LocalDateTime today_date = datetoday.toLocalDate().atStartOfDay();
					LocalDateTime database_date = rs.getDate("complaint_date").toLocalDate().atStartOfDay();
					
					long daysBetween = Duration.between(database_date, today_date).toDays();
					complaint_info.setComplaint_date_different(daysBetween + " days ago");
				}
				else {
					complaint_info.setComplaint_date_different(null);
				}
				complaint_info.setLocation_idnum(rs.getString("location_idnum"));
				complaint_info.setComplaint_detail(rs.getString("complaint_detail"));
				
				complaint_list.add(complaint_info);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
		    try { if (ps != null) ps.close(); } catch (Exception e) {e.printStackTrace();};
		    try { if (connect != null) connect.close(); } catch (Exception e) {e.printStackTrace();};
		}
		return complaint_list;
	}
	
	public static List<Complaint> viewcomplaintstatustotal() {
		List<Complaint> complaint_status_total_list = new ArrayList<Complaint>();
		
		try {
			connect = Database_Connection.getConnection();
			ps = connect.prepareStatement("SELECT complaint_status, COUNT(complaint_status) AS complaint_status_total FROM complaint GROUP BY complaint_status");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Complaint complaint_info = new Complaint();
				complaint_info.setComplaint_status(rs.getString("complaint_status"));
				complaint_info.setComplaint_status_total(rs.getInt("complaint_status_total"));
				
				complaint_status_total_list.add(complaint_info);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
		    try { if (ps != null) ps.close(); } catch (Exception e) {e.printStackTrace();};
		    try { if (connect != null) connect.close(); } catch (Exception e) {e.printStackTrace();};
		}
		return complaint_status_total_list;
	}
	
	public static Complaint viewcomplaint(String view_idnum) {
		Complaint complaint_info = new Complaint();
		
		try {
			connect = Database_Connection.getConnection();
			ps = connect.prepareStatement("SELECT * FROM complaint WHERE complaint_id = ?");
			ps.setInt(1, Integer.parseInt(view_idnum));
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				complaint_info.setComplaint_id(rs.getString("complaint_id"));
				complaint_info.setComplaint_name(rs.getString("complaint_name"));
				complaint_info.setComplaint_phonenum(rs.getString("complaint_phonenum"));
				complaint_info.setComplaint_status(rs.getString("complaint_status"));
				complaint_info.setComplaint_date(rs.getDate("complaint_date"));
				complaint_info.setComplaint_detail(rs.getString("complaint_detail"));
				complaint_info.setLocation_idnum(rs.getString("location_idnum"));
				complaint_info.setComplaint_staff(rs.getString("complaint_staff"));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
		    try { if (ps != null) ps.close(); } catch (Exception e) {e.printStackTrace();};
		    try { if (connect != null) connect.close(); } catch (Exception e) {e.printStackTrace();};
		}
		return complaint_info;
	}
	
	public boolean complaintstatusnoti() {
		boolean status_noti = false;
		
		try {
			connect = Database_Connection.getConnection();
			ps = connect.prepareStatement("SELECT * FROM complaint");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				if(rs.getString("complaint_status").equals("Not Complete")) {
					status_noti = true;
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
		    try { if (ps != null) ps.close(); } catch (Exception e) {e.printStackTrace();};
		    try { if (connect != null) connect.close(); } catch (Exception e) {e.printStackTrace();};
		}
		return status_noti;
	}
}
