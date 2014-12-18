package Words.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Words.model.Board;
import Words.model.Model;
import Words.model.Poem;
import Words.model.Word;
import Words.view.Application;
import Words.view.ApplicationCanvas;
/**
 * Created by Ruizhu on 12/17/14.
 */
public class ReleasePoemControllerTest {
	Board b;
	Model m;
	ApplicationCanvas app;
	ReleasePoemController RPC;
	
	Word word1, word2;
	Poem poem1;

	@Before
	public void setUp() throws Exception {
		b = new Board();
		m = new Model(b);
		app = new ApplicationCanvas(m);
		RPC = new ReleasePoemController(m, app);
		RPC.releasePoem();
		
	}



	@Test
	public void testRelease() {
		word1 = new Word(60, 600, 40, 14, "lovely", 0 );
		word2 = new Word(80, 530, 30 ,14, "cat", 2);
		poem1 = new Poem(word1, word2, 2);
		m.setSubmittedPoem(poem1);
		app = new ApplicationCanvas(m);
		RPC = new ReleasePoemController(m, app);
		assertEquals(poem1,m.getSubmittedPoem());
		RPC.releasePoem();
		assertEquals(0,b.getPoems().size());
	}

}
