package servlets;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import program.Data;
import program.ReaderXMLtest;


/**
 * Servlet implementation class UploadPdf
 */
@MultipartConfig
public class UploadPdf extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private boolean isMultipart;
	   private String filePath;
	   private int maxFileSize = 50 * 1024;
	   private int maxMemSize = 4 * 1024;
	   private File file ;
	   private final static Logger LOGGER = 
	            Logger.getLogger(UploadPdf.class.getCanonicalName());
    /**
     * @see HttpServlet#HttpServlet()
     */
	   
    public UploadPdf() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		URL url = getClass().getResource("./WEB-INF/letter.pdf");
		ServletOutputStream out=response.getOutputStream();
		out.println(url.toString());
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	   public void doPost(HttpServletRequest request, HttpServletResponse response)
			      throws ServletException, java.io.IOException {
			      // Check that we have a file upload request
		// Create path components to save the file
		    String mod=(String) request.getAttribute("mod");
		    Part filePart=null;
		    System.out.println("mod:  "+mod);
		    if(mod!=null&&mod.equals("true"))
		    {
		    	 filePart = (Part) request.getAttribute("file"+request.getAttribute("n"));
		    	 System.out.println("letto file1");
		    	 System.out.println(filePart);
		    }
		    else
		    {
		    	filePart=request.getPart("file1");
		    }
		    final String fileName = getFileName(filePart);
		    ServletContext context = getServletContext();
		    //elimino il file e lo ricarico ma non modifico files.xml
		    //ATTENZIONE
		    //se kletter viene cancelata..
		    Data d=new Data();
		    String pathxml = context.getRealPath("files/Files.xml");
			String path = System.getProperty( "catalina.base" )+"/webapps/newsletter/WEB-INF/";
			String webInfPathTemp = getServletConfig().getServletContext().getRealPath("WEB-INF");
			String webInfPath= webInfPathTemp.substring(0, webInfPathTemp.lastIndexOf("WEB-INF"));
		    OutputStream out = null;
		    InputStream filecontent = null;
		    final PrintWriter writer = response.getWriter();
		    try {
		    	File f2=new File(webInfPath+"/"+fileName);
		    	System.out.println(f2.getAbsolutePath());
		        out = new FileOutputStream(f2);
		        filecontent = filePart.getInputStream();

		        int read = 0;
		        final byte[] bytes = new byte[1024];

		        while ((read = filecontent.read(bytes)) != -1) {
		            out.write(bytes, 0, read);
		        }
		        writer.println("New file " + fileName + " created at " + webInfPath);
				ReaderXMLtest r=ReaderXMLtest.getInstance();
		        System.out.println("pathxml  "+pathxml);
		     if(mod==null)
		     {   
		        r.createLetter(fileName.substring(0,fileName.lastIndexOf(".")), pathxml);
		    }
		        LOGGER.log(Level.INFO, "File{0}being uploaded to {1}", 
		                new Object[]{fileName, webInfPath});
		    } catch (FileNotFoundException fne) {
		        writer.println("You either did not specify a file to upload or are "
		                + "trying to upload a file to a protected or nonexistent "
		                + "location.");
		        writer.println("<br/> ERROR: " + fne.getMessage());

		        LOGGER.log(Level.SEVERE, "Problems during file upload. Error: {0}", 
		                new Object[]{fne.getMessage()});
		    } finally {
		        if (out != null) {
		            out.close();
		        }
		        if (filecontent != null) {
		            filecontent.close();
		        }
		        if (writer != null) {
		            writer.close();
		        }
		    }
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