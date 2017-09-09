package servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import program.Data;
import program.FilePdf;
import program.ReaderXMLtest;

/**
 * Servlet implementation class ListLetters
 */
public class ListLetters extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListLetters() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Data d=new Data();
		PrintWriter out = response.getWriter();
		ReaderXMLtest x=new ReaderXMLtest();
		ServletContext context = getServletContext();
		String fullPath = d.getUrl()+"/Files.xml";
		List<FilePdf> files=x.readFiles(context.getRealPath("files/Files.xml"));
		//generare pagina html con lista e link a servlet (parametro)
		//poi servlet genera pagina automatic
		Iterator<FilePdf> i=files.iterator();
		String message="[";
		while(i.hasNext())
		{
			FilePdf s=i.next();
			//message+="<a ref=\"/newsletter/ViewLetter?name="+s+"\">"+s+"</a><br>";
		//	message+="<a href=\""+s.getName()+".pdf\">"+s.getName()+"</a><br>";
			message+=s.toJson()+",";
		}
		message=message.substring(0, message.length()-1);
		message+="]";
		out.println(message); // This will be available as ${message}
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
