package Words.controller;
import Words.model.*;

public class DisconnectionMove extends Move{
	Poem disconnectPoem;
	Word disconnectWord;
	Word remainWord;
	Row r;
	Model model;
	Board b;
	int type;
	
	public DisconnectionMove(Poem disconnectPoem, Word disconnectWord,Row disconnectRow,Model model,int direction){
		this.disconnectPoem = disconnectPoem;
		this.disconnectWord = disconnectWord;
		this.r = disconnectRow;
		this.type = direction;
		this.model = model;
		this.b = model.getBoard();
	}
	
	@Override
	public boolean execute(){
		Word w = model.getSelectedWordinPoem();
		if(type == 1){
			r.removeWord(w);
			if(r.getWordNumber() > 1){
			r.setLocationAfterConnection(r.getX()+w.getWidth(),r.getY());
			}
			else if(r.getWordNumber() == 1&&disconnectPoem.getRowNumber() == 1){
				r.setLocationAfterConnection(r.getX()+w.getWidth(),r.getY());
				b.addWords(r.getFirstWord());
				this.remainWord = r.getFirstWord();
				disconnectPoem.removeRow(r);
				if(disconnectPoem.getRowNumber() == 0){
					b.removePoem(disconnectPoem);
					disconnectPoem = null;
					}
			
			}
			else if(r.getWordNumber() == 1&&disconnectPoem.getRowNumber() != 1){
				r.setLocationAfterConnection(r.getX()+w.getWidth(),r.getY());
				
			}
			model.getBoard().addWords(w);
		}
		else 
		if(type == 2){
			r.removeWord(w);
			if(disconnectPoem.getRowNumber() == 1){
			  if(r.getWordNumber() == 1){
				b.addWords(r.getFirstWord());
				this.remainWord = r.getFirstWord();
				disconnectPoem.removeRow(r);
				if(disconnectPoem.getRowNumber() == 0){
					b.removePoem(disconnectPoem);
					disconnectPoem = null;
				}
			}
			model.getBoard().addWords(w);
		 }
			else {
				model.getBoard().addWords(w);
			}
		}
		model.setSelectedWordinPoem(null);
		return true;
	}
	
	@Override
	public boolean undo(){
		System.out.println("signal!");
		if(disconnectPoem != null){
			if(disconnectPoem != null){
				if(type == 1){
					r.addWord(disconnectWord);
					r.setLocationAfterConnection(disconnectWord.getX(), disconnectWord.getY());
				}
				else{
					r.addWord(disconnectWord);
				}
				b.getWords().remove(disconnectWord);
				return true;
			}
			
			
		}
		disconnectPoem = new Poem(disconnectWord,remainWord,type);
	    b.getWords().remove(remainWord);
	    b.getWords().remove(disconnectWord);
		b.addPoems(disconnectPoem);
		return true;
     }
}
	

