package Words.controller;

import Words.model.Board;
import Words.model.Model;
import Words.model.Poem;
import Words.model.Word;
import Words.view.ApplicationCanvas;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ReleasePoemControllerTest {
    Model model;
    ApplicationCanvas panel;
    Board b0;

    @Before
    public void setUp() throws Exception {
        System.out.println("set up: setUP.RPCT");
        b0 = new Board();
        model= new Model(b0);
        panel = new ApplicationCanvas(model);
        Word w1 = new Word(10, 20, 20,20, "Test",1);
        Word w2 = new Word(30, 20, 20, 20, "Word", 1);
        Poem p = new Poem(w1, w2, 1);
        b0.addPoems(p);
        model.setSelectedPoem(p);
        System.out.println("set up: setUp.RPCT");
    }

    @Test
    public void testReleasePoem() throws Exception {
        ReleasePoemController ctr = new ReleasePoemController(model,panel);
        ctr.releasePoem();
        assert (model.getMoves().peek().getClass().getName().equals("ReleasePoemMove"));
        assertEquals(b0.getPoems().size(), 0);
    }

    @Test
    public void testActionPerformed() throws Exception {

    }
}