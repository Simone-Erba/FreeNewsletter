package servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import program.ReaderXMLtest;

/**
 * Servlet implementation class RemovePdf
 */
@WebServlet("/RemovePdf")
public class RemovePdf extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemovePdf() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				String name=request.getParameter("name");
				ServletContext context = getServletContext();
				File f=new File(context.getRealPath("/"+name+".pdf"));
				ReaderXMLtest r=ReaderXMLtest.getInstance();
				r.removeLetter(name, context.getRealPath("files/Files.xml"));
				if(f.delete()){
					response.getWriter().println(f.getName() + " is deleted!");
				}else{
					response.getWriter().println("Delete operation is failed.");
				}	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
	}
}
