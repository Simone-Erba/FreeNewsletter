package program;

import java.time.LocalDate;
import java.util.Date;

public class User {
String email;
LocalDate date;
String pass;

public String getPass() {
	return pass;
}
protected void setPass(String pass) {
	this.pass = pass;
}
public String getEmail() {
	return email;
}
protected void setEmail(String email) {
	this.email = email;
}
public LocalDate getDate() {
	return date;
}
protected void setDate(LocalDate date) {
	this.date = date;
}
public User(String email, String pass, LocalDate date) {
	super();
	this.pass=pass;
	this.email = email;
	this.date = date;
}
public String toJson()
{
	return "{\"mail\":\""+email+"\",\"pass\":\""+pass+"\",\"scad\":\""+date+"\"}";
}
public User() {
}
}
