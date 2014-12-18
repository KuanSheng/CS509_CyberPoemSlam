package Words.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Words.model.Poem;
/**
 * Created by Ruizhu on 12/17/14.
 */
public class MovePoemTest {
	Poem p;
	MovePoem mP;

	@Before
	public void setUp() throws Exception {
		p = new Poem(100,150);
		mP = new MovePoem(p, 50,190,500,600);
	}

	@Test
	public void testExecute() {
		mP.execute();
		assertEquals(p.getX(),500);
		assertEquals(p.getY(),600);
		mP.undo();
		assertEquals(p.getX(),50);
		assertEquals(p.getY(),190);
		mP.redo();
		assertEquals(p.getX(),500);
		assertEquals(p.getY(),600);
		
	}


}
