package WordsTest;

import Words.model.BoardMemento;
import org.junit.*;
import static org.junit.Assert.*;
import Words.model.Board;
import Words.model.Word;
import Words.model.Row;
import Words.model.Poem;

import java.lang.reflect.Array;
import java.util.*;

public class BoardEntityTest {
    private Board testBoard;
    private Word testWord1, testWord2, testWord3, testWord4, testWord5;
    private Poem testPoem1, testPoem2, testPoem3;
    private Row testRow1, testRow2, testRow3;
    private ArrayList<Word> unprotectedW = new ArrayList<Word>();
    private ArrayList<Word> protectedW = new ArrayList<Word>();
    private ArrayList<Word> shapes = new ArrayList<Word>();
    private ArrayList<Poem> poems = new ArrayList<Poem>();

    @Before
    public void setUp() throws Exception {
        testBoard = new Board();
        testWord1 = new Word(1,1,10,10,"test word1",0);
        testWord2 = new Word(2,1,10,10,"test word2",1);
        testWord3 = new Word(3,1,10,10,"test word3",1);
        testWord4 = new Word(4,1,10,10,"test word4",1);
        testWord5 = new Word(5,1,10,10,"test word5",1);

        testPoem1 = new Poem(20,3);
        testPoem2 = new Poem(30,3);
        testPoem3 = new Poem(40,2);

        poems.add(testPoem1);
        poems.add(testPoem2);
        poems.add(testPoem3);

        protectedW.add(testWord1);
        protectedW.add(testWord2);

        unprotectedW.add(testWord3);
        unprotectedW.add(testWord4);

        shapes.add(testWord1);
        shapes.add(testWord2);
        shapes.add(testWord3);
        shapes.add(testWord4);

        BoardMemento m = new BoardMemento(shapes);
        testBoard.restore(m);

    }

    @After
    public void clear() throws Exception {
        protectedW.clear();
        unprotectedW.clear();
        poems.clear();
        shapes.clear();
    }

    @Test
    public void testGetSet() throws Exception {
        testBoard.setProtectedWords(protectedW);
        testBoard.setunProtectedWords(unprotectedW);
        testBoard.setPoemList(poems);

        assertEquals(testBoard.getProtectedWords().size(), 2);
        assertEquals(testBoard.getunProtectedWords().size(), 2);
        assertEquals(testBoard.getPoemList().size(), 3);

        assertEquals(testBoard.getProtectedWords().get(0).getValue(),"test word1");
        assertEquals(testBoard.getProtectedWords().get(1).getValue(),"test word2");
        assertEquals(testBoard.getunProtectedWords().get(0).getValue(),"test word3");
        assertEquals(testBoard.getunProtectedWords().get(1).getValue(),"test word4");

        // test restore and word getter
        assertEquals(testBoard.getWords().size(),4);
        assertEquals(testBoard.getWords().get(0).getValue(),"test word1");
        assertEquals(testBoard.getWords().get(3).getValue(),"test word4");

        //getState test

        // test restore and word getter
        assertEquals(testBoard.getWords().size(),4);
        assertEquals(testBoard.getWords().get(0).getValue(),"test word1");
        assertEquals(testBoard.getWords().get(3).getValue(),"test word4");

    }

    @Test
    public void testUpdate() throws Exception {
        testBoard.setProtectedWords(protectedW);
        testBoard.setunProtectedWords(unprotectedW);
        testBoard.setPoemList(poems);

        BoardMemento m = new BoardMemento(shapes);
        testBoard.restore(m);
        testBoard.protect(testWord3);
        testBoard.protect(testWord5);
        assertEquals(testBoard.getProtectedWords().size(),3);
        assertEquals(testBoard.getunProtectedWords().size(),1);
        assertEquals(testBoard.getProtectedWords().get(2).getValue(), "test word3");


        testBoard.unprotect(testWord1);
        testBoard.unprotect(testWord5);
        assertEquals(testBoard.getunProtectedWords().size(),2);
        assertEquals(testBoard.getProtectedWords().size(),2);
        assertEquals(testBoard.getunProtectedWords().get(1).getValue(), "test word1");
//        testBoard.unprotect(testWord2);
//        testBoard.unprotect(testWord5);
//        assertEquals(testBoard.getProtectedWords().size(),3);


    }

    @Test
    public void testAddRemove() throws Exception {
        testBoard.setPoemList(poems);

        BoardMemento m = new BoardMemento(shapes);
        testBoard.restore(m);
        testBoard.removePoem(testPoem3);
        assertEquals(testBoard.getPoemList().size(),2);
        testBoard.addPoems(testPoem3);
        assertEquals(testBoard.getPoemList().size(),3);
        testBoard.addWords(testWord1);
        assertEquals(testBoard.getWords().size(),5);
    }

    @Test
    public void testreleaseWords() throws Exception{
        testBoard.setProtectedWords(protectedW);
        testBoard.setunProtectedWords(unprotectedW);

        testBoard.releaseWords(testWord1);
        assertEquals(testBoard.getProtectedWords().size(),1);
        assertEquals(testBoard.getunProtectedWords().size(),3);
        assertEquals(testBoard.getunProtectedWords().get(2).getValue(),"test word1");


    }

    @Test
    public void testprotectWords() throws Exception{
        testBoard.setProtectedWords(protectedW);
        testBoard.setunProtectedWords(unprotectedW);

        testBoard.protect(testWord3);
        assertEquals(testBoard.getProtectedWords().size(),3);
        assertEquals(testBoard.getunProtectedWords().size(),1);
        assertEquals(testBoard.getProtectedWords().get(2).getValue(),"test word3");

    }

    @Test
    public void testfindPoem() throws Exception{
        poems.clear();
        Poem Poem1 = new Poem(1, 1);
        Poem Poem2 = new Poem(1, 2);
        Poem Poem3 = new Poem(10,10);
        Poem1 = new Poem(testWord1, testWord2, 1);
        Poem2 = new Poem(testWord3, testWord4, 2);
        Poem3 = new Poem(testWord1, testWord2, 1);

        poems.add(Poem1);
        poems.add(Poem2);
        poems.add(Poem3);
        testBoard.setPoemList(poems);
        assertEquals(testBoard.findPoem(1,1), Poem1);
        assertEquals(testBoard.findPoem(2,1), Poem1);
    }

    @Test
    public void testfindWord() throws Exception{
        ArrayList<Word> w = testBoard.getWords();
        assertEquals(testBoard.findWord(1,2).getValue(), "test word1");
    }

    @Test
    public void testcheckOverlap() throws Exception{
        testBoard.addWords(testWord1);
        testBoard.addWords(testWord2);

        // verify it will return the first overlapped element
        assertEquals(testBoard.checkOverlap(testWord1).getValue(),"test word2");
        assertEquals(testBoard.checkOverlap(testWord2).getValue(),"test word1");
        assertEquals(testBoard.checkOverlap(testWord3).getValue(),"test word1");
        assertEquals(testBoard.checkOverlap(testWord4).getValue(),"test word1");

        testWord5 = new Word(20,10,10,10,"test word5",1);
        assertEquals(testBoard.checkOverlap(testWord5),null);
    }

    @Test
    public void testcheckOverlapPoem(){
        poems.clear();
        Poem Poem1 = new Poem(1, 1);
        Poem Poem2 = new Poem(1, 2);
        Poem1 = new Poem(testWord1, testWord2, 1);
        Poem2 = new Poem(testWord3, testWord4, 2);

        poems.add(Poem1);
        poems.add(Poem2);
        testBoard.setPoemList(poems);
        assertTrue(testBoard.checkOverlapPoem(Poem1));
        assertTrue(testBoard.checkOverlapPoem(Poem2));
    }

    @Test
    public void testcheckPotentialOverlap(){
        poems.clear();
        Poem Poem1 = new Poem(1, 1);
        Poem Poem2 = new Poem(1, 2);
        Poem1 = new Poem(testWord1, testWord2, 1);
        Poem2 = new Poem(testWord3, testWord4, 2);

        poems.add(Poem1);
        poems.add(Poem2);
        testBoard.setPoemList(poems);
        assertTrue(testBoard.checkPotentialOverlap(testWord1, testWord2, 1));
        assertFalse(testBoard.checkPotentialOverlap(testWord1, testWord2, 2));
        assertFalse(testBoard.checkPotentialOverlap(testWord1, testWord3, 1));
    }

    @Test
    public void getOverlapTypeTest(){
        poems.clear();
        Poem Poem1 = new Poem(1, 1);
        Poem Poem2 = new Poem(1, 2);
        Poem1 = new Poem(testWord1, testWord2, 1);
        Poem2 = new Poem(testWord3, testWord4, 2);

        poems.add(Poem1);
        poems.add(Poem2);
        testBoard.setPoemList(poems);
        assertEquals(testBoard.getOverlapType(testWord1, testWord2), 0);
        assertEquals(testBoard.getOverlapType(testWord1, testWord3), 5);
        assertEquals(testBoard.getOverlapType(testWord1, testWord4), 6);
    }

    @Test
    public void getOverlapTypeRowWord(){
        poems.clear();
        Poem Poem1 = new Poem(1, 1);
        Poem Poem2 = new Poem(1, 2);
        Poem1 = new Poem(testWord1, testWord2, 1);
        Poem2 = new Poem(testWord3, testWord4, 2);

        poems.add(Poem1);
        poems.add(Poem2);
        testBoard.setPoemList(poems);
        assertEquals(testBoard.getOverlapType(testWord1, testWord2), 0);
        assertEquals(testBoard.getOverlapType(testWord1, testWord3), 5);
        assertEquals(testBoard.getOverlapType(testWord1, testWord4), 6);
    }

    @Test
    public void getOverlapTypeRowWordTest(){
        testRow1 = new Row(1,1);
        testRow2 = new Row(2,1);
        testRow2 = new Row(3,1);

        testRow1 = new Row(testWord1, testWord2, 1);
        testRow2 = new Row(testWord3, testWord4, 1);
        testRow3 = new Row(testWord2, testWord4, 1);

        testBoard.setPoemList(poems);
        assertEquals(testBoard.getOverlapTypeRowWord(testWord1, testRow2), 5);
        assertEquals(testBoard.getOverlapTypeRowWord(testWord3, testRow2), 0);
        assertEquals(testBoard.getOverlapTypeRowWord(testWord2, testRow3), 0);
        assertEquals(testBoard.getOverlapTypeRowWord(testWord1, testRow3), 0);
    }

    @Test
    public void iteratorTest() {
        Iterator<Word> iterator = testBoard.iterator();
        assertEquals(iterator.next().getValue(), "test word1");
        assertEquals(iterator.next().getValue(), "test word2");
        assertEquals(iterator.next().getValue(), "test word3");
        assertEquals(iterator.next().getValue(), "test word4");

        testBoard.setProtectedWords(protectedW);
        testBoard.setunProtectedWords(unprotectedW);

        Iterator<Word> protectedWordsItrator = testBoard.protectedWordsIterator();
        assertEquals(protectedWordsItrator.next().getValue(),"test word1");
        assertEquals(protectedWordsItrator.next().getValue(),"test word2");

        Iterator<Word> unprotectedWordssIterator = testBoard.unProtectedWordsIterator();
        assertEquals(unprotectedWordssIterator.next().getValue(),"test word3");
        assertEquals(unprotectedWordssIterator.next().getValue(),"test word4");

    }
}