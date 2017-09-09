package servlets;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import program.ReaderXMLtest;
import program.User;

/**
 * Servlet implementation class EmailAlert
 */
@WebServlet("/EmailAlert")
public class EmailAlert extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
	final static String username = "financialnewslettertest@gmail.com";//
	final static String password = "numeroalto1";
static Session session;
	private static void connessione(){
		Properties props = System.getProperties();
		props.setProperty("mail.smtp.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
		props.setProperty("mail.smtp.socketFactory.fallback", "true");
		props.setProperty("mail.smtp.port", "587");
		props.setProperty("mail.smtp.socketFactory.port", "587");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.debug", "true");
		props.put("mail.store.protocol", "pop3");
		props.put("mail.transport.protocol", "smtp");

		session = Session.getDefaultInstance(props, 
				new Authenticator(){
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}});
}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmailAlert() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("att "+request.getAttribute("mail"));
		String email=request.getParameter("mail");
		String content="Attenzione il tuo abbonamento è scaduto, ma puoi rinnovare collegandoti al nostro sito";
		connessione();
System.out.println("par "+email);
			   String to=email;
			// -- Create a new message --
			Message msg = new MimeMessage(session);

			// -- Set the FROM and TO fields --
			try {
				msg.setFrom(new InternetAddress("financialnewslettertest@gmail.com"));
				msg.setSentDate(new Date());
				
						msg.setRecipients(Message.RecipientType.TO, 
								InternetAddress.parse(to,false));
						msg.setSubject("Avviso scadenza");
						
	
						
							msg.setText(content);
						
						Transport.send(msg);
						System.out.println("Message sent.");
					
			
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
	}
		   
	}

}
