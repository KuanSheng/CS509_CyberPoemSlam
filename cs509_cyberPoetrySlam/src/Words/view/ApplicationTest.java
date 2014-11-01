package Words.view;

import static org.junit.Assert.*;

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
		a=new Application(m);
		a.setVisible (true);
		
		System.out.println("Sample GUI");
	}
	
  

}
