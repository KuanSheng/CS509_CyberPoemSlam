package Words.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Words.model.Board;
import Words.model.Model;
import Words.model.Poem;
import Words.model.Row;
import Words.model.Word;
import Words.view.ApplicationCanvas;
/**
 * Created by Ruizhu on 12/17/14.
 */
public class ReleasePoemMoveTest {
	
	Board board;
	Model model;
	ApplicationCanvas app;
	Word word1, word2, word3,word4,word5,word6;
	Poem p1,p2,p3,p4,p5;
	
	ReleasePoemMove RPM;

	@Before
	public void setUp() throws Exception {
		board= new Board();
		word1=new Word(30,400,40,14,"my",1);
		word2 = new Word(50,400,40,14,"new",1);
		word3 = new Word(29,390,40,14,"like",9);
		word4= new Word(49,390,40,14,"cat",8);
		word5 = new Word(33,380,40,14, "high",1);
		word6 = new Word(53,380,40,14,"short",1);
		p1=new Poem(word1,word2,1);
		p2 =new Poem(word3,word4,1);
		p3 = new Poem(word5,word6,1);
		p4 = new Poem(p1,p2,1);
		p5 = new Poem(p4,p3,1);
		
		model = new Model(board);
		model.setSubmittedPoem(p5);
		app = new ApplicationCanvas(model);
		
	}

	@Test
	public void testExecute() {
		RPM = new ReleasePoemMove(model,app);
		assertEquals(p5,model.getSubmittedPoem());
		RPM.execute();
		assertEquals(0,board.getPoems().size());
		RPM.undo();
		assertEquals(p5,model.getSubmittedPoem());
		RPM.redo();
		assertEquals(p5,model.getSubmittedPoem());
	}

	

}
