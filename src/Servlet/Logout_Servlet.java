package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/Logout_Servlet")
public class Logout_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public Logout_Servlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession(true);
			session.setAttribute("session_idnum", null);
			session.setAttribute("session_name", null);
			session.setAttribute("session_level", null);
			session.setAttribute("session_status", null);
			session.invalidate();
			response.sendRedirect("index.jsp");
		}
		catch(Throwable e) {
			e.printStackTrace();
		}
	}
}