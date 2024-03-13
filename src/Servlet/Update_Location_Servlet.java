package Servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import Model.Location;

import DAO.Location_DAO;

@WebServlet("/Update_Location_Servlet")
public class Update_Location_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Location_DAO locationdao;
	HttpSession session;
	
    public Update_Location_Servlet() {
        super();
        locationdao = new Location_DAO();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Location location_info = new Location();
		session = request.getSession(true);
		
		location_info.setLocation_idnum(request.getParameter("location_idnum"));
		location_info.setLocation_block(request.getParameter("location_block"));
		
		if(request.getParameter("location_level") != "") {
			location_info.setLocation_level(request.getParameter("location_level"));
		}
		
		if(request.getParameter("location_maint_date") != "") {
			String date = request.getParameter("location_maint_date");
			Date dt;
			try {
				dt = new SimpleDateFormat("yyyy-MM-dd").parse(date);
				java.sql.Date sqlDate = new java.sql.Date(dt.getTime());
				location_info.setLocation_maint_date(sqlDate);
			}
			catch (ParseException e) {
				e.printStackTrace();
			}
		}

		if(request.getParameter("location_maint_status") != "") {
			location_info.setLocation_maint_status(request.getParameter("location_maint_status"));
		}
		
		if(request.getParameter("location_byod") != "") {
			location_info.setLocation_byod(Boolean.parseBoolean(request.getParameter("location_byod")));
		}
		else {
			location_info.setLocation_byod(false);
		}
		
		session.setAttribute("session_status", locationdao.updatelocation(location_info));
		response.sendRedirect("Redirect_Servlet?action=location_update&id=" + request.getParameter("location_idnum"));
	}
}