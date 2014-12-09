package Words.view;

import Words.controller.RefreshSwapTableController;
import Words.controller.RefreshWordTableController;
import Words.controller.SwapAddListener;
import Words.controller.SwapRemoveListener;
import Words.model.Board;
import Words.model.Model;
import Words.model.SwapModel;
import Words.model.WordModel;

import javax.swing.*;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;

/**
 * Created by Jun on 12/8/2014. Copied WordTable of Sun
 */
public class SwapTable extends JPanel {
    /* keep eclipse happy */
    private static final long serialVersionUID = 2341L;

    /** The model that "backs" the JTable. Different from Board. */
    SwapModel swapModel = null;

    /** Actual GUI entity. */
    JTable jtable = null;

    /**
     * This constructor creates the display which manages the list of active players.
     */
    public SwapTable(Board board, Application app) {

        // create the model for the data which backs this table
        swapModel = new SwapModel(board);

        // add to listener chain
        board.addListener(new RefreshSwapTableController(this));

        // the proposed dimension of the UI
        Dimension mySize = new Dimension(250, 210);

        // Scrollable panel will enclose the JTable and support scrolling vertically
        JScrollPane jsp = new JScrollPane();
        jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jsp.setPreferredSize(mySize);

        // Just add the JTable to the set. First create the list of Players,
        // then the SwingModel that supports the JTable which you now create.
        jtable = new JTable(swapModel);
        jtable.setPreferredSize(mySize);

        // let's tell the JTable about its columns.
        TableColumnModel columnModel = new DefaultTableColumnModel();

        // must build one by one...
        String[] headers = new String[] { swapModel.wordLabel, swapModel.wordTypeLabel};
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
//        header.addMouseListener(new MouseAdapter()	{
//            public void mouseClicked (MouseEvent e)	{
//                JTableHeader h = (JTableHeader) e.getSource() ;
//                new SortController(WordTable.this).process(h, e.getPoint());
//            }
//        });

        //to add listener to handle add into swap reqeust -- JUN start===================
        jtable.getSelectionModel().addListSelectionListener(new SwapRemoveListener(jtable, board, app));
//        jtable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
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
}