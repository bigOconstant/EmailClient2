
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;
import javax.swing.JTable;
import java.io.*;


public class DataStore extends AbstractTableModel{

	private static final long serialVersionUID = -721350522511100848L;
	private static ArrayList<Contact> Persons ;
	private static Configuration config;
	private static DataStore instance = null;
	
	private static JTable Table;
	private static String Subject;
	private static String OutAddress;
	private static String Body;
	String Header[] = {"First Name","Last Name","Email Address","Mailing Address","Phone Number"};


	public void setSubject(String S){
		Subject = S;
	}
	public void setOutAddress(String S){
		OutAddress = S;
	}
	public void setBody(String S)
	{
		Body = S;
	}
	
	public String getSubject(){
		return Subject;
	}
	public String getOutAddress(){
		return OutAddress;
	}
	public String getBody(){
		return Body;
	}

	public String getColumnName(int index){
		return Header[index];
		
	}

	
	private DataStore(){
		Persons = new ArrayList<Contact>();
		
//		Contact P = new Contact("Katy","Perry","katyperry@gmail.com","307 my address","864-678-0569");
//		Persons.add(P);
		Table = new JTable(this);
	}
	
	public static DataStore getInstance(){
		if(instance == null){
			instance = new DataStore();			
			load();
		}
		return instance;
	}
	
	private static void load(){
		try {
			FileInputStream fis = new FileInputStream("contacts.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);
			Persons = (ArrayList<Contact>) ois.readObject();
			ois.close();

			FileInputStream fis2 = new FileInputStream("config.dat");
			ObjectInputStream ois2 = new ObjectInputStream(fis2);
			config = (Configuration) ois2.readObject();
			ois.close();				
		} catch (Exception exc) {
			config = new Configuration("","","","");
			System.out.println("Exception: " + exc);
		}					
	}
	
	public static void save(){
		try {
			FileOutputStream fos = new FileOutputStream("contacts.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(Persons);
			oos.close();
			
			FileOutputStream fos2 = new FileOutputStream("config.dat");
			ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
			oos2.writeObject(config);
			oos2.close();
			
		} catch (Exception exc) {
			System.out.println("Exception: " + exc);
		}		
	}
	
	public JTable getTable(){
		return Table;
	}
	
	public ArrayList<Contact> GetList(){
		return Persons;
	}
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 5;
	}

	
	public void AddPerson(Contact P){
		Persons.add(P);
	}
	public void RemovePerson(int i)
	{
		Persons.remove(i);
	}
	
	public void RemovePerson(Contact c){
		Persons.remove(c);
	}
	
	public Contact GetPerson(int i){
		return Persons.get(i);
	}

	public Configuration getConfig(){
		return config;
	}

	public static void setConfig(Configuration c){
		config = c;
	}
	
	public int getSize() {
		return Persons.size();
	}
	public int getRowCount() {
		return Persons.size();
	}

	
	public Object getValueAt(int arg0, int arg1) {
		switch(arg1){
		case 0:
			return Persons.get(arg0).getFirstName();
		case 1:
			return Persons.get(arg0).getLastName();
		case 2:
			return Persons.get(arg0).getEmail();
		case 3:
			return Persons.get(arg0).getPostAdd();
		case 4:
			return Persons.get(arg0).getPhone();
		default:
			return null;
		}
	}

	
}