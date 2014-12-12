package Words.model;

import broker.util.Swap;

import javax.swing.table.AbstractTableModel;

/**
 * Created by Jun on 12/8/2014.
 */


//=============

/**
 * Created by evasun on 11/25/14.
 * delegate to wordModel to manage the state instead of using board
 */
public class SwapModel extends AbstractTableModel {
    /** Keep Eclipse Happy. */
    private static final long serialVersionUID = 2L;

    // set the word type definition to query the word type by index number
    // TODO: will be replaced by a static class or properties file
    private static String[] wordTypeDefinition = {"verb", "adj", "noun", "adv"};

    /** Board maintains the state. */
    Board board;

    OurSwap swap;

    /** Key values. */
    public static final String wordLabel = "Word";
    public static final String wordTypeLabel = "Word Type";

    /** The Table model needs to know the board which contains the shapes. */
    public SwapModel (Board b, OurSwap swap) {

        this.board = b;
        this.swap  = swap;
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public int getRowCount() {
        return swap.getOurOffer().size();
    }


//    @Override
//    public Object getValueAt(int rowIndex, int columnIndex) {
////        Word s = board.getOurSwap(rowIndex);
//        Word s = swap.getOurOffer().get(rowIndex);
//        if(s == null) return "EMPTY";
//        if (columnIndex == 0) {
//            return s.value;
//        } else if (columnIndex == 1) {
//            return wordTypeDefinition[s.getWordType()];
//        }else {
//            return "EMPTY";
//        }
//        // no idea who you are...
//    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex){
            case 0 :
                return swap.getOurOffer().get(rowIndex).value;
            case 1 :
                return Word.TYPE_INT_TO_STRING[swap.getOurOffer().get(rowIndex).getWordType()];
        }
        return null;
    }
}