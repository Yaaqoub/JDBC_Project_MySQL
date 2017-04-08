package modele;

import java.io.File;

import javax.swing.JTable;
/**
 * In here there is new package jxl .. 
 * so if you want to work u have to download the jxl.jar
 */
import javax.swing.table.TableModel;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ToExcel {
	public ToExcel(){
		
	}
	
	/**
	 * I used this function in the class Frame to put the JTable content in an excel file
	 * @param table get the Jtable 
	 * @param file get the file location and it name
	 */
	public void fillData(JTable table, File file) {
        try {

            WritableWorkbook workbook1 = Workbook.createWorkbook(file);
            WritableSheet sheet1 = workbook1.createSheet("First Sheet", 0); 
            TableModel model = table.getModel();

            for (int i = 0; i < model.getColumnCount(); i++) {
                Label column = new Label(i, 0, model.getColumnName(i));
                sheet1.addCell(column);
            }
            int j = 0;
            for (int i = 0; i < model.getRowCount(); i++) {
                for (j = 0; j < model.getColumnCount(); j++) {
                    Label row = new Label(j, i + 1, 
                            model.getValueAt(i, j).toString());
                    sheet1.addCell(row);
                }
            }
            workbook1.write();
            workbook1.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }	
}
