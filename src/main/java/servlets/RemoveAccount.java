package servlets;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import program.Data;
import program.ReaderXMLtest;


/**
 * Servlet implementation class RemoveAccount
 */
public class RemoveAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveAccount() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Data d=new Data();
		// TODO Auto-generated method stub
		String email=request.getParameter("mail");
		ReaderXMLtest r=ReaderXMLtest.getInstance();
		ServletContext context = getServletContext();
		String fullPath = d.getUrl()+"/Users";
		r.removeUser(email, context.getRealPath("files/Users.xml"));
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Data d=new Data();
		// TODO Auto-generated method stub
		String email=request.getParameter("mail");
		ReaderXMLtest r=ReaderXMLtest.getInstance();
		ServletContext context = getServletContext();
		String fullPath = d.getUrl()+"/Users";
		r.removeUser(email, context.getRealPath("files/Users.xml"));
	}

}
