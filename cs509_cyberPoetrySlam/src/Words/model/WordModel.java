package Words.model;

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
            return wordTypeDefinition[s.getWordType()];
        }
        // no idea who you are...
        return "";
    }

//    /**
//     * Sort the ArrayList of cars by given field, whose value is
//     * either {@link UserModelGUI#uidStr}, {@link UserModelGUI#rNameStr},
//     * {@link UserModelGUI#tableNumStr}, or {@link UserModelGUI#timeStr}.
//     *
//     * @param columnIndex
//     */
//    public void sort(final String fieldName) {
//        board.sort(new Comparator<Word>() {
//
//            @Override
//            public int compare(Word s1, Word s2) {
//                if (fieldName.equals(xLabel)) {
//                    return s1.getX() - s2.getX();
//                } else if (fieldName.equals(yLabel)) {
//                    return s1.getY() - s2.getY();
//                }
//
//                // default to word
//                return (s1.getValue().compareTo(s2.getValue()));
//            }
//        });
//    }
}
