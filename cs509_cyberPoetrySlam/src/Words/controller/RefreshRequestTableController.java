package Words.controller;

import Words.view.RequestTable;
import Words.view.WordTable;

import java.io.Serializable;

/**
 * this controller refreshed the request table (third one from left of our gui)
 * Created by Jun on 12/8/2014.
 */
public class RefreshRequestTableController implements Listener, Serializable {

    /** Widget to be refreshed. */
    RequestTable table;

    public RefreshRequestTableController(RequestTable table) {
        this.table = table;
    }

    @Override
    public void update() {
        table.refreshTable();
    }
}