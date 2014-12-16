package Words.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Words.model.Board;
import Words.model.Model;
import Words.view.ApplicationCanvas;

public class UndoControllerTest {
	
	Board b;
	Model model;
	ApplicationCanvas canvas;
	UndoController UC;

	@Before
	public void setUp() throws Exception {
		b = new Board();
		model = new Model(b);
		canvas = new ApplicationCanvas(model);
		UC = new UndoController(model, canvas);
 	}


	@Test
	public void testProcess() {
		fail("Not yet implemented");
	}

}
