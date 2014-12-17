package Words.model;


import javax.swing.table.AbstractTableModel;
import java.lang.reflect.Array;
import java.util.HashMap;

/**
 * Created by evasun on 11/26/14.
 */
public class WordTypeModel extends AbstractTableModel {
    Board board;
    public static final String typeLabel = "Words Type";
    public static final String numberLabel = "Number";

    // set the word type definition to query the word type by index number
    // TODO: will be replaced by a static class or properties file
    private static String[] wordTypeDefinition = {"verb", "adj", "noun", "adv"};

    public WordTypeModel (Board b){
        this.board = b;
    }
    public HashMap<Integer, Integer> wordTypeCount;
    @Override
    public int getColumnCount() {return 2;}

    @Override
    public int getRowCount() { return Word.TYPE_COUNT + 1; }


    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(rowIndex < 0) return null; // added to solve problem of index of of bound
        if(rowIndex == 0){
            if (columnIndex == 0) {
                return "Unprotected Word";
            } else if (columnIndex == 1) {
                return board.size();
            }
            return "";
        }
        else{
            HashMap<Integer, Integer> wordTypes = board.countType();
            if (columnIndex == 0) {
                return Word.TYPE_INT_TO_STRING[rowIndex-1];
            } else if (columnIndex == 1) {
                Integer count = wordTypes.get(rowIndex-1);
                return count == null ? 0 : count;
            }
            // no idea who you are...
            return "";
        }

    }

}