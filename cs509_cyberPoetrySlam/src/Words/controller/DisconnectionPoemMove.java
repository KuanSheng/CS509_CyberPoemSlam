/**
 * Disconnect a row from a poem.
 * 1.Disconnect the first row;
 * 2.Disconnect the last row;
 * 3.Disconnect a mid row
 * created and modified by Kuan**/
/**
 * Do not change these attributes during operation!
 * or undo will kill you!!!
 * it should be stateless.**/

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
	
	public DisconnectionPoemMove(Poem disconnectPoem,Row disconnectRow,Board board){
		this.disconnectPoem = disconnectPoem;
		this.disconnectRow = disconnectRow;
		this.board = board;
	}
	
	@Override
	public boolean execute(){
		if(disconnectPoem.getRowNumber() == 1){
			return false;
		}
		
		if(disconnectRow.equals(disconnectPoem.getFirstRow())){
			disconnectPoem.removeRow(disconnectRow);
			Row r = disconnectRow.getNextRow();
			disconnectPoem.setLocationAfterConnection(r.getX(),r.getY());
			r.setFormerRow(null);
			
			this.newUpPoem = new Poem(disconnectRow.getX(),disconnectRow.getY());
			newUpPoem.addRow(disconnectRow);
			newUpPoem.setLastRow(disconnectRow);
			disconnectRow.setNextRow(null);
			board.addPoems(newUpPoem);
			
			this.type = 1;
			return true;
		}
		else if(disconnectRow.equals(disconnectPoem.getLastRow())){
			disconnectPoem.removeRow(disconnectRow);
			Row r = disconnectRow.getFormerRow();
			r.setNextRow(null);
			disconnectRow.setFormerRow(null);
			
			this.newPoem = new Poem(disconnectRow.getX(),disconnectRow.getY());
			newPoem.addRow(disconnectRow);
			newPoem.setLastRow(disconnectRow);
			board.addPoems(newPoem);
			this.type = 2;
			return true;
		}
		else{
			Row r1 = disconnectPoem.getFirstRow();
			Row r2 = disconnectRow.getFormerRow();
			Row r3 = disconnectRow.getNextRow();
			
			newPoem = new Poem(disconnectRow.getX(),disconnectRow.getY());
			newUpPoem = new Poem(r1.getX(),r1.getY());
			newBotPoem = new Poem(r3.getX(),r3.getY());
			
			newPoem.addRow(disconnectRow);
			disconnectRow.setFormerRow(null);
			disconnectRow.setNextRow(null);
			
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
			
			
			board.removePoem(disconnectPoem);
			board.addPoems(newPoem);
			board.addPoems(newUpPoem);
			board.addPoems(newBotPoem);
			
			this.type = 3;
			return true;
		}
		
	}
	
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
	
	public void undoMidRow(){
		disconnectRow.setNextRow(newBotPoem.getFirstRow());
		disconnectRow.setFormerRow(newUpPoem.getLastRow());
		board.addPoems(disconnectPoem);
		board.removePoem(newPoem);
		board.removePoem(newBotPoem);
		board.removePoem(newUpPoem);
	}
	
	public void undoFirstRow(){
		disconnectPoem.addRow(disconnectRow);
		disconnectRow.setNextRow(disconnectPoem.getFirstRow());;
		disconnectPoem.setLocationAfterConnection(disconnectRow.getX(), disconnectRow.getY());
		board.removePoem(newUpPoem);
	}
	
	public void undoLastRow(){
		disconnectPoem.addRow(disconnectRow);
		disconnectRow.setFormerRow(disconnectPoem.getLastRow());
		disconnectPoem.setLastRow(disconnectRow);
		board.removePoem(newPoem);
	}
}
