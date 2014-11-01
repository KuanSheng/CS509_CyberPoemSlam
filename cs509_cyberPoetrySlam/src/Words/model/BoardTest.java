package Words.model;

import org.junit.*;
import static org.junit.Assert.*;
import Words.model.Board;
import Words.model.Word;
import Words.model.Poem;

import java.lang.reflect.Array;
import java.util.*;

public class BoardTest {
    private Board b;
    private Word testWord1, testWord2, testWord3, testWord4, testWord5;
    private Poem testPoem1, testPoem2, testPoem3;

    private ArrayList<Word> unprotectedW = new ArrayList<Word>();
    private ArrayList<Word> protectedW = new ArrayList<Word>();
    private ArrayList<Word> words = new ArrayList<Word>();
    private ArrayList<Poem> poems = new ArrayList<Poem>();

    @Before
    public void setUp() throws Exception {
        b = new Board();
        testWord1 = new Word(1,1,10,10,"test word1",1);
        testWord2 = new Word(2,1,10,10,"test word2",1);
        testWord3 = new Word(3,1,10,10,"test word3",1);
        testWord4 = new Word(4,1,10,10,"test word4",1);
        testWord5 = new Word(5,1,10,10,"test word5",1);

        testPoem1 = new Poem(1,100);
        testPoem2 = new Poem(50,50);
        testPoem3 = new Poem(40,80);

        b.addPoems(testPoem1);
        b.addPoems(testPoem2);
        b.addPoems(testPoem3);

        b.addWords(testWord1);
        b.addWords(testWord2);
        b.addWords(testWord3);
        b.addWords(testWord4);
        b.addWords(testWord5);   

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
        assertEquals(testBoard.getProtectedWords().size(), 2);
        assertEquals(testBoard.getUnprotectedWords().size(), 2);
        assertEquals(testBoard.getPoemList().size(), 3);

        assertEquals(testBoard.getProtectedWords().get(0).getValue(),"test word1");
        assertEquals(testBoard.getProtectedWords().get(1).getValue(),"test word2");
        assertEquals(testBoard.getUnprotectedWords().get(0).getValue(),"test word3");
        assertEquals(testBoard.getUnprotectedWords().get(1).getValue(),"test word4");

        // test restore and word getter
        assertEquals(testBoard.getWords().size(),4);
        assertEquals(testBoard.getWords().get(0).getValue(),"test word1");
        assertEquals(testBoard.getWords().get(3).getValue(),"test word4");

        //getState test
        BoardMemento m = testBoard.getState();
        testBoard.restore(m);
        // test restore and word getter
        assertEquals(testBoard.getWords().size(),4);
        assertEquals(testBoard.getWords().get(0).getValue(),"test word1");
        assertEquals(testBoard.getWords().get(3).getValue(),"test word4");

    }

    @Test
    public void testUpdate() throws Exception {
        testBoard.protect(testWord3);
        testBoard.protect(testWord5);
        assertEquals(testBoard.getProtectedWords().size(),3);
        assertEquals(testBoard.getUnprotectedWords().size(),1);
        assertEquals(testBoard.getProtectedWords().get(2).getValue(), "test word3");


        testBoard.unprotect(testWord1);
        testBoard.unprotect(testWord5);
        assertEquals(testBoard.getUnprotectedWords().size(),2);
        assertEquals(testBoard.getProtectedWords().size(),2);
        assertEquals(testBoard.getUnprotectedWords().get(1).getValue(), "test word1");
//        testBoard.unprotect(testWord2);
//        testBoard.unprotect(testWord5);
//        assertEquals(testBoard.getProtectedWords().size(),3);


    }

    @Test
    public void testAddRemove() throws Exception {
        testBoard.removePoem(testPoem3);
        assertEquals(testBoard.getPoemList().size(),2);
        testBoard.addPoem(testPoem3);
        assertEquals(testBoard.getPoemList().size(),3);
        testBoard.addWords(testWord1);
        assertEquals(testBoard.getWords().size(),5);
    }

    @Test
    public void testreleaseWords() throws Exception{
        testBoard.releaseWords(testWord1);
        assertEquals(testBoard.getProtectedWords().size(),1);
        assertEquals(testBoard.getUnprotectedWords().size(),3);
        assertEquals(testBoard.getUnprotectedWords().get(2).getValue(),"test word1");


    }

    @Test
    public void testprotectWords() throws Exception{
        testBoard.protect(testWord3);
        assertEquals(testBoard.getProtectedWords().size(),3);
        assertEquals(testBoard.getUnprotectedWords().size(),1);
        assertEquals(testBoard.getProtectedWords().get(2).getValue(),"test word3");

    }

    @Test
    public void testfindWord() throws Exception{
        ArrayList<Word> w = testBoard.getWords();
        assertEquals(testBoard.findWord(1,2).getValue(), "test word1");
    }

    @Test
    public void iteratorTest() {
        Iterator<Word> iterator = testBoard.iterator();
        assertEquals(iterator.next().getValue(), "test word1");
        assertEquals(iterator.next().getValue(), "test word2");
        assertEquals(iterator.next().getValue(), "test word3");
        assertEquals(iterator.next().getValue(), "test word4");

        Iterator<Word> protectedWordsItrator = testBoard.protectedWordsIterator();
        assertEquals(protectedWordsItrator.next().getValue(),"test word1");
        assertEquals(protectedWordsItrator.next().getValue(),"test word2");

        Iterator<Word> unprotectedWordssIterator = testBoard.unprotectedWordssIterator();
        assertEquals(unprotectedWordssIterator.next().getValue(),"test word3");
        assertEquals(unprotectedWordssIterator.next().getValue(),"test word4");

    }
}