package Words.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import Words.model.Board;
import Words.model.Model;
import Words.model.Poem;
import Words.model.Row;
import Words.controller.*;

public class Application extends Frame {
    Model model;
    public ApplicationCanvas panel;
    JButton disconnectButton;
    // added by JUN to share panel with StoreStateController
	/**
	 * This is the default constructor
	 */
//	public Application(Model m) { //Changed by JUN
    public Application(Model m, final ApplicationCanvas panel){
		super();
		this.model = m;
        this.panel = panel;
		setTitle("CyberPoetrySlam");
		setSize(650,490);
		
		JPanel p = new JPanel();
		p.setSize(650,490);
		p.setBackground(Color.yellow);
		p.setBounds(0 ,245, 650, 345);
		/**Button button = new Button("Undo");
		p.add(button);
		add(p);*/
		
		disconnectButton = new JButton("Disconnect Row");
		p.add(disconnectButton);
		
		

		if(model == null)
			return;
		// mark as final so the anonymous class below can find it
//		final ApplicationCanvas panel = new ApplicationCanvas(model); //commented by Jun
		add(p);
		p.add(panel);
	}
	
    public ApplicationCanvas getpanel(){
    	return this.panel;
    }
}
