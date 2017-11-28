package servlets;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import program.ReaderXMLtest;

/**
 * Servlet implementation class RemovePdf
 */
@WebServlet("/RemovePdfNoXml")
@MultipartConfig
public class RemovePdfNoXml extends HttpServlet {
	private boolean isMultipart;
	   private String filePath;
	   private int maxFileSize = 50 * 1024;
	   private int maxMemSize = 4 * 1024;
	   private File file ;
	private static final long serialVersionUID = 1L;
	   private final static Logger LOGGER = 
	            Logger.getLogger(UploadPdf.class.getCanonicalName());
   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemovePdfNoXml() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name=request.getParameter("name");
		ServletContext context = getServletContext();
		File f=new File(context.getRealPath("/"+name+".pdf"));
		//ReaderXMLtest r=new ReaderXMLtest();
		//r.removeLetter(name, context.getRealPath("/files/Users.xml"));
		if(f.delete()){
			System.out.println(f.getName() + " is deleted!");
		}else{
			System.out.println("Delete operation is failed.");
		}	
		 final Part filePart = request.getPart("file1");
		    String newname=filePart.getName();
		request.setAttribute("file1", filePart);
		RequestDispatcher rd = request.getRequestDispatcher("./UploadPdf?mod=true");
		rd.forward(request,response);
		ReaderXMLtest r=ReaderXMLtest.getInstance();
		String fullPath=context.getRealPath("files/Files.xml");
		r.modifyLetter(name, newname, fullPath);	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name=request.getParameter("name");
		System.out.println(name);
		String n=request.getParameter("n");
		System.out.println(n);
		 final Part filePart = request.getPart("file"+n);
		 System.out.println(filePart);
		ServletContext context = getServletContext();
		File f=new File(context.getRealPath("/"+name+".pdf"));
		//ReaderXMLtest r=new ReaderXMLtest();
		//r.removeLetter(name, context.getRealPath("/files/Users.xml"));
		if(f.delete()){
			System.out.println(f.getName() + " is deleted!");
		}else{
			System.out.println("Delete operation is failed.");
		}	
	    String newname = getFileName(filePart);
	    newname=newname.substring(0,newname.lastIndexOf("."));
		request.setAttribute("file"+n, filePart);
		request.setAttribute("mod", "true");
		request.setAttribute("n", n);
		RequestDispatcher rd = request.getRequestDispatcher("./UploadPdf");
		rd.forward(request,response);
		ReaderXMLtest r=ReaderXMLtest.getInstance();
		String fullPath=context.getRealPath("files/Files.xml");
		r.modifyLetter(name, newname, fullPath);
	}
	private String getFileName(final Part part) {
	    final String partHeader = part.getHeader("content-disposition");
	    LOGGER.log(Level.INFO, "Part Header = {0}", partHeader);
	    for (String content : part.getHeader("content-disposition").split(";")) {
	        if (content.trim().startsWith("filename")) {
	            return content.substring(
	                    content.indexOf('=') + 1).trim().replace("\"", "");
	        }
	    }
	    return null;
	}
}
