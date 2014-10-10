package shapes;

import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

import java.io.*;
import shapes.model.*;
import shapes.view.*;

/** Class to simply launch the GUI. */
public class Main {
	
	static final String defaultStorage = "Wordmap.storage";
	
	static void storeState(Board b, String location) {
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(location));
			oos.writeObject(b.getState());
		} catch (Exception e) {
			
		}
		
		if (oos != null) {
			try { oos.close(); } catch (IOException ioe) { } 
		}
		
	}
	
	static Board loadState(String location) {
		 ObjectInputStream ois = null;
		 Board b = new Board();
		 try {
			 ois = new ObjectInputStream (new FileInputStream(location));
			 BoardMemento m = (BoardMemento) ois.readObject();
			 ois.close();
			 b.restore(m);
		 } catch (Exception e) {
			 // unable to perform restore
		 }
		 
		 // close down safely
		 if (ois != null) {
			 try { ois.close(); } catch (IOException ioe) { }
		 }
		 
		 return b;
	}
	
	/** Launch GUI by installing window controller on exit. */ 
	public static void main (String args[]) {
		System.out.println ("Welcome to MY WordMap!");
		
		// Create the entity objects that form the basis of our model
		final Board b = loadState(defaultStorage);
		Model model = new Model(b);
		
		System.out.println ("Model constructed");
		
		// In some cases we can only construct the view once model 
		// is available. In other cases, we can construct the View 
		// object and then configure it later with the model object. 
		// In this example, we show the former. 
		final Application pr = new Application(model);
		
		// This controller is an anonymous class who responds to closing 
		// events by exiting the application.
		pr.addWindowListener (new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				storeState(b, defaultStorage);
				System.exit(0);
			}
		});
			
		// launch everything and go!
		pr.setVisible (true);
	}
}
