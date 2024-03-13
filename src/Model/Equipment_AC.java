package Model;

public class Equipment_AC extends Equipment {
	private String equipment_idnum;
	private int equipment_quantity;
	
	public String getEquipment_idnum() {
		return equipment_idnum;
	}
	public void setEquipment_idnum(String equipment_idnum) {
		this.equipment_idnum = equipment_idnum;
	}
	public int getEquipment_quantity() {
		return equipment_quantity;
	}
	public void setEquipment_quantity(int equipment_quantity) {
		this.equipment_quantity = equipment_quantity;
	}
}