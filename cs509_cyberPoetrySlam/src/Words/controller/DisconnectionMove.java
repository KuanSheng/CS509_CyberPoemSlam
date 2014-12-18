/**created and modified by KuanSheng
 * disconnect one edge word from a poem
 * we have four cases here
 * 1.disconnect word in one row poem
 * 2.disconnect word in first row
 * 3.disconnect word in mid row
 * 4.disconnect word in last row**/

package Words.controller;
import Words.model.*;

public class DisconnectionMove extends Move{
	Poem disconnectPoem;
	Poem newUpPoem;
	Poem newBotPoem;
	Word disconnectWord;
	Word remainWord;
	Row r;
	Row remainRow;
	Model model;
	Board b;
	int type;
	int disType;
	boolean nullSign = false;
	
	public DisconnectionMove(Poem disconnectPoem, Word disconnectWord,Row disconnectRow,Model model,int direction){
		this.disconnectPoem = disconnectPoem;
		this.disconnectWord = disconnectWord;
		this.r = disconnectRow;
		this.type = direction;//type 1 means the first word, 2 means the last word
		this.model = model;
		this.b = model.getBoard();
	}
	
	@Override
	public boolean execute(){
		//disconnect word from one row poem
		if(disconnectPoem.getRowNumber() == 1){
			this.disconnectWordinOneRowPoem();
			this.disType = 1;
			return true;
		}
		//disconnect word from first row
		else if(r.equals(disconnectPoem.getFirstRow())){
			this.disconnectWordinFirstRow();
			this.disType = 2;
			return true;
		}
		
		//disconnect word from last row
		else if(r.equals(disconnectPoem.getLastRow())){
			this.disconnectWordinLastRow();
			this.disType = 3;
			return true;
		}
		//disconnect word from mid row
		else{
			this.disconnectWordinMidRow();
			this.disType = 4;
			return true;
		}
	}
	
	@Override
	public boolean redo(){
		if(disconnectPoem.getRowNumber() == 1){
			this.disconnectWordinOneRowPoem();
			this.disType = 1;
			return true;
		}
		
		else if(r.equals(disconnectPoem.getFirstRow())){
			this.disconnectWordinFirstRow();
			this.disType = 2;
			return true;
		}
		
		else if(r.equals(disconnectPoem.getLastRow())){
			this.disconnectWordinLastRow();
			this.disType = 3;
			return true;
		}
		
		else{
			this.disconnectWordinMidRow();
			this.disType = 4;
			return true;
		}
	}
	
	public void disconnectWordinOneRowPoem(){
		if(type == 1){
			r.removeWord(disconnectWord);
			r.setLocationAfterConnection(r.getX()+disconnectWord.getWidth(),r.getY());
			if(r.getWordNumber() == 1){
				this.remainWord = r.getFirstWord();
				disconnectPoem.removeRow(r);
				b.removePoem(disconnectPoem);
				nullSign = true;
				b.addWords(remainWord);
			}
		}
		else{
			r.removeWord(disconnectWord);
			if(r.getWordNumber() == 1){
				this.remainWord = r.getFirstWord();
				disconnectPoem.removeRow(r);
				b.removePoem(disconnectPoem);
				nullSign = true;
				b.addWords(remainWord);
			}
		}
		b.addWords(disconnectWord);
		model.setSelectedWordinPoem(null);
	}
	
	public void disconnectWordinFirstRow(){
		if(type == 1){
			if(r.getWordNumber() == 1){
				remainRow = r.getNextRow();
				remainRow.setFormerRow(null);
				r.removeWord(disconnectWord);
				disconnectPoem.removeRow(r);
				r = null;
				disconnectPoem.setLocationAfterConnection(remainRow.getX(),remainRow.getY());
			}
			else{
				r.removeWord(disconnectWord);
				r.setLocationAfterConnection(r.getX()+disconnectWord.getWidth(),r.getY());
				disconnectPoem.setLocationAfterConnection(r.getX(),r.getY());
			}
		}
		else {
			if(r.getWordNumber() == 1){
				remainRow = r.getNextRow();
				remainRow.setFormerRow(null);
				r.removeWord(disconnectWord);
				disconnectPoem.removeRow(r);
				r = null;
				disconnectPoem.setLocationAfterConnection(remainRow.getX(),remainRow.getY());
			}
			else{
				r.removeWord(disconnectWord);
			}
		}
		
		b.addWords(disconnectWord);
		model.setSelectedWordinPoem(null);
	}
	
	public void disconnectWordinLastRow(){
		if(type == 1){
			if(r.getWordNumber() == 1){
				remainRow = r.getFormerRow();
				remainRow.setNextRow(null);
				r.removeWord(disconnectWord);
				disconnectPoem.removeRow(r);
				r = null;
				disconnectPoem.setLastRow(remainRow);
			}
			else{
				r.removeWord(disconnectWord);
				r.setLocationAfterConnection(r.getX()+disconnectWord.getWidth(),r.getY());
			}
		}
		else{
			if(r.getWordNumber() == 1){
				remainRow = r.getFormerRow();
				remainRow.setNextRow(null);
				r.removeWord(disconnectWord);
				disconnectPoem.removeRow(r);
				r = null;
				disconnectPoem.setLastRow(remainRow);
			}
			else{
				r.removeWord(disconnectWord);
			}
		}
		
		b.addWords(disconnectWord);
		model.setSelectedWordinPoem(null);
	}
	
	public void disconnectWordinMidRow(){
		if(type == 1){
			if(r.getWordNumber() == 1){
				Row r1 = disconnectPoem.getFirstRow();
				Row r2 = r.getFormerRow();
				Row r3 = r.getNextRow();
				
				newUpPoem = new Poem(r1.getX(),r1.getY());
				newBotPoem = new Poem(r3.getX(),r3.getY());
				newUpPoem.setLastRow(r.getFormerRow());
				newBotPoem.setLastRow(r.getNextRow());
				//wnewUpPoem.setMax_Position(disconnectPoem.getX(), disconnectPoem.getY());
				
				r2.setNextRow(null);
				
				while(r1!=null){
					newUpPoem.addRow(r1);
					r1 = r1.getNextRow();
				}
				
				
				r3.setFormerRow(null);
				
				while(r3!=null){
					newBotPoem.addRow(r3);
					r3 = r3.getNextRow();
				}
				
				r.removeWord(disconnectWord);
				disconnectPoem.removeRow(r);
				b.removePoem(disconnectPoem);
				nullSign = true;
				//disconnectPoem = null;
				b.addPoems(newUpPoem);
				b.addPoems(newBotPoem);
			}
			else{
				r.removeWord(disconnectWord);
				r.setLocationAfterConnection(r.getX()+disconnectWord.getWidth(), r.getY());
			}
		}
		else{
			if(r.getWordNumber() == 1){
				Row r1 = disconnectPoem.getFirstRow();
				Row r2 = r.getFormerRow();
				Row r3 = r.getNextRow();
				
				newUpPoem = new Poem(r1.getX(),r1.getY());
				newBotPoem = new Poem(r3.getX(),r3.getY());
				
				
				r2.setNextRow(null);
				
				while(r1!=null){
					newUpPoem.addRow(r1);
					r1 = r1.getNextRow();
				}
				newUpPoem.setLastRow(r1);
				
				r3.setFormerRow(null);
				
				while(r3!=null){
					newBotPoem.addRow(r3);
					r3 = r3.getNextRow();
				}
				newBotPoem.setLastRow(r3);
				
				disconnectPoem.removeRow(r);
				b.removePoem(disconnectPoem);
				//disconnectPoem = null;
				nullSign = false;
				b.addPoems(newUpPoem);
				b.addPoems(newBotPoem);
			}
			else{
				r.removeWord(disconnectWord);
			}
		}
		b.addWords(disconnectWord);
		model.setSelectedWordinPoem(null);
	}
	@Override
	public boolean undo(){
		switch(disType){
		case 1:
			this.undoForOneRow();
			break;
		case 2:
			this.undoForFirstRow();
			break;
		case 3:
			this.undoForLastRow();
			break;
		case 4:
			this.undoForMidRow();
			break;
		}
		return true;
	}
	
	public void undoForOneRow(){
		if(nullSign == false){
			if(type == 1){
				r.addWord(disconnectWord);
				r.setLocationAfterConnection(disconnectWord.getX(), disconnectWord.getY());
			}
			else{
				r.addWord(disconnectWord);
			}
			b.getWords().remove(disconnectWord);
			return;
		}
		else{
			if(type == 1){
			    /*disconnectPoem = new Poem(disconnectWord,remainWord,type);*/
				r.addWord(disconnectWord);
			    r.setLocationAfterConnection(disconnectWord.getX(), disconnectWord.getY());
			    disconnectPoem.addRow(r);
			    disconnectPoem.setLastRow(r);
			    r.setFormerRow(null);
			    r.setNextRow(null);
			}
			else{
				r.addWord(disconnectWord);
				disconnectPoem.addRow(r);
				disconnectPoem.setLastRow(r);
				r.setFormerRow(null);
				r.setNextRow(null);
				/*disconnectPoem = new Poem(remainWord,disconnectWord,type);*/
		    }
			b.getWords().remove(remainWord);
		    b.getWords().remove(disconnectWord);
			b.addPoems(disconnectPoem);
			return;
		}
	}
	
	public void undoForFirstRow(){
		if(r!=null){
			if(type == 1){
				r.addWord(disconnectWord);
				r.setLocationAfterConnection(disconnectWord.getX(), disconnectWord.getY());
			}
			else{
				r.addWord(disconnectWord);
			}
			b.getWords().remove(disconnectWord);
			return;
		}
		else{
			r = new Row(disconnectWord.getX(),disconnectWord.getY(),disconnectWord.getHeight(),0);
			r.addWord(disconnectWord);
			disconnectPoem.addRow(r);
			r.setNextRow(disconnectPoem.getFirstRow());
			disconnectPoem.getFirstRow().setFormerRow(r);
			disconnectPoem.setLocationAfterConnection(r.getX(),r.getY());
			b.getWords().remove(disconnectWord);
			return;
		}
	}
	
	public void undoForLastRow(){
		if(r!=null){
			if(type == 1){
				r.addWord(disconnectWord);
				r.setLocationAfterConnection(disconnectWord.getX(), disconnectWord.getY());
			}
			else{
				r.addWord(disconnectWord);
			}
			b.getWords().remove(disconnectWord);
			return;
		}
		else{
			r = new Row(disconnectWord.getX(),disconnectWord.getY(),disconnectWord.getHeight(),0);
			r.addWord(disconnectWord);
			disconnectPoem.addRow(r);
			r.setFormerRow(disconnectPoem.getLastRow());
			disconnectPoem.getLastRow().setNextRow(r);
			disconnectPoem.setLastRow(r);
            b.getWords().remove(disconnectWord);
			return;
		}
	}
	
	public void undoForMidRow(){
		if(nullSign == false){
			if(type == 1){
				r.addWord(disconnectWord);
				r.setLocationAfterConnection(disconnectWord.getX(), disconnectWord.getY());
			}
			else{
				r.addWord(disconnectWord);
			}
			b.getWords().remove(disconnectWord);
		}
		else{
			//disconnectPoem = new Poem(newUpPoem.getX(),newUpPoem.getY());
			//r = new Row(disconnectWord.getX(),disconnectWord.getY(),disconnectWord.getHeight(),0);
			r.addWord(disconnectWord);
			r.setLocationAfterConnection(disconnectWord.getX(), disconnectWord.getY());
			//disconnectPoem.addRow(r);
			disconnectPoem.setLastRow(newBotPoem.getLastRow());
			newUpPoem.getLastRow().setNextRow(r);
			r.setNextRow(newBotPoem.getFirstRow());
			r.setFormerRow(newUpPoem.getLastRow());
			disconnectPoem.addRow(r);
			
			/*Row r1 = newUpPoem.getFirstRow();
			while(r1 != null){
				disconnectPoem.addRow(r1);
				r1 = r1.getNextRow();
			}*/
			/*for(Row r:newUpPoem.getRows()){
				disconnectPoem.addRow(r);
			}
			
			for(Row r:newBotPoem.getRows()){
				disconnectPoem.addRow(r);
			}
			/*Row r2 = newBotPoem.getFirstRow();
			while(r2!=null){
				disconnectPoem.addRow(r2);
				r2 = r2.getNextRow();
			}*/

			
			b.addPoems(disconnectPoem);
			b.removePoem(newBotPoem);
			b.removePoem(newUpPoem);
			b.getWords().remove(disconnectWord);
		}
	}
	
}

	

