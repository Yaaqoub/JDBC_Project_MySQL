package modele;

import javax.swing.JOptionPane;
import javax.swing.JTable;

public class DeleteRow {
	
	private FillJTable fill = new FillJTable();

	public DeleteRow() {
		
	}
	
	/**
	 * i call this from class Frame to delete a row in a JTable and in Database
	 * @param query the delete query 
	 * @param tableName that's for the database table selected so i can refresh the Jtable after removing a row
	 * @param table with that we call a JTable so i can afect change in it 
	 */
	public void delete(String query, String tableName, JTable table) {
		try{
			int dialogButton = JOptionPane.YES_NO_OPTION;
			int dialogResult = JOptionPane.showConfirmDialog (null, "Do you really want to delete that row !!","Warning",dialogButton);
			if(dialogResult == JOptionPane.YES_OPTION){
				MyConnection.stmt.executeUpdate(query);
			}
			else{
				return;
			}
			//refresh Jtable
			String finalQuery = "SELECT * " + tableName;
			table.setModel(fill.fillTable(finalQuery));
			
			
		}catch(Exception ex){
			JOptionPane.showMessageDialog(null,"" + ex.getMessage());
		}
	}
}
