package Model;

import java.sql.*;

public class Complaint {
	private String complaint_id;
	private String complaint_name;
	private String complaint_phonenum;
	private String complaint_status;
	private int complaint_status_total;
	private Date complaint_date;
	private String complaint_detail;
	private String location_idnum;
	private String complaint_date_different;
	private String complaint_staff;
	
	public String getComplaint_id() {
		return complaint_id;
	}
	public void setComplaint_id(String complaint_id) {
		this.complaint_id = complaint_id;
	}
	public String getComplaint_name() {
		return complaint_name;
	}
	public void setComplaint_name(String complaint_name) {
		this.complaint_name = complaint_name;
	}
	public String getComplaint_phonenum() {
		return complaint_phonenum;
	}
	public void setComplaint_phonenum(String complaint_phonenum) {
		this.complaint_phonenum = complaint_phonenum;
	}
	public String getComplaint_status() {
		return complaint_status;
	}
	public void setComplaint_status(String complaint_status) {
		this.complaint_status = complaint_status;
	}
	public int getComplaint_status_total() {
		return complaint_status_total;
	}
	public void setComplaint_status_total(int complaint_status_total) {
		this.complaint_status_total = complaint_status_total;
	}
	public Date getComplaint_date() {
		return complaint_date;
	}
	public void setComplaint_date(Date complaint_date) {
		this.complaint_date = complaint_date;
	}
	public String getComplaint_detail() {
		return complaint_detail;
	}
	public void setComplaint_detail(String complaint_detail) {
		this.complaint_detail = complaint_detail;
	}
	public String getLocation_idnum() {
		return location_idnum;
	}
	public void setLocation_idnum(String location_idnum) {
		this.location_idnum = location_idnum;
	}
	public String getComplaint_date_different() {
		return complaint_date_different;
	}
	public void setComplaint_date_different(String complaint_date_different) {
		this.complaint_date_different = complaint_date_different;
	}
	public String getComplaint_staff() {
		return complaint_staff;
	}
	public void setComplaint_staff(String complaint_staff) {
		this.complaint_staff = complaint_staff;
	}
}
