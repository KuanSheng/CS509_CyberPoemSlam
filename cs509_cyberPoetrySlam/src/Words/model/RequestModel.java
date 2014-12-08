package Words.model;

/**
 * Created by Jun on 12/8/2014.
 */

import javax.swing.table.AbstractTableModel;

/**
 * Created by Jun on 12/8/2014.
 */


//=============

/**
 * Created by evasun on 11/25/14.
 * delegate to wordModel to manage the state instead of using board
 */
public class RequestModel extends AbstractTableModel {
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
    public RequestModel (Board b) {
        this.board = b;
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public int getRowCount() {
        return board.ourSwap.size();
    }


    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Word s = board.getOurSwap(rowIndex);
        if(s == null) return "EMPTY";
        if (columnIndex == 0) {
            return s.value;
        } else if (columnIndex == 1) {
            return wordTypeDefinition[s.getWordType()];
        }else {
            return "EMPTY";
        }
        // no idea who you are...
    }
}