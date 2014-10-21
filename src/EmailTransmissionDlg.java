
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailTransmissionDlg extends JDialog {
	
	
	final EmailTransmissionDlg Window; 
	
	public EmailTransmissionDlg(String EmailAddress,int row){
		Window = this;
		Window.EmailCreate(EmailAddress,row);
		Window.setModal(true);
	}
	

	
	public void EmailCreate(String EmailAddress,int row){
		
		  DataStore T;
		    T = DataStore.getInstance();
		    Contact P = T.GetPerson(row);
		    String SendingAddress = P.getEmail();
		
		JPanel newpane5 = new JPanel();
		  
		final JTextField LastNameText = new JTextField(" ", 20); 
		Window.setSize(750,430);
		Window.getContentPane().setLayout(new FlowLayout());
		JLabel Address = new JLabel("From ");
		JLabel Email = new JLabel(EmailAddress);
		JSplitPane PhoneCombined = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,Address,Email);
		
		JLabel To = new JLabel("To  ");
		final JTextField outAddress = new JTextField(SendingAddress, 20);
		
		JSplitPane ToAddress = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,To,outAddress);
		
		
		
		JPanel SendersAddress = new JPanel();
		SendersAddress.add(ToAddress);
		JPanel RecepientsAddress = new JPanel();
		RecepientsAddress.add(SendersAddress);
		
		
		Window.getContentPane().setLayout(new FlowLayout());
		final JTextArea textArea = new JTextArea();
		textArea.setColumns(50);
		textArea.setRows(20);
		JPanel text = new JPanel();
		
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		
		JScrollPane pane = new JScrollPane(textArea);
		text.add(pane);
		
		//JPanel testing = new JPanel();
		
		
		JButton send = new JButton("Send");
		
		JButton Cancel = new JButton("Cancel");
		
		
		ActionListener CancelSave = new ActionListener(){
        	public void actionPerformed(ActionEvent c){
        		Window.dispose();
        	}
		};
		Cancel.addActionListener(CancelSave);

		
		ActionListener SaveFirstNametext = new ActionListener(){ 	
			public void actionPerformed(ActionEvent c){
				DataStore T;
			    T = DataStore.getInstance();
		     String X = LastNameText.getText();
			 T.setSubject(LastNameText.getText());
			 
			 X = outAddress.getText();
			 T.setOutAddress(X);
			 
			 X = textArea.getText();
			 
			 T.setBody(X);

			 Window.dispose();
			 String s = T.getOutAddress();
			 try {
					InternetAddress k[] = InternetAddress.parse(s);
					for(InternetAddress s1: k){
						sendEmail(T.getBody(),s1.toString(),T.getSubject());
					}
				} catch (AddressException e) {
					 //TODO Auto-generated catch block
					e.printStackTrace();
				}
			 //sendEmail(T.getBody(),T.getOutAddress(),T.getSubject());
			}
		};
		send.addActionListener(SaveFirstNametext);
	    text.add(send);
	    text.add(Cancel);

	    JLabel subject = new JLabel("Subject");
	    Window.add(subject);
	    //final JTextField LastNameText = new JTextField("", 20);
		newpane5.add(add(LastNameText));
		Window.add(newpane5);
	    Window.add(SendersAddress);
		Window.add(text);
		Window.add(PhoneCombined);
		Window.add(RecepientsAddress);
		
		
		
	};
		
		
	private void sendEmail(String body, String out, String subj){
		try {
			Properties props = new Properties();
			props.put("mail.smtp.host", DataStore.getInstance().getConfig().getOutSMTP());
			// Trying to send via gmail
			
			
			props.put("mail.smtp.socketFactory.port","465");
			props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth","true");
			props.put("mail.smtp.port", "465");
			
			Session session = Session.getDefaultInstance(props,new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(DataStore.getInstance().getConfig().getUserName(),
							DataStore.getInstance().getConfig().getPassword());
				}
			});
			// end added code
			//Session session = Session.getDefaultInstance(props, null);
			
			Message msg = new MimeMessage(session);
			msg.setRecipient(RecipientType.TO, new InternetAddress(out));
			msg.setSubject(subj);
			msg.setFrom(new InternetAddress(DataStore.getInstance().getConfig().getUserEmail()));
			msg.setText(body);
			
			Transport.send(msg);
			System.out.println("Message sent");
		} catch (Exception exc) {
			System.out.println("Exception: " + exc);
		}
	}


}