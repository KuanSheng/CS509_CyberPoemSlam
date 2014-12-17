package Words.controller;

import Words.view.WordTable;

import java.io.Serializable;

public class RefreshWordTableController implements Listener, Serializable {

	/** Widget to be refreshed. */
	WordTable table;
	
	public RefreshWordTableController(WordTable table) {
		this.table = table;
	}
	
	@Override
	public void update() {
		table.refreshTable();
	}
}