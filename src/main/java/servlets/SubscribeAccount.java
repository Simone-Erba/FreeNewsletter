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
 * Servlet implementation class SubscribeAccount
 */
public class SubscribeAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubscribeAccount() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	add to xml file
		Data d=new Data();
		String pass=request.getParameter("pass");
		String email=request.getParameter("email");
		System.out.println(email);
		ReaderXMLtest r=ReaderXMLtest.getInstance();
		ServletContext context = getServletContext();
		String fullPath = d.getUrl()+"/Users.xml";
		System.out.println(fullPath);
		r.createUser(email,pass,context.getRealPath("files/Users.xml"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
