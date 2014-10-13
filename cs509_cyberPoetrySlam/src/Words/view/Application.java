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
		
		model = m;
		
		setTitle("Sample Application");
		setLayout(new FlowLayout());
		setSize(633, 700);
		
		Panel p = new Panel();
		p.setSize(633, 40);
		
		/**Button button = new Button("Undo");
		p.add(button);
		add(p);*/
		
		// mark as final so the anonymous class below can find it
		final ApplicationCanvas panel = new ApplicationCanvas(model);
		add(panel);
		
	
		
	}
	
}
