
import java.io.Serializable;

public class Configuration implements Serializable {

	private static final long serialVersionUID = -3908349960518851333L;

	private String userEmail;
	private String outSMTP;
	private String username;
	private String password;
	
	public Configuration(String email, String smtp,String user,String Pass){
		userEmail = new String(email);
		outSMTP = new String(smtp);
		username = new String(user);
		password = new String(Pass);
	}
		
	public String getUserEmail(){
		return userEmail;
	}
	
	public String getOutSMTP(){
		return outSMTP;
	}
	
	public void setUserEmail(String email){
		userEmail = new String(email);
	}
	
	public void setOutSMTP(String smtp){
		outSMTP = new String(smtp);
	}
	
	public void setUserName(String user){
		username = new String(user);
	}
	public void setPassword(String pass){
		password = new String(pass);
	}
	public String getUserName(){
		return username;
	}
	public String getPassword(){
		return password;
	}
	
}