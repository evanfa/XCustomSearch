package utils.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

import app.model.Record;

public class JTable_Component extends AbstractTableModel implements Observer{
	
	private static final String[] columnNames = {
    		"Id File", 
    		"File Name", 
    		"Absolute File Path", 
    		"Selection"};
	
	 private static final Class[] columnClasses = {
			 Integer.class, 
			 String.class, 
			 String.class, 
			 Boolean.class
			 };
	 
	 private ArrayList<Record> downloadList = new ArrayList<Record>();
			 
	 public void addDownload(Record download) {
	        //download.addObserver(this);    
	        downloadList.add(download);
	        // Fire table row insertion notification to table.
	        fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
	    }
	 

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return downloadList.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
        
       Record download = downloadList.get(row);
       switch (col) {
           case 0: // URL
               return download.getIndexId();
           case 1: // Size
               return download.getFileName();
           case 2: // Progress
               return download.getFilePath();
           case 3: //Speed
               return download.isSelectedId();
       }
       return "";
	}
	
	@Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		
		System.out.println("Vals: "+aValue+"Row: "+rowIndex+"Col: "+columnIndex);
		
		if (aValue instanceof Boolean) {
			fireTableCellUpdated(rowIndex, columnIndex);
		}
		
		/*if (columnIndex == 3) {
        	fireTableCellUpdated(rowIndex, columnIndex);
        	System.out.println("Flag: "+((Boolean) aValue).booleanValue());
            if (aValue instanceof Boolean) {
            	System.out.println("Flag: "+((Boolean) aValue).booleanValue());
                data[rowIndex][columnIndex] = (Boolean)aValue;
                fireTableCellUpdated(rowIndex, columnIndex);
            }
        }*/
    }
	
	@Override
	public void update(Observable o, Object arg) {
        int index = downloadList.indexOf(o);
        // Fire table row update notification to table.
        fireTableRowsUpdated(index, index);
    }
	
    // Get a column's name.
    public String getColumnName(int col) {
        return columnNames[col];
    }
     
    // Get a column's class.
    public Class getColumnClass(int col) {
        return columnClasses[col];
    }
    
    public void setListItemsFound(List<Record> listItemsFound) {
		this.downloadList = (ArrayList<Record>) listItemsFound;
	}

}
