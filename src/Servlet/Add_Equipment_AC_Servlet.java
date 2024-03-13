package Servlet;

import java.io.*;
import java.nio.file.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import Model.Equipment_AC;

import DAO.Equipment_DAO;

@WebServlet("/Add_Equipment_AC_Servlet")
@MultipartConfig(
		fileSizeThreshold = 1024* 1024 * 10, //10Mb
		maxFileSize = 1024 * 1024 * 1000, //1Gb
		maxRequestSize = 1024 * 1024 * 1000 //1Gb
	)
public class Add_Equipment_AC_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Equipment_DAO equipmentdao;
	HttpSession session;
	
    public Add_Equipment_AC_Servlet() {
        super();
        equipmentdao = new Equipment_DAO();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Equipment_AC equipment_ac_info = new Equipment_AC();
		session = request.getSession(true);
		
		equipment_ac_info.setEquipment_idnum(request.getParameter("equipment_idnum"));
		equipment_ac_info.setEquipment_status(request.getParameter("equipment_status"));
		
		if(request.getParameter("equipment_description") != "") {
			equipment_ac_info.setEquipment_description(request.getParameter("equipment_description"));
		}
		if(request.getParameter("equipment_price").length() != 0) {
			equipment_ac_info.setEquipment_price(Double.parseDouble(request.getParameter("equipment_price")));
		}
		
		if(request.getParameter("equipment_report_date").length() != 0) {
			String date = request.getParameter("equipment_report_date");
			Date dt;
			try {
				dt = new SimpleDateFormat("yyyy-MM-dd").parse(date);
				java.sql.Date sqlDate = new java.sql.Date(dt.getTime());
				equipment_ac_info.setEquipment_report_date(sqlDate);
			}
			catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		if(request.getParameter("equipment_maint_date").length() != 0) {
			String date = request.getParameter("equipment_maint_date");
			Date dt;
			try {
				dt = new SimpleDateFormat("yyyy-MM-dd").parse(date);
				java.sql.Date sqlDate = new java.sql.Date(dt.getTime());
				equipment_ac_info.setEquipment_maint_date(sqlDate);
			}
			catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		if(request.getParameter("equipment_insert_date").length() != 0) {
			String date = request.getParameter("equipment_insert_date");
			Date dt;
			try {
				dt = new SimpleDateFormat("yyyy-MM-dd").parse(date);
				java.sql.Date sqlDate = new java.sql.Date(dt.getTime());
				equipment_ac_info.setEquipment_insert_date(sqlDate);
			}
			catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		equipment_ac_info.setEqtype_idnum(request.getParameter("eqtype_idnum"));
		equipment_ac_info.setDepartment_idnum(request.getParameter("department_idnum"));
		equipment_ac_info.setStaff_idnum(request.getParameter("staff_idnum"));
		
		if(request.getParameter("supplier_idnum") != "") {
			equipment_ac_info.setSupplier_idnum(request.getParameter("supplier_idnum"));
		}
		else {
			equipment_ac_info.setSupplier_idnum(null);
		}
		
		if(request.getParameter("location_idnum") != "") {
			equipment_ac_info.setLocation_idnum(request.getParameter("location_idnum"));
		}
		else {
			equipment_ac_info.setLocation_idnum(null);
		}

		if(request.getParameter("equipment_quantity").length() != 0) {
			equipment_ac_info.setEquipment_quantity(Integer.parseInt(request.getParameter("equipment_quantity")));
		}
		else {
			equipment_ac_info.setEquipment_quantity(0);
		}
		
		Part inputfile = request.getPart("equipment_image");
		if(inputfile.getSize() > 0) {
			String foldername = "resources";
			String uploadpath = request.getServletContext().getRealPath("") + foldername;
			File directory = new File(uploadpath);
			if(!directory.exists()) {
				directory.mkdirs();
			}
			System.out.println(directory);
			String filename = inputfile.getSubmittedFileName();
			
			InputStream is = inputfile.getInputStream();
			Files.copy(is, Paths.get(uploadpath + File.separator + filename), StandardCopyOption.REPLACE_EXISTING);
			equipment_ac_info.setEquipment_image(filename);
		}
		
		session.setAttribute("session_status", equipmentdao.addequipmentac(equipment_ac_info));
		response.sendRedirect("Redirect_Servlet?action=equipmentac");
	}
}