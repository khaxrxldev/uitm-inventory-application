package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import Model.Staff;

import DAO.Staff_DAO;
import DAO.Complaint_DAO;

@WebServlet("/Login_Servlet")
public class Login_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Staff_DAO staffdao;
	private Complaint_DAO complaintdao;
	HttpSession session;
	
    public Login_Servlet() {
        super();
        staffdao = new Staff_DAO();
        complaintdao = new Complaint_DAO();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Staff staff_info = new Staff();
		session = request.getSession(true);
		
		staff_info.setStaff_idnum(request.getParameter("staff_idnum"));
		staff_info.setStaff_password(request.getParameter("staff_password"));
		
		Staff staff_login = staffdao.loginstaff(staff_info);
		
		if(staff_login.isValid_login() == "Successfully login") {
			session.setAttribute("session_idnum", staff_login.getStaff_idnum());
			session.setAttribute("session_name", staff_login.getStaff_name());
			session.setAttribute("session_level", staff_login.getStaff_level());
			session.setAttribute("session_noti", complaintdao.complaintstatusnoti());
			response.sendRedirect("Redirect_Servlet?action=location");
		}
		else {
			session.setAttribute("session_status", staff_login.isValid_login());
			response.sendRedirect("index.jsp");
		}
	}
}