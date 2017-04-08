package modele;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class FillJTable {
	
	private Vector<String> columnNames;
	private Vector<Vector<Object>> data;
	
	public FillJTable() {
		
	}
	
	/**
	 * i called this model in the Frame class so i can fill the JTable with the  data extracted in here
	 * @param query the sql query
	 * @return it return a Jtable model
	 */
	public DefaultTableModel fillTable(String query) {
		try{
			ResultSet rs = MyConnection.stmt.executeQuery(query);
			
			ResultSetMetaData metaData = rs.getMetaData();
			
			// names of columns
		    columnNames = new Vector<String>();
		    int columnCount = metaData.getColumnCount();
		    for (int column = 1; column <= columnCount; column++) {
		        columnNames.add(metaData.getColumnName(column));
		    }
		    
		    // data of the table
		    data = new Vector<Vector<Object>>();
		    while (rs.next()) {
		        Vector<Object> vector = new Vector<Object>();
		        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
		            vector.add(rs.getObject(columnIndex));
		        }
		        data.add(vector);
		    }
		}catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		
		return new DefaultTableModel(data, columnNames);
	}
}
