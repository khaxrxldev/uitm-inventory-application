package Servlet;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/Image_Servlet")
public class Image_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String UPLOAD_DIRECTORY = "resources";
	public static String filename = null;
	
    public Image_Servlet() {
        super();
    }
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		filename = request.getParameter("filename");
		if (filename == null || filename.equals("")) {
			System.out.print("File is empty");
		}
		else {
			String applicationpath = getServletContext().getRealPath("");
			String downloadpath = applicationpath + File.separator + UPLOAD_DIRECTORY;
			String filepath = downloadpath + File.separator + filename;
			
			File file = new File(filepath);
			OutputStream os = null;
			FileInputStream is = null;
			
			if(file.exists()) {
				os = response.getOutputStream();
            	is = new FileInputStream(file);
				byte[] buffer = new byte[0x7a120];
            	int bytesread = -1;
            	
            	while ((bytesread = is.read(buffer)) != -1 ) {
            		os.write(buffer, 0, bytesread);
            		is.read(buffer);
            	}
            	os.flush();
				os.close();
			}
			else {
				System.out.print("File not exist");
			}
		}
	}
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}
}