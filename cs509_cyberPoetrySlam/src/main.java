import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

import Words.model.*;
import Words.view.*;

public class main {
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Board b = new Board();
		int dx = 200;
		int dy = 350;
		
		b.addWords(new Word(dx,dy,50,14,"test",2));
		Model model = new Model(b);
		if(model == null)
			System.out.println("main a main");
		final Application app = new Application(model);
		app.addWindowListener(new WindowAdapter(){
			
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
     app.setVisible(true);
  }
}