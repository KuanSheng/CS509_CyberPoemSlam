package Words.model;


import javax.swing.table.AbstractTableModel;
import java.util.HashMap;

/**
 * Created by evasun on 11/26/14.
 */
public class WordTypeModel extends AbstractTableModel {
    Board board;
    public static final String typeLabel = "Words Type";
    public static final String numberLabel = "Number";
    private int init = 1;

    public WordTypeModel (Board b){
        this.board = b;
    }
    public HashMap<Integer, Integer> wordTypeCount;
    @Override
    public int getColumnCount() {return 2;}

    @Override
    public int getRowCount() { return board.countType().size() + 1; }


    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(rowIndex == 0){
            if (columnIndex == 0) {
                return "Unprotected Word";
            } else if (columnIndex == 1) {
                return board.size();
            }
            return "";
        }
        else{
            Word s = board.get(rowIndex - 1);
            HashMap<Integer, Integer> wordTypes = board.countType();

            if (columnIndex == 0) {
                return s.wordType;
            } else if (columnIndex == 1) {
                return wordTypes.get(s.wordType);
            }
            // no idea who you are...
            return "";
        }

    }

}
