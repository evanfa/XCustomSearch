import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumnModel;

import app.controller.ControllerItems;
import app.model.Record;
import app.view.ColorUI;
import app.view.ExTable;
import app.view.ExTextField;
import app.view.ExUtilities;
import app.view.ExperienceUI;
import utils.components.JCheckBox_Component;
import utils.components.JTable_Component;


public class RunApp extends JFrame implements Observer {
	public static final Color mainColor = new Color(33,37,43);
	public static final Color secondColor = new Color(44,49,58);
	public static final Color thirdColor = new Color(10,58,107);
	public static final Color borderColor = new Color(171,178,191);
	public static final Color backgroundColor = new Color(226,234,245);
	public static final Color foregroundColor = new Color(215, 218, 224);
	public static final Color secondaryBorderColor = new Color(171,178,191);

	// Add download text field.
	private JTextField addTextField;
	// Download table's data model.
	private JTable_Component tableModel;
	// Table listing downloads.
	private JTable table;
	// These are the buttons for managing the selected download.
	private JButton pauseButton, resumeButton;
	private JButton cancelButton, clearButton;     
	// Currently selected download.
	private Record selectedDownload;
	// Flag for whether or not table selection is being cleared.
	private boolean clearing;
	private TableColumnModel columnModel;


	public RunApp() {

		setTitle("Permits - Administration Control Panel");
		setSize(1320, 480);
		setResizable(true);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				//setExtendedState(JFrame.ICONIFIED);
				actionExit();
			}
		});
		
		Toolkit.getDefaultToolkit().setDynamicLayout(true); 
		
		initComponents();
		setLocationRelativeTo(null);
	}
	
	@SuppressWarnings("unchecked")
    private void initComponents() {
		
		JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        JMenuItem fileExitMenuItem = new JMenuItem("Exit",
                KeyEvent.VK_X);
        fileExitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actionExit();
            }
        });
        fileMenu.add(fileExitMenuItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
         
        // Set up add panel.
        JPanel searchPanel = new JPanel();
        addTextField = new JTextField(30);
        searchPanel.add(addTextField);
        
        JButton addButton = new JButton("Search");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actionAdd(addTextField.getText());
            }
        });
        searchPanel.add(addButton);
         
        // Set up Downloads table.
        tableModel = new JTable_Component();
        table = new JTable(tableModel);
        
        columnModel = table.getColumnModel();
        
        columnModel.getColumn(0).setPreferredWidth(8);
        columnModel.getColumn(1).setPreferredWidth(260);
        columnModel.getColumn(2).setPreferredWidth(780);
        columnModel.getColumn(3).setPreferredWidth(15);
        
        /*table.getSelectionModel().addListSelectionListener(new
                ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                tableSelectionChanged();
            }
        });*/
        
        
        // Allow only one row at a time to be selected.
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
         
        // Set up ProgressBar as renderer for progress column.
        //ProgressRenderer renderer = new ProgressRenderer(0, 100);
        JCheckBox_Component renderCB = new JCheckBox_Component(false);
        //renderer.setStringPainted(true); // show progress text
        //table.setDefaultRenderer(JProgressBar.class, renderer);
        table.setDefaultRenderer(JCheckBox.class, renderCB);
         
        // Set table's row height large enough to fit JProgressBar.
        table.setRowHeight(
                (int) renderCB.getPreferredSize().getHeight());
         
        // Set up downloads panel.
        JPanel downloadsPanel = new JPanel();
        downloadsPanel.setBorder(
                BorderFactory.createTitledBorder("Downloads"));
        downloadsPanel.setLayout(new BorderLayout());
        downloadsPanel.add(new JScrollPane(table),
                BorderLayout.CENTER);
         
        // Set up buttons panel.
        JPanel buttonsPanel = new JPanel();
        pauseButton = new JButton("Pause");
        pauseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //actionPause();
            	//TODO Add To Move Selected Items to Download Tab
            }
        });
        
        pauseButton.setEnabled(false);
        buttonsPanel.add(pauseButton);
        /*
        resumeButton = new JButton("Resume");
        resumeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actionResume();
            }
        });*/
        
        //resumeButton.setEnabled(false);
        
        //buttonsPanel.add(resumeButton);
        
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //actionCancel();
            	//TODO Remove Selected Items
            }
        });
        
        cancelButton.setEnabled(false);
        buttonsPanel.add(cancelButton);
        clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               // actionClear();
            	//TODO Clean Current Search
            }
        });
        
        clearButton.setEnabled(false);
        buttonsPanel.add(clearButton);
         
        // Add panels to display.
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(searchPanel, BorderLayout.NORTH);
        getContentPane().add(downloadsPanel, BorderLayout.CENTER);
        getContentPane().add(buttonsPanel, BorderLayout.SOUTH);
        
        //**********************************************************************
        /*
         * Set up Colors and Desings 
         */
        //**********************************************************************
        ColorUI colorUI = new ColorUI(
        		mainColor, 
        		secondColor, 
        		thirdColor, 
        		borderColor, 
        		backgroundColor, 
        		foregroundColor, 
        		secondaryBorderColor);
        
        ExperienceUI eu =  new ExperienceUI(colorUI);
        eu.createUI();
        
        ExUtilities.modificarElementos(colorUI,eu.getExComboBox() ,searchPanel);
        ExUtilities.eliminarBordesScrollPane(searchPanel);
        ExUtilities.modificarBotones(eu.getExButton(), searchPanel);
        
        ExUtilities.addPlaceHolder(addTextField, "Search Param", "");
        
        //ExTextField.createUI(addTextField);
        
        ExTextField.setBackground(addTextField);
        ExTable.modificarGridTabla(table);
        ExTable.modifyHeadersTable(table);
        
        /*ExTable.modificarGrid(table);
        
        ExTable xT = new ExTable(
        		eu.getColorUI(), 
        		borderColor, 
        		null, 
        		null, 
        		null, 
        		null);
        
        xT.setGridColor(secondColor);
        */
        
        //**********************************************************************
        //**********************************************************************
	}

	private void actionAdd(String txtField) {
		System.out.println("Loading Table");
		ControllerItems itemsFound = new ControllerItems();
		
		tableModel = new JTable_Component();
		//tableModel.setValueAt(aValue, rowIndex, columnIndex);
		tableModel.setListItemsFound(ControllerItems.getArrayListResultsIfCoincidenceFound(itemsFound.getLst(), txtField));
		
		table.setModel(tableModel);
		
		//TableColumnModel columnModel = table.getColumnModel();
		   columnModel.getColumn(0).setPreferredWidth(8);
	        columnModel.getColumn(1).setPreferredWidth(250);
	        columnModel.getColumn(2).setPreferredWidth(780);
	        columnModel.getColumn(3).setPreferredWidth(15);
	}
	
	// Add a new download.
    /*private void actionAdd() {
        URL verifiedUrl = verifyUrl(addTextField.getText());
        if (verifiedUrl != null) {
            tableModel.addDownload(new Download(verifiedUrl));
            addTextField.setText(""); // reset add text field
        } else {
            JOptionPane.showMessageDialog(this,
                    "Invalid Download URL", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }*/
     
    // Verify download URL.
    private URL verifyUrl(String url) {
        // Only allow HTTP URLs.
        if (!url.toLowerCase().startsWith("http://") && !url.toLowerCase().startsWith("https://"))
            return null;
         
        // Verify format of URL.
        URL verifiedUrl = null;
        try {
            verifiedUrl = new URL(url);
        } catch (Exception e) {
            return null;
        }
         
        // Make sure URL specifies a file.
        if (verifiedUrl.getFile().length() < 2)
            return null;
         
        return verifiedUrl;
    }
     
    // Called when table row selection changes.
   /* private void tableSelectionChanged() {
        if (selectedDownload != null)
            selectedDownload.deleteObserver(PACP_Run.this);
        if (!clearing) {
            selectedDownload =
                    tableModel.getDownload(table.getSelectedRow());
            selectedDownload.addObserver(PACP_Run.this);
            updateButtons();
        }
    }
    */
     
   /*
    private void actionPause() {
        selectedDownload.pause();
        updateButtons();
    }
     
    // Resume the selected download.
    private void actionResume() {
        selectedDownload.resume();
        updateButtons();
    }
     
    // Cancel the selected download.
    private void actionCancel() {
        selectedDownload.cancel();
        updateButtons();
    }
     
    // Clear the selected download.
    private void actionClear() {
        clearing = true;
        tableModel.clearDownload(table.getSelectedRow());
        clearing = false;
        selectedDownload = null;
        updateButtons();
    }*/
     
  /* Update each button's state based off of the
     currently selected download's status. */
    
    /*
    private void updateButtons() {
        if (selectedDownload != null) {
            int status = selectedDownload.getStatus();
            switch (status) {
                case Download.DOWNLOADING:
                    pauseButton.setEnabled(true);
                    resumeButton.setEnabled(false);
                    cancelButton.setEnabled(true);
                    clearButton.setEnabled(false);
                    break;
                case Download.PAUSED:
                    pauseButton.setEnabled(false);
                    resumeButton.setEnabled(true);
                    cancelButton.setEnabled(true);
                    clearButton.setEnabled(false);
                    break;
                case Download.ERROR:
                    pauseButton.setEnabled(false);
                    resumeButton.setEnabled(true);
                    cancelButton.setEnabled(false);
                    clearButton.setEnabled(true);
                    break;
                default: // COMPLETE or CANCELLED
                    pauseButton.setEnabled(false);
                    resumeButton.setEnabled(false);
                    cancelButton.setEnabled(false);
                    clearButton.setEnabled(true);
            }
        } else {
            // No download is selected in table.
            pauseButton.setEnabled(false);
            resumeButton.setEnabled(false);
            cancelButton.setEnabled(false);
            clearButton.setEnabled(false);
        }
    }
*/
	
    private void actionExit() {
        System.exit(0);
    }
	
	public static void main(String[] args) {
    	System.setProperty("https.protocols", "TLSv1");
        RunApp manager = new RunApp();
        //ImageIcon img = new ImageIcon("pacp/commons/icon.png");
        //manager.setIconImage(img.getImage());
        manager.show();
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}
