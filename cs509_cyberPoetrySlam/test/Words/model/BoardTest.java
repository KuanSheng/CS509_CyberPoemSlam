package Words.model;


import org.junit.*;
import static org.junit.Assert.*;
import Words.model.Board;
import Words.model.Word;
import Words.model.Row;
import Words.model.Poem;

import java.lang.reflect.Array;
import java.util.*;
/**
 * Created by Ruizhu on 12/17/14.
 */
public class BoardTest {
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
        testBoard.protectedWords.addAll(protectedW);
        testBoard.setunProtectedWords(unprotectedW);
        testBoard.getPoems().addAll(poems);

        assertEquals(testBoard.getProtectedWords().size(), 2);
        assertEquals(testBoard.getunprotectedWords().size(), 2);
        assertEquals(testBoard.getPoems().size(), 3);

        assertEquals(testBoard.getProtectedWords().get(0).getValue(),"test word1");
        assertEquals(testBoard.getProtectedWords().get(1).getValue(),"test word2");
        assertEquals(testBoard.getunprotectedWords().get(0).getValue(),"test word3");
        assertEquals(testBoard.getunprotectedWords().get(1).getValue(),"test word4");

        // test restore and word getter
        assertEquals(testBoard.getWords().size(),0);
    }

    @Test
    public void testreleaseWords() throws Exception{
        testBoard.protectedWords.addAll(protectedW);
        testBoard.setunProtectedWords(unprotectedW);

        testBoard.releaseWords(testWord1);
        assertEquals(testBoard.getProtectedWords().size(),1);
        assertEquals(testBoard.getunprotectedWords().size(),3);
        assertEquals(testBoard.getunprotectedWords().get(2).getValue(),"test word1");


    }

    @Test
    public void testprotectWords() throws Exception{
        testBoard.protectedWords.addAll(protectedW);
        testBoard.setunProtectedWords(unprotectedW);

        testBoard.protectedWords.add(testWord3);
        assertEquals(testBoard.getProtectedWords().size(),3);
        assertEquals(testBoard.getunprotectedWords().size(),2);
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
       testBoard.getPoems().addAll(poems);
        assertEquals(testBoard.findPoem(1,1), Poem1);
        assertEquals(testBoard.findPoem(2,1), Poem1);
    }

    @Test
    public void testfindWord() throws Exception{
        testBoard.addWords(testWord1);
        testBoard.addWords(testWord2);
        testBoard.addWords(testWord3);
        testBoard.addWords(testWord4);

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
        testBoard.getPoems().addAll(poems);
        assertFalse(testBoard.checkOverlapPoem(Poem1));
        assertFalse(testBoard.checkOverlapPoem(Poem2));
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
        testBoard.getPoems().addAll(poems);
        assertTrue(testBoard.checkPotentialOverlap(testWord1, testWord2, 1));
        assertTrue(testBoard.checkPotentialOverlap(testWord1, testWord2, 2));
        assertTrue(testBoard.checkPotentialOverlap(testWord1, testWord3, 1));
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
        testBoard.getPoems().addAll(poems);
        assertEquals(testBoard.getOverlapType(testWord1, testWord2), 0);
        assertEquals(testBoard.getOverlapType(testWord1, testWord3), 0);
        assertEquals(testBoard.getOverlapType(testWord1, testWord4), 0);
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
        testBoard.getPoems().addAll(poems);
        assertEquals(testBoard.getOverlapType(testWord1, testWord2), 0);
        assertEquals(testBoard.getOverlapType(testWord1, testWord3), 0);
        assertEquals(testBoard.getOverlapType(testWord1, testWord4), 0);
    }

    @Test
    public void getOverlapTypeRowWordTest(){
        testRow1 = new Row(1,1,240,14);
        testRow2 = new Row(2,1,240,14);
        testRow2 = new Row(3,1,240,14);

        testRow1 = new Row(testWord1, testWord2, 1);
        testRow2 = new Row(testWord3, testWord4, 1);
        testRow3 = new Row(testWord2, testWord4, 1);

        testBoard.getPoems().addAll(poems);
        assertEquals(testBoard.getOverlapTypeRowWord(testWord1, testRow2), 5);
        assertEquals(testBoard.getOverlapTypeRowWord(testWord3, testRow2), 0);
        assertEquals(testBoard.getOverlapTypeRowWord(testWord2, testRow3), 0);
        assertEquals(testBoard.getOverlapTypeRowWord(testWord1, testRow3), 5);
    }

    @Test
    public void iteratorTest() {
        testBoard.addWords(testWord1);
        testBoard.addWords(testWord2);
        testBoard.addWords(testWord3);
        testBoard.addWords(testWord4);

        Iterator<Word> iterator = testBoard.iterator();
        assertEquals(iterator.next().getValue(), "test word1");
        assertEquals(iterator.next().getValue(), "test word2");
        assertEquals(iterator.next().getValue(), "test word3");
        assertEquals(iterator.next().getValue(), "test word4");

        testBoard.protectedWords.addAll(protectedW);
        testBoard.setunProtectedWords(unprotectedW);

        Iterator<Word> protectedWordsItrator = testBoard.protectedWordsIterator();
        assertEquals(protectedWordsItrator.next().getValue(),"test word1");
        assertEquals(protectedWordsItrator.next().getValue(),"test word2");

        Iterator<Word> unprotectedWordssIterator = testBoard.unprotectedWordsIterator();
        assertEquals(unprotectedWordssIterator.next().getValue(),"test word3");
        assertEquals(unprotectedWordssIterator.next().getValue(),"test word4");

    }



    @Test
    public void checkDisconnectionAvailabilityTest() {
        poems.clear();
        Poem Poem1 = new Poem(10, 12);
        testRow1 = new Row(12,1,10,14);
        testRow2 = new Row(4,1,13,12);
        testRow3 = new Row(13,1,14,12);

        testWord1 = new Word(21,1,10,10,"test word1",0);
        testWord2 = new Word(22,1,10,10,"test word2",1);
        testWord3 = new Word(23,1,10,10,"test word3",1);
        testWord4 = new Word(24,1,10,10,"test word4",1);

        testRow1 = new Row(testWord1, testWord2, 1);
        testRow2 = new Row(testWord3, testWord4, 1);
        testRow3 = new Row(testWord2, testWord4, 1);

        Poem1.addRow(testRow1);
        Poem1.addRow(testRow2);
        Poem1.addRow(testRow3);

        assertTrue(testBoard.checkDisconnectionAvailability(Poem1,testWord3));

        testRow2.setFormerRow(testRow1);
        assertTrue(testBoard.checkDisconnectionAvailability(Poem1,testWord3));

        testRow2.setNextRow(testRow3);
        assertFalse(testBoard.checkDisconnectionAvailability(Poem1,testWord2));

    }

    @Test
    public void getSubmittedPoemByAreaTest() {
        poems.clear();
        Poem Poem1 = new Poem(10, 12);
        Poem Poem2 = new Poem(10, 20);

        testRow1 = new Row(8,11,7,14);
        testRow2 = new Row(2,1,2,4);

        Poem1.setLastRow(testRow1);
        poems.add(Poem1);
        testBoard.getPoems().addAll(poems);

        Area a = new Area(5,10,250,20);
        Area a1 = new Area(4,12,2,9);
        assertEquals(testBoard.getSubmittedPoemByArea(a), Poem1);
        assertNull(testBoard.getSubmittedPoemByArea(a1));
    }
    
    @Test
    public void testSwap(){
    	Word word1 = new Word(60, 600, 40, 14, "lovely", 0 );
		Word word2 = new Word(80, 530, 30 ,14, "cat", 2);
		Word word3 = new Word(100, 550, 30,14,"dog", 2);
		Word word4 = new Word(100, 490, 50,14,  "white", 0);
		testBoard.unProtectedWords.add(word1);
		testBoard.unProtectedWords.add(word2);
		testBoard.unProtectedWords.add(word3);
		testBoard.unProtectedWords.add(word4);
		
		testBoard.addOurSwap(word1);
		assertEquals(1, testBoard.getOurSwapCount());
		assertEquals(word1,testBoard.getOurSwap(0));
		testBoard.addOurSwap(0);
		assertEquals(2, testBoard.getOurSwapCount());
		
		testBoard.removeOurSwap(1);
		assertEquals(1, testBoard.getOurSwapCount());
		testBoard.clearOurSwap();
		assertEquals(0, testBoard.getOurSwapCount());
		
		testBoard.removeWords(word4);
		assertEquals(2,testBoard.getunprotectedWords().size());
		
		
    }
}