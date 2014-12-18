package Words.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Words.model.Board;
import Words.model.Model;
import Words.model.Poem;
import Words.model.Row;
import Words.model.Word;
/**
 * Created by Ruizhu on 12/17/14.
 */
public class DisconnectionMoveTest {
	Model model;
	Board b;
	
	Word word1, word2, word3,word4,word5,word6;
	Row row1,row2,row3,row11;
	Poem p1,p2,p3,p4,p5;
	DisconnectionMove DM;

	@Before
	public void setUp() throws Exception {
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
		
		b= new Board();
		model = new Model(b);
	
		
	}
	@Test
	public void testone() {
		DM = new DisconnectionMove(p1,word1,row11,model,1);
		DM.execute();
		assertEquals(p1.getLastRow().getWordNumber(),2);
        assertEquals(b.getWords().size(),2);
		DM.undo();
		assertEquals(p1.getLastRow().getWordNumber(),2);
        assertEquals(b.getWords().size(),0);
		DM.redo();
		assertEquals(p1.getLastRow().getWordNumber(),1);
        assertEquals(b.getWords().size(),2);
		
		
	}
	
	@Test
	public void testOne2(){
		DM = new DisconnectionMove(p1,word2,row11,model,2);
		DM.execute();
		assertEquals(p1.getLastRow().getWordNumber(),2);
        assertEquals(b.getWords().size(),2);
		DM.undo();
		assertEquals(p1.getLastRow().getWordNumber(),2);
        assertEquals(b.getWords().size(),0);
		DM.redo();
		assertEquals(p1.getLastRow().getWordNumber(),1);
        assertEquals(b.getWords().size(),2);
		
	}

	@Test
	public void testfirst() {
		DM = new DisconnectionMove(p5,word1,row1,model,1);
		DM.execute();
		assertEquals(p1.getLastRow().getWordNumber(),1);
        assertEquals(b.getWords().size(),1);
		DM.undo();
		assertEquals(p1.getLastRow().getWordNumber(),2);
        assertEquals(b.getWords().size(),0);
		DM.redo();
		assertEquals(p1.getLastRow().getWordNumber(),1);
        assertEquals(b.getWords().size(),1);
		
		DM = new DisconnectionMove(p5,word2,row1,model,1);
		DM.execute();
		assertEquals(p1.getLastRow().getWordNumber(),0);
        assertEquals(b.getWords().size(),2);
		DM.undo();
		assertEquals(p1.getLastRow().getWordNumber(),0);
        assertEquals(b.getWords().size(),1);
		DM.redo();
		assertEquals(p1.getLastRow().getWordNumber(),0);
        assertEquals(b.getWords().size(),2);
	}
	
	@Test
	public void testFisrt2(){
		DM = new DisconnectionMove(p5,word2,row1,model,2);
		DM.execute();
		assertEquals(p1.getLastRow().getWordNumber(),1);
        assertEquals(b.getWords().size(),1);
		DM.undo();
		assertEquals(p1.getLastRow().getWordNumber(),2);
        assertEquals(b.getWords().size(),0);
		DM.redo();
		assertEquals(p1.getLastRow().getWordNumber(),1);
        assertEquals(b.getWords().size(),1);
		
		DM = new DisconnectionMove(p5,word1,row1,model,2);
		DM.execute();
		assertEquals(p1.getLastRow().getWordNumber(),0);
        assertEquals(b.getWords().size(),2);
		DM.undo();
		assertEquals(p1.getLastRow().getWordNumber(),0);
        assertEquals(b.getWords().size(),1);
		DM.redo();
		assertEquals(p1.getLastRow().getWordNumber(),0);
        assertEquals(b.getWords().size(),2);
	}
	
	@Test
	public void testmiddle() {
		DM = new DisconnectionMove(p5,word4,row3,model,2);
		DM.execute();
		assertEquals(row3.getWordNumber(),1);
        assertEquals(b.getWords().size(),1);
		DM.undo();
		assertEquals(row3.getWordNumber(),2);
        assertEquals(b.getWords().size(),0);
		DM.redo();
		assertEquals(row3.getWordNumber(),1);
        assertEquals(b.getWords().size(),1);
		
		DM = new DisconnectionMove(p5,word3,row3,model,2);
		DM.execute();
		assertEquals(row3.getWordNumber(),1);
        assertEquals(b.getWords().size(),2);
		DM.undo();
		assertEquals(row3.getWordNumber(),2);
        assertEquals(b.getWords().size(),1);
		DM.redo();
		assertEquals(row3.getWordNumber(),1);
        assertEquals(b.getWords().size(),2);
	}
	
	@Test
	public void testmiddle2() {
		DM = new DisconnectionMove(p5,word3,row3,model,1);
		DM.execute();
		assertEquals(row3.getWordNumber(),1);
        assertEquals(b.getWords().size(),1);
		DM.undo();
		assertEquals(row3.getWordNumber(),2);
        assertEquals(b.getWords().size(),0);
		DM.redo();
		assertEquals(row3.getWordNumber(),1);
        assertEquals(b.getWords().size(),1);
        
		DM = new DisconnectionMove(p5,word4,row3,model,1);
		DM.execute();
		assertEquals(row3.getWordNumber(),0);
        assertEquals(b.getWords().size(),2);
		DM.undo();
		assertEquals(row3.getWordNumber(),1);
        assertEquals(b.getWords().size(),1);
		DM.redo();
		assertEquals(row3.getWordNumber(),0);
        assertEquals(b.getWords().size(),2);
	}
	
	@Test
	public void testlast() {
		DM = new DisconnectionMove(p5,word5,row2,model,1);
		DM.execute();
		assertEquals(p1.getLastRow().getWordNumber(),2);
        assertEquals(b.getWords().size(),1);
		DM.undo();
		assertEquals(p1.getLastRow().getWordNumber(),2);
        assertEquals(b.getWords().size(),0);
		DM.redo();
		assertEquals(p1.getLastRow().getWordNumber(),2);
        assertEquals(b.getWords().size(),1);
		
		DM = new DisconnectionMove(p5,word6,row2,model,1);
		DM.execute();
		assertEquals(p1.getLastRow().getWordNumber(),2);
        assertEquals(b.getWords().size(),2);
		DM.undo();
		assertEquals(p1.getLastRow().getWordNumber(),2);
        assertEquals(b.getWords().size(),1);
		DM.redo();
		assertEquals(p1.getLastRow().getWordNumber(),2);
        assertEquals(b.getWords().size(),2);
	}
	
	@Test
	public void testlast2() {
		DM = new DisconnectionMove(p5,word6,row2,model,2);
		DM.execute();
		assertEquals(p1.getLastRow().getWordNumber(),2);
        assertEquals(b.getWords().size(),1);
		DM.undo();
		assertEquals(p1.getLastRow().getWordNumber(),2);
        assertEquals(b.getWords().size(),0);
		DM.redo();
		assertEquals(p1.getLastRow().getWordNumber(),2);
        assertEquals(b.getWords().size(),1);
		
		DM = new DisconnectionMove(p5,word5,row2,model,2);
		DM.execute();
		assertEquals(p1.getLastRow().getWordNumber(),2);
        assertEquals(b.getWords().size(),2);
		DM.undo();
		assertEquals(p1.getLastRow().getWordNumber(),2);
        assertEquals(b.getWords().size(),1);
		DM.redo();
		assertEquals(p1.getLastRow().getWordNumber(),2);
        assertEquals(b.getWords().size(),2);
	}



}
