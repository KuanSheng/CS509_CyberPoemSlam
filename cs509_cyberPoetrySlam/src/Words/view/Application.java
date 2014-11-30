
package Words.view;

import java.io.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

import Words.controller.ReleasePoemController;
import Words.controller.SubmitPoemController;
import Words.model.*;
;

public class Application extends JFrame {
    Model model;
    WordTable table;
    WordTypeTable typeTable;
    private ApplicationCanvas panel; // added by JUN to share panel with StoreStateController
	/**
	 * This is the default constructor
	 */
//	public Application(Model m) { //Changed by JUN
    public Application(Model m, ApplicationCanvas panel){
		super();
        this.model = m;
        this.panel = panel;

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

        //add listener to "Release" button
        btnRelease.addActionListener(new ReleasePoemController(m, panel));
        btnSubmit.addActionListener(new SubmitPoemController(m, panel, SubmitPoemController.Method.ALL));

        menuPanel.add(btnSwap);
        menuPanel.add(btnRelease);
        menuPanel.add(btnSubmit);
        menuPanel.add(btnDisconnect);

        Container pane = this.getContentPane();
        //pane.setBackground(Color.yellow);
        pane.add(menuPanel);

        if(model == null) return;

        // Where words appear
        panel = new ApplicationCanvas(model);
        panel.setSize(900, 500);
        pane.add(panel);

        // JTable on the side
        JPanel tablePanel = new JPanel();
        tablePanel.setSize(900, 250);

        table = new WordTable(model.getBoard());
        typeTable = new WordTypeTable(model.getBoard());
        tablePanel.add(table);
        tablePanel.add(typeTable);
        pane.add(tablePanel);
    }
	
    public ApplicationCanvas getpanel(){
    	return this.panel;
    }
}