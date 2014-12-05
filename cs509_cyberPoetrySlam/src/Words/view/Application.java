package Words.view;

import java.io.*;
import java.awt.event.*;
import java.awt.*;

import javax.swing.*;

import Words.BrokerManager;
import Words.view.Application;
import Words.controller.DisconnectionPoemController;
import Words.controller.ReleasePoemController;
import Words.controller.SubmitPoemController;
import Words.controller.SwapRequestController;
import Words.model.*;
;

public class Application extends JFrame {
    Model model;
    WordTable table;
    WordTypeTable typeTable;
    final ApplicationCanvas panel; // added by JUN to share panel with StoreStateController
    
    JButton btnSwap;
    
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
        setVisible(true);
        setBackground(Color.orange);
        
        btnSwap = new JButton("Swap/Revoke"); 
        
        JButton btnRelease = new JButton("Release");
        JButton btnSubmit = new JButton("Submit");
        JButton btnDisconnect = new JButton("Disconnect");
        
        
        

        //add listener to "Release" button
        btnRelease.addActionListener(new ReleasePoemController(m, panel));
        btnSubmit.addActionListener(new SubmitPoemController(m, panel, SubmitPoemController.Method.ALL));
        //btnSwap.addActionListener(new SwapRequestController(m, Application.this));

        menuPanel.add(btnSwap); btnSwap.setEnabled(false);
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

        table = new WordTable(model.getBoard());
        typeTable = new WordTypeTable(model.getBoard());
        tablePanel.add(table);
        tablePanel.add(typeTable);
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
    
}