
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;



public class ContactEditingDlg extends JDialog{

	private static final long serialVersionUID = 9040581206672060689L;
	
	final ContactEditingDlg WContact; 
	
	
	ContactEditingDlg (Contact T, int row){
		WContact = this;
		WContact.CreateContact(T, row);
		WContact.setModal(true);
	}

	
	public void CreateContact(Contact c, int row){
		
		final int r = row;
		
		WContact.setSize(450, 350);
		//WContact.setModal(true);

		
		WContact.getContentPane().setLayout(new FlowLayout());
	    JLabel AddingLabel = new JLabel("Contact Information");
	    JPanel AddingPane = new JPanel();
	    AddingPane.add(AddingLabel);
	    
		JPanel newpane = new JPanel();
		JPanel newpane2 = new JPanel();
		
		JPanel newpane4 = new JPanel();
		JPanel newpane5 = new JPanel();
		JLabel FirstNameL = new JLabel("        First Name");
		JLabel LastNameL = new JLabel("       Last Name ");
		newpane.add(FirstNameL);
		newpane4.add(LastNameL);
		
		final JTextField LastNameText = new JTextField(c.getLastName(), 20);
		newpane5.add(add(LastNameText));
        		
		final JTextField FirstNameText = new JTextField(c.getFirstName(), 20);
		
        newpane2.add(add(FirstNameText));
        JSplitPane newpane3 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,newpane,newpane2);        
        JSplitPane newpane6 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,newpane4,newpane5);
                
        //email address
        JLabel EmailAddressL = new JLabel("  Email Address");
        JPanel EmailPanel = new JPanel();
        EmailPanel.add(EmailAddressL);
        JPanel EmailText = new JPanel();
        
        final JTextField EmailAddressText = new JTextField(c.getEmail(), 20);
        EmailText.add(add(EmailAddressText));
        JSplitPane EmailCombined = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,EmailPanel,EmailText);
        
        
        // Home address       
        JLabel PostalAddressL = new JLabel("Postal Address");
        JPanel PostalPanel = new JPanel();
        PostalPanel.add(PostalAddressL);
        JPanel PostalText = new JPanel();
        
        final JTextField PostalAddressText = new JTextField(c.getPostAdd(), 20);
        PostalText.add(add(PostalAddressText));
        JSplitPane PostalCombined = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,PostalPanel,PostalText);
                
        // phone
        JLabel PhoneAddress = new JLabel(" Phone Number");
        JPanel PhonePanel = new JPanel();
        PhonePanel.add(PhoneAddress);
        JPanel PhoneText = new JPanel();
        
        final JTextField PhoneNumberText = new JTextField(c.getPhone(), 20);
        PhoneText.add(add(PhoneNumberText));
        JSplitPane PhoneCombined = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,PhonePanel,PhoneText);
               
        JPanel saving = new JPanel();
        JButton saveContact = new JButton("Save Contact");  
        JButton Cancel		= new JButton("Cancel");

             
        ActionListener SaveText = new ActionListener(){
			public void actionPerformed(ActionEvent c){
				
				//String str = jTextField.getText()
				String FirstName = FirstNameText.getText();
				String LastName = LastNameText.getText();
			    String EmailAdd = EmailAddressText.getText();
			    String Postal = PostalAddressText.getText();
			    String Phone = PhoneNumberText.getText();
			    
			    Contact newContact = new Contact(FirstName,LastName,EmailAdd,Postal, Phone);
			    
			    DataStore T;
			    T = DataStore.getInstance();
			    if(EmailAdd.contains("@") &&  EmailAdd.contains(".")){
			    	T.AddPerson(newContact);
					T.fireTableDataChanged();				
					if (r >= 0){
						DataStore.getInstance().RemovePerson(r);
					}
					WContact.dispose();
			    } 
			    else{
				String message = "Invalid Email";
				JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
				JOptionPane.ERROR_MESSAGE);
			    }
			    			    				
			}		
		};
		
		 ActionListener CancelSave = new ActionListener(){
	        	public void actionPerformed(ActionEvent c){
	        		WContact.dispose();
	        	}
		 };
		
		Cancel.addActionListener(CancelSave);

						
		saveContact.addActionListener(SaveText);
		saving.add(saveContact);
		saving.add(Cancel);

		       
        WContact.add(AddingPane);
        WContact.add(newpane3);
        WContact.add(newpane6);
	    WContact.add(EmailCombined);
	    WContact.add(PostalCombined);
	    WContact.add(PhoneCombined);
	    WContact.add(saving);	    
	    
	    WContact.setLocationRelativeTo(null);  
	    WContact.setVisible(true);
		};		
		
		public ContactEditingDlg(){
			this(new Contact("","","","",""), -1);
		}
}