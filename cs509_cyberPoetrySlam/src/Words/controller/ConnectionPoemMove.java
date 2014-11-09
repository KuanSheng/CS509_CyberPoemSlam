package Words.controller;
import Words.model.*;

public class ConnectionPoemMove extends Move{
	Poem connectPoem;
	Word selectedWord;
	Board b;
	int oldx;
	int oldy;
	int connectionType;
	
	public ConnectionPoemMove(Poem connectionPoem,Word selectedWord,Board b,int oldx, int oldy,int connectionType){
		this.connectPoem = connectionPoem;
		this.selectedWord = selectedWord;
		this.b = b;
		this.oldx = oldx;
		this.oldy = oldy;
		this.connectionType = connectionType;
		System.out.println(oldx);
	}
	
	@Override
	public boolean execute(){
		if(connectionType == 1||connectionType == 4||connectionType == 5){
		Row r = connectPoem.getOverlapRow(selectedWord);
		r.addWord(selectedWord);
		selectedWord.setLocation(r.getX()-selectedWord.getWidth(), r.getY());
		r.setLocationAfterConnection(selectedWord.getX(),selectedWord.getY());
		connectPoem.setLocationAfterConnection(r.getX(),r.getY());
		b.getWords().remove(selectedWord);
		}
		
		else if(connectionType == 2||connectionType == 3||connectionType == 6){
			Row r = connectPoem.getOverlapRow(selectedWord);
			
			r.addWord(selectedWord);
			selectedWord.setLocation(r.getX()+r.getWidth()-selectedWord.getWidth(),r.getY());
			b.getWords().remove(selectedWord);
		}
		return true;
	}
	
	@Override
	public boolean undo(){
		System.out.println("signal!");
		Row r = connectPoem.getOverlapRow(selectedWord);
		
		if(selectedWord.getX() == r.getX()){
			r.removeWord(selectedWord);
			
			b.getWords().add(selectedWord);
			b.getProtectedWords().add(selectedWord);
			
			r.setLocationAfterConnection(r.getX()+selectedWord.getWidth(),r.getY());
			connectPoem.setLocationAfterConnection(r.getX(), r.getY());
			selectedWord.setLocation(oldx,oldy);
		}
		else{
            r.removeWord(selectedWord);
			
			b.getWords().add(selectedWord);
			b.getProtectedWords().add(selectedWord);
			selectedWord.setLocation(oldx,oldy);
		}
		return true;
	}
}
