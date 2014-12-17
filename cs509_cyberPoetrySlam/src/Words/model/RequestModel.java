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
    private static final long serialVersionUID = 4L;

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


    //added by JUN for editing table -- start
//    private String[][] requets = new String[1][2];
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return rowIndex >= 0 ? true : false;
    }

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
    //added by JUN for editing table -- end
//    @Override
//    public Object getValueAt(int rowIndex, int columnIndex) {
//        if(rowIndex >= requets.length ){
//            expandTable();
//        }
//        return requets[rowIndex][columnIndex];
//    }
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
    /**
     *    expand the table when a new swap pair is added.
     */
//    private void expandTable(){
//        int newRowCount = getRowCount();
//        int newColCount = getColumnCount();
//        String[][] expand = new String[newRowCount][newColCount];
//        for(int i = 0; i < newRowCount-1; i ++){
//            expand[i][0] = requets[i][0];
//            expand[i][1] = requets[i][1];
//        }
//        expand[newRowCount-1][0] = ""; //set default value and type to empty
//        expand[newRowCount-1][1] = "";
//        requets = expand;
//    }
}