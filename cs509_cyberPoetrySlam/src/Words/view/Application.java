package Words.view;

import java.awt.event.*;
import java.awt.*;

import javax.swing.*;

import Words.BrokerManager;
import Words.controller.*;
import Words.model.*;
;

public class Application extends JFrame {
    Model model;
    WordTable table;
    WordTypeTable typeTable;
    SwapTable swapTable;
    RequestTable requestTable;
    final ApplicationCanvas panel; // added by JUN to share panel with StoreStateController
    
    JButton btnSwap;

    JButton btnAddSwap;
    JButton btnRemoveSwap;

    
    /** 
	 * Stores reference to broker so can use as needed. Set once broker connection has been made. 
	 * If no broker available, then this becomes null. 
	 */
	BrokerManager broker;
    
	/**
	 * This is the default constructor
	 */
//	public Application(Model m) { //Changed by JUN
    public Application(Model m){
		super();
        this.model = m;
        this.panel = new ApplicationCanvas(model);
        this.panel.setSize(900, 500);
        //this.panel = panel;

        setTitle("CyberPoetrySlam");
        setSize(900, 900);
        setLayout(new FlowLayout());

        JPanel menuPanel = new JPanel();
        menuPanel.setSize(900, 150);
//        setVisible(true);  //commented by JUN , this line is causing restore state to fail. Because window open event is trigered here, but restore state controller is not added yet
        setBackground(Color.orange);
        
        btnSwap = new JButton("Swap/Revoke");
        btnAddSwap = new JButton("Add Swap");
        btnRemoveSwap = new JButton("Remove Swap");


        JButton btnRelease = new JButton("Release");
        JButton btnSubmit = new JButton("Submit");
        JButton btnDisconnect = new JButton("Disconnect");


        
        

        //add listener to "Release" button
        btnRelease.addActionListener(new ReleasePoemController(m, panel));
        btnSubmit.addActionListener(new SubmitPoemController(m, panel, SubmitPoemController.Method.ALL));
        //btnSwap.addActionListener(new SwapRequestController(m, Application.this));


        //
        btnAddSwap.addActionListener(new AddSwapButtonListner(btnAddSwap));
        btnRemoveSwap.addActionListener(new RemoveSwapButtonListner(btnRemoveSwap));

        //
        menuPanel.add(btnSwap); btnSwap.setEnabled(false);
        menuPanel.add(btnAddSwap);
//        btnAddSwap.setEnabled(false);
        menuPanel.add(btnRemoveSwap);
//        btnRemoveSwap.setEnabled(false);
        menuPanel.add(btnRelease);
        menuPanel.add(btnSubmit);
        menuPanel.add(btnDisconnect);

        Container pane = this.getContentPane();
        //pane.setBackground(Color.yellow);
        pane.add(menuPanel);

        if(model == null) return;

        // Where words appear
        
        pane.add(panel);

        // JTable on the side
        JPanel tablePanel = new JPanel();
        tablePanel.setSize(900, 250);

        table = new WordTable(model.getBoard(), this); // give table reference to app so as to refresh after operations JUN
        typeTable = new WordTypeTable(model.getBoard());
        swapTable = new SwapTable(model.getBoard(), this);// give table reference to app so as to refresh after operations JUN
        requestTable = new RequestTable(model.getBoard());
        tablePanel.add(table);
        tablePanel.add(typeTable); //todo to be uncommented JUN
        tablePanel.add(swapTable);
        tablePanel.add(requestTable);
        pane.add(tablePanel);

        btnDisconnect.addActionListener(new ActionListener() {

			@Override
		public void actionPerformed(ActionEvent arg0) {
				// register controller
				DisconnectionPoemController disconnect = new DisconnectionPoemController(model,panel);
				disconnect.disconnectRow();
				model.setSelectedRow(null);
			}
		});
        
        /**
         * Ruizhu add for BrokerManager
         */
        btnSwap.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// act immediately. Doesn't require registration. Note the special syntax
				// to refer to the enclosing class within an anonymous class (Application.this)
				new SwapRequestController(model, Application.this).process(true);
			}
		});
		
    }
	
    public ApplicationCanvas getpanel(){
    	return this.panel;
    }
    
    /**
     * Ruizhu add for BrokerManager
     */
    public JButton getSwapButton() { return btnSwap ; }
    
    public void setBroker(BrokerManager bm) { 
		this.broker = bm;
		this.setTitle("ID:" + bm.getID());
		btnSwap.setEnabled(true);
	}
	public BrokerManager getBroker() { return broker; }

    public void refreshTables(){
        if(table != null)        table.refreshTable();
        if(typeTable != null)    typeTable.refreshTable();
        if(swapTable != null)    swapTable.refreshTable();
        if(requestTable != null) requestTable.refreshTable();
    }
}