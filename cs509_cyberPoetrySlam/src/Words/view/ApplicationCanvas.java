package Words.view;

import java.awt.*;


import Words.model.Board;

import javax.swing.*;

public class ApplicationCanvas extends Canvas{
	Board board;
    public String canvasTitle;
    public int canvasHeight;
    public int canvasWidth;

    // constructor
    public ApplicationCanvas(Board board) {
        this.board = board;
    }

    public void setTitle(String title) {
        this.canvasTitle = title;
    }

    public void setSize(int h, int w){
        this.canvasHeight = h;
        this.canvasWidth = w;
    }

	public void drawCanvas() {
        JFrame frame = new JFrame(this.canvasTitle);
        frame.setSize(this.canvasHeight,this.canvasWidth);
        GridLayout layout = new GridLayout(2, 1);
        frame.setLayout(layout);

        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.ORANGE);

        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.YELLOW);

        frame.getContentPane().add(panel1);
        frame.getContentPane().add(panel2);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
