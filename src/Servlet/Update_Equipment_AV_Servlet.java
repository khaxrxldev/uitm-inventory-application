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

import Model.Equipment_AV;

import DAO.Equipment_DAO;

@WebServlet("/Update_Equipment_AV_Servlet")
@MultipartConfig(
		fileSizeThreshold = 1024* 1024 * 10, //10Mb
		maxFileSize = 1024 * 1024 * 1000, //1Gb
		maxRequestSize = 1024 * 1024 * 1000 //1Gb
	)
public class Update_Equipment_AV_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Equipment_DAO equipmentdao;
	HttpSession session;
	
    public Update_Equipment_AV_Servlet() {
        super();
        equipmentdao = new Equipment_DAO();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Equipment_AV equipment_av_info = new Equipment_AV();
		session = request.getSession(true);
		
		equipment_av_info.setEquipment_idnum(request.getParameter("equipment_idnum"));
		equipment_av_info.setEquipment_status(request.getParameter("equipment_status"));
		
		if(request.getParameter("equipment_description") != "") {
			equipment_av_info.setEquipment_description(request.getParameter("equipment_description"));
		}
		if(request.getParameter("equipment_price").length() != 0) {
			equipment_av_info.setEquipment_price(Double.parseDouble(request.getParameter("equipment_price")));
		}
		
		if(request.getParameter("equipment_report_date").length() != 0) {
			String date = request.getParameter("equipment_report_date");
			Date dt;
			try {
				dt = new SimpleDateFormat("yyyy-MM-dd").parse(date);
				java.sql.Date sqlDate = new java.sql.Date(dt.getTime());
				equipment_av_info.setEquipment_report_date(sqlDate);
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
				equipment_av_info.setEquipment_maint_date(sqlDate);
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
				equipment_av_info.setEquipment_insert_date(sqlDate);
			}
			catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		equipment_av_info.setEqtype_idnum(request.getParameter("eqtype_idnum"));
		equipment_av_info.setDepartment_idnum(request.getParameter("department_idnum"));
		equipment_av_info.setStaff_idnum(request.getParameter("staff_idnum"));
		
		if(request.getParameter("supplier_idnum") != "") {
			equipment_av_info.setSupplier_idnum(request.getParameter("supplier_idnum"));
		}
		
		if(request.getParameter("location_idnum") != "") {
			equipment_av_info.setLocation_idnum(request.getParameter("location_idnum"));
		}
		
		equipment_av_info.setEquipment_brand(request.getParameter("equipment_brand"));
		equipment_av_info.setEquipment_model(request.getParameter("equipment_model"));
		
		if(request.getParameter("equipment_financialnum") != "") {
			equipment_av_info.setEquipment_financialnum(request.getParameter("equipment_financialnum"));
		}
		if(request.getParameter("equipment_treasurecode") != "") {
			equipment_av_info.setEquipment_treasurecode(request.getParameter("equipment_treasurecode"));
		}
		if(request.getParameter("equipment_officialnum") != "") {
			equipment_av_info.setEquipment_officialnum(request.getParameter("equipment_officialnum"));
		}
		if(request.getParameter("equipment_remote") != "") {
			equipment_av_info.setEquipment_remote(request.getParameter("equipment_remote"));
		}
		if(request.getParameter("equipment_converter") != "") {
			equipment_av_info.setEquipment_converter(request.getParameter("equipment_converter"));
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
			equipment_av_info.setEquipment_image(filename);
		}
		
		session.setAttribute("session_status", equipmentdao.updateequipmentav(equipment_av_info));
		response.sendRedirect("Redirect_Servlet?action=equipmentav_update&id=" + request.getParameter("equipment_idnum"));
	}
}