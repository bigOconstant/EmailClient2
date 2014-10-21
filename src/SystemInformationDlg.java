import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JLabel;

public class SystemInformationDlg extends JDialog {
	
	private static final long serialVersionUID = 1390565679421583353L;

	final SystemInformationDlg SysWindow; 
	
	public SystemInformationDlg(String s){
		SysWindow = this;
		SysWindow.InfoDlg(s);
		SysWindow.setModal(true);
	}
	
	
	
	public void InfoDlg(String s){
		//super();
		SysWindow.setSize(600, 400);
		
	
		
		
		SysWindow.getContentPane().setLayout(new FlowLayout());
	    JLabel label = new JLabel("Written by Caleb McCarthy");
	    label.setFont(new Font("Serif", Font.PLAIN, 36));
	    SysWindow.getContentPane().add(label);
	    
	    
	    JLabel label2 = new JLabel("Email client designed to");
	    label2.setFont(new Font("Serif", Font.PLAIN, 24));
	    SysWindow.getContentPane().add(label2);
	    
	    
	    JLabel label3 = new JLabel("Send email from contact List");
	    label3.setFont(new Font("Serif", Font.PLAIN, 24));
	    SysWindow.getContentPane().add(label3);
	    
	    SysWindow.setLocationRelativeTo(null);  
	    SysWindow.setVisible(true);
	}
	
}