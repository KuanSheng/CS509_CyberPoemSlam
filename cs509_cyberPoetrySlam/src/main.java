import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

import Words.model.*;
import Words.view.*;

public class main {
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Model model = new Model();
		
		final Application app = new Application(model);
		
		app.addWindowListener(new WindowAdapter(){
			
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
     app.setVisible(true);
  }
}