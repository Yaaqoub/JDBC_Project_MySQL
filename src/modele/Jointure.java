package modele;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Jointure {
	
	private ResultSet rs = null;
	private DatabaseMetaData meta = null; 
	private String idFirsttable;
	private String idOtheTable;
	
	
	public Jointure() {
		
	}
	
	/**
	 * i call this method in Fram class to add jointure to the labels
	 * @param tableName call them table name from the combobox in class Fram
	 * @param from call the Jlabel select in class fram
	 * @param where call the Jtextfield  where in class fram
	 */
	public void addJointure(String tableName, JLabel from, JTextField where) {
		try{
			meta = MyConnection.connection.getMetaData();
			rs = meta.getExportedKeys(null, null, tableName);
			buildPanel(tableName, from, where);
		} catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 *  this method to build a panel to put Jtable in it to display tables who have asso with the table selected
	 * @param tablename get the table selected name
	 * @param from get the lable from
	 * @param where get the textfield where 
	 */
	private void buildPanel(final String tablename, final JLabel from, final JTextField where) {
		JButton selectedTable = new JButton("Use Selected Table");

	    Object[] options = {selectedTable};
	    
	    final JTable table = new JTable(buildTableModel(rs));

	    selectedTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				//System.out.println(table.getValueAt(table.getSelectedRow(), 0));
				from.setText(" FROM " + tablename + "," + table.getValueAt(table.getSelectedRow(), 0));
				where.setText(" WHERE " + tablename + "." + idFirsttable + " = " +
						table.getValueAt(table.getSelectedRow(), 0) + "." + idOtheTable);
			}
		});
	    JScrollPane pane = new JScrollPane(table);
	    pane.setPreferredSize(new Dimension(300,125));
	    JOptionPane.showOptionDialog(null, pane, "Liste Tables",
	    		JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION,
	    		null, options, options[0]);
	}
	
	/**
	 * this methode is to fill Jtable in the option pane we created in buildPanel()
	 * @param rs call tehe resultset to extract data from it
	 * @return it return table model and we call it in buildPanel() to fill the JTable with it
	 */
	
	public DefaultTableModel buildTableModel(ResultSet rs) {
		Vector<String> columnNames = null;
		Vector<Vector<Object>> data = null;
		try{
			// names of column
		    columnNames = new Vector<String>();
		    columnNames.add("Tables");

		    // data of the table
		    data = new Vector<Vector<Object>>();
		    while (rs.next()) {
		        Vector<Object> vector = new Vector<Object>();
		        vector.add(rs.getObject(7));
		        data.add(vector);
		        idFirsttable = rs.getString(4);
		        idOtheTable = rs.getString(8);
		    }
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	    
	    return new DefaultTableModel(data, columnNames);

	}
}
