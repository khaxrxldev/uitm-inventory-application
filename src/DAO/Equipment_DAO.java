package DAO;

import java.sql.*;
import java.sql.Date;
import java.time.*;
import java.util.*;

import Database.Database_Connection;
import Model.Equipment;
import Model.Equipment_AV;
import Model.Equipment_AC;

public class Equipment_DAO {
	private static Connection connect = null;
	private static PreparedStatement ps = null;

	String equipment_idnum, equipment_status, equipment_description, eqtype_idnum, department_idnum, location_idnum, staff_idnum, supplier_idnum, equipment_image;
	double equipment_price;
	Date equipment_report_date, equipment_maint_date, equipment_insert_date;
	
	String equipment_brand, equipment_model, equipment_financialnum, equipment_treasurecode, equipment_officialnum, equipment_remote, equipment_converter;
	
	int equipment_quantity, equipment_brand_total;
	
	public String addequipmentav(Equipment_AV new_equipment_av) {
		String status = null;
		
		equipment_idnum = new_equipment_av.getEquipment_idnum();
		equipment_status = new_equipment_av.getEquipment_status();
		equipment_description = new_equipment_av.getEquipment_description();
		equipment_price = new_equipment_av.getEquipment_price();
		equipment_report_date = new_equipment_av.getEquipment_report_date();
		equipment_maint_date = new_equipment_av.getEquipment_maint_date();
		equipment_insert_date = new_equipment_av.getEquipment_insert_date();
		eqtype_idnum = new_equipment_av.getEqtype_idnum();
		department_idnum = new_equipment_av.getDepartment_idnum();
		location_idnum = new_equipment_av.getLocation_idnum();
		staff_idnum = new_equipment_av.getStaff_idnum();
		supplier_idnum = new_equipment_av.getSupplier_idnum();
		equipment_image = new_equipment_av.getEquipment_image();
		
		equipment_brand = new_equipment_av.getEquipment_brand();
		equipment_model = new_equipment_av.getEquipment_model();
		equipment_financialnum = new_equipment_av.getEquipment_financialnum();
		equipment_treasurecode = new_equipment_av.getEquipment_treasurecode();
		equipment_officialnum = new_equipment_av.getEquipment_officialnum();
		equipment_remote = new_equipment_av.getEquipment_remote();
		equipment_converter = new_equipment_av.getEquipment_converter();
		
		try {
			connect = Database_Connection.getConnection();
			ps = connect.prepareStatement("SELECT * FROM equipment WHERE equipment_idnum = ?");
			ps.setString(1, equipment_idnum);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				status = "ID number already exist";
			}
			else {
				ps = connect.prepareStatement("INSERT INTO equipment (equipment_idnum, equipment_status, equipment_description, equipment_price, equipment_report_date, equipment_maint_date, equipment_insert_date, eqtype_idnum, department_idnum, location_idnum, staff_idnum, supplier_idnum, equipment_image) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
				ps.setString(1, equipment_idnum);
				ps.setString(2, equipment_status);
				ps.setString(3, equipment_description);
				ps.setDouble(4, equipment_price);
				ps.setDate(5, equipment_report_date);
				ps.setDate(6, equipment_maint_date);
				ps.setDate(7, equipment_insert_date);
				ps.setString(8, eqtype_idnum);
				ps.setString(9, department_idnum);
				ps.setString(10, location_idnum);
				ps.setString(11, staff_idnum);
				ps.setString(12, supplier_idnum);
				ps.setString(13, equipment_image);
				
				ps.execute();
				
				ps = connect.prepareStatement("INSERT INTO equipment_av (equipment_idnum, equipment_brand, equipment_model, equipment_financialnum, equipment_treasurecode, equipment_officialnum, equipment_remote, equipment_converter) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
				ps.setString(1, equipment_idnum);
				ps.setString(2, equipment_brand);
				ps.setString(3, equipment_model);
				ps.setString(4, equipment_financialnum);
				ps.setString(5, equipment_treasurecode);
				ps.setString(6, equipment_officialnum);
				ps.setString(7, equipment_remote);
				ps.setString(8, equipment_converter);
				
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
	
	public String addequipmentac(Equipment_AC new_equipment_ac) {
		String status = null;
		
		equipment_idnum = new_equipment_ac.getEquipment_idnum();
		equipment_status = new_equipment_ac.getEquipment_status();
		equipment_description = new_equipment_ac.getEquipment_description();
		equipment_price = new_equipment_ac.getEquipment_price();
		equipment_report_date = new_equipment_ac.getEquipment_report_date();
		equipment_maint_date = new_equipment_ac.getEquipment_maint_date();
		equipment_insert_date = new_equipment_ac.getEquipment_insert_date();
		eqtype_idnum = new_equipment_ac.getEqtype_idnum();
		department_idnum = new_equipment_ac.getDepartment_idnum();
		location_idnum = new_equipment_ac.getLocation_idnum();
		staff_idnum = new_equipment_ac.getStaff_idnum();
		supplier_idnum = new_equipment_ac.getSupplier_idnum();
		equipment_image = new_equipment_ac.getEquipment_image();
		
		equipment_quantity = new_equipment_ac.getEquipment_quantity();
		
		try {
			connect = Database_Connection.getConnection();
			ps = connect.prepareStatement("SELECT * FROM equipment WHERE equipment_idnum = ?");
			ps.setString(1, equipment_idnum);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				status = "ID number already exist";
			}
			else {
				ps = connect.prepareStatement("INSERT INTO equipment (equipment_idnum, equipment_status, equipment_description, equipment_price, equipment_report_date, equipment_maint_date, equipment_insert_date, eqtype_idnum, department_idnum, location_idnum, staff_idnum, supplier_idnum, equipment_image) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
				ps.setString(1, equipment_idnum);
				ps.setString(2, equipment_status);
				ps.setString(3, equipment_description);
				ps.setDouble(4, equipment_price);
				ps.setDate(5, equipment_report_date);
				ps.setDate(6, equipment_maint_date);
				ps.setDate(7, equipment_insert_date);
				ps.setString(8, eqtype_idnum);
				ps.setString(9, department_idnum);
				ps.setString(10, location_idnum);
				ps.setString(11, staff_idnum);
				ps.setString(12, supplier_idnum);
				ps.setString(13, equipment_image);
				
				ps.execute();
				
				ps = connect.prepareStatement("INSERT INTO equipment_ac (equipment_idnum, equipment_quantity) VALUES (?, ?)");
				ps.setString(1, equipment_idnum);
				ps.setInt(2, equipment_quantity);
				
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
	
	public String updateequipmentav(Equipment_AV update_equipment_av) {
		String status = null;
		
		equipment_idnum = update_equipment_av.getEquipment_idnum();
		equipment_status = update_equipment_av.getEquipment_status();
		equipment_description = update_equipment_av.getEquipment_description();
		equipment_price = update_equipment_av.getEquipment_price();
		equipment_report_date = update_equipment_av.getEquipment_report_date();
		equipment_maint_date = update_equipment_av.getEquipment_maint_date();
		equipment_insert_date = update_equipment_av.getEquipment_insert_date();
		eqtype_idnum = update_equipment_av.getEqtype_idnum();
		department_idnum = update_equipment_av.getDepartment_idnum();
		location_idnum = update_equipment_av.getLocation_idnum();
		staff_idnum = update_equipment_av.getStaff_idnum();
		supplier_idnum = update_equipment_av.getSupplier_idnum();
		equipment_image = update_equipment_av.getEquipment_image();

		equipment_brand = update_equipment_av.getEquipment_brand();
		equipment_model = update_equipment_av.getEquipment_model();
		equipment_financialnum = update_equipment_av.getEquipment_financialnum();
		equipment_treasurecode = update_equipment_av.getEquipment_treasurecode();
		equipment_officialnum = update_equipment_av.getEquipment_officialnum();
		equipment_remote = update_equipment_av.getEquipment_remote();
		equipment_converter = update_equipment_av.getEquipment_converter();
		
		try {
			connect = Database_Connection.getConnection();
			ps = connect.prepareStatement("UPDATE equipment SET equipment_status = ?, equipment_description = ?, equipment_price = ?, equipment_report_date = ?, equipment_maint_date = ?, equipment_insert_date = ?, eqtype_idnum = ?, department_idnum = ?, location_idnum = ?, staff_idnum = ?, supplier_idnum = ? WHERE equipment_idnum = ?");
			ps.setString(1, equipment_status);
			ps.setString(2, equipment_description);
			ps.setDouble(3, equipment_price);
			ps.setDate(4, equipment_report_date);
			ps.setDate(5, equipment_maint_date);
			ps.setDate(6, equipment_insert_date);
			ps.setString(7, eqtype_idnum);
			ps.setString(8, department_idnum);
			ps.setString(9, location_idnum);
			ps.setString(10, staff_idnum);
			ps.setString(11, supplier_idnum);
			ps.setString(12, equipment_idnum);
			
			if(equipment_image != null) {
				ps = connect.prepareStatement("UPDATE equipment SET equipment_image = ? WHERE equipment_idnum = ?");
				ps.setString(1, equipment_image);
				ps.setString(2, equipment_idnum);
			}
			
			ps.execute();
			
			ps = connect.prepareStatement("UPDATE equipment_av SET equipment_brand = ?, equipment_model = ?, equipment_financialnum = ?, equipment_treasurecode = ?, equipment_officialnum = ?, equipment_remote = ?, equipment_converter = ? WHERE equipment_idnum = ?");
			ps.setString(1, equipment_brand);
			ps.setString(2, equipment_model);
			ps.setString(3, equipment_financialnum);
			ps.setString(4, equipment_treasurecode);
			ps.setString(5, equipment_officialnum);
			ps.setString(6, equipment_remote);
			ps.setString(7, equipment_converter);
			ps.setString(8, equipment_idnum);
			
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
	
	public String updateequipmentac(Equipment_AC update_equipment_ac) {
		String status = null;
		
		equipment_idnum = update_equipment_ac.getEquipment_idnum();
		equipment_status = update_equipment_ac.getEquipment_status();
		equipment_description = update_equipment_ac.getEquipment_description();
		equipment_price = update_equipment_ac.getEquipment_price();
		equipment_report_date = update_equipment_ac.getEquipment_report_date();
		equipment_maint_date = update_equipment_ac.getEquipment_maint_date();
		equipment_insert_date = update_equipment_ac.getEquipment_insert_date();
		eqtype_idnum = update_equipment_ac.getEqtype_idnum();
		department_idnum = update_equipment_ac.getDepartment_idnum();
		location_idnum = update_equipment_ac.getLocation_idnum();
		staff_idnum = update_equipment_ac.getStaff_idnum();
		supplier_idnum = update_equipment_ac.getSupplier_idnum();
		equipment_image = update_equipment_ac.getEquipment_image();
		
		equipment_quantity = update_equipment_ac.getEquipment_quantity();
		
		try {
			connect = Database_Connection.getConnection();
			ps = connect.prepareStatement("UPDATE equipment SET equipment_status = ?, equipment_description = ?, equipment_price = ?, equipment_report_date = ?, equipment_maint_date = ?, equipment_insert_date = ?, eqtype_idnum = ?, department_idnum = ?, location_idnum = ?, staff_idnum = ?, supplier_idnum = ? WHERE equipment_idnum = ?");
			ps.setString(1, equipment_status);
			ps.setString(2, equipment_description);
			ps.setDouble(3, equipment_price);
			ps.setDate(4, equipment_report_date);
			ps.setDate(5, equipment_maint_date);
			ps.setDate(6, equipment_insert_date);
			ps.setString(7, eqtype_idnum);
			ps.setString(8, department_idnum);
			ps.setString(9, location_idnum);
			ps.setString(10, staff_idnum);
			ps.setString(11, supplier_idnum);
			ps.setString(12, equipment_idnum);
			
			if(equipment_image != null) {
				ps = connect.prepareStatement("UPDATE equipment SET equipment_image = ? WHERE equipment_idnum = ?");
				ps.setString(1, equipment_image);
				ps.setString(2, equipment_idnum);
			}
			
			ps.execute();
			
			ps = connect.prepareStatement("UPDATE equipment_ac SET equipment_quantity = ? WHERE equipment_idnum = ?");
			ps.setInt(1, equipment_quantity);
			ps.setString(2, equipment_idnum);
			
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
	
	public String deleteequipment(String delete_idnum, String delete_dept) {
		String status = null;
		
		try {
			connect = Database_Connection.getConnection();
			if(delete_dept.equalsIgnoreCase("UiTM_AV")) {
				ps = connect.prepareStatement("DELETE FROM equipment_av WHERE equipment_idnum = ?");
				ps.setString(1, delete_idnum);
			}
			else if(delete_dept.equalsIgnoreCase("UiTM_AC")) {
				ps = connect.prepareStatement("DELETE FROM equipment_ac WHERE equipment_idnum = ?");
				ps.setString(1, delete_idnum);
			}
			ps.execute();
			
			ps = connect.prepareStatement("DELETE FROM equipment WHERE equipment_idnum = ?");
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
	
	public static List<Equipment> viewequipmentlist(String equipment_department) {
		List<Equipment> equipment_list = new ArrayList<Equipment>();
		
		try {
			connect = Database_Connection.getConnection();
			if(equipment_department == "") {
				ps = connect.prepareStatement("SELECT * FROM equipment E JOIN equipment_type ET ON E.eqtype_idnum = ET.eqtype_idnum LEFT JOIN location L ON E.location_idnum = L.location_idnum JOIN department D ON E.department_idnum = D.department_idnum");
			}
			else if(equipment_department == "equipmentav") {
				ps = connect.prepareStatement("SELECT * FROM equipment E JOIN equipment_type ET ON E.eqtype_idnum = ET.eqtype_idnum LEFT JOIN location L ON E.location_idnum = L.location_idnum JOIN department D ON E.department_idnum = D.department_idnum WHERE E.department_idnum = 'UiTM_AV'");
			}
			else if(equipment_department == "equipmentac") {
				ps = connect.prepareStatement("SELECT * FROM equipment E JOIN equipment_type ET ON E.eqtype_idnum = ET.eqtype_idnum LEFT JOIN location L ON E.location_idnum = L.location_idnum JOIN department D ON E.department_idnum = D.department_idnum WHERE E.department_idnum = 'UiTM_AC'");
			}
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Equipment equipment_info = new Equipment();
				
				equipment_info.setEquipment_idnum(rs.getString("equipment_idnum"));
				equipment_info.setEquipment_status(rs.getString("equipment_status"));
				equipment_info.setEquipment_description(rs.getString("equipment_description"));
				equipment_info.setEquipment_price(rs.getDouble("equipment_price"));
				equipment_info.setEquipment_report_date(rs.getDate("equipment_report_date"));
				equipment_info.setEquipment_maint_date(rs.getDate("equipment_maint_date"));
				equipment_info.setEquipment_insert_date(rs.getDate("equipment_insert_date"));
				equipment_info.setEqtype_idnum(rs.getString("eqtype_idnum"));
				equipment_info.setDepartment_idnum(rs.getString("department_idnum"));
				equipment_info.setLocation_idnum(rs.getString("location_idnum"));
				equipment_info.setStaff_idnum(rs.getString("staff_idnum"));
				equipment_info.setSupplier_idnum(rs.getString("supplier_idnum"));
				equipment_info.setEquipment_type(Equipment_Type_DAO.viewequipmenttype(rs.getString("eqtype_idnum")));
				equipment_info.setLocation(Location_DAO.viewlocation(rs.getString("location_idnum")));
				equipment_info.setDepartment(Department_DAO.viewdepartment(rs.getString("department_idnum")));
				
				if(rs.getDate("equipment_report_date") != null) {
					long millis = System.currentTimeMillis();  
					java.sql.Date datetoday = new java.sql.Date(millis);
					
					LocalDateTime today_date = datetoday.toLocalDate().atStartOfDay();
					LocalDateTime database_date = rs.getDate("equipment_report_date").toLocalDate().atStartOfDay();
					
					long daysBetween = Duration.between(database_date, today_date).toDays();
					equipment_info.setReport_date_different(daysBetween + " days ago");
				}
				else {
					equipment_info.setReport_date_different(null);
				}
				
				if(rs.getDate("equipment_maint_date") != null) {
					long millis = System.currentTimeMillis();  
					java.sql.Date datetoday = new java.sql.Date(millis);
					
					LocalDateTime today_date = datetoday.toLocalDate().atStartOfDay();
					LocalDateTime database_date = rs.getDate("equipment_maint_date").toLocalDate().atStartOfDay();
					
					long daysBetween = Duration.between(database_date, today_date).toDays();
					equipment_info.setMaint_date_different(daysBetween + " days ago");
				}
				else {
					equipment_info.setMaint_date_different(null);
				}
				
				equipment_list.add(equipment_info);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
		    try { if (ps != null) ps.close(); } catch (Exception e) {e.printStackTrace();};
		    try { if (connect != null) connect.close(); } catch (Exception e) {e.printStackTrace();};
		}
		return equipment_list;
	}
	
	public static List<Equipment> viewequipmentmaintenance(String department_idnum) {
		List<Equipment> equipment_maintenance_list = new ArrayList<Equipment>();
		
		try {
			connect  = Database_Connection.getConnection();
			if(department_idnum == "") {
				ps = connect.prepareStatement("SELECT * FROM equipment E JOIN equipment_type ET ON E.eqtype_idnum = ET.eqtype_idnum WHERE E.equipment_status != 'Good' ORDER BY E.equipment_idnum");
			}
			else if(department_idnum.equalsIgnoreCase("UiTM_AV")) {
				ps = connect.prepareStatement("SELECT * FROM equipment E JOIN equipment_type ET ON E.eqtype_idnum = ET.eqtype_idnum WHERE E.equipment_status != 'Good' AND E.department_idnum = ? ORDER BY E.equipment_idnum");
				ps.setString(1, department_idnum);
			}
			else if(department_idnum.equalsIgnoreCase("UiTM_AC")) {
				ps = connect.prepareStatement("SELECT * FROM equipment E JOIN equipment_type ET ON E.eqtype_idnum = ET.eqtype_idnum WHERE E.equipment_status != 'Good' AND E.department_idnum = ? ORDER BY E.equipment_idnum");
				ps.setString(1, department_idnum);
			}
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Equipment equipment_maintenance_info = new Equipment();
				
				equipment_maintenance_info.setEquipment_idnum(rs.getString("equipment_idnum"));
				equipment_maintenance_info.setEquipment_status(rs.getString("equipment_status"));
				equipment_maintenance_info.setEquipment_description(rs.getString("equipment_description"));
				equipment_maintenance_info.setEquipment_price(rs.getDouble("equipment_price"));
				equipment_maintenance_info.setEquipment_report_date(rs.getDate("equipment_report_date"));
				equipment_maintenance_info.setEquipment_maint_date(rs.getDate("equipment_maint_date"));
				equipment_maintenance_info.setEquipment_insert_date(rs.getDate("equipment_insert_date"));
				equipment_maintenance_info.setEqtype_idnum(rs.getString("eqtype_idnum"));
				equipment_maintenance_info.setDepartment_idnum(rs.getString("department_idnum"));
				equipment_maintenance_info.setLocation_idnum(rs.getString("location_idnum"));
				equipment_maintenance_info.setStaff_idnum(rs.getString("staff_idnum"));
				equipment_maintenance_info.setSupplier_idnum(rs.getString("supplier_idnum"));
				equipment_maintenance_info.setEquipment_type(Equipment_Type_DAO.viewequipmenttype(rs.getString("eqtype_idnum")));
				equipment_maintenance_info.setDepartment(Department_DAO.viewdepartment(rs.getString("department_idnum")));
				
				if(rs.getDate("equipment_report_date") != null) {
					long millis = System.currentTimeMillis();  
					java.sql.Date datetoday = new java.sql.Date(millis);
					
					LocalDateTime today_date = datetoday.toLocalDate().atStartOfDay();
					LocalDateTime database_date = rs.getDate("equipment_report_date").toLocalDate().atStartOfDay();
					
					long daysBetween = Duration.between(database_date, today_date).toDays();
					equipment_maintenance_info.setReport_date_different(daysBetween + " days ago");
				}
				else {
					equipment_maintenance_info.setReport_date_different(null);
				}
				
				if(rs.getDate("equipment_maint_date") != null) {
					long millis = System.currentTimeMillis();  
					java.sql.Date datetoday = new java.sql.Date(millis);
					
					LocalDateTime today_date = datetoday.toLocalDate().atStartOfDay();
					LocalDateTime database_date = rs.getDate("equipment_maint_date").toLocalDate().atStartOfDay();
					
					long daysBetween = Duration.between(database_date, today_date).toDays();
					equipment_maintenance_info.setMaint_date_different(daysBetween + " days ago");
				}
				else {
					equipment_maintenance_info.setMaint_date_different(null);
				}
				
				equipment_maintenance_list.add(equipment_maintenance_info);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
		    try { if (ps != null) ps.close(); } catch (Exception e) {e.printStackTrace();};
		    try { if (connect != null) connect.close(); } catch (Exception e) {e.printStackTrace();};
		}
		return equipment_maintenance_list;
	}
	
	public static List<Equipment> viewequipmentlocation(String equipment_location) {
		List<Equipment> equipment_location_list = new ArrayList<Equipment>();
		
		try {
			connect = Database_Connection.getConnection();
			ps = connect.prepareStatement("SELECT * FROM EQUIPMENT E JOIN equipment_type ET ON E.eqtype_idnum = ET.eqtype_idnum LEFT JOIN location L ON E.location_idnum = L.location_idnum WHERE E.location_idnum = ?");
			ps.setString(1, equipment_location);

			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Equipment equipment_location_info = new Equipment();
				
				equipment_location_info.setEquipment_idnum(rs.getString("equipment_idnum"));
				equipment_location_info.setEquipment_status(rs.getString("equipment_status"));
				equipment_location_info.setEquipment_description(rs.getString("equipment_description"));
				equipment_location_info.setEquipment_price(rs.getDouble("equipment_price"));
				equipment_location_info.setEquipment_report_date(rs.getDate("equipment_report_date"));
				equipment_location_info.setEquipment_maint_date(rs.getDate("equipment_maint_date"));
				equipment_location_info.setEquipment_insert_date(rs.getDate("equipment_insert_date"));
				equipment_location_info.setEqtype_idnum(rs.getString("eqtype_idnum"));
				equipment_location_info.setDepartment_idnum(rs.getString("department_idnum"));
				equipment_location_info.setLocation_idnum(rs.getString("location_idnum"));
				equipment_location_info.setStaff_idnum(rs.getString("staff_idnum"));
				equipment_location_info.setSupplier_idnum(rs.getString("supplier_idnum"));
				equipment_location_info.setEquipment_type(Equipment_Type_DAO.viewequipmenttype(rs.getString("eqtype_idnum")));
				equipment_location_info.setLocation(Location_DAO.viewlocation(rs.getString("location_idnum")));
				
				if(rs.getDate("equipment_report_date") != null) {
					long millis = System.currentTimeMillis();  
					java.sql.Date datetoday = new java.sql.Date(millis);
					
					LocalDateTime today_date = datetoday.toLocalDate().atStartOfDay();
					LocalDateTime database_date = rs.getDate("equipment_report_date").toLocalDate().atStartOfDay();
					
					long daysBetween = Duration.between(database_date, today_date).toDays();
					equipment_location_info.setReport_date_different(daysBetween + " days ago");
				}
				else {
					equipment_location_info.setReport_date_different(null);
				}
				
				if(rs.getDate("equipment_maint_date") != null) {
					long millis = System.currentTimeMillis();  
					java.sql.Date datetoday = new java.sql.Date(millis);
					
					LocalDateTime today_date = datetoday.toLocalDate().atStartOfDay();
					LocalDateTime database_date = rs.getDate("equipment_maint_date").toLocalDate().atStartOfDay();
					
					long daysBetween = Duration.between(database_date, today_date).toDays();
					equipment_location_info.setMaint_date_different(daysBetween + " days ago");
				}
				else {
					equipment_location_info.setMaint_date_different(null);
				}
				
				equipment_location_list.add(equipment_location_info);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
		    try { if (ps != null) ps.close(); } catch (Exception e) {e.printStackTrace();};
		    try { if (connect != null) connect.close(); } catch (Exception e) {e.printStackTrace();};
		}
		return equipment_location_list;
	}
	
	public static List<Equipment_AV> viewequipmentdisplay() {
		List<Equipment_AV> equipment_display_list = new ArrayList<Equipment_AV>();
		
		try {
			connect = Database_Connection.getConnection();
			ps = connect.prepareStatement("SELECT * FROM equipment E JOIN equipment_type ET ON E.eqtype_idnum = ET.eqtype_idnum JOIN equipment_av EAV ON E.equipment_idnum = EAV.equipment_idnum WHERE E.department_idnum = 'UiTM_AV' AND ET.eqtype_category = 'Display'");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Equipment_AV equipment_display_info = new Equipment_AV();
				
				equipment_display_info.setEquipment_idnum(rs.getString("equipment_idnum"));
				equipment_display_info.setEquipment_status(rs.getString("equipment_status"));
				equipment_display_info.setEquipment_description(rs.getString("equipment_description"));
				equipment_display_info.setEquipment_price(rs.getDouble("equipment_price"));
				equipment_display_info.setEquipment_report_date(rs.getDate("equipment_report_date"));
				equipment_display_info.setEquipment_maint_date(rs.getDate("equipment_maint_date"));
				equipment_display_info.setEquipment_insert_date(rs.getDate("equipment_insert_date"));
				equipment_display_info.setEqtype_idnum(rs.getString("eqtype_idnum"));
				equipment_display_info.setDepartment_idnum(rs.getString("department_idnum"));
				equipment_display_info.setLocation_idnum(rs.getString("location_idnum"));
				equipment_display_info.setStaff_idnum(rs.getString("staff_idnum"));
				equipment_display_info.setSupplier_idnum(rs.getString("supplier_idnum"));
				equipment_display_info.setEquipment_type(Equipment_Type_DAO.viewequipmenttype(rs.getString("eqtype_idnum")));
				
				equipment_display_info.setEquipment_remote(rs.getString("equipment_remote"));
				equipment_display_info.setEquipment_converter(rs.getString("equipment_converter"));
				
				equipment_display_list.add(equipment_display_info);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
		    try { if (ps != null) ps.close(); } catch (Exception e) {e.printStackTrace();};
		    try { if (connect != null) connect.close(); } catch (Exception e) {e.printStackTrace();};
		}
		return equipment_display_list;
	}
	
	public static List<Equipment_AC> viewequipmentcapacity() {
		List<Equipment_AC> equipment_capacity_list = new ArrayList<Equipment_AC>();
		
		try {
			connect = Database_Connection.getConnection();
			ps = connect.prepareStatement("SELECT * FROM equipment E JOIN equipment_type ET ON E.eqtype_idnum = ET.eqtype_idnum JOIN equipment_ac EAC ON E.equipment_idnum = EAC.equipment_idnum WHERE E.department_idnum = 'UiTM_AC' AND ET.eqtype_name = 'Chair' OR ET.eqtype_name = 'Table'");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Equipment_AC equipment_capacity_info = new Equipment_AC();
				
				equipment_capacity_info.setEquipment_idnum(rs.getString("equipment_idnum"));
				equipment_capacity_info.setEquipment_status(rs.getString("equipment_status"));
				equipment_capacity_info.setEquipment_description(rs.getString("equipment_description"));
				equipment_capacity_info.setEquipment_price(rs.getDouble("equipment_price"));
				equipment_capacity_info.setEquipment_report_date(rs.getDate("equipment_report_date"));
				equipment_capacity_info.setEquipment_maint_date(rs.getDate("equipment_maint_date"));
				equipment_capacity_info.setEquipment_insert_date(rs.getDate("equipment_insert_date"));
				equipment_capacity_info.setEqtype_idnum(rs.getString("eqtype_idnum"));
				equipment_capacity_info.setDepartment_idnum(rs.getString("department_idnum"));
				equipment_capacity_info.setLocation_idnum(rs.getString("location_idnum"));
				equipment_capacity_info.setStaff_idnum(rs.getString("staff_idnum"));
				equipment_capacity_info.setSupplier_idnum(rs.getString("supplier_idnum"));
				equipment_capacity_info.setEquipment_type(Equipment_Type_DAO.viewequipmenttype(rs.getString("eqtype_idnum")));
				
				equipment_capacity_info.setEquipment_quantity(rs.getInt("equipment_quantity"));
				
				equipment_capacity_list.add(equipment_capacity_info);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
		    try { if (ps != null) ps.close(); } catch (Exception e) {e.printStackTrace();};
		    try { if (connect != null) connect.close(); } catch (Exception e) {e.printStackTrace();};
		}
		return equipment_capacity_list;
	}
	
	public static List<Equipment> viewequipmentreport(String filtertype, String filtervalue) {
		List<Equipment> equipment_report_list = new ArrayList<Equipment>();
		
		try {
			connect = Database_Connection.getConnection();
			if(filtertype.equalsIgnoreCase("Equipment Type")) {
				ps = connect.prepareStatement("SELECT * FROM equipment E JOIN equipment_type ET ON E.eqtype_idnum = ET.eqtype_idnum JOIN department D ON E.department_idnum = D.department_idnum WHERE E.eqtype_idnum = ? ORDER BY E.equipment_idnum");
				ps.setString(1, filtervalue);
			}
			else if(filtertype.equalsIgnoreCase("Status")) {
				ps = connect.prepareStatement("SELECT * FROM equipment E JOIN equipment_type ET ON E.eqtype_idnum = ET.eqtype_idnum JOIN department D ON E.department_idnum = D.department_idnum WHERE E.equipment_status = ? ORDER BY E.equipment_idnum");
				ps.setString(1, filtervalue);
			}
			else if(filtertype.equalsIgnoreCase("Department")) {
				ps = connect.prepareStatement("SELECT * FROM equipment E JOIN equipment_type ET ON E.eqtype_idnum = ET.eqtype_idnum JOIN department D ON E.department_idnum = D.department_idnum WHERE E.department_idnum = ? ORDER BY E.equipment_idnum");
				ps.setString(1, filtervalue);
			}
			else if(filtertype.equalsIgnoreCase("Location")) {
				ps = connect.prepareStatement("SELECT * FROM equipment E JOIN equipment_type ET ON E.eqtype_idnum = ET.eqtype_idnum JOIN department D ON E.department_idnum = D.department_idnum WHERE E.location_idnum = ? ORDER BY E.equipment_idnum");
				ps.setString(1, filtervalue);
			}
			else if(filtertype.equalsIgnoreCase("Supplier")) {
				ps = connect.prepareStatement("SELECT * FROM equipment E JOIN equipment_type ET ON E.eqtype_idnum = ET.eqtype_idnum JOIN department D ON E.department_idnum = D.department_idnum WHERE E.supplier_idnum = ? ORDER BY E.equipment_idnum");
				ps.setString(1, filtervalue);
			}
			else if(filtertype.equalsIgnoreCase("No Filter")) {
				ps = connect.prepareStatement("SELECT * FROM equipment E JOIN equipment_type ET ON E.eqtype_idnum = ET.eqtype_idnum JOIN department D ON E.department_idnum = D.department_idnum ORDER BY E.equipment_idnum");
			}
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Equipment equipment_report_info = new Equipment();
				
				equipment_report_info.setEquipment_idnum(rs.getString("equipment_idnum"));
				equipment_report_info.setEquipment_status(rs.getString("equipment_status"));
				equipment_report_info.setEquipment_description(rs.getString("equipment_description"));
				equipment_report_info.setEquipment_price(rs.getDouble("equipment_price"));
				equipment_report_info.setEquipment_report_date(rs.getDate("equipment_report_date"));
				equipment_report_info.setEquipment_maint_date(rs.getDate("equipment_maint_date"));
				equipment_report_info.setEquipment_insert_date(rs.getDate("equipment_insert_date"));
				equipment_report_info.setEqtype_idnum(rs.getString("eqtype_idnum"));
				equipment_report_info.setDepartment_idnum(rs.getString("department_idnum"));
				equipment_report_info.setLocation_idnum(rs.getString("location_idnum"));
				equipment_report_info.setStaff_idnum(rs.getString("staff_idnum"));
				equipment_report_info.setSupplier_idnum(rs.getString("supplier_idnum"));
				equipment_report_info.setEquipment_type(Equipment_Type_DAO.viewequipmenttype(rs.getString("eqtype_idnum")));
				equipment_report_info.setDepartment(Department_DAO.viewdepartment(rs.getString("department_idnum")));
				
				if(rs.getDate("equipment_report_date") != null) {
					long millis = System.currentTimeMillis();  
					java.sql.Date datetoday = new java.sql.Date(millis);
					
					LocalDateTime today_date = datetoday.toLocalDate().atStartOfDay();
					LocalDateTime database_date = rs.getDate("equipment_report_date").toLocalDate().atStartOfDay();
					
					long daysBetween = Duration.between(database_date, today_date).toDays();
					equipment_report_info.setReport_date_different(daysBetween + " days ago");
				}
				else {
					equipment_report_info.setReport_date_different(null);
				}
				
				if(rs.getDate("equipment_maint_date") != null) {
					long millis = System.currentTimeMillis();  
					java.sql.Date datetoday = new java.sql.Date(millis);
					
					LocalDateTime today_date = datetoday.toLocalDate().atStartOfDay();
					LocalDateTime database_date = rs.getDate("equipment_maint_date").toLocalDate().atStartOfDay();
					
					long daysBetween = Duration.between(database_date, today_date).toDays();
					equipment_report_info.setMaint_date_different(daysBetween + " days ago");
				}
				else {
					equipment_report_info.setMaint_date_different(null);
				}
				
				equipment_report_list.add(equipment_report_info);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
		    try { if (ps != null) ps.close(); } catch (Exception e) {e.printStackTrace();};
		    try { if (connect != null) connect.close(); } catch (Exception e) {e.printStackTrace();};
		}
		return equipment_report_list;
	}
	
	public static List<Equipment> viewequipmentstatustotal(boolean equipment_maintenance, String department) {
		List<Equipment> equipment_status_total_list = new ArrayList<Equipment>();
		
		try {
			connect = Database_Connection.getConnection();
			if(department.equals("av")) {
				if(equipment_maintenance == false) {
					ps = connect.prepareStatement("SELECT equipment_status, COUNT(equipment_status) AS equipment_status_total FROM public.equipment WHERE department_idnum = 'UiTM_AV' GROUP BY equipment_status");
				}
				else if(equipment_maintenance == true) {
					ps = connect.prepareStatement("SELECT equipment_status, COUNT(equipment_status) AS equipment_status_total FROM public.equipment WHERE department_idnum = 'UiTM_AV' AND equipment_status != 'Good' GROUP BY equipment_status");
				}
			}
			else if(department.equals("hea")) {
				if(equipment_maintenance == false) {
					ps = connect.prepareStatement("SELECT equipment_status, COUNT(equipment_status) AS equipment_status_total FROM public.equipment WHERE department_idnum = 'UiTM_AC' GROUP BY equipment_status");
				}
				else if(equipment_maintenance == true) {
					ps = connect.prepareStatement("SELECT equipment_status, COUNT(equipment_status) AS equipment_status_total FROM public.equipment WHERE department_idnum = 'UiTM_AC' AND equipment_status != 'Good' GROUP BY equipment_status");
				}
			}
			else if(department.equals("both")) {
				if(equipment_maintenance == false) {
					ps = connect.prepareStatement("SELECT equipment_status, COUNT(equipment_status) AS equipment_status_total FROM public.equipment GROUP BY equipment_status");
				}
				else if(equipment_maintenance == true) {
					ps = connect.prepareStatement("SELECT equipment_status, COUNT(equipment_status) AS equipment_status_total FROM public.equipment WHERE equipment_status != 'Good' GROUP BY equipment_status");
				}
			}
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Equipment equipment_status_total_info = new Equipment();
				equipment_status_total_info.setEquipment_status(rs.getString("equipment_status"));
				equipment_status_total_info.setEquipment_status_total(rs.getInt("equipment_status_total"));
				
				equipment_status_total_list.add(equipment_status_total_info);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
		    try { if (ps != null) ps.close(); } catch (Exception e) {e.printStackTrace();};
		    try { if (connect != null) connect.close(); } catch (Exception e) {e.printStackTrace();};
		}
		return equipment_status_total_list;
	}
	
	public static List<Equipment_AV> viewequipmentbrandtotal() {
		List<Equipment_AV> equipment_brand_total_list = new ArrayList<Equipment_AV>();
		
		try {
			connect = Database_Connection.getConnection();
			ps = connect.prepareStatement("SELECT EAV.equipment_brand, COUNT(EAV.equipment_brand) AS equipment_brand_total FROM public.equipment_av EAV JOIN public.equipment E ON EAV.equipment_idnum = E.equipment_idnum GROUP BY EAV.equipment_brand");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Equipment_AV equipment_brand_total_info = new Equipment_AV();
				equipment_brand_total_info.setEquipment_brand(rs.getString("equipment_brand"));
				equipment_brand_total_info.setEquipment_brand_total(rs.getInt("equipment_brand_total"));
				
				equipment_brand_total_list.add(equipment_brand_total_info);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
		    try { if (ps != null) ps.close(); } catch (Exception e) {e.printStackTrace();};
		    try { if (connect != null) connect.close(); } catch (Exception e) {e.printStackTrace();};
		}
		return equipment_brand_total_list;
	}
	
	public static Equipment_AV viewequipmentav(String view_idnum) {
		Equipment_AV equipment_av_info = new Equipment_AV();
		
		try {
			connect = Database_Connection.getConnection();
			ps = connect.prepareStatement("SELECT * FROM equipment E JOIN equipment_av EAV ON E.equipment_idnum = EAV.equipment_idnum WHERE E.department_idnum = 'UiTM_AV' AND E.equipment_idnum = ?");
			ps.setString(1, view_idnum);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				equipment_av_info.setEquipment_idnum(rs.getString("equipment_idnum"));
				equipment_av_info.setEquipment_status(rs.getString("equipment_status"));
				equipment_av_info.setEquipment_description(rs.getString("equipment_description"));
				equipment_av_info.setEquipment_price(rs.getDouble("equipment_price"));
				equipment_av_info.setEquipment_report_date(rs.getDate("equipment_report_date"));
				equipment_av_info.setEquipment_maint_date(rs.getDate("equipment_maint_date"));
				equipment_av_info.setEquipment_insert_date(rs.getDate("equipment_insert_date"));
				equipment_av_info.setEqtype_idnum(rs.getString("eqtype_idnum"));
				equipment_av_info.setDepartment_idnum(rs.getString("department_idnum"));
				equipment_av_info.setLocation_idnum(rs.getString("location_idnum"));
				equipment_av_info.setStaff_idnum(rs.getString("staff_idnum"));
				equipment_av_info.setSupplier_idnum(rs.getString("supplier_idnum"));
				equipment_av_info.setEquipment_image(rs.getString("equipment_image"));
				
				equipment_av_info.setEquipment_brand(rs.getString("equipment_brand"));
				equipment_av_info.setEquipment_model(rs.getString("equipment_model"));
				equipment_av_info.setEquipment_financialnum(rs.getString("equipment_financialnum"));
				equipment_av_info.setEquipment_treasurecode(rs.getString("equipment_treasurecode"));
				equipment_av_info.setEquipment_officialnum(rs.getString("equipment_officialnum"));
				equipment_av_info.setEquipment_remote(rs.getString("equipment_remote"));
				equipment_av_info.setEquipment_converter(rs.getString("equipment_converter"));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
		    try { if (ps != null) ps.close(); } catch (Exception e) {e.printStackTrace();};
		    try { if (connect != null) connect.close(); } catch (Exception e) {e.printStackTrace();};
		}
		return equipment_av_info;
	}
	
	public static Equipment_AC viewequipmentac(String view_idnum) {
		Equipment_AC equipment_ac_info = new Equipment_AC();
		
		try {
			connect = Database_Connection.getConnection();
			ps = connect.prepareStatement("SELECT * FROM equipment E JOIN equipment_ac EAC ON E.equipment_idnum = EAC.equipment_idnum WHERE E.department_idnum = 'UiTM_AC' AND E.equipment_idnum = ?");
			ps.setString(1, view_idnum);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				equipment_ac_info.setEquipment_idnum(rs.getString("equipment_idnum"));
				equipment_ac_info.setEquipment_status(rs.getString("equipment_status"));
				equipment_ac_info.setEquipment_description(rs.getString("equipment_description"));
				equipment_ac_info.setEquipment_price(rs.getDouble("equipment_price"));
				equipment_ac_info.setEquipment_report_date(rs.getDate("equipment_report_date"));
				equipment_ac_info.setEquipment_maint_date(rs.getDate("equipment_maint_date"));
				equipment_ac_info.setEquipment_insert_date(rs.getDate("equipment_insert_date"));
				equipment_ac_info.setEqtype_idnum(rs.getString("eqtype_idnum"));
				equipment_ac_info.setDepartment_idnum(rs.getString("department_idnum"));
				equipment_ac_info.setLocation_idnum(rs.getString("location_idnum"));
				equipment_ac_info.setStaff_idnum(rs.getString("staff_idnum"));
				equipment_ac_info.setSupplier_idnum(rs.getString("supplier_idnum"));
				equipment_ac_info.setEquipment_image(rs.getString("equipment_image"));
				
				equipment_ac_info.setEquipment_quantity(rs.getInt("equipment_quantity"));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
		    try { if (ps != null) ps.close(); } catch (Exception e) {e.printStackTrace();};
		    try { if (connect != null) connect.close(); } catch (Exception e) {e.printStackTrace();};
		}
		return equipment_ac_info;
	}
}