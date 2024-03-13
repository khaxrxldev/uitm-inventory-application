package DAO;

import java.sql.*;
import java.util.*;

import Database.Database_Connection;
import Model.Equipment_Type;

public class Equipment_Type_DAO {
	private static Connection connect = null;
	private static PreparedStatement ps = null;

	String eqtype_idnum, eqtype_name, eqtype_category, department_idnum;
	int eqtype_total;
	
	public String addequipmenttype(Equipment_Type new_equipment_type) {
		String status = null;
		
		eqtype_idnum = new_equipment_type.getEqtype_idnum();
		eqtype_name = new_equipment_type.getEqtype_name();
		eqtype_category = new_equipment_type.getEqtype_category();
		department_idnum = new_equipment_type.getDepartment_idnum();
		
		try {
			connect = Database_Connection.getConnection();
			ps = connect.prepareStatement("SELECT * FROM equipment_type WHERE eqtype_idnum = ?");
			ps.setString(1, eqtype_idnum);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				status = "ID number already exist";
			}
			else {
				ps = connect.prepareStatement("INSERT INTO equipment_type (eqtype_idnum, eqtype_name, eqtype_category, department_idnum) VALUES (?, ?, ?, ?)");
				ps.setString(1, eqtype_idnum);
				ps.setString(2, eqtype_name);
				ps.setString(3, eqtype_category);
				ps.setString(4, department_idnum);
				
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
	
	public String updateequipmenttype(Equipment_Type update_equipment_type) {
		String status = null;
		
		eqtype_idnum = update_equipment_type.getEqtype_idnum();
		eqtype_name = update_equipment_type.getEqtype_name();
		eqtype_category = update_equipment_type.getEqtype_category();
		department_idnum = update_equipment_type.getDepartment_idnum();
		
		try {
			connect = Database_Connection.getConnection();
			ps = connect.prepareStatement("UPDATE equipment_type SET eqtype_name = ?, eqtype_category = ?, department_idnum = ? WHERE eqtype_idnum = ?");
			ps.setString(1, eqtype_name);
			ps.setString(2, eqtype_category);
			ps.setString(3, department_idnum);
			ps.setString(4, eqtype_idnum);
			
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
	
	public String deleteequipmenttype(String delete_idnum) {
		String status = null;
		
		try {
			connect = Database_Connection.getConnection();
			ps = connect.prepareStatement("DELETE FROM equipment_type WHERE eqtype_idnum = ?");
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
	
	public static List<Equipment_Type> viewequipmenttypelist() {
		List<Equipment_Type> equipment_type_list = new ArrayList<Equipment_Type>();
		
		try {
			connect = Database_Connection.getConnection();
			ps = connect.prepareStatement("SELECT * FROM equipment_type ET LEFT JOIN department D ON ET.department_idnum = D.department_idnum");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Equipment_Type equipment_type_info = new Equipment_Type();
				
				equipment_type_info.setEqtype_idnum(rs.getString("eqtype_idnum"));
				equipment_type_info.setEqtype_name(rs.getString("eqtype_name"));
				equipment_type_info.setEqtype_category(rs.getString("eqtype_category"));
				equipment_type_info.setDepartment_idnum(rs.getString("department_idnum"));
				equipment_type_info.setDepartment(Department_DAO.viewdepartment(rs.getString("department_idnum")));
				
				equipment_type_list.add(equipment_type_info);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
		    try { if (ps != null) ps.close(); } catch (Exception e) {e.printStackTrace();};
		    try { if (connect != null) connect.close(); } catch (Exception e) {e.printStackTrace();};
		}
		return equipment_type_list;
	}
	
	public static List<Equipment_Type> viewequipmenttypetotal(boolean equipment_maintenance, String department) {
		List<Equipment_Type> equipment_type_total_list = new ArrayList<Equipment_Type>();
		
		try {
			connect = Database_Connection.getConnection();
			if(department.equals("av")) {
				if(equipment_maintenance == false) {
					ps = connect.prepareStatement("SELECT ET.eqtype_name, COUNT(E.eqtype_idnum) AS eqtype_total FROM public.equipment_type ET LEFT JOIN public.equipment E ON ET.eqtype_idnum = E.eqtype_idnum WHERE E.department_idnum = 'UiTM_AV' GROUP BY ET.eqtype_idnum");
				}
				else if(equipment_maintenance == true) {
					ps = connect.prepareStatement("SELECT ET.eqtype_name, COUNT(E.eqtype_idnum) AS eqtype_total FROM public.equipment_type ET LEFT JOIN public.equipment E ON ET.eqtype_idnum = E.eqtype_idnum WHERE E.department_idnum = 'UiTM_AV' AND E.equipment_status != 'Good' GROUP BY ET.eqtype_idnum");
				}
			}
			else if(department.equals("hea")) {
				if(equipment_maintenance == false) {
					ps = connect.prepareStatement("SELECT ET.eqtype_name, COUNT(E.eqtype_idnum) AS eqtype_total FROM public.equipment_type ET LEFT JOIN public.equipment E ON ET.eqtype_idnum = E.eqtype_idnum WHERE E.department_idnum = 'UiTM_AC' GROUP BY ET.eqtype_idnum");
				}
				else if(equipment_maintenance == true) {
					ps = connect.prepareStatement("SELECT ET.eqtype_name, COUNT(E.eqtype_idnum) AS eqtype_total FROM public.equipment_type ET LEFT JOIN public.equipment E ON ET.eqtype_idnum = E.eqtype_idnum WHERE E.department_idnum = 'UiTM_AC' AND E.equipment_status != 'Good' GROUP BY ET.eqtype_idnum");
				}
			}
			else if(department.equals("both")) {
				if(equipment_maintenance == false) {
					ps = connect.prepareStatement("SELECT ET.eqtype_name, COUNT(E.eqtype_idnum) AS eqtype_total FROM public.equipment_type ET LEFT JOIN public.equipment E ON ET.eqtype_idnum = E.eqtype_idnum GROUP BY ET.eqtype_idnum");
				}
				else if(equipment_maintenance == true) {
					ps = connect.prepareStatement("SELECT ET.eqtype_name, COUNT(E.eqtype_idnum) AS eqtype_total FROM public.equipment_type ET LEFT JOIN public.equipment E ON ET.eqtype_idnum = E.eqtype_idnum WHERE E.equipment_status != 'Good' GROUP BY ET.eqtype_idnum");
				}
			}
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Equipment_Type equipment_type_total_info = new Equipment_Type();
				
				equipment_type_total_info.setEqtype_name(rs.getString("eqtype_name"));
				equipment_type_total_info.setEqtype_total(rs.getInt("eqtype_total"));
				
				equipment_type_total_list.add(equipment_type_total_info);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
		    try { if (ps != null) ps.close(); } catch (Exception e) {e.printStackTrace();};
		    try { if (connect != null) connect.close(); } catch (Exception e) {e.printStackTrace();};
		}
		return equipment_type_total_list;
	}
	
	public static Equipment_Type viewequipmenttype(String view_idnum) {
		Equipment_Type equipment_type_info = new Equipment_Type();
		
		try {
			connect = Database_Connection.getConnection();
			ps = connect.prepareStatement("SELECT * FROM equipment_type WHERE eqtype_idnum = ?");
			ps.setString(1, view_idnum);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				equipment_type_info.setEqtype_idnum(rs.getString("eqtype_idnum"));
				equipment_type_info.setEqtype_name(rs.getString("eqtype_name"));
				equipment_type_info.setEqtype_category(rs.getString("eqtype_category"));
				equipment_type_info.setDepartment_idnum(rs.getString("department_idnum"));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
		    try { if (ps != null) ps.close(); } catch (Exception e) {e.printStackTrace();};
		    try { if (connect != null) connect.close(); } catch (Exception e) {e.printStackTrace();};
		}
		return equipment_type_info;
	}
}
