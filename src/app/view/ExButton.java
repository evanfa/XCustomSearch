package app.view;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.plaf.ButtonUI;
import javax.swing.plaf.basic.BasicButtonUI;

public class ExButton extends BasicButtonUI implements IComponents{
    private static ColorUI colorUI;
    private Font fuente;
    private Border border;
    
    public ExButton(ColorUI colorUI, int size) {
        ExButton.colorUI = colorUI;
        //fuente = new Font("Segoe UI", Font.BOLD, size);
        fuente = new Font("SansSerif", Font.BOLD, size);
        border = BorderFactory.createLineBorder(ExButton.colorUI.getColorTerciario(), 2);
    }
    
    public ExButton(ColorUI colorUI) {
        ExButton.colorUI = colorUI;
        //fuente = new Font("Segoe UI", Font.BOLD, 13);
        //fuente = new Font("SansSerif", Font.TYPE1_FONT, 13);
        fuente = new Font("SansSerif", Font.BOLD, 13);
        border = BorderFactory.createLineBorder(ExButton.colorUI.getColorTerciario(), 2);
    }

    public ExButton(ColorUI colorUI, Font fuente, Border border) {
        ExButton.colorUI = colorUI;
        this.fuente = fuente;
        this.border = border;
    }
    
    
    public static ButtonUI createUI(JComponent c){
        return new ExButton(colorUI);
    }

    public static ColorUI getColorUI() {
        return colorUI;
    }

    public static void setColorUI(ColorUI aColorUI) {
        colorUI = aColorUI;
    }

    public Font getFuente() {
        return fuente;
    }

    public void setFuente(Font fuente) {
        this.fuente = fuente;
    }

    public Border getBorder() {
        return border;
    }

    public void setBorder(Border border) {
        this.border = border;
    }

    @Override
    public void modificarUI(JComponent c) {
        c.setFont(fuente);
        c.setBackground(ExButton.colorUI.getColorFondo());
        c.setForeground(ExButton.colorUI.getColorTerciario());
        c.setBorder(border);
        c.setCursor(new java.awt.Cursor(Cursor.HAND_CURSOR));
        c.setToolTipText("Search Param");
    }

    @Override
    public void crearDisenio() {
        UIManager.put("Button.foreground", ExButton.colorUI.getColorForeground());
    }
    
    
}