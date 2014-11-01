package Words.controller;

import Words.model.*;
import Words.view.*;

public class WordDisconnectionController {
	final Model model;
	final ApplicationCanvas panel;
	final Board b;
	Poem disConnectPoem;
	
	public WordDisconnectionController(Model model,ApplicationCanvas a,Poem p){
		this.model = model;
		this.panel = a;
		this.b = model.getBoard();
		disConnectPoem = p;
	}
	
	public void disconnectEdgeWord(int type,Row r){
		Word w = model.getSelectedWordinPoem();
		if(type == 1){
			r.removeWord(w);
			if(r.getWordNumber() > 1){
			r.setLocation(r.getX()+w.getWidth(),r.getY());
			}
			else if(r.getWordNumber() == 1){
				r.setLocation(r.getX()+w.getWidth(),r.getY());
				b.addWords(r.getFirstWord());
				disConnectPoem.removeRow(r);
				if(disConnectPoem.getRowNumber() == 0){
					System.out.println("cao");
					b.removePoem(disConnectPoem);
				}
			}
			model.getBoard().addWords(w);
		}
		else if(type == 2){
			r.removeWord(w);
			if(r.getWordNumber() == 1){
				b.addWords(r.getFirstWord());
				disConnectPoem.removeRow(r);
				if(disConnectPoem.getRowNumber() == 0){
					b.removePoem(disConnectPoem);
				}
			}
			model.getBoard().addWords(w);
		}
		
		model.setSelectedWordinPoem(null);
		panel.repaint();
	}
}