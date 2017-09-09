package program;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.xml.sax.*;
import org.w3c.dom.*;
public class ReaderXMLtest {
	public void createLetter(String letter, String fullPath) {
		Document dom=readXML(fullPath);
		Element users=dom.getDocumentElement();
		Element newletter = dom.createElement("file");
		Element name = dom.createElement("name");
		Element date = dom.createElement("date");
		name.appendChild(dom.createTextNode(letter));
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate localDate = LocalDate.now();
		System.out.println(dtf.format(localDate)); //2016/11/16
		date.appendChild(dom.createTextNode(dtf.format(localDate).toString()));
		newletter.appendChild(name);
		newletter.appendChild(date);
		users.appendChild(newletter);
		applyandoutput(dom,fullPath);
	}
	public void removeUser(String useri, String fullPath) {
		Document dom=readXML(fullPath);
		Element main=dom.getDocumentElement();
		NodeList letters= main.getChildNodes();
		int i=0;
		Node n=null;
		while((n=letters.item(i))!=null)
		{
			NodeList attr= n.getChildNodes();
			Node n1=null;
			int j=0;
			while((n1=attr.item(j))!=null)
			{
				if(n1.getTextContent().equals(useri))
				{
					main.removeChild(n);
					System.out.println(n1.getNodeName());
				}
				j++;
			}

			i++;
		}

		applyandoutput(dom,fullPath);
	}
	public void removeLetter(String useri, String fullPath) {
		Document dom=readXML(fullPath);
		Element main=dom.getDocumentElement();
		NodeList letters= main.getChildNodes();
		int i=0;
		Node n=null;
		System.out.println("searching for deleting");
		while((n=letters.item(i))!=null)
		{
			NodeList attr= n.getChildNodes();
			Node n1=null;
			int j=0;
			while((n1=attr.item(j))!=null)
			{
				String nam=n1.getTextContent();
				System.out.println(nam);
				if(nam.equals(useri))
				{
					main.removeChild(n);
					System.out.println("deleted "+n.getNodeName()+"   "+n1.getTextContent());
				}
				j++;
			}
			i++;
		}
		applyandoutput(dom,fullPath);
	}
	private void applyandoutput(Document dom, String fullPath) {
		// TODO Auto-generated method stub
		TransformerFactory transformerFactory =
				TransformerFactory.newInstance();
		Transformer transformer = null;
		try {
			transformer = transformerFactory.newTransformer();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DOMSource source = new DOMSource(dom);
		StreamResult result =
				new StreamResult(new File(fullPath));
		try {
			transformer.transform(source, result);
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Output to console for testing
		StreamResult consoleResult =
				new StreamResult(System.out);
		try {
			transformer.transform(source, consoleResult);
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void createUser(String useri, String pass, String fullPath) {
		Document dom=readXML(fullPath);

		// create data elements and place them under root
		System.out.println(fullPath);
		Element users=dom.getDocumentElement();
		Element newletter = dom.createElement("user");
		Element name = dom.createElement("email");
		Element password = dom.createElement("pass");
		Element date = dom.createElement("scad");
		password.appendChild(dom.createTextNode(pass));
		name.appendChild(dom.createTextNode(useri));
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate localDate = LocalDate.now().plusYears(1);
		date.appendChild(dom.createTextNode(dtf.format(localDate).toString()));
		newletter.appendChild(name);
		newletter.appendChild(date);
		newletter.appendChild(password);
		users.appendChild(newletter);
		applyandoutput(dom,fullPath);
	}
	public void modifyLetter(String name, String newName, String fullPath)
	{
		Document dom=readXML(fullPath);
		NodeList nList = dom.getElementsByTagName("file");
		for (int temp = 0; temp < nList.getLength(); temp++)
		{
			Node nNode = nList.item(temp);
			System.out.println("MODIFY searching "+nNode.getTextContent());
			NodeList nodes=nNode.getChildNodes();
			for (int temp2 = 0; temp2 < nodes.getLength(); temp2++)
				{
					Node n = nodes.item(temp2);
					if (n.getNodeName() == "name"&&n.getTextContent().equals(name)) 
					{
						System.out.println("MODIFY trovato "+n.getTextContent());
						n.setTextContent(newName);
						System.out.println("MODIFY changed to"+newName);
					}
				}
			
		}
		applyandoutput(dom,fullPath);
	}
	public List<FilePdf> readFiles(String fullPath)
	{
		Document doc=readXML(fullPath);
		List<FilePdf> files = new ArrayList<FilePdf>();
		NodeList nList = doc.getElementsByTagName("file");
		for (int temp = 0; temp < nList.getLength(); temp++)
		{
			Node nNode = nList.item(temp);
			FilePdf u=new FilePdf();
			NodeList nodes=nNode.getChildNodes();
			for (int temp2 = 0; temp2 < nodes.getLength(); temp2++)
				{
					Node n = nodes.item(temp2);
					if (n.getNodeName() == "name") 
					{
						u.setName(n.getTextContent());
					}
					if (n.getNodeName() == "date")
					{
						DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd");
						String input = n.getTextContent().trim();
						LocalDate localDate = LocalDate.parse(input, DATE_FORMAT);
						u.setDate(localDate);
					}
				}
				files.add(u);
			}
		return files;
	}


	public Document readXML(String xml) {
		System.out.println("reading "+xml);
		Document dom = null;
		// Make an  instance of the DocumentBuilderFactory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			// use the factory to take an instance of the document builder
			DocumentBuilder db = dbf.newDocumentBuilder();
			// parse using the builder to get the DOM mapping of the    
			// XML file
			dom = db.parse(xml);



		} catch (ParserConfigurationException pce) {
			System.out.println(pce.getMessage());
		} catch (SAXException se) {
			System.out.println(se.getMessage());
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		}
		return dom;
	}
	public List<User> readUsers(String fullPath) {
		// TODO Auto-generated method stub
		Document doc=readXML(fullPath);
		List<User> files = new ArrayList<User>();
		NodeList nList = doc.getElementsByTagName("user");
		for (int temp = 0; temp < nList.getLength(); temp++)
		{
			Node nNode = nList.item(temp);
			User u=new User();
			NodeList nodes=nNode.getChildNodes();
			for (int temp2 = 0; temp2 < nodes.getLength(); temp2++)
				{
					Node n = nodes.item(temp2);
					if (n.getNodeName() == "pass") 
					{
						u.setPass(n.getTextContent());
					}
					if (n.getNodeName() == "email") 
					{
						u.setEmail(n.getTextContent());
					}
					if (n.getNodeName() == "scad")
					{
						DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd");
						String input = n.getTextContent().trim();
						LocalDate localDate = LocalDate.parse(input, DATE_FORMAT);
						u.setDate(localDate);
					}
				}
				files.add(u);
			}
		return files;
	}
}