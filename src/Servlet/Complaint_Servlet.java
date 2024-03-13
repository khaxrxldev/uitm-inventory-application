package Servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import DAO.Complaint_DAO;

@WebServlet("/Complaint_Servlet")
public class Complaint_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Complaint_DAO complaintdao;
	
    public Complaint_Servlet() {
        super();
        complaintdao = new Complaint_DAO();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SimpleDateFormat dayformat = new SimpleDateFormat("EEEE");
		SimpleDateFormat dateformat = new SimpleDateFormat("MMM dd, yyyy");
		SimpleDateFormat timeformat = new SimpleDateFormat("hh:mm aa");
		Date date = new Date();
		
		request.setAttribute("reportdate", dayformat.format(date) + " " + dateformat.format(date));
		request.setAttribute("reporttime", timeformat.format(date).toString().toUpperCase());
		request.setAttribute("reportfilter", null);
		request.setAttribute("reportsemester", request.getParameter("semester"));
		
		request.setAttribute("complaintlist", complaintdao.viewcomplaintlist());
		
		request.setAttribute("graph_complaint_status", Boolean.parseBoolean(request.getParameter("graph_complaint_status")));
		
		request.setAttribute("complaintstatusgraph", complaintdao.viewcomplaintstatustotal());
		
		RequestDispatcher view = request.getRequestDispatcher("Staff_complaint_page.jsp");
		view.forward(request, response);
	}
}