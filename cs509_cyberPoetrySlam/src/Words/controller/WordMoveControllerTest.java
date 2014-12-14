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
        
        assertEquals(s.getX(),240);
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
        
        assertEquals(m.selectedArea.getX(),100);
        assertEquals(m.selectedArea.getY(),200);
        assertEquals(m.selectedArea.getWidth(),150);
        assertEquals(m.selectedArea.getHeight(),50);
        
        control.buildSelectionArea(ox, oy);
        control.drag(50,150);
        
        assertEquals(m.selectedArea.getX(),50);
        assertEquals(m.selectedArea.getY(),150);
        assertEquals(m.selectedArea.getWidth(),50);
        assertEquals(m.selectedArea.getHeight(),50);
        
        control.buildSelectionArea(ox, oy);
        control.drag(150,150);
        
        assertEquals(m.selectedArea.getX(),100);
        assertEquals(m.selectedArea.getY(),150);
        assertEquals(m.selectedArea.getWidth(),50);
        assertEquals(m.selectedArea.getHeight(),50);
	}
}
