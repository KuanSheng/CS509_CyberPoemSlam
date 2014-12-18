/**created by Kuansheng**/
package Words.controller;

import Words.model.*;
import Words.view.*;

public class WordDisconnectionController {
	final Model model;
	final ApplicationCanvas panel;
	final Board b;
	Poem disConnectPoem;
	/**constructor**/
	public WordDisconnectionController(Model model,ApplicationCanvas a,Poem p){
		this.model = model;
		this.panel = a;
		this.b = model.getBoard();
		this.disConnectPoem = p;
	}
	
	/**execute the operation**/
	public void disconnectEdgeWord(int type,Row r){
		Word w = model.getSelectedWordinPoem();
		DisconnectionMove move = new DisconnectionMove(this.disConnectPoem,w,r,this.model,type);
		if(move.execute()){
		panel.repaint();
		}
		
		//record move
		model.getMoves().push(move);
	}
}