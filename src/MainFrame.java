
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
//import java.awt.event.WindowListener;


import javax.swing.*;
import javax.swing.border.BevelBorder;
//import javax.swing.plaf.basic.BasicOptionPaneUI.ButtonActionListener;



public class MainFrame extends JFrame {
	
	
	public MainFrame(String caption) {
		super(caption);
		initFrame();
		
	}

	
	/* Need to add ContactTable in to the DataStore class */ 
	//static ContactTable tableModel = new ContactTable();
	private void initFrame() {
		
		/* button creation**********************************************/
		final JButton Add = new JButton("Add");
		final JButton buttonEdit = new JButton("Edit");
		final JButton buttonDelete = new JButton("Delete");
		final JTextArea textArea = new JTextArea();
		final JButton File = new JButton("FILE");

		
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("e-mail.png"));
   
        
		this.setSize(800, 500);
		
	
		
		JTable tablee = DataStore.getInstance().getTable();
		this.addWindowListener(new WindowEventHandler());
	    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		
		 
		/* Beginning of Menu creation ****************************************/
		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		menuBar.setBorder(new BevelBorder(BevelBorder.RAISED));
		JMenu menu = new JMenu("FILE");
		
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription(
		        "The only menu in this program that has menu items");
		menuBar.add(menu);
		//a group of JMenuItems
		JMenuItem menuItem;
		
		menuItem = new JMenuItem("Exit",
		                         KeyEvent.VK_T);
		menuItem.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_1, ActionEvent.ALT_MASK));
		menuItem.getAccessibleContext().setAccessibleDescription(
		        "This doesn't really do anything");
		
		// action listener for exiting
				ActionListener exitbutton = new ActionListener(){
					public void actionPerformed(ActionEvent c){
						DataStore.save();
						System.out.println("exiting program");
						System.exit(0);
					}
				};
		menuItem.addActionListener(exitbutton);
		menu.add(menuItem);
		
		//Build second menu in the menu bar.
		menu = new JMenu("Configuration");
		menu.setMnemonic(KeyEvent.VK_N);
		menu.getAccessibleContext().setAccessibleDescription(
      "To be added");
		
		JMenuItem editConfig = new JMenuItem("Configure");
		
		
		//addcontact.addActionListener(contactadd);
		menu.add(editConfig);
		
		ActionListener eCon = new ActionListener(){
			public void actionPerformed(ActionEvent c){
				ConfigurationDig help = new ConfigurationDig();
				help.setVisible(true);	
				
			}
			
		};
		editConfig.addActionListener(eCon);

		
		
		menuBar.add(menu);

		menu = new JMenu("Help");
		menu.getAccessibleContext().setAccessibleDescription(
	      "This menu desplays help info");
		JMenuItem displayhelp = new JMenuItem("About");
		menu.add(displayhelp);
		
		// Defines how to display about info
		ActionListener about = new ActionListener(){
			public void actionPerformed(ActionEvent c){
				SystemInformationDlg help = new SystemInformationDlg("About");
				help.setVisible(true);	
				
			}
			
		};
		displayhelp.addActionListener(about);
		menuBar.add(menu);

		this.setJMenuBar(menuBar);

		
		this.getContentPane(  ).add(menuBar, BorderLayout.NORTH);
		/* end of Menu Code *******************************************/
		
		
		
		ActionListener contactadd = new ActionListener(){
			public void actionPerformed(ActionEvent c){
				ContactEditingDlg adding = new ContactEditingDlg();
				DataStore T;
				T = DataStore.getInstance();

				adding.setVisible(true);					
				
				T.fireTableDataChanged();
				
			}
		};
		Add.addActionListener(contactadd);{
			
		}
		
		ActionListener editContact = new ActionListener(){
			public void actionPerformed(ActionEvent c){
				DataStore T;
				T = DataStore.getInstance();
				int row = T.getTable().getSelectedRow();
				if(row >= 0){
					ContactEditingDlg adding = new ContactEditingDlg(T.GetPerson(row),row);
					adding.setVisible(true);					
					
					T.fireTableDataChanged();
				}
				
				
			}
		};

		
		//int rowIndex = T.getSelectedRow();
		ActionListener DeleteContact = new ActionListener(){
			public void actionPerformed(ActionEvent c){
				DataStore T;
				T = DataStore.getInstance();
				int row = T.getTable().getSelectedRow();
				if(row >= 0){
				//T.RemovePerson(row);
				System.out.println(row);
				//default icon, custom title
				System.out.printf("JOptionPane.YES_OPTION    = %d%n" +
                        "JOptionPane.NO_OPTION     = %d%n" +
                        "JOptionPane.CLOSED_OPTION = %d%n",
                         JOptionPane.YES_OPTION,
                         JOptionPane.NO_OPTION,
                         JOptionPane.CLOSED_OPTION);
				int more;
     
				more = JOptionPane.showConfirmDialog(null, "Delete Contact?", "Input",
                                    JOptionPane.YES_NO_OPTION);
				if(more == JOptionPane.YES_OPTION){
        	  T.RemovePerson(row);
				}
				else{
        	  
				}
				T.fireTableDataChanged();
				}
			}
		};
				
		buttonDelete.addActionListener(DeleteContact);	
		buttonEdit.addActionListener(editContact);
		
		
		JPanel buttons = new JPanel();
		buttons.setLayout(new FlowLayout());
		buttons.add(Add);
		buttons.add(buttonDelete);
		buttons.add(buttonEdit);
		buttons.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		
		JPanel right = new JPanel();
		right.setLayout(new BorderLayout());
		
		right.add(buttons,"South");
		
		
	//	DataStore T;
		//T = DataStore.getInstance();
		//System.out.println(T.GetPerson(1).getFirstName());
		//ContactTable tableModel = new ContactTable(dData.GetList());
		
		
		
		 //Setting up action listener *//*
		tablee.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if (e.getClickCount() == 2){
					JTable target = (JTable)e.getSource();
					int row = target.getSelectedRow();
					int column = target.getSelectedColumn();
					
					EmailTransmissionDlg Wmail = new EmailTransmissionDlg(DataStore.getInstance().getConfig().getUserEmail(),row);
					Wmail.setVisible(true);
					DataStore T;
					T = DataStore.getInstance();
					
					
					T.fireTableDataChanged();
					
				}
			}});

		
		
		//tablee = new JTable(tableModel);
		JScrollPane ScrollPane = new JScrollPane(tablee);
		right.add(ScrollPane);

		
		//right.add(tablee,"North");
		
		
		
		this.getContentPane().add(right);
		
		}


	

		
	}   
class WindowEventHandler extends WindowAdapter {
	  public void windowClosing(WindowEvent evt) {
	    DataStore T;
	    T = DataStore.getInstance();
	    T.save();
	    System.exit(0);
	  }
	}