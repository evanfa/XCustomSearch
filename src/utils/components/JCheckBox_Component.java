package utils.components;

import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class JCheckBox_Component extends JCheckBox implements TableCellRenderer{
	
	public JCheckBox_Component(boolean flagValue) {
        super();
    }

	@Override
	public Component getTableCellRendererComponent(JTable arg0, Object arg1, 
			boolean arg2, boolean arg3, int arg4, int arg5) {
		setSelected(arg2);
		return this;
	}

}
