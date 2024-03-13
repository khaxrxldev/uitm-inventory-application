package DAO;

import java.sql.*;
import java.sql.Date;
import java.time.*;
import java.util.*;

import Database.Database_Connection;
import Model.Location;

public class Location_DAO {
	private static Connection connect = null;
	private static PreparedStatement ps = null;

	String location_idnum, location_block, location_maint_status, location_level;
	Date location_maint_date;
	boolean location_byod;
	
	public String addlocation(Location new_location) {
		String status = null;
		
		location_idnum = new_location.getLocation_idnum();
		location_block = new_location.getLocation_block();
		location_level = new_location.getLocation_level();
		location_maint_date = new_location.getLocation_maint_date();
		location_maint_status = new_location.getLocation_maint_status();
		location_byod = new_location.isLocation_byod();
		
		try {
			connect = Database_Connection.getConnection();
			ps = connect.prepareStatement("SELECT * FROM location WHERE location_idnum = ?");
			ps.setString(1, location_idnum);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				status = "ID number already exist";
			}
			else {
				ps = connect.prepareStatement("INSERT INTO location (location_idnum, location_block, location_level, location_maint_date, location_maint_status, location_byod) VALUES (?, ?, ?, ?, ?, ?)");
				ps.setString(1, location_idnum);
				ps.setString(2, location_block);
				ps.setString(3, location_level);
				ps.setDate(4, location_maint_date);
				ps.setString(5, location_maint_status);
				ps.setBoolean(6, location_byod);
				
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
	
	public String updatelocation(Location update_location) {
		String status = null;
		
		location_idnum = update_location.getLocation_idnum();
		location_block = update_location.getLocation_block();
		location_level = update_location.getLocation_level();
		location_maint_date = update_location.getLocation_maint_date();
		location_maint_status = update_location.getLocation_maint_status();
		location_byod = update_location.isLocation_byod();
		
		try {
			connect = Database_Connection.getConnection();
			ps = connect.prepareStatement("UPDATE location SET location_block = ?, location_level = ?, location_maint_date = ?, location_maint_status = ?, location_byod = ? WHERE location_idnum = ?");
			ps.setString(1, location_block);
			ps.setString(2, location_level);
			ps.setDate(3, location_maint_date);
			ps.setString(4, location_maint_status);
			ps.setBoolean(5, location_byod);
			ps.setString(6, location_idnum);
			
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
	
	public String deletelocation(String delete_idnum) {
		String status = null;
		try {
			connect = Database_Connection.getConnection();
			ps = connect.prepareStatement("DELETE FROM location WHERE location_idnum = ?");
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
	
	public static List<Location> viewlocationlist() {
		List<Location> location_list = new ArrayList<Location>();
		
		try {
			connect = Database_Connection.getConnection();
			ps = connect.prepareStatement("SELECT * FROM location");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Location location_info = new Location();
				
				location_info.setLocation_idnum(rs.getString("location_idnum"));
				location_info.setLocation_block(rs.getString("location_block"));
				location_info.setLocation_level(rs.getString("location_level"));
				location_info.setLocation_maint_date(rs.getDate("location_maint_date"));
				location_info.setLocation_maint_status(rs.getString("location_maint_status"));
				location_info.setLocation_byod(rs.getBoolean("location_byod"));
				
				if(rs.getDate("location_maint_date") != null) {
					long millis = System.currentTimeMillis();  
					java.sql.Date datetoday = new java.sql.Date(millis);
					
					LocalDateTime today_date = datetoday.toLocalDate().atStartOfDay();
					LocalDateTime database_date = rs.getDate("location_maint_date").toLocalDate().atStartOfDay();
					
					long daysBetween = Duration.between(database_date, today_date).toDays();
					location_info.setLocation_date_different(daysBetween + " days ago");
				}
				else {
					location_info.setLocation_date_different(null);
				}
				
				location_list.add(location_info);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
		    try { if (ps != null) ps.close(); } catch (Exception e) {e.printStackTrace();};
		    try { if (connect != null) connect.close(); } catch (Exception e) {e.printStackTrace();};
		}
		return location_list;
	}
	
	public static Location viewlocation(String view_idnum) {
		Location location_info = new Location();
		
		try {
			connect = Database_Connection.getConnection();
			ps = connect.prepareStatement("SELECT * FROM location WHERE location_idnum = ?");
			ps.setString(1, view_idnum);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				location_info.setLocation_idnum(rs.getString("location_idnum"));
				location_info.setLocation_block(rs.getString("location_block"));
				location_info.setLocation_level(rs.getString("location_level"));
				location_info.setLocation_maint_date(rs.getDate("location_maint_date"));
				location_info.setLocation_maint_status(rs.getString("location_maint_status"));
				location_info.setLocation_byod(rs.getBoolean("location_byod"));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
		    try { if (ps != null) ps.close(); } catch (Exception e) {e.printStackTrace();};
		    try { if (connect != null) connect.close(); } catch (Exception e) {e.printStackTrace();};
		}
		return location_info;
	}
}