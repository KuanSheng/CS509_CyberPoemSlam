package Words.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Words.model.Board;
import Words.model.Model;
import Words.model.Poem;
import Words.model.Word;
import Words.view.ApplicationCanvas;

public class WordDisconnectionControllerTest {
	
	Model model;
	ApplicationCanvas panel;
	Board b;
	Poem originalPoem;
	Poem leftDisconnected;
	Poem rightDisconnected;
	Word w1;
	Word w2;
	Word w3;
	WordDisconnectionController wdc;
	@Before
	public void setUp() throws Exception {
		b = new Board();
		model = new Model(b);
		panel = new ApplicationCanvas(model);
		
		w1 = new Word(10, 10, 50, 14, "test", 1);
		w2 = new Word(60, 10, 120, 14, "sample", 1);
		w3 = new Word(180, 10, 60, 14, "poem", 1);
		originalPoem = new Poem(w1, w2, 1);
		originalPoem.addWord(w3);
		
		leftDisconnected = new Poem(w2, w3, 1);
		rightDisconnected = new Poem(w2, w3, 1);
		
		
		wdc = new WordDisconnectionController(model, panel, originalPoem);
			
		
	}

//	@Test
//	public void testWordDisconnectionController() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testDisconnectEdgeWord() {
		System.out.println("entering testDisconnectEdgeWord.W-D-C-T");
		System.out.println("initial poems" + b.getPoems());
		b.addPoems(originalPoem);
		model.setSelectedWordinPoem(w1);
		System.out.println("mid : testDisconnectEdgeWord.W-D-C-T");
		assertEquals(b, model.getBoard());
		System.out.println("2 : testDisconnectEdgeWord.W-D-C-T");
		assertEquals(originalPoem, b.getPoems().get(0));
		System.out.println("3 : testDisconnectEdgeWord.W-D-C-T");
		assertNotNull(model.getBoard().getPoems().get(0).getRows());
		System.out.println("4 : testDisconnectEdgeWord.W-D-C-T");
		assertNotNull(model.getBoard().getPoems().get(0).getRows().get(0));

		System.out.println("5 : testDisconnectEdgeWord.W-D-C-T");
		wdc.disconnectEdgeWord(1, model.getBoard().getPoems().get(0).getRows().get(0));
		System.out.println("6 : testDisconnectEdgeWord.W-D-C-T");
		assertNotNull(originalPoem);
		System.out.println("7 poems after operation" + b.getPoems());
		assertNotNull(leftDisconnected);
		System.out.println("8 poems after operation" + b.getPoems());
//		assertEquals(leftDisconnected, originalPoem);	
		assertTrue(leftDisconnected.equals(originalPoem));
		System.out.println("9 : testDisconnectEdgeWord.W-D-C-T");
	}
}
