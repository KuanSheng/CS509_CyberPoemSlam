
package Words.view;

import java.io.*;
import java.awt.event.*;
import java.awt.*;

import javax.swing.*;

import Words.controller.DisconnectionPoemController;
import Words.controller.RedoController;
import Words.controller.ReleasePoemController;
import Words.controller.SubmitPoemController;
import Words.controller.UndoController;
import Words.model.*;
;

public class Application extends JFrame {
    Model model;
    WordTable table;
    WordTypeTable typeTable;
    final ApplicationCanvas panel; // added by JUN to share panel with StoreStateController
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
        JButton btnSwap = new JButton("Swap/Revoke");
        JButton btnRelease = new JButton("Release");
        JButton btnSubmit = new JButton("Submit");
        JButton btnDisconnect = new JButton("Disconnect");
        JButton btnUndo = new JButton("Undo");
        JButton btnRedo = new JButton("Redo");
        
        //add listener to "Release" button
        btnRelease.addActionListener(new ReleasePoemController(m, panel));
        btnSubmit.addActionListener(new SubmitPoemController(m, panel, SubmitPoemController.Method.ALL));

        menuPanel.add(btnSwap);
        menuPanel.add(btnRelease);
        menuPanel.add(btnSubmit);
        menuPanel.add(btnDisconnect);
        menuPanel.add(btnUndo);
        menuPanel.add(btnRedo);

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
}