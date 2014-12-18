package Words.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Words.controller.SubmitPoemController.Method;
import Words.model.Board;
import Words.model.Model;
import Words.model.Poem;
import Words.model.Word;
import Words.view.ApplicationCanvas;
/**
 * Created by Ruizhu on 12/17/14.
 */
public class SubmitPoemControllerTest {
	 Model m;
	    ApplicationCanvas panel;
	    Board board;
	    
	    Word word1, word2;
	    Poem p1;
	    SubmitPoemController SPC;

	@Before
	public void setUp() throws Exception {
		board = new Board();
		m = new Model(board);
		panel = new ApplicationCanvas(m);
		
		word1 = new Word(50,100,40,14, "happy",1);
		word2 =new Word(70,100,40,13,"new",1);
		p1 = new Poem(word1,word2,1);
		m.setSubmittedPoem(p1);
	}

	


	@Test
	public void testSubmitPoem() {
		SPC = new SubmitPoemController(m, panel);
		
		SPC = new SubmitPoemController(m, panel, Method.ALL);
		SPC.submitPoem();
		
		SPC = new SubmitPoemController(m, panel, Method.SER);
		SPC.submitPoem();
		
		SPC = new SubmitPoemController(m, panel, Method.TXT);
		SPC.submitPoem();
		
		SPC = new SubmitPoemController(m, panel, Method.EMAIL);
		SPC.submitPoem();
	}
	
	@Test
	public void testset(){
		SPC.setMethod(Method.EMAIL);
	}


}
