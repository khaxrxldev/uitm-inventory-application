package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import Model.Supplier;

import DAO.Supplier_DAO;

@WebServlet("/Update_supplier_Servlet")
public class Update_supplier_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Supplier_DAO supplierdao;
	HttpSession session;
	
    public Update_supplier_Servlet() {
        super();
        supplierdao = new Supplier_DAO();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Supplier supplier_info = new Supplier();
		session = request.getSession(true);
		
		supplier_info.setSupplier_idnum(request.getParameter("supplier_idnum"));
		supplier_info.setSupplier_name(request.getParameter("supplier_name"));
		
		if(request.getParameter("supplier_phonenum") != "") {
			supplier_info.setSupplier_phonenum(request.getParameter("supplier_phonenum"));
		}
		
		if(request.getParameter("supplier_email") != "") {
			supplier_info.setSupplier_email(request.getParameter("supplier_email"));
		}
		
		if(request.getParameter("supplier_address") != "") {
			supplier_info.setSupplier_address(request.getParameter("supplier_address"));
		}
		
		session.setAttribute("session_status", supplierdao.updatesupplier(supplier_info));
		response.sendRedirect("Redirect_Servlet?action=supplier_update&id=" + request.getParameter("supplier_idnum"));
	}
}