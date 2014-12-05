package Words.controller;
import Words.model.*;

public class DisconnectionPoemMove extends Move{
	Poem disconnectPoem;
	Row disconnectRow;
	Board board;
	
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
			
			Poem newUpPoem = new Poem(disconnectRow.getX(),disconnectRow.getY());
			newUpPoem.addRow(disconnectRow);
			newUpPoem.setLastRow(disconnectRow);
			disconnectRow.setNextRow(null);
			board.addPoems(newUpPoem);
			
			return true;
		}
		else if(disconnectRow.equals(disconnectPoem.getLastRow())){
			disconnectPoem.removeRow(disconnectRow);
			Row r = disconnectRow.getFormerRow();
			r.setNextRow(null);
			disconnectRow.setFormerRow(null);
			
			Poem newPoem = new Poem(disconnectRow.getX(),disconnectRow.getY());
			newPoem.addRow(disconnectRow);
			newPoem.setLastRow(disconnectRow);
			board.addPoems(newPoem);
			return true;
		}
		else{
			Row r1 = disconnectPoem.getFirstRow();
			Row r2 = disconnectRow.getFormerRow();
			Row r3 = disconnectRow.getNextRow();
			
			Poem newPoem = new Poem(disconnectRow.getX(),disconnectRow.getY());
			Poem newUpPoem = new Poem(r1.getX(),r1.getY());
			Poem newBotPoem = new Poem(r3.getX(),r3.getY());
			
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
			
			return true;
		}
		
	}
	
	@Override
	public boolean undo(){
		return false;
	}
}