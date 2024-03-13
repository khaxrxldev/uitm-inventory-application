package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import Model.Department;
import Model.Equipment;
import Model.Equipment_AV;
import Model.Equipment_AC;
import Model.Equipment_Type;
import Model.Location;
import Model.Staff;
import Model.Supplier;

import DAO.Department_DAO;
import DAO.Equipment_DAO;
import DAO.Equipment_Type_DAO;
import DAO.Location_DAO;
import DAO.Staff_DAO;
import DAO.Supplier_DAO;

@WebServlet("/Default_Servlet")
public class Default_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Department_DAO departmentdao;
	private Equipment_DAO equipmentdao;
	private Equipment_Type_DAO equipmenttypedao;
	private Location_DAO locationdao;
	private Staff_DAO staffdao;
	private Supplier_DAO supplierdao;
	
    public Default_Servlet() {
        super();
        departmentdao = new Department_DAO();
        equipmentdao = new Equipment_DAO();
        equipmenttypedao = new Equipment_Type_DAO();
        locationdao = new Location_DAO();
        staffdao = new Staff_DAO();
        supplierdao = new Supplier_DAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Department department_info = new Department();
		Equipment equipment_info = new Equipment();
		Equipment_AV equipment_av_info = new Equipment_AV();
		Equipment_AC equipment_ac_info = new Equipment_AC();
		Equipment_Type equipment_type_info = new Equipment_Type();
		Location location_info = new Location();
		Staff staff_info = new Staff();
		Supplier supplier_info = new Supplier();
	}
}