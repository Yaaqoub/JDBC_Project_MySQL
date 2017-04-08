package vue;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import modele.BaseData;
import modele.DeleteRow;
import modele.FillJTable;
import modele.Jointure;
import modele.MyConnection;
import modele.ToExcel;

public class Frame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6165031319168234350L;
	
	//All menu bar variables
	private JMenuItem connection;
	private JMenuItem logOut;
	private JMenuItem exit;
	private JMenuItem about;
	//End menu bar variables
	
	//ComboBox variables
	private JComboBox<Object> tableCombo;
	private JComboBox<Object> colonneCombo;
	private JComboBox<Object> saveQuery;
	//End ComboBox variables
	
	//Buttons variables
	private JButton executer;
	private JButton print;
	private JButton delete;
	private JButton save;
	private JButton jointure;
	//End Buttons variables
	
	//Labels variables
	private JLabel select;
	private JLabel from;
	private JTextField where;
	//End Labels variables
	
	private JTable table;
	
	private MyConnection cnx;
	FillJTable fillT = new FillJTable(); // call the FillJTable constructor
	
	private String getToDelete = "";   	// this to get the tableCombo value to use it in the delete query
	private String [] querySaved;		// this to fill the save query combo Box
	
	public Frame() {
		init();
	}
	
	//that's the Frame configuration
	private void init() {
		this.setSize(700, 600); 						// Frame size
		this.setLocationRelativeTo(null); 				// Put Frame in center 
		this.setTitle("JDBC_Projet_Yaaqoub_Semlali"); 	// Frame name
		this.setResizable(false);		 				// Set the Frame to be not resizable
		setDefaultCloseOperation(EXIT_ON_CLOSE); 		// if we click on close, close the Frame
		this.setLayout(null);
		
		this.setJMenuBar(createMenu()); 				// Add Menu Bar to the Frame
		createComnoBox();								// Call ComboBox function
		createButtons();								// Call Buttons function
		createLables();									// Call Lables function
		createJtable();									// Call JTable function
		addListener();									// Call the Listener function
	}
	
	//Function to create Menu
	private JMenuBar createMenu() {
		JMenuBar menubar = new JMenuBar();
		
		//Menu File
		JMenu file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_F);
		
    	connection = new JMenuItem("Connection");
    	file.add(connection);
    	
    	logOut = new JMenuItem("Log out");
    	file.add(logOut);
    	logOut.setEnabled(false);
    	
    	exit = new JMenuItem("Exit");
    	file.add(exit);
    	
    	menubar.add(file);
    	//End Menu File
    	
    	//Menu Help
    	JMenu help = new JMenu("Help");
    	help.setMnemonic(KeyEvent.VK_H);
    	
    	about = new JMenuItem("About");
    	help.add(about);
    	
    	menubar.add(help);
    	//End Menu Help
    	return menubar;
	}
	
	private void createComnoBox() {
		//Create new ComboBox
		tableCombo = new JComboBox<Object>();
    	colonneCombo = new JComboBox<Object>();
    	saveQuery = new JComboBox<Object>();
    	
    	saveQuery.addItem("Saved Query");
    	
    	//Set ComboBox size and location
    	tableCombo.setBounds(30, 30,130,30);
    	colonneCombo.setBounds(190, 30,130,30);
    	saveQuery.setBounds(350, 30,200,30);
    	
    	//Add ComboBox to the Frame
    	this.add(tableCombo);
    	this.add(colonneCombo);
    	this.add(saveQuery);
	}
	
	private void createButtons() {
		//Create Buttons
		executer = new JButton("Executer");
    	executer.setBounds(560, 30, 100, 30);
    	executer.setBackground(Color.RED);
    	executer.setEnabled(false);
    	executer.setOpaque(true);
    	this.add(executer);
    	
    	save = new JButton("Save Query");
    	save.setBounds(450, 500, 150, 30);
    	save.setBackground(Color.LIGHT_GRAY);
    	save.setOpaque(true);
    	this.add(save);
    	
    	print = new JButton("Print");
    	print.setBounds(60, 500, 150, 30);
    	print.setBackground(Color.LIGHT_GRAY);
    	print.setOpaque(true);
    	print.setEnabled(false);
    	this.add(print);
    	
    	delete = new JButton("Delete");
    	delete.setBounds(250, 500, 150, 30);
    	delete.setBackground(Color.LIGHT_GRAY);
    	delete.setOpaque(true);
    	delete.setEnabled(false);
    	this.add(delete);
    	
    	jointure = new JButton("Jointure");
    	jointure.setBounds(250, 450, 150, 30);
    	jointure.setBackground(Color.LIGHT_GRAY);
    	jointure.setOpaque(true);
    	jointure.setEnabled(false);
    	this.add(jointure);
    	//End Create Buttons
	}
	
	private void createLables() {
		//Create Labels
		select = new JLabel (" SELECT * ");
		select.setBounds(10, 70, 650, 30);
    	select.setBackground(Color.LIGHT_GRAY);
    	select.setOpaque(true);
		
    	from = new JLabel (" FROM ");
    	from.setBounds(10, 100, 650, 30);
    	from.setBackground(Color.LIGHT_GRAY);
    	from.setOpaque(true);
    	
    	where = new JTextField (" WHERE ");
    	where.setBounds(10, 135, 650, 30);

    	this.add(select);
    	this.add(from);
    	this.add(where);
    	//End Create Labels
	}
	
	private void createJtable() {
		table = new JTable();
		table.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.GRAY));
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 170, 650, 250);
		this.getContentPane().add(scrollPane);
	}
	
	//add an anonymous listener to the Frame Component (buttons, MenuItems, ...)
	private void addListener() {
		// Connection listener
		connection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				cnx = new MyConnection(); 		// call connection class
				connection.setEnabled(false); 	// disable connection Menu
				logOut.setEnabled(true);		// enable logOut Menu
				if (cnx.getIsCon() == true){  	// if the application is connected set the executer button color to green
            		executer.setBackground(Color.GREEN);
            		executer.setEnabled(true);
            		jointure.setEnabled(true);
            	}
				new BaseData(tableCombo, "tables", null);
			}
		});
		// End connection listener
		
		// ComboBox Table Listener
		tableCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				@SuppressWarnings("unchecked")
				JComboBox<String> comboBox = (JComboBox<String>) event.getSource();
				Object selected = comboBox.getSelectedItem();
				new BaseData(colonneCombo, "columns", selected.toString());
				from.setText(" FROM " + selected.toString());
				select.setText("SELECT *");
				where.setText(" WHERE ");
				getToDelete = selected.toString();
			}
		});
		//End ComboBox Table Listener
		
		// ComboBox Table Listener
		colonneCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				@SuppressWarnings("unchecked")
				JComboBox<String> comboBox = (JComboBox<String>) event.getSource();
				Object selected = comboBox.getSelectedItem();
				select.setText("SELECT " + selected.toString());
			}
		});
		//End ComboBox Table Listener
		
		// Button executer Listener
		executer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String query;
				/**
				 * if there is only 'where' then it's true
				 * trim to remove space in the right and left of the string
				 * and equalsIgnoreCase to compare the where lable with "where"
				 */
            	if (where.getText().trim().equalsIgnoreCase("where")) { 
            		query = select.getText() + from.getText();
            	}
            	else{ // if there is somthing in where use this 
            		query = select.getText() + from.getText() + where.getText();
            	}
            	table.setModel(fillT.fillTable(query)); // fill the  table with Jtable model
            	repaint();
                revalidate();
                delete.setEnabled(true);
                print.setEnabled(true);
			}
		});
		//End Button executer Listener
		
		// Button delete Listener
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String deleteQuery = "DELETE FROM " + getToDelete 
						+ " WHERE " + colonneCombo.getItemAt(1).toString() 
						+ " = " + table.getValueAt(table.getSelectedRow(), 0).toString();
				DeleteRow deleterow = new DeleteRow();
				deleterow.delete(deleteQuery, from.getText(), table);
			}
		});
		//End Button delete Listener
		
		// Button print Listener
		print.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
                    ToExcel exp = new ToExcel();
                    exp.fillData(table, new File("C:\\result.xls"));
                    JOptionPane.showMessageDialog(null, "Data saved at " +
                            "'C: \\ result.xls' successfully", "Message",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                } 
			}
		});
		//End Button print Listener
		
		// Button save Listener
		final int max = 20;
    	querySaved = new String[max];
    	final DefaultComboBoxModel<Object> saveModel = new DefaultComboBoxModel<Object>();
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (where.getText().trim().equalsIgnoreCase("where")) {
					int count = Collections.frequency(Arrays.asList(querySaved), null);
					int toFill = max - count;
					querySaved[toFill] = select.getText() + from.getText();
					saveModel.addElement(querySaved[toFill]);
					saveQuery.setModel(saveModel);
            	}
				else{
					int count = Collections.frequency(Arrays.asList(querySaved), null);
					int toFill = max - count;
					querySaved[toFill] = select.getText() + from.getText() + where.getText();
					saveModel.addElement(querySaved[toFill]);
					saveQuery.setModel(saveModel);
				}
			}
		});
		//End Button save Listener
		
		// ComboBox save query Listener
		saveQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				@SuppressWarnings("unchecked")
				JComboBox<String> comboBox = (JComboBox<String>) event.getSource();
				Object selected = comboBox.getSelectedItem();
				String exQuery = selected.toString();
				
				table.setModel(fillT.fillTable(exQuery)); // fill the  table with Jtable model
            	repaint();
                revalidate();
			}
		});
		//End ComboBox save query Listener
		
		// Menu logOut Listener
		logOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try{
					MyConnection.connection.close(); 
					MyConnection.stmt.close();
                	from.setText("");
                	select.setText("");
                	where.setText("");
                	table.setModel(new DefaultTableModel());
                	tableCombo.addItem(" ");
                	colonneCombo.addItem(" ");
                	for(int i=tableCombo.getItemCount() - 2; i>=0; i--){
                		tableCombo.removeItemAt(i);
                	}
                	for(int i=colonneCombo.getItemCount() - 2; i>=0; i--){
                		colonneCombo.removeItemAt(i);
                	}
                	executer.setBackground(Color.red);
                	executer.setEnabled(false);
                	connection.setEnabled(true);
                	logOut.setEnabled(false);
                	
            	}catch(Exception e){
            		e.printStackTrace();
            	}
			}
		});
		//End Menu logOut Listener
		
		// Button jointure Listener
		jointure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				select.setText("SELECT * ");
				Jointure join = new Jointure();
				join.addJointure(tableCombo.getSelectedItem().toString(), from, where);
			}
		});
		//End Button jointure Listener
		
		// Button Exit Listener
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});
		//End Exit Listener
	}
}