package Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import Model.Complaint;

import DAO.Complaint_DAO;

@WebServlet("/Update_Complaint_Servlet")
public class Update_Complaint_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Complaint_DAO complaintdao;
	HttpSession session;
	
    public Update_Complaint_Servlet() {
        super();
        complaintdao = new Complaint_DAO();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Complaint complaint_info = new Complaint();
		session = request.getSession(true);
		
		complaint_info.setComplaint_id(request.getParameter("complaint_id"));
		complaint_info.setComplaint_status(request.getParameter("complaint_status"));
		complaint_info.setComplaint_detail(request.getParameter("complaint_detail"));
		complaint_info.setComplaint_staff(request.getParameter("complaint_staff"));
		
		session.setAttribute("session_status", complaintdao.updatecomplaint(complaint_info));
		response.sendRedirect("Redirect_Servlet?action=complaint_update&id=" + request.getParameter("complaint_id"));
	}
}