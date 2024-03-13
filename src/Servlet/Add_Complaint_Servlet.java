package Servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import Model.Complaint;

import DAO.Complaint_DAO;

@WebServlet("/Add_Complaint_Servlet")
public class Add_Complaint_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Complaint_DAO complaintdao;
	HttpSession session;
	
    public Add_Complaint_Servlet() {
        super();
        complaintdao = new Complaint_DAO();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Complaint complaint_info = new Complaint();
		session = request.getSession(true);
		
		complaint_info.setComplaint_name(request.getParameter("complaint_name"));
		complaint_info.setComplaint_phonenum(request.getParameter("complaint_phonenum"));
		complaint_info.setLocation_idnum(request.getParameter("complaint_location"));
		String date = request.getParameter("complaint_date");
		Date dt;
		try {
			dt = new SimpleDateFormat("yyyy-MM-dd").parse(date);
			java.sql.Date sqlDate = new java.sql.Date(dt.getTime());
			complaint_info.setComplaint_date(sqlDate);
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		complaint_info.setComplaint_status(request.getParameter("complaint_status"));
		complaint_info.setComplaint_detail(request.getParameter("complaint_detail"));
		
		session.setAttribute("session_status", complaintdao.addcomplaint(complaint_info));
		response.sendRedirect("Redirect_Servlet?action=lecturer_complaint");
	}
}