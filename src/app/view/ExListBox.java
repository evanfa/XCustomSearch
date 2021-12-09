package app.view;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.UIManager;
import javax.swing.plaf.ListUI;
import javax.swing.plaf.basic.BasicListUI;

public class ExListBox extends BasicListUI implements IComponents{
    private static ColorUI colorUI;

    public ExListBox(ColorUI colorUI) {
        this.colorUI = colorUI;
    }
    
    
    public static ListUI createUI(JComponent c){
        return new ExListBox(colorUI);
    }
    
    @Override
    public void crearDisenio(){
        UIManager.put("List.selectionForeground", this.colorUI.getColorFondo());
        UIManager.put("List.selectionBackground", this.colorUI.getColorTerciario());
        UIManager.put("List.focusCellHighlightBorder", BorderFactory.createEmptyBorder());
        UIManager.put("List.border", BorderFactory.createLineBorder(this.colorUI.getColorBordeSecundario()));
    }

    @Override
    public void modificarUI(JComponent c) {
        if(c instanceof JList){
            JList ls = (JList)c;
            ls.setUI(this);
        }
    }
    
}