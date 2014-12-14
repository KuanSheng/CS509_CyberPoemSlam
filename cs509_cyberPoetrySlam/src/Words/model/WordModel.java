package Words.model;
import Words.model.Board;

import javax.swing.table.AbstractTableModel;



import java.util.Comparator;
import javax.swing.table.AbstractTableModel;

/**
 * Created by evasun on 11/25/14.
 * delegate to wordModel to manage the state instead of using board
 */
public class WordModel extends AbstractTableModel {
    /** Keep Eclipse Happy. */
    private static final long serialVersionUID = 1L;

    // set the word type definition to query the word type by index number
    // TODO: will be replaced by a static class or properties file
    private static String[] wordTypeDefinition = {"verb", "adj", "noun", "adv"};

    /** Board maintains the state. */
    Board board;

    /** Key values. */
    public static final String wordLabel = "Word";
    public static final String wordTypeLabel = "Word Type";

    /** The Table model needs to know the board which contains the shapes. */
    public WordModel (Board b) {
        this.board = b;
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public int getRowCount() {
        return board.size();
    }


    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Word s = board.get(rowIndex);

        if (columnIndex == 0) {
            return s.value;
        } else if (columnIndex == 1) {
            return Word.TYPE_INT_TO_STRING[s.getWordType()];
        }
        // no idea who you are...
        return "";
    }

    public static final int VERB_INT = 0;
    public static final int ADJ_INT = 1;
    public static final int NOUN_INT = 2;
    public static final int ADV_INT = 3;


    public static final String VERB_STRING = "verb";
    public static final String ADJ_STRING = "adj";
    public static final String NOUN_STRING = "noun";
    public static final String ADV_STRING = "adv";
}

