package program;

import java.time.LocalDate;

public class FilePdf {
String name;
LocalDate date;
public FilePdf(String name, LocalDate date) {
	super();
	this.name = name;
	this.date = date;
}
public FilePdf() {
	super();
}
public String getName() {
	return name;
}
protected void setName(String name) {
	this.name = name;
}
public LocalDate getDate() {
	return date;
}
protected void setDate(LocalDate date) {
	this.date = date;
}
public String toJson()
{
	return "{\"name\":\""+name+"\",\"date\":\""+date+"\"}";
}
}
