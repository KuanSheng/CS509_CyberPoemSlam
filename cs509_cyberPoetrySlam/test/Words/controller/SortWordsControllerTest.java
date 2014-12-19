package Words.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import Words.model.Board;
import Words.model.Model;
import Words.model.Row;
import Words.model.Word;
import Words.view.ApplicationCanvas;
import Words.view.WordTable;
/**
 * Created by Ruizhu on 12/17/14.
 */
public class SortWordsControllerTest {
	
	   ArrayList<Word> words;
	   WordTable table;
	    Model model;
	    ApplicationCanvas panel;
	    Board board;
	    Word word1,word2,word3,word4,word5,word6,word7,word8,word9,word10,
	    word11,word12,word13,word14,word15,word16,word17,word18,word19,word20;
	    
	    SortWordsController SWC1, SWC2;

	@Before
	public void setUp() throws Exception {
		words = new ArrayList<Word>();
		board = new Board();
		model = new Model(board);
		panel = new ApplicationCanvas(model);
		SWC1 = new SortWordsController(model, panel);
		SWC2 = new SortWordsController(table, board);
	}



	@Test
	public void testProcess() {
		word1 = new Word(60, 600, 40, 14, "lovely", 0 );words.add(word1);
		word2 = new Word(80, 530, 30 ,14, "cat", 2);words.add(word2);		
		word3 = new Word(100, 550, 30,14,"dog", 3);words.add(word3);
		word4 = new Word(100, 490, 50,14,  "white", 4);words.add(word4);
		word5 = new Word(60,476,40,14,"Happy",1);words.add(word5);
		word6 = new Word(60, 600, 40, 14, "egg", 5);words.add(word6);
		word7 = new Word(80, 530, 30 ,14, "function", 6);	words.add(word7);	
		word8 = new Word(100, 550, 30,14,"software", 7);words.add(word8);
		word9 = new Word(100, 490, 50,14,  "hardware", 8);words.add(word9);
		word10 = new Word(60,476,40,14,"beautiful",9);words.add(word10);
		word11 = new Word(60, 600, 40, 14, "near", 0 );words.add(word11);
		word12 = new Word(80, 530, 30 ,14, "one", 2);	words.add(word12);	
		word13 = new Word(100, 550, 30,14,"purplr", 3);words.add(word13);
		word14 = new Word(100, 490, 50,14,  "cup", 4);words.add(word4);
		word15 = new Word(60,476,40,14,"yours",1);words.add(word15);
		word16 = new Word(60, 600, 40, 14, "tailor", 5);words.add(word16);
		word17 = new Word(80, 530, 30 ,14, "Rachel", 6);	words.add(word17);	
		word18 = new Word(100, 550, 30,14,"Mike", 7);words.add(word18);
		word19 = new Word(100, 490, 50,14,  "seven", 8);words.add(word19);
		word20 = new Word(60,476,40,14,"zero",9);words.add(word20);
		SWC2.sortByType(words);
		SWC1.sortByAlphabet(words);

		
	}



}
