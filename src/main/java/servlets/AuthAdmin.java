package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AuthAdmin
 */
@WebServlet("/AuthAdmin")
public class AuthAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String pass=(String) request.getParameter("pass");
		if(pass.equals("324d876c9dd6c6cadd20389e4b42c353cbb03b40ad677a47468efe73719ba468"))
		{
			HttpSession session = request.getSession();
			session.setAttribute("test", "test");
			//setting session to expiry in 30 mins
			session.setMaxInactiveInterval(30*60);
			//Cookie userName = new Cookie("mail", mail.trim());
			//userName.setMaxAge(30*60);
			//response.addCookie(userName);
			response.sendRedirect("Admin.jsp");
			System.out.println("AUTH ADMIN SUCCESS");
		}
		else
		{
			response.sendRedirect("Index.html");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
