
import java.io.Serializable;

public class Contact implements Serializable{

	private static final long serialVersionUID = 4010688690612123571L;
	
	private String firstName;
	private String lastName;
	private String postAdd;
	private String phone;
	private String email;
	
	public Contact(String f, String l, String e, String p, String ph){
		firstName = new String(f);
		lastName = new String(l);
		email = new String(e);
		postAdd = new String(p);
		phone = new String(ph);
	}
	
	public String getFirstName(){
		return firstName;
	}
	
	public String getLastName(){
		return lastName;
	}
	
	public String getPostAdd(){
		return postAdd;
	}
	
	public String getPhone(){
		return phone;
	}
	
	public String getEmail(){
		return email;
	}

	public void setFirstName(String f){
		firstName = new String(f);
	}
	
	public void setLastName(String l){
		lastName = new String(l);
	}
	
	public void setPostAdd(String p){
		postAdd = new String(p);
	}
	
	public void setPhone(String ph){
		phone = new String(ph);
	}
	
	public void setEmail(String e){
		email = new String(e);
	}
	
	public boolean isEmpty(){
		boolean b = false;
		if (firstName.isEmpty() && lastName.isEmpty() && postAdd.isEmpty() && phone.isEmpty() && email.isEmpty()){
			b = true;
		}
		return b;
	}
}
