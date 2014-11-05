package Words.view;

import static org.junit.Assert.*;

import java.awt.Panel;

import org.junit.Before;
import org.junit.Test;

import Words.model.Board;
import Words.model.Model;

public class ApplicationTest {
	Application a;

	@Before
	public void setUp() throws Exception {
		
	
}

	
	@Test
	public void testApplication() {
		Board b=new Board();
		Model m= new Model(b);
		ApplicationCanvas p = new ApplicationCanvas(m);
		a=new Application(m, p);
		assertEquals(p, a.getpanel());
		a.setVisible (true);
		
		System.out.println("Sample GUI");
	}
	
  

}
