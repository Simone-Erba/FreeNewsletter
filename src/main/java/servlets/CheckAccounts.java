package servlets;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import program.ReaderXMLtest;
import program.User;

/**
 * Servlet implementation class CheckAccounts
 */
@WebServlet("/CheckAccounts")
public class CheckAccounts extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckAccounts() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ReaderXMLtest r=ReaderXMLtest.getInstance();
		ServletContext context = getServletContext();
		String fullPath = context.getRealPath("files/Users.xml");
    	List<User> users=r.readUsers(fullPath);
    	Iterator<User> i=users.iterator();
		LocalDate current = LocalDate.now();
    	while(i.hasNext())
    	{
    		User u=i.next();	
    		LocalDate scad=u.getDate();
    		if(current.compareTo(scad)>0)
    		{
    			//account scaduto
    			r.removeUser(u.getEmail(), fullPath);
    			  URL url = null;
    				try {
    					url = new URL("http://localhost:8080/newsletter/EmailAlert");
    				} catch (MalformedURLException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    				Map<String,Object> params = new LinkedHashMap<>();
    		        params.put("mail", u.getEmail());
    		        StringBuilder postData = new StringBuilder();
    		        for (Map.Entry<String,Object> param : params.entrySet()) {
    		            if (postData.length() != 0) postData.append('&');
    		            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
    		            postData.append('=');
    		            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
    		        }
    		        byte[] postDataBytes = postData.toString().getBytes("UTF-8");

    	            HttpURLConnection con = null;
    				try {
    					con = (HttpURLConnection) url.openConnection();
    				} catch (IOException e1) {
    					// TODO Auto-generated catch block
    					e1.printStackTrace();
    				}
    	            try {
    					con.setRequestMethod("POST");
    				} catch (ProtocolException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    	            con.setDoOutput(true);
    	            con.getOutputStream().write(postDataBytes);

    	            int responseCode2 = 0;
    				try {
    					responseCode2 = con.getResponseCode();
    				} catch (IOException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    				System.out.println(responseCode2);
    		}
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
