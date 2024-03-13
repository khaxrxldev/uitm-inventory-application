package Model;

import java.sql.*;

public class Location {
	private String location_idnum;
	private String location_block;
	private String location_level;
	private Date location_maint_date;
	private String location_maint_status;
	private boolean location_byod;
	private String location_date_different;
	
	public String getLocation_idnum() {
		return location_idnum;
	}
	public void setLocation_idnum(String location_idnum) {
		this.location_idnum = location_idnum;
	}
	public String getLocation_block() {
		return location_block;
	}
	public void setLocation_block(String location_block) {
		this.location_block = location_block;
	}
	public String getLocation_level() {
		return location_level;
	}
	public void setLocation_level(String location_level) {
		this.location_level = location_level;
	}
	public Date getLocation_maint_date() {
		return location_maint_date;
	}
	public void setLocation_maint_date(Date location_maint_date) {
		this.location_maint_date = location_maint_date;
	}
	public String getLocation_maint_status() {
		return location_maint_status;
	}
	public void setLocation_maint_status(String location_maint_status) {
		this.location_maint_status = location_maint_status;
	}
	public boolean isLocation_byod() {
		return location_byod;
	}
	public void setLocation_byod(boolean location_byod) {
		this.location_byod = location_byod;
	}
	public String getLocation_date_different() {
		return location_date_different;
	}
	public void setLocation_date_different(String location_date_different) {
		this.location_date_different = location_date_different;
	}
}