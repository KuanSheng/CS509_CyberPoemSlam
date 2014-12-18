package Words.view;

import static org.junit.Assert.*;

import java.awt.Panel;

import org.junit.Before;
import org.junit.Test;

import Words.model.Board;
import Words.model.Model;
/**
 * Created by Ruizhu on 12/17/14.
 */
public class ApplicationTest {
	Application a;



	
	@Test
	public void testApplication() {
		Board b=new Board();
		Model m= new Model(b);
		ApplicationCanvas p = new ApplicationCanvas(m);
		a=new Application(m);
		a.setVisible (true);
		
	}
	
  

}
