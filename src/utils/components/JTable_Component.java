package utils.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

import app.model.Record;

@SuppressWarnings("serial")
public class JTable_Component extends AbstractTableModel implements Observer{
	private ArrayList<Record> downloadList = new ArrayList<Record>();
	private static final int COLUMN_CHECK_BOX = 3;
	 
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
		System.out.println("Vals: "+aValue+"Row: "+rowIndex+" Col: "+columnIndex);
		
		Record download = downloadList.get(rowIndex);
		
		if(!download.selectedId) {
			download.selectedId = true;
		}else {
			download.selectedId = false;
		}

    }
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return columnIndex == COLUMN_CHECK_BOX;
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
    /*public Class getColumnClass(int col) {
        return columnClasses[col];
    }*/
    
    public Class<?> getColumnClass(int col){
    	switch(col) {
    	case 0: return Integer.class;
    	case 1: return String.class;
    	case 2: return String.class;
    	case 3: return Boolean.class;
    	default: return String.class;
    	}
    }
    
    public void setListItemsFound(List<Record> listItemsFound) {
		this.downloadList = (ArrayList<Record>) listItemsFound;
	}

}
