import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

import Words.controller.StoreStateController;
import Words.model.*;
import Words.view.*;

public class main {
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Board b = new Board();
		int dx = 200;
		int dy = 200;
		
		//b.addWords(new Word(dx,dy,200,14,"test",2));
		Model model = new Model(b);

        //todo to be deleted just for test --------start
        model.getBoard().addWords(new Word( 10 , 10, 100, 100, "test jun", 1));
        //---end todo

		if(model == null)
			System.out.println("main a main");
        ApplicationCanvas panel = new ApplicationCanvas(model);
		final Application app = new Application(model, panel );
        app.addWindowListener(new StoreStateController(model, panel ));

        //commented by JUN to test store/restore state
//		app.addWindowListener(new WindowAdapter(){
//
//			public void windowClosing(WindowEvent e){
//				System.exit(0);
//			}
//		});
     app.setVisible(true);
//        System.out.println(model + " : main");
  }
}