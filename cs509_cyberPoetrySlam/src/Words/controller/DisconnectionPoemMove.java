/**
 * created and modified by KuanSheng
 * Disconnect a row from a poem
 * we have 3 cases here
 * 1.disconnect the first row
 * 2.disconnect the second row
 * 3.disconnect the mid row**/
package Words.controller;
import Words.model.*;

public class DisconnectionPoemMove extends Move{
	Poem disconnectPoem;
	Row disconnectRow;
	Poem newPoem;
	Poem newBotPoem;
	Poem newUpPoem;
	Board board;
	int type = 0;
	
	/**constructor**/
	public DisconnectionPoemMove(Poem disconnectPoem,Row disconnectRow,Board board){
		this.disconnectPoem = disconnectPoem;
		this.disconnectRow = disconnectRow;
		this.board = board;
	}
	
	/**execute the operation**/
	@Override
	public boolean execute(){
		if(disconnectPoem.getRowNumber() == 1){
	    	return false;
		}
		//disconnect the first row
		if(disconnectRow.equals(disconnectPoem.getFirstRow())){
			disconnectPoem.removeRow(disconnectRow);
			Row r = disconnectRow.getNextRow();
			disconnectPoem.setLocationAfterConnection(r.getX(),r.getY());
			r.setFormerRow(null);
			//generate a new poem
			this.newUpPoem = new Poem(disconnectRow.getX(),disconnectRow.getY());
			newUpPoem.addRow(disconnectRow);
			newUpPoem.setLastRow(disconnectRow);
			disconnectRow.setNextRow(null);
			board.addPoems(newUpPoem);
			//record which type row for undo
			this.type = 1;
			return true;
		}
		else if(disconnectRow.equals(disconnectPoem.getLastRow())){
			disconnectPoem.removeRow(disconnectRow);
			Row r = disconnectRow.getFormerRow();
			r.setNextRow(null);
			disconnectPoem.setLastRow(r);
			disconnectRow.setFormerRow(null);
			
			this.newPoem = new Poem(disconnectRow.getX(),disconnectRow.getY());
			newPoem.addRow(disconnectRow);
			newPoem.setLastRow(disconnectRow);
			board.addPoems(newPoem);
			this.type = 2;
			return true;
		}
		else{
			Row r = disconnectPoem.getFirstRow();
			Row r1 = disconnectPoem.getLastRow();
			Row r2 = disconnectRow.getFormerRow();
			Row r3 = disconnectRow.getNextRow();
			
			newPoem = new Poem(disconnectRow.getX(),disconnectRow.getY());
			if(r1 == null){
				System.out.println("woca");
			}
			newUpPoem = new Poem(r.getX(),r.getY());
			newBotPoem = new Poem(r3.getX(),r3.getY());
			
			newPoem.addRow(disconnectRow);
			newPoem.setLastRow(disconnectRow);
			disconnectRow.setFormerRow(null);
			disconnectRow.setNextRow(null);
			
			
			r2.setNextRow(null);
			
			while(r!=null){
				newUpPoem.addRow(r);
				r = r.getNextRow();
			}
			
			r3.setFormerRow(null);
			
			while(r3!=null){
				newBotPoem.addRow(r3);
				r3 = r3.getNextRow();
			}
			
			newUpPoem.setLastRow(r2);
			newBotPoem.setLastRow(r1);
			board.removePoem(disconnectPoem);
			board.addPoems(newPoem);
			board.addPoems(newUpPoem);
			board.addPoems(newBotPoem);
			
			this.type = 3;
			return true;
		}
		
	}
	
	/**when redo, we do not need to new a poem, only need to resume the removed one.
	 * generate a new one may change the object of poem**/
	@Override
	public boolean redo(){
		if(disconnectPoem.getRowNumber() == 1){
	    	return false;
		}
		
		if(disconnectRow.equals(disconnectPoem.getFirstRow())){
			disconnectPoem.removeRow(disconnectRow);
			Row r = disconnectRow.getNextRow();
			disconnectPoem.setLocationAfterConnection(r.getX(),r.getY());
			r.setFormerRow(null);
			
			//this.newUpPoem = new Poem(disconnectRow.getX(),disconnectRow.getY());
			//newUpPoem.addRow(disconnectRow);
			//newUpPoem.setLastRow(disconnectRow);
			//disconnectRow.setNextRow(null);
			board.addPoems(newUpPoem);
			
			this.type = 1;
			return true;
		}
		else if(disconnectRow.equals(disconnectPoem.getLastRow())){
			disconnectPoem.removeRow(disconnectRow);
			Row r = disconnectRow.getFormerRow();
			r.setNextRow(null);
			disconnectPoem.setLastRow(r);
			disconnectRow.setFormerRow(null);
			
			//this.newPoem = new Poem(disconnectRow.getX(),disconnectRow.getY());
			//newPoem.addRow(disconnectRow);
			//newPoem.setLastRow(disconnectRow);
			board.addPoems(newPoem);
			this.type = 2;
			return true;
		}
		else{
			Row r1 = disconnectPoem.getLastRow();
			Row r2 = disconnectRow.getFormerRow();
			Row r3 = disconnectRow.getNextRow();
			
			/*newPoem = new Poem(disconnectRow.getX(),disconnectRow.getY());
			newUpPoem = new Poem(r1.getX(),r1.getY());
			newBotPoem = new Poem(r3.getX(),r3.getY());*/
			
			//newPoem.addRow(disconnectRow);
			newUpPoem.setLastRow(r2);
			newPoem.setLastRow(disconnectRow);
			newBotPoem.setLastRow(r1);
			disconnectRow.setFormerRow(null);
			disconnectRow.setNextRow(null);
			
			r2.setNextRow(null);
			r2.setFormerRow(null);
			/*while(r1!=null){
				newUpPoem.addRow(r1);
				r1 = r1.getNextRow();
			}
			
			r3.setFormerRow(null);
			
			while(r3!=null){
				newBotPoem.addRow(r3);
				r3 = r3.getNextRow();
			}*/
			
			
			board.removePoem(disconnectPoem);
			board.addPoems(newPoem);
			board.addPoems(newUpPoem);
			board.addPoems(newBotPoem);
			
			this.type = 3;
			return true;
		}
	}
	
	/**undo operation**/
	@Override
	public boolean undo(){
		switch(type){
		case 1:
			undoFirstRow();
			break;
		case 2:
			undoLastRow();
			break;
		case 3:
			undoMidRow();
			break;
		}
		return true;
	}
	
	/**undo mid row disconnection**/
	public void undoMidRow(){
		disconnectRow.setNextRow(newBotPoem.getFirstRow());
		disconnectRow.setFormerRow(newUpPoem.getLastRow());
		board.addPoems(disconnectPoem);
		board.removePoem(newPoem);
		board.removePoem(newBotPoem);
		board.removePoem(newUpPoem);
	}
	
	/**undo first row disconnection**/
	public void undoFirstRow(){
		disconnectPoem.addRow(disconnectRow);
		disconnectRow.setNextRow(disconnectPoem.getFirstRow());;
		disconnectPoem.setLocationAfterConnection(disconnectRow.getX(), disconnectRow.getY());
		board.removePoem(newUpPoem);
	}
	
	/**undo last row disconnection**/
	public void undoLastRow(){
		disconnectPoem.addRow(disconnectRow);
		disconnectRow.setFormerRow(disconnectPoem.getLastRow());
		disconnectPoem.setLastRow(disconnectRow);
		board.removePoem(newPoem);
	}
}