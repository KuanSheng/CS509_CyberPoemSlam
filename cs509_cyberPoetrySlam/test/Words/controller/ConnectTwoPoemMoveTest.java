package Words.controller;

import static org.junit.Assert.*;
import Words.model.*;

import org.junit.Before;
import org.junit.Test;
/**
 * Created by Xinyuan on 12/18/14.
 */
public class ConnectTwoPoemMoveTest {
    Model m;
    Board b;
    Word w1;
    Word w2;

    Poem poem1;
    Poem poem2;

    ConnectTwoPoemMove tpm;

    @Before
    public void setUp() throws Exception {
        b = new Board();
        m = new Model(b);
        w1= new Word(50, 55, 13,20,"new",1);
        w2= new Word(60,55,13,20,"ha",1);

        poem1 = new Poem(w1,w2,1);
        poem2 = new Poem(w1,w2,1);
        tpm = new ConnectTwoPoemMove(poem1, poem2, b, 10, 20, 1);
    }

    @Test
    public void executeTest() {
        assertTrue(tpm.execute());
        assertEquals(b.getPoems().size(), 1);
    }

    @Test
    public void redoTest() {
        assertTrue(tpm.redo());
        assertEquals(b.getPoems().size(), 1);
    }

    @Test
    public void undoTest() {
        assertTrue(tpm.undo());
        assertEquals(b.getPoems().size(), 2);
        assertEquals(tpm.selectedPoem.getX(), 10);
        assertEquals(tpm.selectedPoem.getY(), 20);
        assertEquals(tpm.newy, 55);
    }


}
