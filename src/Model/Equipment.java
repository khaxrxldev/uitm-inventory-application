package Model;

import java.sql.*;

public class Equipment {
	private String equipment_idnum;
	private String equipment_status;
	private String equipment_description;
	private double equipment_price;
	private Date equipment_report_date;
	private Date equipment_maint_date;
	private Date equipment_insert_date;
	private String eqtype_idnum;
	private String department_idnum;
	private String location_idnum;
	private String staff_idnum;
	private String supplier_idnum;
	private int equipment_status_total;
	private String report_date_different;
	private String maint_date_different;
	private Equipment_Type equipment_type;
	private Location location;
	private Department department;
	private String equipment_image;
	
	public String getEquipment_idnum() {
		return equipment_idnum;
	}
	public void setEquipment_idnum(String equipment_idnum) {
		this.equipment_idnum = equipment_idnum;
	}
	public String getEquipment_status() {
		return equipment_status;
	}
	public void setEquipment_status(String equipment_status) {
		this.equipment_status = equipment_status;
	}
	public String getEquipment_description() {
		return equipment_description;
	}
	public void setEquipment_description(String equipment_description) {
		this.equipment_description = equipment_description;
	}
	public double getEquipment_price() {
		return equipment_price;
	}
	public void setEquipment_price(double equipment_price) {
		this.equipment_price = equipment_price;
	}
	public Date getEquipment_report_date() {
		return equipment_report_date;
	}
	public void setEquipment_report_date(Date equipment_report_date) {
		this.equipment_report_date = equipment_report_date;
	}
	public Date getEquipment_maint_date() {
		return equipment_maint_date;
	}
	public void setEquipment_maint_date(Date equipment_maint_date) {
		this.equipment_maint_date = equipment_maint_date;
	}
	public Date getEquipment_insert_date() {
		return equipment_insert_date;
	}
	public void setEquipment_insert_date(Date equipment_insert_date) {
		this.equipment_insert_date = equipment_insert_date;
	}
	public String getEqtype_idnum() {
		return eqtype_idnum;
	}
	public void setEqtype_idnum(String eqtype_idnum) {
		this.eqtype_idnum = eqtype_idnum;
	}
	public String getDepartment_idnum() {
		return department_idnum;
	}
	public void setDepartment_idnum(String department_idnum) {
		this.department_idnum = department_idnum;
	}
	public String getLocation_idnum() {
		return location_idnum;
	}
	public void setLocation_idnum(String location_idnum) {
		this.location_idnum = location_idnum;
	}
	public String getStaff_idnum() {
		return staff_idnum;
	}
	public void setStaff_idnum(String staff_idnum) {
		this.staff_idnum = staff_idnum;
	}
	public String getSupplier_idnum() {
		return supplier_idnum;
	}
	public void setSupplier_idnum(String supplier_idnum) {
		this.supplier_idnum = supplier_idnum;
	}
	public int getEquipment_status_total() {
		return equipment_status_total;
	}
	public void setEquipment_status_total(int equipment_status_total) {
		this.equipment_status_total = equipment_status_total;
	}
	public String getReport_date_different() {
		return report_date_different;
	}
	public void setReport_date_different(String report_date_different) {
		this.report_date_different = report_date_different;
	}
	public String getMaint_date_different() {
		return maint_date_different;
	}
	public void setMaint_date_different(String maint_date_different) {
		this.maint_date_different = maint_date_different;
	}
	public Equipment_Type getEquipment_type() {
		return equipment_type;
	}
	public void setEquipment_type(Equipment_Type equipment_type) {
		this.equipment_type = equipment_type;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public String getEquipment_image() {
		return equipment_image;
	}
	public void setEquipment_image(String equipment_image) {
		this.equipment_image = equipment_image;
	}
}