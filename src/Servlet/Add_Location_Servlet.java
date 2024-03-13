package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import Model.Location;

import DAO.Location_DAO;

@WebServlet("/Add_Location_Servlet")
public class Add_Location_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Location_DAO locationdao;
	HttpSession session;
	
    public Add_Location_Servlet() {
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
		
		if(request.getParameter("location_byod") != "") {
			location_info.setLocation_byod(Boolean.parseBoolean(request.getParameter("location_byod")));
		}
		else {
			location_info.setLocation_byod(false);
		}
		
		session.setAttribute("session_status", locationdao.addlocation(location_info));
		response.sendRedirect("Redirect_Servlet?action=location");
	}
}