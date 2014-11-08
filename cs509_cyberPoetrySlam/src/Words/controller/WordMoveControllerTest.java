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
		panel = new ApplicationCanvas(m);
		app = new Application(m,panel);
		//panel = app.getpanel();
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
    public void testWordProtect(){
    	WordMoveController control = new WordMoveController(m,panel);
        panel.addMouseListener(control);
        
        
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
        b.addWords(s2);
        
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
       control.drag(201, 280);
       control.release();
       
       Word s2 = new Word(200,360,360,14,"test",2);
       b.addWords(s2);
       control.anchor = new Point(200,250);
       
       control.originalx = s2.getX();
       control.originaly = s2.getY();
       control.anchor = new Point();
       
       control.setSelectedPoem(p);
       control.drag(200, 250);
       control.release();
       
       
   	 }
    @Test
   public void testWordMoveOverlap(){
    	 WordMoveController control = new WordMoveController(m,panel);
         panel.addMouseListener(control);
         
         Word s = new Word(200,250,120,14,"Hello",2);
         Word w = new Word(200,290,300,14,"Hello",2);
         
         b.addWords(s);
         b.addWords(w);
         
         control.originalx = w.getX();
         control.originaly = w.getY();
         control.anchor = new Point(200,350);
         
         control.setSelectedWord(w);
         m.getSelected().setLocation(210, 251);
         control.release();
         
         
    }
    @Test
    public void testPoemMoveOverlap(){
    	 WordMoveController control = new WordMoveController(m,panel);
         panel.addMouseListener(control);
         
    	   Word s = new Word(200,250,120,14,"Hello",2);
    	   Word w = new Word(240,260,120,14,"World",2);
        
    	   Poem p = new Poem(s,w,2);
    	   b.addPoems(p);
    	   
    	   Word w1 = new Word(200,290,300,14,"Hello",2);
    	   
    	   b.addWords(w1);
    	   
    	   control.originalx = w1.getX();
           control.originaly = w1.getY();
           control.anchor = new Point(200,350);
    	   
           control.setSelectedWord(w1);
           m.getSelected().setLocation(210, 251);
           control.release();
    }
    
    @Test
    public void testWordMoveCross(){
    	 WordMoveController control = new WordMoveController(m,panel);
         panel.addMouseListener(control);
         
    	   Word s = new Word(200,250,120,14,"Hello",2);
    	   Word w = new Word(240,350,120,14,"World",2);
    	   
    	   b.addWords(s);
           b.addWords(w);
           
           control.originalx = w.getX();
           control.originaly = w.getY();
           control.anchor = new Point(200,350);
           
           control.setSelectedWord(w);
           m.getSelected().setLocation(210, 251);
           control.release();
    	   
    }
    @Test
    public void testWordMoveCross2(){
    	 WordMoveController control = new WordMoveController(m,panel);
         panel.addMouseListener(control);
         
    	   //Word s = new Word(200,250,120,14,"Hello",2);
    	   Word w = new Word(240,350,120,14,"World",2);
    	   
    	   //b.addWords(s);
           b.addWords(w);
           
           control.originalx = w.getX();
           control.originaly = w.getY();
           control.anchor = new Point(200,350);
           
           control.setSelectedWord(w);
           m.getSelected().setLocation(210, 251);
           control.release();
    	   
    }
    @Test
    public void testPoemOverLapPoem(){
    	WordMoveController control = new WordMoveController(m,panel);
        panel.addMouseListener(control);
        
        Word s = new Word(200,250,120,14,"Hello",2);
 	    Word w = new Word(240,260,120,14,"World",2);
 	    
 	    b.addWords(s);
 	    b.addWords(w);
     
 	    Poem p = new Poem(s,w,2);
 	    b.addPoems(p);
 	    
 	    Word s1 = new Word(200,150,120,14,"Hello",2);
 	    Word w1 = new Word(210,160,120,14,"Hello",2);
 	    
 	    b.addWords(w1);
 	    b.addWords(s1);
 	    
 	   Poem p2 = new Poem(s1,w1,2);
	    b.addPoems(p2);
	    
	    control.originalx = p.getX();
        control.originaly = p.getY();
        control.anchor = new Point(200,350);
        
        control.setSelectedPoem(p);
        //m.getSelectedPoem().setLocation(200, 151);
        control.release();
 	    
    }
    
    @Test 
    public void testPotentialOverlap(){
    	WordMoveController control = new WordMoveController(m,panel);
        panel.addMouseListener(control);
        
        Word s = new Word(200,250,120,14,"Hello",2);
 	    Word w = new Word(330,262,120,14,"World",2);
 	    
 	    b.addWords(s);
	    b.addWords(w);
	    
	    Word test = new Word(200,290,120,14,"Hello",2);
	    b.addWords(test);
	    
	    control.originalx = test.getX();
        control.originaly = test.getY();
        control.anchor = new Point(200,350);
        
        control.setSelectedWord(test);
        m.getSelected().setLocation(309,242);
        control.release();
	    
    }
    
    @Test
    public void testPotentialOverlap2(){
    	WordMoveController control = new WordMoveController(m,panel);
        panel.addMouseListener(control);
        
        Word s = new Word(200,262,120,14,"Hello",2);
 	    Word w = new Word(330,250,120,14,"World",2);
 	    
 	    b.addWords(s);
	    b.addWords(w);
	    
	    Word test = new Word(200,290,120,14,"Hello",2);
	    b.addWords(test);
	    
	    control.originalx = test.getX();
        control.originaly = test.getY();
        control.anchor = new Point(200,350);
        
        control.setSelectedWord(test);
        m.getSelected().setLocation(290,242);
        control.release();
	    
    }

    @Test
    public void testPotentialOverlapPoem(){
    	WordMoveController control = new WordMoveController(m,panel);
        panel.addMouseListener(control);
        
        Word s = new Word(200,262,120,14,"Hello",2);
 	    Word w = new Word(330,250,120,14,"World",2);
 	    
 	    Poem p = new Poem(s,w,2);
 	    
 	    Word k = new Word(432,262,120,14,"test",2);
	    
	    Word test = new Word(200,290,120,14,"Hello",2);
	    b.addWords(test);
	    
	    control.originalx = test.getX();
        control.originaly = test.getY();
        control.anchor = new Point(200,350);
        
        control.setSelectedWord(test);
        m.getSelected().setLocation(414,242);
        control.release();
	    
    }
    

}
