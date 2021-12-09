package app.view;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class ExButtonEditor extends DefaultCellEditor {

    private JButton button;
    private String label;
    private boolean isPushed;

    public ExButtonEditor(JCheckBox checkBox) {
        super(checkBox);
        button = new JButton();
        button.setOpaque(true);
//        button.addActionListener((ActionEvent e) -> {
//            fireEditingStopped();
//        });
        
        
        button.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
//            // display/center the jdialog when the button is pressed
//            JDialog d = new JDialog(frame, "Hello", true);
//            d.setLocationRelativeTo(frame);
//            d.setVisible(true);
        	  
        	  System.out.println("Called Event Checkbox");
        	 
        	  //??fireEditingStopped
        	  
          }
        });
        
        
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        if (isSelected) {
            button.setForeground(table.getSelectionForeground());
            button.setBackground(table.getSelectionBackground());
        } else {
            button.setForeground(table.getForeground());
            button.setBackground(table.getBackground());
        }
        label = (value == null) ? "" : value.toString();
        button.setText(label);
        isPushed = true;
        return button;
    }
    
    public synchronized void addActionListener(ActionListener l){
        listenerList.add(ActionListener.class, l);
    }

    @Override
    public Object getCellEditorValue() {
        if (isPushed) {
            JOptionPane.showMessageDialog(button, label + ": Ouch!");
        }
        isPushed = false;
        return label;
    }

    @Override
    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }

    public JButton getButton() {
        return button;
    }

    public void setButton(JButton button) {
        this.button = button;
    }

    public boolean isIsPushed() {
        return isPushed;
    }

    public void setIsPushed(boolean isPushed) {
        this.isPushed = isPushed;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    
    
}