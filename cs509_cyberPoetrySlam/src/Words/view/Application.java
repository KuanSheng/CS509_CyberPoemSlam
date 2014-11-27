package Words.view;

import java.io.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

import Words.controller.ReleasePoemController;
import Words.controller.SubmitPoemController;
import Words.model.Board;
import Words.model.Model;
import Words.model.WordTypeModel;

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
        JPanel p = new JPanel();


        //setLayout(new BorderLayout());
        JPanel imagePanel = new JPanel();
        imagePanel.setSize(633, 700);
        setVisible(true);
        JButton b1 = new JButton("Swap/Revoke");
        JButton b2 = new JButton("Release");
        JButton b3 = new JButton("Submit");
        JButton b4 = new JButton("Disconnect");
//        GridBagConstraints c = new GridBagConstraints();
        imagePanel.add(b1);
        imagePanel.add(b2);
        imagePanel.add(b3);
        imagePanel.add(b4);

        this.model = m;
        this.panel = panel;

        //add listener to "Release" button
        b2.addActionListener(new ReleasePoemController(m, panel));
        b3.addActionListener(new SubmitPoemController(m, panel, SubmitPoemController.Method.ALL));

		setTitle("CyberPoetrySlam");
		setSize(900,700);
        setResizable(true);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		p.setSize(650,490);
		p.setBackground(Color.yellow);
		p.setBounds(0 ,245, 650, 345);

		if(model == null)
			return;
		// mark as final so the anonymous class below can find it
//		final ApplicationCanvas panel = new ApplicationCanvas(model); //commented by Jun
		add(p);
        p.add(imagePanel);
        p.add(panel);

        table = new WordTable(model.getBoard());
        typeTable = new WordTypeTable(model.getBoard());
//        p.add(table);
        //p.add(typeTable);
        JPanel jp = new JPanel();
        p.setSize(65,40);
        p.setBackground(Color.red);
        jp.add(typeTable);
        p.add(jp);
    }
	
    public ApplicationCanvas getpanel(){
    	return this.panel;
    }
}
