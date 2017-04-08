package modele;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class MyConnection {
	
	private String pilote= "com.mysql.jdbc.Driver"; // Connection driver
	private String msg = "";						// that string to display it if connection done
	
	private String url; 							// DataBase URL
    private String login; 							// Database login
    private String pw; 								// DataBase password
    
    public static Connection connection;			// i use static because i don't want to call the constructor every time
    public static Statement stmt;					// i use static bacause i use that a lot so i want to create it just once
    
    private JTextField database;							// DataBase URL Field
    private JTextField username;							// DataBase login Field
    private JPasswordField password;						// DataBase password Field
    
    private boolean isCon = false;					// if there is connection True , if there isn't False 
    
	public MyConnection() {
		createLoginPanel();
		connectionDB();
	}
    
    public void setIsCon(boolean isCon) {
    	this.isCon = isCon;
    }
    
    public boolean getIsCon() {
    	return this.isCon;
    }
	
	//Connection configuration
	void connectionDB() {
		//Check if the use write the database name or his username
		if(database.getText().equals("") || username.getText().equals("")) {
			JOptionPane.showMessageDialog(null,"You Forget to write your Database name or your Username");
			createLoginPanel(); // show the Connection Panel again if he miss something
		}
		else {
			url = "jdbc:mysql://localhost/" + database.getText();
		    login = username.getText();
		    pw = new String(password.getPassword());
		}
		
		try{
			Class.forName(pilote);
			
			connection = DriverManager.getConnection(url,login,pw);
			
			msg = "You are now connected to DataBase: " + database.getText();
			
			stmt = connection.createStatement();
			setIsCon(true);
			JOptionPane.showMessageDialog(null,msg);
			
		}catch(Exception ex){
			//JOptionPane.showMessageDialog(null,"Erreur:" + ex.getMessage());
			new MyConnection(); // create a new connection if the erreur happen
		}
		
	}
	
	//Create a Panel for Connection
	void createLoginPanel() {
		JFrame frame = new JFrame();
		JPanel panel = new JPanel(new BorderLayout(5, 5));
		JPanel label = new JPanel(new GridLayout(0, 1, 2, 15));
		label.add(new JLabel("Database", SwingConstants.RIGHT));
	    label.add(new JLabel("Username", SwingConstants.RIGHT));
	    label.add(new JLabel("Password", SwingConstants.RIGHT));
	    
	    panel.add(label, BorderLayout.WEST);
		JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
		
		database = new JTextField();
	    controls.add(database);
	    database.setText("projet");
	    
		username = new JTextField();
	    controls.add(username);
	    username.setText("root");
	    
	    password = new JPasswordField();
	    controls.add(password);
	    
	    panel.add(controls, BorderLayout.CENTER);
		JOptionPane.showMessageDialog(frame, panel, "Login", JOptionPane.DEFAULT_OPTION);
	}
}