package Words.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import Words.model.Board;
import Words.model.Model;
import Words.model.Poem;
import Words.model.Word;
import Words.view.ApplicationCanvas;
/**
 * Created by Ruizhu on 12/17/14.
 */
public class StoreStateControllerTest {
	
	 Board b;
	 Model model;
     ApplicationCanvas panel;
     Word word1,word2;
     Poem p;
     StoreStateController SSC;
     ArrayList<Word> words;

	@Before
	public void setUp() throws Exception {
		b= new Board();
		word1 = new Word(40,40,40,14,"aaa",1);
		word2 = new Word(49,50,40,14, "isn",3);
		p = new Poem(word1,word2,1);
		b.getWords().add(word1);
		b.getWords().add(word2);
		b.getPoems().add(p);
		words = b.getWords();
		
		model = new Model(b);
		panel = new ApplicationCanvas(model);
		SSC = new StoreStateController(model,panel);
		
	}

	@Test
	public void testStoreStateController() {
		SSC.storeState();
		SSC.restoreState();
		SSC.readinWords();
	}
	
	@Test 
	public void testWord(){
		assertEquals(SSC.createWord("merry", "adjective").getValue(),"merry");
		assertEquals(SSC.createWord("merry", "adjective").getWordType(),0);
	
	}
	
	@Test
	public void testAssign(){
		SSC.assignWords(words,2);
	}



}
