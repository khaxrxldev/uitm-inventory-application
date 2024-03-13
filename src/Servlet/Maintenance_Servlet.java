package Servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import DAO.Equipment_DAO;
import DAO.Equipment_Type_DAO;

@WebServlet("/Maintenance_Servlet")
public class Maintenance_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Equipment_DAO equipmentdao;
	private Equipment_Type_DAO equipmenttypedao;
	
    public Maintenance_Servlet() {
        super();
        equipmentdao = new Equipment_DAO();
        equipmenttypedao = new Equipment_Type_DAO();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SimpleDateFormat dayformat = new SimpleDateFormat("EEEE");
		SimpleDateFormat dateformat = new SimpleDateFormat("MMM dd, yyyy");
		SimpleDateFormat timeformat = new SimpleDateFormat("hh:mm aa");
		Date date = new Date();
		
		request.setAttribute("reportdate", dayformat.format(date) + " " + dateformat.format(date));
		request.setAttribute("reporttime", timeformat.format(date).toString().toUpperCase());
		request.setAttribute("reportfilter", request.getParameter("department_idnum"));
		request.setAttribute("reportsemester", request.getParameter("semester"));
		
		request.setAttribute("equipmentmaintenancelist", equipmentdao.viewequipmentmaintenance(request.getParameter("department_idnum")));
		
		request.setAttribute("graph_maintenance_type", Boolean.parseBoolean(request.getParameter("graph_maintenance_type")));
		request.setAttribute("graph_maintenance_status", Boolean.parseBoolean(request.getParameter("graph_maintenance_status")));
		
		request.setAttribute("equipmenttypemaintenancegraph", equipmenttypedao.viewequipmenttypetotal(true, "both"));
		request.setAttribute("equipmentstatusmaintenancegraph", equipmentdao.viewequipmentstatustotal(true, "both"));
		RequestDispatcher view = request.getRequestDispatcher("Staff_maintenance_page.jsp");
		view.forward(request, response);
	}
}