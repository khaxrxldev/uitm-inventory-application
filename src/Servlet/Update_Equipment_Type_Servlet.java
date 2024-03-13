package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import Model.Equipment_Type;

import DAO.Equipment_Type_DAO;

@WebServlet("/Update_Equipment_Type_Servlet")
public class Update_Equipment_Type_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Equipment_Type_DAO equipmenttypedao;
	HttpSession session;
	
    public Update_Equipment_Type_Servlet() {
        super();
        equipmenttypedao = new Equipment_Type_DAO();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Equipment_Type equipment_type_info = new Equipment_Type();
		session = request.getSession(true);
		
		equipment_type_info.setEqtype_idnum(request.getParameter("eqtype_idnum"));
		equipment_type_info.setEqtype_name(request.getParameter("eqtype_name"));
		
		if(request.getParameter("eqtype_category") != "") {
			equipment_type_info.setEqtype_category(request.getParameter("eqtype_category"));
		}
		
		equipment_type_info.setDepartment_idnum(request.getParameter("department_idnum"));

		session.setAttribute("session_status", equipmenttypedao.updateequipmenttype(equipment_type_info));
		response.sendRedirect("Redirect_Servlet?action=equipmenttype_update&id=" + request.getParameter("eqtype_idnum"));
	}
}