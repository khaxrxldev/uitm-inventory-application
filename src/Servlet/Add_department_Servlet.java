package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import Model.Department;

import DAO.Department_DAO;

@WebServlet("/Add_department_Servlet")
public class Add_department_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Department_DAO departmentdao;
	HttpSession session;
	
    public Add_department_Servlet() {
        super();
        departmentdao = new Department_DAO();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Department department_info = new Department();
		session = request.getSession(true);
		
		department_info.setDepartment_idnum(request.getParameter("department_idnum"));
		department_info.setDepartment_name(request.getParameter("department_name"));
		department_info.setLocation_idnum(request.getParameter("location_idnum"));
		
		session.setAttribute("session_status", departmentdao.adddepartment(department_info));
		response.sendRedirect("Redirect_Servlet?action=department");
	}
}