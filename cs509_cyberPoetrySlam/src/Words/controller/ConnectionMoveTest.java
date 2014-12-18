package Words.controller;

import static org.junit.Assert.*;
import Words.model.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by Xinyuan on 12/17/14.
 */
public class ConnectionMoveTest {
    Model m;
    Board b;
    Word connectWord;
    Word selectWord;
    ConnectionMove cm;

    @Before
    public void setUp() throws Exception {
        b = new Board();
        m = new Model(b);
        connectWord = new Word(200,250,120,14,"Hello",2);
        selectWord = new Word(240,260,120,14,"World",2);
        cm = new ConnectionMove(connectWord, selectWord, b, 10, 20);
    }

    @Test
    public void testExecute() {
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
