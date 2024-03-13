package DAO;

import java.sql.*;
import java.util.*;

import Database.Database_Connection;
import Model.Supplier;

public class Supplier_DAO {
	private static Connection connect = null;
	private static PreparedStatement ps = null;
	
	String supplier_idnum, supplier_name, supplier_phonenum, supplier_email, supplier_address;
	
	public String addsupplier(Supplier new_supplier) {
		String status = null;
		
		supplier_idnum = new_supplier.getSupplier_idnum();
		supplier_name = new_supplier.getSupplier_name();
		supplier_phonenum = new_supplier.getSupplier_phonenum();
		supplier_email = new_supplier.getSupplier_email();
		supplier_address = new_supplier.getSupplier_address();
		
		try {
			connect = Database_Connection.getConnection();
			ps = connect.prepareStatement("SELECT * FROM supplier WHERE supplier_idnum = ?");
			ps.setString(1, supplier_idnum);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				status = "ID number already exist";
			}
			else {
				ps = connect.prepareStatement("INSERT INTO supplier (supplier_idnum, supplier_name, supplier_phonenum, supplier_email, supplier_address) VALUES (?, ?, ?, ?, ?)");
				ps.setString(1, supplier_idnum);
				ps.setString(2, supplier_name);
				ps.setString(3, supplier_phonenum);
				ps.setString(4, supplier_email);
				ps.setString(5, supplier_address);
				
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
	
	public String updatesupplier(Supplier update_supplier) {
		String status = null;
		supplier_idnum = update_supplier.getSupplier_idnum();
		supplier_name = update_supplier.getSupplier_name();
		supplier_phonenum = update_supplier.getSupplier_phonenum();
		supplier_email = update_supplier.getSupplier_email();
		supplier_address = update_supplier.getSupplier_address();
		
		try {
			connect = Database_Connection.getConnection();
			ps = connect.prepareStatement("UPDATE supplier SET supplier_name = ?, supplier_phonenum = ?, supplier_email = ?, supplier_address = ? WHERE supplier_idnum = ?");
			ps.setString(1, supplier_name);
			ps.setString(2, supplier_phonenum);
			ps.setString(3, supplier_email);
			ps.setString(4, supplier_address);
			ps.setString(5, supplier_idnum);
			
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
	
	public String deletesupplier(String delete_idnum) {
		String status = null;
		
		try {
			connect = Database_Connection.getConnection();
			ps = connect.prepareStatement("DELETE FROM supplier WHERE supplier_idnum = ?");
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
	
	public static List<Supplier> viewsupplierlist() {
		List<Supplier> supplier_list = new ArrayList<Supplier>();
		
		try {
			connect = Database_Connection.getConnection();
			ps = connect.prepareStatement("SELECT * FROM supplier");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Supplier supplier_info = new Supplier();
				
				supplier_info.setSupplier_idnum(rs.getString("supplier_idnum"));
				supplier_info.setSupplier_name(rs.getString("supplier_name"));
				supplier_info.setSupplier_phonenum(rs.getString("supplier_phonenum"));
				supplier_info.setSupplier_email(rs.getString("supplier_email"));
				supplier_info.setSupplier_address(rs.getString("supplier_address"));
				
				supplier_list.add(supplier_info);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
		    try { if (ps != null) ps.close(); } catch (Exception e) {e.printStackTrace();};
		    try { if (connect != null) connect.close(); } catch (Exception e) {e.printStackTrace();};
		}
		return supplier_list;
	}
	
	public static List<Supplier> viewsuppliertotal(String department) {
		List<Supplier> supplier_total_list = new ArrayList<Supplier>();
		
		try {
			connect = Database_Connection.getConnection();
			if(department.equals("av")) {
				ps = connect.prepareStatement("SELECT S.supplier_name, COUNT(E.supplier_idnum) AS supplier_total FROM public.supplier S LEFT JOIN public.equipment E ON S.supplier_idnum = E.supplier_idnum WHERE E.department_idnum = 'UiTM_AV' GROUP BY (S.supplier_idnum)");
			}
			else if(department.equals("hea")) {
				ps = connect.prepareStatement("SELECT S.supplier_name, COUNT(E.supplier_idnum) AS supplier_total FROM public.supplier S LEFT JOIN public.equipment E ON S.supplier_idnum = E.supplier_idnum WHERE E.department_idnum = 'UiTM_AC' GROUP BY (S.supplier_idnum)");
			}
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Supplier supplier_total_info = new Supplier();
				
				supplier_total_info.setSupplier_name(rs.getString("supplier_name"));
				supplier_total_info.setSupplier_total(rs.getInt("supplier_total"));
				
				supplier_total_list.add(supplier_total_info);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
		    try { if (ps != null) ps.close(); } catch (Exception e) {e.printStackTrace();};
		    try { if (connect != null) connect.close(); } catch (Exception e) {e.printStackTrace();};
		}
		return supplier_total_list;
	}
	
	public static Supplier viewsupplier(String view_idnum) {
		Supplier supplier_info = new Supplier();
		
		try {
			connect = Database_Connection.getConnection();
			ps = connect.prepareStatement("SELECT * FROM supplier WHERE supplier_idnum = ?");
			ps.setString(1, view_idnum);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				supplier_info.setSupplier_idnum(rs.getString("supplier_idnum"));
				supplier_info.setSupplier_name(rs.getString("supplier_name"));
				supplier_info.setSupplier_phonenum(rs.getString("supplier_phonenum"));
				supplier_info.setSupplier_email(rs.getString("supplier_email"));
				supplier_info.setSupplier_address(rs.getString("supplier_address"));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
		    try { if (ps != null) ps.close(); } catch (Exception e) {e.printStackTrace();};
		    try { if (connect != null) connect.close(); } catch (Exception e) {e.printStackTrace();};
		}
		return supplier_info;
	}
}