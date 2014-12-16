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
		panel = new ApplicationCanvas(m);
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
        assertEquals(m.getSelected(),s);
    }
	
	@Test
	public void testPoemSelected(){
		WordMoveController control = new WordMoveController(m,panel);
        panel.addMouseListener(control);
        
		Word s = new Word(200,240,120,14,"Hello",2);
		Word w = new Word(320,240,120,14,"World",2);
		
		Poem p = new Poem(s,w,2);
		b.addPoems(p);
		control.anchor = new Point(210,250);
		control.setSelectedPoem(p);
		assertEquals(m.getSelectedPoem(),p);
		assertEquals(control.originalx,p.getX());
		assertEquals(control.originaly,p.getY());
		}
	
	@Test
	public void testDragToMoveWord(){
		WordMoveController control = new WordMoveController(m,panel);
        panel.addMouseListener(control);
        
        Word s = new Word(200,240,120,14,"Hello",2);
        b.addWords(s);
        
        control.anchor = new Point(210,250);
        control.setSelectedWord(s);
       
        control.drag(250, 250);
        control.release();
        
        assertEquals(s.getX(),240);
        assertEquals(s.getY(),240);
        
        control.anchor = new Point(250,245);
        control.setSelectedWord(s);       
        control.drag(250, 345);
        control.release();
        
        assertEquals(s.getX(),240);
        assertEquals(s.getY(),340);
        
        control.anchor = new Point(250,345);
        control.setSelectedWord(s);       
        control.drag(200, 345);
        control.release();
        
        assertEquals(s.getX(),190);
        assertEquals(s.getY(),340);
        
        control.anchor = new Point(200,345);
        control.setSelectedWord(s);       
        control.drag(200, 245);
        control.release();
        
        assertEquals(s.getX(),190);
        assertEquals(s.getY(),240);
        
        Word w1 = new Word(190,120,120,14,"overlap",2);
        Word w2 = new Word(320,120,120,14,"overlap",2);
        b.addWords(w1);
        b.addWords(w2);
        
        control.anchor = new Point(200,245);
        control.setSelectedWord(s);       
        control.drag(220,120);
        control.release();
        
        assertEquals(s.getX(),190);
        assertEquals(s.getY(),240);
    }
	
	@Test
	public void testDragToMovePoem(){
		WordMoveController control = new WordMoveController(m,panel);
        panel.addMouseListener(control);
        
		Word s = new Word(200,240,120,14,"Hello",2);
		Word w = new Word(320,240,120,14,"World",2);
		
		Poem p = new Poem(s,w,2);
		b.addPoems(p);
		control.anchor = new Point(210,250);
		control.setSelectedPoem(p);
		
		control.drag(250,250);
		control.release();
		
		assertEquals(p.getX(),240);
        assertEquals(p.getY(),240);
        
        Word w1 = new Word(500,240,120,14,"overlap",2);
        b.addWords(w1);
        
        control.anchor = new Point(245,250);
		control.setSelectedPoem(p);
		
		control.drag(300,250);
		control.release();
		
		assertEquals(p.getX(),240);
        assertEquals(p.getY(),240);
        
        control.anchor = new Point(245,250);
		control.setSelectedPoem(p);
		
		control.drag(300,350);
		control.release();
		
		assertEquals(p.getX(),240);
        assertEquals(p.getY(),240);
	}
	
	@Test
	public void testDragToDrawArea(){
		WordMoveController control = new WordMoveController(m,panel);
        panel.addMouseListener(control);
        
        int ox = 100;
        int oy = 200;
        
        control.buildSelectionArea(ox, oy);
        control.drag(250,250);
        
        assertEquals(m.getSelectedArea().getX(),100);
        assertEquals(m.getSelectedArea().getY(),200);
        assertEquals(m.getSelectedArea().getWidth(),150);
        assertEquals(m.getSelectedArea().getHeight(),50);
        
        control.buildSelectionArea(ox, oy);
        control.drag(50,150);
        
        assertEquals(m.getSelectedArea().getX(),50);
        assertEquals(m.getSelectedArea().getY(),150);
        assertEquals(m.getSelectedArea().getWidth(),50);
        assertEquals(m.getSelectedArea().getHeight(),50);
        
        control.buildSelectionArea(ox, oy);
        control.drag(150,150);
        
        assertEquals(m.getSelectedArea().getX(),100);
        assertEquals(m.getSelectedArea().getY(),150);
        assertEquals(m.getSelectedArea().getWidth(),50);
        assertEquals(m.getSelectedArea().getHeight(),50);
        
        control.buildSelectionArea(ox, oy);
        control.drag(50,250);
        
        assertEquals(m.getSelectedArea().getX(),50);
        assertEquals(m.getSelectedArea().getY(),200);
        assertEquals(m.getSelectedArea().getWidth(),50);
        assertEquals(m.getSelectedArea().getHeight(),50);
	}
	
	@Test
	public void testDragToSelectRow(){
		WordMoveController control = new WordMoveController(m,panel);
        panel.addMouseListener(control);
        
		Word s = new Word(200,240,120,14,"Hello",2);
		Word w = new Word(320,240,120,14,"World",2);
		
		Poem p = new Poem(s,w,2);
		b.addPoems(p);
		
		control.buildSelectionArea(180,230);
		control.drag(450,260);
		control.release();
		
		Row r1 = p.getFirstRow();
		Row r2 = m.getSelectedRow();
		
		assertEquals(r1,r2);
	}
	
	@Test
	public void testDragToShiftRow(){
		WordMoveController control = new WordMoveController(m,panel);
        panel.addMouseListener(control);
        
		Word s = new Word(200,240,120,14,"Hello",2);
		Word w = new Word(320,240,120,14,"World",2);
		Word s1 = new Word(200,254,120,14,"Hello",2);
		Word w2 = new Word(320,254,120,14,"World",2);
		
		Poem p1 = new Poem(s,w,2);
		Poem p2 = new Poem(s1,w2,2);
		
		Poem p = new Poem(p1,p2,1);
		b.addPoems(p); 
		
		control.buildSelectionArea(180,230);
		control.drag(450,260);
		control.release();
		
		control.anchor = new Point(210,250);
		control.setRowFlag(m.getSelectedRow());
		control.drag(220,250);
		
		Row r = p.getLastRow();
		assertEquals(r.getX(),200);
	}
	
	@Test
	public void testWordOverlap(){
		WordMoveController control = new WordMoveController(m,panel);
        panel.addMouseListener(control);
        
		Word s = new Word(200,240,120,14,"Hello",2);
		Word w = new Word(320,240,120,14,"World",2);
		b.addWords(s);
		b.addWords(w);
		
		control.anchor = new Point(330,245);
		control.setSelectedWord(w);
		control.drag(315,245);
		control.release();
		
		assertEquals(w.getX(),320);
		
		Word w1 = new Word(500,240,120,14,"test",2);
		b.addWords(w1);
		
		control.anchor = new Point(510,245);
		control.setSelectedWord(w1);
		control.drag(430, 245);
		control.release();
		
		assertEquals(w1.getX(),440);
		
		Word w2 = new Word(500,240,120,14,"test",2);
		b.addWords(w2);
		
		control.anchor = new Point(510,245);
		control.setSelectedWord(w2);
		control.drag(180, 245);
		control.release();
		
		assertEquals(w2.getX(),80);
		
		Word w3 = new Word(570,240,120,14,"test",2);
		Word w4 = new Word(240,360,120,14,"test",2);
		b.addWords(w4);
		b.addWords(w3);
		
		control.anchor = new Point(245,365);
		control.setSelectedWord(w4);
		control.drag(560, 240);
		control.release();
		
		assertEquals(w4.getX(),240);
		assertEquals(w4.getY(),360);
		
		
	}
	
	@Test
	public void TestDisconnection(){
		WordMoveController control = new WordMoveController(m,panel);
        panel.addMouseListener(control);
        
		Word s = new Word(200,240,120,14,"Hello",2);
		Word w = new Word(320,240,120,14,"World",2);
		
		Poem p = new Poem(s,w,2);
		b.addPoems(p);
		
		control.disconnectWord(210, 245);
		assertTrue(b.findWord(210, 245)!=null);
	}
	
	@Test
	public void TestDisconnection2(){
			WordMoveController control = new WordMoveController(m,panel);
	        panel.addMouseListener(control);
	        
			Word s = new Word(200,240,120,14,"Hello",2);
			Word w = new Word(320,240,120,14,"World",2);
			
			Poem p = new Poem(s,w,2);
			b.addPoems(p);
			
			control.disconnectWord(330, 245);
			assertTrue(b.findWord(210, 245)!=null);
	}
	@Test
	public void TestOverlapPoem(){
		WordMoveController control = new WordMoveController(m,panel);
        panel.addMouseListener(control);
      
		  Word s = new Word(200,240,120,14,"Hello",2);
		  Word w = new Word(320,240,120,14,"World",2);
		
		  Poem p = new Poem(s,w,2);
		  b.addPoems(p);
		  
		  Word s1 = new Word(200,260,120,14,"Hello",2);
		  Word w1 = new Word(320,260,120,14,"World",2);
		  
		  Poem p1 = new Poem(s1,w1,2);
		  b.addPoems(p1);
		  
		  control.anchor = new Point(245,265);
		  control.setSelectedPoem(p1);
		  control.drag(245, 245);
		  control.release();
		  
		  assertTrue(!b.findPoem(p1));
		  assertTrue(b.findPoem(210, 245)!=null);
	}
	
	@Test
	public void TestConnectionTwoPoem(){
		  WordMoveController control = new WordMoveController(m,panel);
          panel.addMouseListener(control);
        
		  Word s = new Word(200,240,120,14,"Hello",2);
		  Word w = new Word(320,240,120,14,"World",2);
		
		  Poem p = new Poem(s,w,2);
		  b.addPoems(p);
		  
		  Word s1 = new Word(200,250,120,14,"Hello",2);
		  Word w1 = new Word(320,250,120,14,"World",2);
		  
		  Poem p1 = new Poem(s1,w1,2);
		  b.addPoems(p1);
		  
		  control.connectTwoPoem(p, p1);
		  
		  assertTrue(!b.findPoem(p1));
		  assertTrue(b.findPoem(210, 245)!=null);
	}
}