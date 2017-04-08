package modele;

import java.sql.*;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class BaseData {

	private DatabaseMetaData md;
	private ResultSet rs;
	
	public BaseData(JComboBox<Object> comboTable, String type, String selectedItem) {
		getTablesColumnsNames(comboTable, type, selectedItem);
	}
	
	/**
	 * this function to get tables and column names from the database.
	 * @param combobox the ComboBox for columns or tables
	 * @param type there is two type 'tables' and 'columns'
	 * @param selectedItem that param is just for the columns it get selected items of the comboTable
	 */
	private void getTablesColumnsNames(JComboBox<Object> combobox, String type, String selectedItem) {
		try{
			md = MyConnection.connection.getMetaData(); 
			if(type.equals("tables")) { // that's for combobox tables
				rs = md.getTables(null, null, "%", null);
				while (rs.next()) {
					combobox.addItem(rs.getString(3));
    			}
			} 
			else if (type.equals("columns")) { // that's for combobox columns
				//I use the model because to refresh the combo every time i select a table to add new column
				DefaultComboBoxModel<Object> modele = new DefaultComboBoxModel<Object>();
				modele.addElement("*");
				
				rs = md.getColumns(null, null, selectedItem, "%");
				while (rs.next()) {
					modele.addElement(rs.getString(4));
					combobox.setModel(modele);
				}
			}

		}catch(Exception ex){
			JOptionPane.showMessageDialog(null,"" + ex.getMessage());
		}
	}
}
