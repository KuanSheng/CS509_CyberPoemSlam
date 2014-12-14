package Words.view;
import Words.controller.PopupWordController;
import Words.controller.RefreshWordTableController;
import Words.controller.SortWordsController;
import Words.controller.SwapAddListener;
import Words.model.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;

/**
 * Created by evasun on 11/25/14.
 */
public class WordTable extends JPanel{
    /* keep eclipse happy */
    private static final long serialVersionUID = 4561L;

    /** The model that "backs" the JTable. Different from Board. */
    WordModel wordModel = null;

    /** Actual GUI entity. */
    JTable jtable = null;

    Application app = null;

    /**
     * This constructor creates the display which manages the list of active players.
     */
    public WordTable(final Board board, Application app, OurSwap swap){
//        this(board);
//        this.app = app;
//    }
//    public WordTable(Board board) {

        // create the model for the data which backs this table
        wordModel = new WordModel(board);

     // add to listener chain
    	board.addListener(new RefreshWordTableController(this));
        
        // the proposed dimension of the UI
        Dimension mySize = new Dimension(250, 210);

        // Scrollable panel will enclose the JTable and support scrolling vertically
        JScrollPane jsp = new JScrollPane();
        jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jsp.setPreferredSize(mySize);

        // Just add the JTable to the set. First create the list of Players,
        // then the SwingModel that supports the JTable which you now create.
        jtable = new JTable(wordModel);
        jtable.setPreferredSize(mySize);

        // let's tell the JTable about its columns.
        TableColumnModel columnModel = new DefaultTableColumnModel();

        // must build one by one...
        String[] headers = new String[] { wordModel.wordLabel, wordModel.wordTypeLabel};
        int index = 0;
        for (String h : headers) {
            TableColumn col = new TableColumn(index++);
            col.setHeaderValue(h);
            columnModel.addColumn(col);
        }
        jtable.setColumnModel(columnModel);

        // let's install a sorter and also make sure no one can rearrange columns
        JTableHeader header = jtable.getTableHeader();
        // purpose of this sorter is to sort by columns.
        header.addMouseListener(new MouseAdapter()	{
            public void mouseClicked (MouseEvent e)	{
                JTableHeader h = (JTableHeader) e.getSource() ;
                new SortWordsController(WordTable.this, board).process(e);
            }
        });

        //to add listener to handle add into swap reqeust -- JUN start===================
        jtable.getSelectionModel().addListSelectionListener(new SwapAddListener(jtable, swap, app));
        jtable.getSelectionModel().addListSelectionListener(new PopupWordController(jtable, board));
//            @Override
//            public void valueChanged(ListSelectionEvent event) {
//                if(event.getValueIsAdjusting()) return; // this listener would be called twice
//                                                        //only act the second time -- when releasning
//                if (jtable.getSelectedRow() > -1) {
//                    // print first column value from selected row
//                    System.out.println(jtable.getValueAt(jtable.getSelectedRow(), 0).toString());
//                    System.out.println(jtable.getSelectedRow());
////                    System.out.println();
//                }
//            }
//        });
        //to add listener to handle add into swap reqeust -- JUN end======================

        // Here's the trick. Make the JScrollPane take its view from the JTable.
        jsp.setViewportView(jtable);

        // add the scrolling Pane which encapsulates the JTable
        // physical size limited
        this.setPreferredSize(mySize);
        this.add(jsp);
    }

    /**
     * Causes the display of the shapes to be updated to the latest data.
     */
    public void refreshTable() {
        // THIS is the key method to ensure JTable view is synchronized
        jtable.revalidate();
        jtable.repaint();
        this.revalidate();
        this.repaint();
    }

    public void clearSelection(){
        jtable.clearSelection();
    }

    public JTable getJtable() {
        return jtable;
    }
}