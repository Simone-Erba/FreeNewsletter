package servlets;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import program.FilePdf;
import program.ReaderXMLtest;
import program.User;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
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
 
public class SendEmail extends HttpServlet {
	final static String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
	final static String username = "financialnewslettertest@gmail.com";//
	final static String password = "";
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
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		      throws ServletException, IOException {
		doPost(request, response);
	}
   public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      
      // Recipient's email ID needs to be mentioned.
	   //se è 1/1, 1/4, ecc.. mando mail
	   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate localDate = LocalDate.now();
		int m=localDate.getMonthValue();
		if(localDate.getDayOfMonth()==10&&m==9||(localDate.getDayOfMonth()==1&&(m==1||m==4||m==7||m==10)))
		{
	   connessione();
	   ReaderXMLtest r=new ReaderXMLtest();
	   ServletContext context = getServletContext();
		String fullPath = context.getRealPath("files/Users.xml");
        List<FilePdf> a=r.readFiles(context.getRealPath("files/Files.xml"));
        Iterator<FilePdf> i=a.iterator();
        FilePdf last=null;
        while(i.hasNext())
        {
        	FilePdf f=i.next();
        	if(last==null)
        	{
        		last=f;
        	}
        	else
        	{
        		if(f.getDate().isAfter(last.getDate()))
        		{
        			last=f;
        		}
        	}
        }
		String filename = context.getRealPath(last.getName()+".pdf");

	   List<User> list=r.readUsers(fullPath);
	   Iterator<User> it=list.iterator();
	   while(it.hasNext())
	   {
		   User u=it.next();
		   String to=u.getEmail();
		// -- Create a new message --
		Message msg = new MimeMessage(session);

		// -- Set the FROM and TO fields --
		try {
			msg.setFrom(new InternetAddress("financialnewslettertest@gmail.com"));
			msg.setSentDate(new Date());
			
					msg.setRecipients(Message.RecipientType.TO, 
							InternetAddress.parse(to,false));
					msg.setSubject("Newsletter settimale");
					
					 MimeBodyPart messageBodyPart = new MimeBodyPart();
					 Multipart multipart = new MimeMultipart();

			         // Set text message part

			         // Part two is attachment
			         /*DataSource source = new FileDataSource(filename);
			         messageBodyPart.setDataHandler(new DataHandler(source));
			         messageBodyPart.setFileName(filename);*/
			         messageBodyPart.attachFile(new File(filename));
			         multipart.addBodyPart(messageBodyPart);

			         // Send the complete message parts
			         msg.setContent(multipart);
					
					//	msg.setText("contenuto prova");
					
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
   }
} 