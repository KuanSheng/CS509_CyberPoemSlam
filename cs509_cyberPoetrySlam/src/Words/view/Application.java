package Words.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Words.model.Board;

public class Application extends Frame {
    // dummy main entrance
    public static void main (String[] arg){
        Board b = new Board();
        Application app = new Application(b);
    }

	public Application(Board b){
		super();
		
		ApplicationCanvas canvas = new ApplicationCanvas(b);
        canvas.setTitle("CyberPoetrySlam");
        canvas.setSize(1000, 800);
        canvas.drawCanvas();
		//add(panel);
	}
}
