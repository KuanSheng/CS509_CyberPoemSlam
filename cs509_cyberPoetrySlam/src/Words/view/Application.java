package Words.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Words.model.Board;
import Words.model.Model;

public class Application extends Frame {
     Model model;
	/**
	 * This is the default constructor
	 */
	public Application(Model m) {
		super();
		
		setTitle("CyberPoetrySlam");
		setSize(640,490);
		/**Button button = new Button("Undo");
		p.add(button);
		add(p);*/
		
		// mark as final so the anonymous class below can find it
		final ApplicationCanvas panel = new ApplicationCanvas(model);
		add(panel);
	}
	
}
