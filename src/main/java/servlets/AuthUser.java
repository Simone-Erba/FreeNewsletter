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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import program.Data;
import program.ReaderXMLtest;
import program.User;

/**
 * Servlet implementation class AuthUser
 */
public class AuthUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		// Assuming your json object is **jsonObject**, perform the following, it will return your json object  
		
		String mail=(String) request.getParameter("mail");		
		String pass=(String) request.getParameter("pass");
		System.out.println("AUTH "+mail);
		System.out.println("AUTH "+pass);
		ReaderXMLtest r=new ReaderXMLtest();
		ServletContext context = getServletContext();
		System.out.println(context.getContextPath());
		System.out.println(context.getServerInfo());
		Data d=new Data();
		String rel=d.getUrl()+"Users.xml";
		List<User> l=r.readUsers(context.getRealPath("files/Users.xml"));//put fullpath
		System.out.println(l.size());
		Iterator<User> i=l.iterator();
		boolean fine=false;
		while(i.hasNext()&&fine==false)
		{
			User s=i.next();
			System.out.println("AUTH "+s.getEmail());
			System.out.println("AUTH "+s.getPass());
			if(pass.trim().equals(s.getPass().trim())&&mail.trim().equals(s.getEmail().trim()))
			{
				HttpSession session = request.getSession();
				session.setAttribute("mail", mail.trim());
				//setting session to expiry in 30 mins
				session.setMaxInactiveInterval(30*60);
				Cookie userName = new Cookie("mail", mail.trim());
				userName.setMaxAge(30*60);
				response.addCookie(userName);
				response.sendRedirect("User.jsp");
				System.out.println("AUTH succesful");
				fine=true;
				//response.setContentType("application/json");
				//out.println("{\"code\":\"200\",\"mail\":\""+mail+"\"}");
			}
		}
		if(fine==false)
		{
			response.sendRedirect("Index.html");
			//out.println("{\"code\":\"401\"}");
		}
		//out.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
