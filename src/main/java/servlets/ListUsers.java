package servlets;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import program.Data;
import program.FilePdf;
import program.ReaderXMLtest;
import program.User;

/**
 * Servlet implementation class ListUsers
 */
@WebServlet("/ListUsers")
public class ListUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListUsers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Data d=new Data();
		PrintWriter out = response.getWriter();
		ReaderXMLtest x=ReaderXMLtest.getInstance();
				ServletContext context = getServletContext();
				String fullPath = d.getUrl()+"/Users.xml";
				List<User> files=x.readUsers(context.getRealPath("files/Users.xml"));
				//generare pagina html con lista e link a servlet (parametro)
				//poi servlet genera pagina automatic
				Iterator<User> i=files.iterator();
				String message="[";
				while(i.hasNext())
				{
					User s=i.next();
					//message+="<a ref=\"/newsletter/ViewLetter?name="+s+"\">"+s+"</a><br>";
				//	message+="<a href=\""+s.getName()+".pdf\">"+s.getName()+"</a><br>";
					message+=s.toJson()+",";
				}
				message=message.substring(0, message.length()-1);
				message+="]";
				out.println(message);
				}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
