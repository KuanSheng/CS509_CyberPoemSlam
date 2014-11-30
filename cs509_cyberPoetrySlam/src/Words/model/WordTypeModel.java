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
                return wordTypeDefinition[s.wordType];
            } else if (columnIndex == 1) {
                return wordTypes.get(s.wordType);
            }
            // no idea who you are...
            return "";
        }

    }

}
