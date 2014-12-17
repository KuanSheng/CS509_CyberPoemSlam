import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

import Words.BrokerManager;
import Words.controller.StoreStateController;
import Words.model.*;
import Words.view.*;

public class main {
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Board b = new Board();

		Model model = new Model(b);

        //todo to be deleted just for test --------start
//        model.getBoard().addWords(new Word( 10 , 10, 100, 100, "test jun", 1));
        //---end todo
		
		final Application app = new Application(model);
        ApplicationCanvas panel = app.getPanel();

		BrokerManager bm = new BrokerManager(app, model);
		
        app.addWindowListener(new StoreStateController(model, panel));
 /**
  * Ruizhu add for broker       
  */
        if (bm.connect("localhost")) {
			app.setBroker(bm);
		} else if (bm.connect("gheineman.cs.wpi.edu")) {
			app.setBroker(bm);
		}

 
     app.setVisible(true);
  }
}