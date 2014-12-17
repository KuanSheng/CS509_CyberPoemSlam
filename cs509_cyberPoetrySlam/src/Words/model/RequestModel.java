package Words.model;

/**
 * Created by Jun on 12/8/2014.
 */

import javax.swing.table.AbstractTableModel;

/**
 * Created by Jun on 12/8/2014.
 */

/**
 * Created by evasun on 11/25/14.
 * delegate to wordModel to manage the state instead of using board
 */
public class RequestModel extends AbstractTableModel {
    /** Keep Eclipse Happy. */
    private static final long serialVersionUID = 4L;

    /** Board maintains the state. */
    Board board;
    OurSwap swap;

    /** Key values. */
    public static final String wordLabel = "Word";
    public static final String wordTypeLabel = "Word Type";

    /** The Table model needs to know the board which contains the shapes. */
    public RequestModel (Board b, OurSwap swap) {
        this.board = b;
        this.swap  = swap;
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public int getRowCount() {
        return swap.getOurRequest().size();
    }

    /**
     * set table editable so as to get players's input
     * @param rowIndex
     * @param columnIndex
     * @return
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return rowIndex >= 0 ? true : false;
    }

    /**
     * called when user choose not to use default and want a specific word
     * @param aValue
     * @param rowIndex
     * @param columnIndex
     */
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
//        super.setValueAt(aValue, rowIndex, columnIndex);
//        requets[rowIndex][columnIndex] = (String) aValue;
        switch (columnIndex){
            case 0 :
                swap.getOurRequest().get(rowIndex).value = aValue.toString();
                break;
            case 1 :
                swap.getOurRequest().get(rowIndex).type = aValue.toString();
                break;
            default:
                break;

        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(rowIndex >= swap.getOurRequest().size() ){
            return null;
        }
        switch ( columnIndex){
            case 0 :
                return swap.getOurRequest().get(rowIndex).value;
            case 1 :
                return swap.getOurRequest().get(rowIndex).type;
        }
        return null;
    }
}