package Words.view;

import static org.junit.Assert.*;

import java.awt.Graphics;

import org.junit.Before;
import org.junit.Test;

import Words.model.Board;
import Words.model.Model;
/**
 * Created by Ruizhu on 12/17/14.
 */
public class ApplicationCanvasTest {

	ApplicationCanvas AC;
	Graphics g;
	Board b=new Board();
	Model m= new Model(b);
	
	@Before
	public void setUp() throws Exception {
		
	}


	@Test
	public void testInitialize() {
		
		AC=new ApplicationCanvas(m);
		AC.setVisible (true);
		AC.initialize();
		assertEquals(b, AC.board);
	}

	@Test
	public void testpaint(){
		AC.repaint();
	}
	

}
