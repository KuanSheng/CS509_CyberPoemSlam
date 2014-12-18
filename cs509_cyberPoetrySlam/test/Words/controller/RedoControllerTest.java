package Words.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Words.model.Board;
import Words.model.Model;
import Words.view.ApplicationCanvas;
/**
 * Created by Ruizhu on 12/17/14.
 */
public class RedoControllerTest {
	Board b;
	Model model;
	ApplicationCanvas canvas;
	
	RedoController RC;
	@Before
	public void setUp() throws Exception {
		
		b= new Board();
		model = new Model(b);
		canvas = new ApplicationCanvas(model);
		RC = new RedoController(model, canvas);
		
	}


	@Test
	public void testProcess() {
		RC.process();
	}

}
