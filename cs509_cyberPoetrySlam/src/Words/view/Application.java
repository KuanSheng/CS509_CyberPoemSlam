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
		this.model = m;
		setTitle("CyberPoetrySlam");
		setSize(650,490);
		/**Button button = new Button("Undo");
		p.add(button);
		add(p);*/
		if(model == null)
			System.out.println("fuck!(app)");
		// mark as final so the anonymous class below can find it
		final ApplicationCanvas panel = new ApplicationCanvas(model);
		add(panel);
	}
	
}
