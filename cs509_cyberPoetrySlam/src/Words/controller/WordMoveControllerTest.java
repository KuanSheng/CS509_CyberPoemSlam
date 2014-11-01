package Words.controller;

import static org.junit.Assert.*;

import java.awt.Point;

import Words.controller.*;
import Words.model.*;
import Words.view.*;

import org.junit.Before;
import org.junit.Test;

public class WordMoveControllerTest {
	Application app;
	Model m;
    Board b;
    ApplicationCanvas panel;
	@Before
	public void setUp() throws Exception {
		b = new Board();
		m = new Model(b);
		app = new Application(m);
		panel = app.getpanel();
		app.setVisible(true);
	}
	@Test
    public void testWordSelected(){
    	WordMoveController control = new WordMoveController(m,panel);
        panel.addMouseListener(control);
        
    	Word s = new Word(200,350,120,14,"Hello",2);
        b.addWords(s);
        
        control.originalx = s.getX();
        control.originaly = s.getY();
        control.anchor = new Point(210,360);
        
        
        control.setSelectedWord(s);
        System.out.println("error");
        assertEquals(m.getSelected(),s);
    }
	
    @Test
    public void testAddWord(){
    	WordMoveController control = new WordMoveController(m,panel);
    	panel.addMouseListener(control);
    	
    	control.generateNewWord(200, 250);
    	
    	control.generateNewWord(200, 350);
    	assertTrue(b.getWord(200,350));
    }
    @Test
    public void testPoemSelected(){
    	WordMoveController control = new WordMoveController(m,panel);
        panel.addMouseListener(control);
        
    	Word s = new Word(200,250,120,14,"Hello",2);
    	Word w = new Word(240,260,120,14,"World",2);
        
    	Poem p = new Poem(s,w,2);
    	b.addPoems(p);
        
        control.originalx = p.getX();
        control.originaly = p.getY();
        control.anchor = new Point(210,255);
        
        
        control.setSelectedPoem(p);
        assertEquals(m.getSelectedPoem(),p);
    }
    @Test
    public void testWordMove(){
    	WordMoveController control = new WordMoveController(m,panel);
        panel.addMouseListener(control);
        
        Word s = new Word(200,250,120,14,"Hello",2);
        
        b.addWords(s);
        
        control.originalx = s.getX();
        control.originaly = s.getY();
        control.anchor = new Point();
        
        control.setSelectedWord(s);
        control.drag(201, 255);
        control.release();
        
        Word s2 = new Word(200,360,300,14,"Word",2);
        
        control.originalx = s2.getX();
        control.originaly = s2.getY();
        control.anchor = new Point();
        
        control.setSelectedWord(s);
        control.drag(201, 255);
        control.release();
   }
    
    @Test
   public void testPoemMove(){
	   WordMoveController control = new WordMoveController(m,panel);
       panel.addMouseListener(control);
       
       Word s = new Word(200,250,120,14,"Hello",2);
   	   Word w = new Word(240,260,120,14,"World",2);
       
   	   Poem p = new Poem(s,w,2);
   	   b.addPoems(p);
   	   
   	   control.originalx = p.getX();
       control.originaly = p.getY();
       control.anchor = new Point();
   	   
   	   control.setSelectedPoem(p);
   	   System.out.println("woca");
       control.drag(201, 280);
       control.release();
       
       Word s2 = new Word(200,360,360,14,"test",2);
       b.addWords(s2);
       control.anchor = new Point(200,250);
       
       control.originalx = s2.getX();
       control.originaly = s2.getY();
       control.anchor = new Point();
       
       control.setSelectedPoem(p);
   	   System.out.println("woca");
       control.drag(200, 250);
       control.release();
       
       
   	 }
    @Test
   public void testWordMoveOverlap(){
    	 WordMoveController control = new WordMoveController(m,panel);
         panel.addMouseListener(control);
         
         Word s = new Word(200,250,120,14,"Hello",2);
         
         
    }

}
