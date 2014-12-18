package Words.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Words.model.Board;
import Words.model.Poem;
import Words.model.Row;
import Words.model.Word;
/**
 * Created by Ruizhu on 12/17/14.
 */
public class DisconnectionPoemMoveTest {
	
	Board board;
	
	Word word1, word2, word3,word4,word5,word6;
	Row row1,row2,row3,row11;
	Poem p1,p2,p3,p4,p5;
	
	DisconnectionPoemMove DPM;

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
		
		row11 = new Row(word1,word2,2);
		row1 = p5.getFirstRow();
		row1.setNextRow(p4.getLastRow());
		row2 = p5.getLastRow();
		row2.setFormerRow(p4.getLastRow());
		row3 = row1.getNextRow();
		
	}



	@Test
	public void testUndoMidRow() {
		DPM = new DisconnectionPoemMove(p5, row3, board);
		DPM.execute();
		assertEquals(null, p5.getRowByWord(word3));
		assertEquals(p5.getFirstRow(), p5.getRowByWord(word3));
		DPM.undo();
		DPM.redo();
	}

	@Test
	public void testUndoFirstRow() {
		DPM = new DisconnectionPoemMove(p1, row1, board);
		DPM.execute();
		
		DPM = new DisconnectionPoemMove(p5, row1, board);
		DPM.execute();
		DPM.undo();
		DPM.redo();
		
	}

	@Test
	public void testUndoLastRow() {
		DPM = new DisconnectionPoemMove(p5, row2, board);
		DPM.execute();
		DPM.undo();
		DPM.redo();
	}

}
