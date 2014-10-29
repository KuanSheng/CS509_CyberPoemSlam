package Words.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import Words.model.Board;
import Words.model.Model;

public class Application extends Frame {
     Model model;
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
		setSize(650,490);
		
		JPanel p = new JPanel();
		p.setSize(650,490);
		p.setBackground(Color.yellow);
		p.setBounds(0 ,245, 650, 345);
		/**Button button = new Button("Undo");
		p.add(button);
		add(p);*/
		if(model == null)
			System.out.println("fuck!(app)");
		// mark as final so the anonymous class below can find it
//		final ApplicationCanvas panel = new ApplicationCanvas(model); //commented by Jun
		add(p);
		p.add(panel);
	}
	
}
