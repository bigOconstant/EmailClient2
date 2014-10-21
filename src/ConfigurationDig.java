/*Go back and make all these panes readible */
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

public class ConfigurationDig extends JDialog{

	final ConfigurationDig WConfig; 
	
	ConfigurationDig (){
		WConfig = this;
		WConfig.CreateConfig();
		WConfig.setModal(true);
	}
	
	public void CreateConfig(){
		WConfig.setSize(400, 340);
        //WConfig.setModal(true);
		WConfig.getContentPane().setLayout(new FlowLayout());
	    JLabel AddingLabel = new JLabel("Edit Configuration");
	    JPanel AddingPane = new JPanel();
	    AddingPane.add(AddingLabel);
	    
		JPanel newpane = new JPanel();
		JPanel newpane2 = new JPanel();
		
		JPanel newpane4 = new JPanel();
		JPanel newpane5 = new JPanel();
		JPanel usernamePane = new JPanel();
		JPanel passwordPane= new JPanel();
		JPanel username2 = new JPanel();
		JPanel password2 = new JPanel();
		JLabel userEmailL = new JLabel("         User Email");
		JLabel outSMTPL =  new JLabel(" Outgoing SMTP");
		JLabel username = new JLabel("           Username");
		JLabel password = new JLabel("           Password");
		newpane.add(userEmailL);
		newpane4.add(outSMTPL);
		usernamePane.add(username);
		passwordPane.add(password);
		
		DataStore T;
		T = DataStore.getInstance();
		final JTextField smtpText = new JTextField(T.getConfig().getOutSMTP(), 20);
		//combining to panes below
		newpane5.add(add(smtpText));
        		
		final JTextField userEmailText = new JTextField(DataStore.getInstance().getConfig().getUserEmail(), 20);
		
		
		final JTextField usernameText = new JTextField(T.getConfig().getUserName(), 20);
		//final JTextField passwordText = new JTextField(T.getConfig().getPassword(), 20);
		final JPasswordField passwordText = new JPasswordField(T.getConfig().getPassword(),20);
		username2.add(add(usernameText));
		password2.add(add(passwordText));
		
		
        newpane2.add(add(userEmailText));
        JSplitPane newpane3 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,newpane,newpane2);        
        JSplitPane newpane6 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,newpane4,newpane5);
        JSplitPane username3 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,username,username2);
        JSplitPane password3 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,password,password2);
        
        
        JPanel saving = new JPanel();
        JButton saveConfig = new JButton("Save Configuration");        
        JButton Cancel		= new JButton("Cancel");
        final Boolean check;
        check = true;
        ActionListener SaveText = new ActionListener(){
			public void actionPerformed(ActionEvent c){
				
				
				String email = userEmailText.getText();
				String smtp = smtpText.getText();
				String User =  usernameText.getText();
				String Pass = passwordText.getText();		
			    Configuration con = new Configuration(email, smtp,User,Pass);			    
			    DataStore.setConfig(con);
			    
			    
			    if(email.contains("@") &&  email.contains(".") && smtp.contains("smtp")){
			    	WConfig.dispose();
			    } 
			    else{
				String message = "Invalid SMTP Address or Email";
				JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
				JOptionPane.ERROR_MESSAGE);
			    }
			}
					
		};
						
		
		 ActionListener CancelSave = new ActionListener(){
	        	public void actionPerformed(ActionEvent c){
	        		WConfig.dispose();
	        	}
		 };
		
		Cancel.addActionListener(CancelSave);

		
		saveConfig.addActionListener(SaveText);
		saving.add(saveConfig);
		saving.add(Cancel);

		       
        WConfig.add(AddingPane);
        WConfig.add(newpane3);
        WConfig.add(newpane6);
        WConfig.add(username3);
        WConfig.add(password3);
	    WConfig.add(saving);	    
	    
	    WConfig.setLocationRelativeTo(null);  
	    
	    WConfig.setVisible(true);
		};		

	}

