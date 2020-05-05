import java.util.Vector;


public abstract class Person {
	public String name;
	public String phone_number;
	public String address;
	public int member_id;
	public Person(String name, String phone_number, String address, int member_id){
		this.name = name;
		this.phone_number = phone_number;
		this.address = address;
		this.member_id = member_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone_number;
	}
	public void setPhone(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getMemId() {
		return member_id;
	}
	public void setMemId(int member_id) {
		this.member_id = member_id;
	}
	public Book[] getBk() {
		return bk;
	}
	public void setBk(Book[] bk) {
		this.bk = bk;
	}
	Book[] bk;
	public Vector<Object> getall() {
		Vector<Object> myvector = new Vector<Object>();
		myvector.add(name);
		myvector.add(phone_number);
		myvector.add(address);
		myvector.add(member_id);
		return myvector;
	}
}
