import java.util.Vector;

public class Book {
	boolean borrow; 
	String name; 
	int book_num; 
	String location;
	String auth; 
	String genre;
	int lender_id; 	
	public Book(String name2, String auth2, String genre2, String location2, int book_num2) {
		borrow = false; 
		name = name2;
		auth = auth2;
		genre = genre2;
		location = location2;
		book_num = book_num2;
	}
	public Book() {} 
	public Vector<Object> getall() { 
		Vector<Object> myvector = new Vector<Object>();
		myvector.add(name);
		myvector.add(auth);
		myvector.add(genre);
		myvector.add(location);
		myvector.add(book_num);
		myvector.add(lender_id);
		return myvector;
	}
	public boolean isBorrow() {
		return borrow;
	}
	public void setBorrow(boolean borrow) {
		this.borrow = borrow;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getNum() {
		return book_num;
	}
	public void setNum(int num) {
		this.book_num = num;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	public String getPublisher() {
		return genre;
	}
	public void setPublisher(String publisher) {
		this.genre = publisher;
	}
	public int getPersonname() {
		return lender_id;
	}
	public void setPersonname(int personname) {
		this.lender_id = personname;
	}
}
