package Words.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Words.model.Board;

public class Application extends Frame {
	public Application(Board b){
		super();
		
		setTitle("CyberPoetrySlam");
		setSize(650,490);
		
		ApplicationCanvas panel = ApplicationCanvas(b);
		add(panel);
	}
}
