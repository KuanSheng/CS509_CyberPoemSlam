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
 * Created by Xinyuan on 12/17/14.
 */

public class ConnectionMoveTest {

	Board b0;
	int oldXW;
	int oldYW;
	
	ConnectionMove CM;
	Word connectWord_1;
	
	Model m;
    Board b;
    Word connectWord;
    Word selectWord;
    ConnectionMove cm;
	
	@Before
	public void setUp() throws Exception {
		
		b0=new Board();
		connectWord_1 = new Word(40, 490, 10, 20, "my", 1);
		oldXW = 50;
		oldYW = 100;
		Word word1 = new Word(60, 600, 40, 14, "lovely", 0 );
		Word word2 = new Word(80, 530, 30 ,14, "cat", 2);
		
		Word word3 = new Word(100, 550, 30,14,"dog", 2);
		Word word4 = new Word(100, 490, 50,14,  "white", 0);
		
		b = new Board();
        m = new Model(b);
        connectWord = new Word(200,250,120,14,"Hello",2);
        selectWord = new Word(240,260,120,14,"World",2);
        cm = new ConnectionMove(connectWord, selectWord, b, 10, 20);

	}

	@Test
	public void testExecute() {
		Word word1 = new Word(45,480, 10,20, "Happy", 1);
		CM = new ConnectionMove(connectWord_1, word1,b0, oldXW, oldYW);
		CM.execute();	
		CM.undo();
		CM.redo();
		
		
		Word word2 = new Word(35,500, 10,20,"Happy",1);
		CM = new ConnectionMove(connectWord_1, word2,b0, oldXW, oldYW);
		CM.execute();
		CM.undo();
		CM.redo();
		
		Word word3 = new Word(35, 480, 10, 20, "Happy", 1);
		CM = new ConnectionMove(connectWord_1, word3,b0, oldXW, oldYW);
		CM.execute();
		CM.undo();
		CM.redo();
		
		Word word4 = new Word(45, 500, 10,20, "Happy", 1);
		CM = new ConnectionMove(connectWord_1, word4,b0, oldXW, oldYW);
		CM.execute();
		CM.undo();
		CM.redo();
		
		Word word5 = new Word(35,490, 10, 20, "Happy", 1);
		CM = new ConnectionMove(connectWord_1, word5,b0, oldXW, oldYW);
		CM.execute();
		CM.undo();
		CM.redo();
		
		Word word6 = new Word(35,490, 30, 20, "Happy", 1);
		CM = new ConnectionMove(connectWord_1, word6,b0, oldXW, oldYW);
		CM.execute();
		CM.undo();
		CM.redo();
		
		Word word7 = new Word(70,490, 10, 20, "Happy",1);
		CM = new ConnectionMove(connectWord_1, word7,b0, oldXW, oldYW);
		CM.execute();
		CM.undo();
		CM.redo();
	}
	
	 @Test
	    public void testExecute1() {
	        cm.execute();

	        assertEquals(b.getPoems().get(0).getFirstRow().getWords().get(0).getValue(), "Hello");
	        assertEquals(b.getPoems().get(0).getFirstRow().getWords().get(1).getValue(), "World");
	        assertEquals(b.getWords().size(), 0);
	    }

	    @Test
	    public void testUndo() {
	        assertTrue(cm.undo());
	        assertEquals(b.getWords().size(), 2);
	        assertEquals(b.getPoems().size(), 0);
	        assertEquals(b.getProtectedWords().size(), 2);
	        assertEquals(cm.connectWord.getX(), 10);
	        assertEquals(cm.connectWord.getY(), 20);
	    }

	    @Test
	    public void testRedo() {
	        assertTrue(cm.redo());
	        assertEquals(b.getPoems().size(), 1);
	        assertEquals(b.getWords().size(), 0);

	    }
	

	
}
