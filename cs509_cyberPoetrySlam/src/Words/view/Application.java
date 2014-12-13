package Words.view;

import java.awt.event.*;
import java.io.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import Words.controller.DisconnectionPoemController;
import Words.controller.RedoController;
import Words.controller.ReleasePoemController;
import Words.controller.SubmitPoemController;
import Words.controller.UndoController;
import Words.model.*;
;
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
    NewRequestTable newRequestTable;
    OurSwap swap;

    public ApplicationCanvas getPanel() {
        return panel;
    }

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
        setBackground(Color.orange);
        JButton btnSwap = new JButton("Swap/Revoke");
        JButton btnRelease = new JButton("Release");
        JButton btnSubmit = new JButton("Submit");
        JButton btnDisconnect = new JButton("Disconnect");
        JButton btnUndo = new JButton("Undo");
        JButton btnRedo = new JButton("Redo");
        
        //add listener to "Release" button
        btnRelease.addActionListener(new ReleasePoemController(m, panel));
        btnSubmit.addActionListener(new SubmitPoemController(m, panel, SubmitPoemController.Method.ALL));

        btnAddSwap = new JButton("Add Swap");
        btnRemoveSwap = new JButton("Remove Swap");
        menuPanel.add(btnSwap);
        menuPanel.add(btnRelease);
        menuPanel.add(btnSubmit);
        menuPanel.add(btnDisconnect);
        menuPanel.add(btnUndo);
        menuPanel.add(btnRedo);

        this.model = m;
        //add listener to "Release" button
        btnRelease.addActionListener(new ReleasePoemController(m, panel));
        btnSubmit.addActionListener(new SubmitPoemController(m, panel, SubmitPoemController.Method.ALL));
        //btnSwap.addActionListener(new SwapRequestController(m, Application.this));
        if(model == null) return;

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

        swap = new OurSwap(model.getBoard());

        /**
         * Jun add random word for swap -- start
         */
        //create buttons
        JButton randomAdj = new JButton("Add Random Adj");
        JButton randomAdv = new JButton("Add Random Adv");
        JButton randomNoun = new JButton("Add Random Noun");
        JButton randomVerb = new JButton("Add Random Verb");

        //set invisible by default
        randomAdj.setVisible(false);
        randomAdv.setVisible(false);
        randomNoun.setVisible(false);
        randomVerb.setVisible(false);

        //set commands so that controller would know which is calling it
        randomAdj.setActionCommand(AddRandomWordForSwap.ADD_ADJ);
        randomAdv.setActionCommand(AddRandomWordForSwap.ADD_ADV);
        randomNoun.setActionCommand(AddRandomWordForSwap.ADD_NOUN);
        randomVerb.setActionCommand(AddRandomWordForSwap.ADD_VERB);

        //add listener
        randomAdj.addActionListener(new AddRandomWordForSwap(model.getBoard(),swap, this));
        randomAdv.addActionListener(new AddRandomWordForSwap(model.getBoard(),swap, this));
        randomNoun.addActionListener(new AddRandomWordForSwap(model.getBoard(),swap, this));
        randomVerb.addActionListener(new AddRandomWordForSwap(model.getBoard(),swap, this));

        //add into panel
        menuPanel.add(randomAdj);
        menuPanel.add(randomAdv);
        menuPanel.add(randomNoun);
        menuPanel.add(randomVerb);
        /**
         * JUN add random word for swap -- end
         */


        /**
         * add new request table
         */
        btnAddSwap.addActionListener(new AddSwapButtonListner(btnAddSwap, randomAdj, randomAdv, randomNoun, randomVerb));
        btnRemoveSwap.addActionListener(new RemoveSwapButtonListner(btnRemoveSwap));

        table = new WordTable(model.getBoard(), this, swap); // give table reference to app so as to refresh after operations JUN
        typeTable = new WordTypeTable(model.getBoard());
        swapTable = new SwapTable(model.getBoard(), this, swap);// give table reference to app so as to refresh after operations JUN
        requestTable = new RequestTable(model.getBoard(), swap);
//        newRequestTable = new NewRequestTable(model.getBoard(), new OurSwap(model.getBoard()));

        tablePanel.add(table);
        tablePanel.add(typeTable); //todo to be uncommented JUN
        tablePanel.add(swapTable);
        tablePanel.add(requestTable);
//        tablePanel.add(newRequestTable);
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
        
        btnDisconnect.addActionListener(new ActionListener() {

			@Override
		public void actionPerformed(ActionEvent arg0) {
				// register controller
				DisconnectionPoemController disconnect = new DisconnectionPoemController(model,panel);
				disconnect.disconnectRow();
				model.setSelectedRow(null);
			}
		});
        
        btnUndo.addActionListener(new ActionListener() {

			@Override
		public void actionPerformed(ActionEvent arg0) {
				// register controller
				UndoController undo = new UndoController(model,panel);
        		undo.process();
        		return;
			}
		});
        
        btnRedo.addActionListener(new ActionListener() {

			@Override
		public void actionPerformed(ActionEvent arg0) {
				// register controller
				RedoController redo = new RedoController(model,panel);
        		redo.process();
        		return;
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

    public OurSwap getSwap() {
        return swap;
    }

    private class AddSwapButtonListner implements ActionListener {
        JButton button;
        JButton adj;
        JButton adv;
        JButton noun;
        JButton verb;
        Color originColor;
        public AddSwapButtonListner(JButton button, JButton adj, JButton adv, JButton noun, JButton verb){
            this.button = button;
            this.adj = adj;
            this.adv = adv;
            this.noun = noun;
            this.verb = verb;
            originColor = button.getBackground();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            table.clearSelection(); // solved the problem: click a row before clicking "Add swap",
                                        //then would not be able to add that row
            if(SwapAddListener.flip()){
                button.setText("Stopping Adding");
                button.setBackground(Color.red);
                adj.setVisible(true);
                adv.setVisible(true);
                noun.setVisible(true);
                verb.setVisible(true);

            }else {
                button.setText("Add Swap");
                button.setBackground(originColor);
                adj.setVisible(false);
                adv.setVisible(false);
                noun.setVisible(false);
                verb.setVisible(false);
            }
        }
    }

    private class RemoveSwapButtonListner implements ActionListener {
        JButton button;
        Color originColor = null;
        public RemoveSwapButtonListner(JButton button) {
            this.button = button;
            originColor = button.getBackground();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(SwapRemoveListener.flip()){
                System.out.println("color" + button.getBackground());
                button.setText("Stop Removing");
                button.setBackground(Color.red);
            }else {
                button.setText("Remove Swap");
                button.setBackground(originColor);
//            button.setBackground(Co);
            }
        }
    }

}