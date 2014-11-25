package Words.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import Words.controller.ReleasePoemController;
import Words.controller.SubmitPoemController;
import Words.model.Board;
import Words.model.Model;

public class Application extends JFrame {
     Model model;
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
        imagePanel.setSize(650, 30);
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
		setSize(650,490);
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
//        p.add(b1);
//        p.add(b2);
//        p.add(b3);
        p.add(panel);


    }
	
    public ApplicationCanvas getpanel(){
    	return this.panel;
    }
}
