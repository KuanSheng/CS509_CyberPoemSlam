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
public class UndoControllerTest {
	
	Board b;
	Model model;
	ApplicationCanvas canvas;
	UndoController UC1;

	@Before
	public void setUp() throws Exception {
		b = new Board();
		model = new Model(b);
		canvas = new ApplicationCanvas(model);
		UC1 = new UndoController(model, canvas);
 	}
	
	@Test
	public void testProcess(){
		UC1.process();
	}



}
