/**created by kuanSheng
 * we have two cases here
 * 1.one poem and one word
 * 2.one word and one word
 * **/
package Words.controller;

import java.awt.event.MouseAdapter;
import Words.model.*;

import Words.model.Model;
import Words.view.ApplicationCanvas;

public class WordConnectionController extends MouseAdapter {
	final Model model;
	final ApplicationCanvas panel;
	final Board b;
	int oldx;
	int oldy;
	Word connectWord = null;
	Poem connectPoem = null;
	
	/**Constructor for one word and one poem**/
	public WordConnectionController(Model m,ApplicationCanvas p,Word w,int oldx, int oldy){
		this.model = m;
		this.panel = p;
		this.connectWord = w;
		this.oldx = oldx;
		this.oldy = oldy;
		b = model.getBoard();
	}
	
	/**Constructor for one poem and one row**/
	public WordConnectionController(Model m, ApplicationCanvas a, Poem p,int oldx,int oldy){
		this.model = m;
		this.panel = a;
		this.connectPoem = p;
		this.oldx = oldx;
		this.oldy = oldy;
		b = model.getBoard();
	}
	
	/**Execute the connection two words operation**/
	public void connect(){
		if(connectWord == null){
			return;
		}
		Word selectedWord = this.model.getSelected();
		ConnectionMove move = new ConnectionMove(selectedWord,connectWord,b,this.oldx,this.oldy);
		if(move.execute()){
			panel.repaint();
		}
		//record move
		model.getMoves().push(move);
	}
	
	/**Execute the connection one poem and one word operation**/
	public void connectPoem(int connectionType){
		if(connectPoem == null){
			return;
		}
		Word selectedWord = this.model.getSelected();
		ConnectionPoemMove move = new ConnectionPoemMove(connectPoem,selectedWord,b,oldx,oldy,connectionType);
		if(move.execute()){
			panel.repaint();
		}
		//record move
		model.getMoves().push(move);
	}
}