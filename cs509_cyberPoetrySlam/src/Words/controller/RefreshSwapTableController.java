package Words.controller;

import Words.view.SwapTable;
import Words.view.WordTable;

import java.io.Serializable;

/**
 * Created by Jun on 12/8/2014. Copied RefreshWordTableController from Ruizhu
 */


public class RefreshSwapTableController implements Listener, Serializable {

    /** Widget to be refreshed. */
    SwapTable table;

    public RefreshSwapTableController(SwapTable table) {
        this.table = table;
    }

    @Override
    public void update() {
        table.refreshTable();
    }
}