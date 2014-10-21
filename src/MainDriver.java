
import java.util.Vector;



public class MainDriver {
	
	static DataStore T = DataStore.getInstance();
	public static void main(String[] args) {
		//DataStore.setConfig(new Configuration("gurtejs@clemson.edu","smtp.clemson.edu"));

		MainFrame frame = new MainFrame("Simple Mail, created by Caleb McCarthy");
		
		
		
		
		frame.setVisible(true);
	}
}