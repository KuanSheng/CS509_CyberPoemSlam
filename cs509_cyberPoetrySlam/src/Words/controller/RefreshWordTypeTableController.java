package Words.controller;

import Words.view.WordTypeTable;

import java.io.Serializable;

public class RefreshWordTypeTableController implements Listener, Serializable {

	/** Widget to be refreshed. */
	WordTypeTable table;
	
	public RefreshWordTypeTableController(WordTypeTable table) {
		this.table = table;
	}
	
	@Override
	public void update() {
		table.refreshTable();
	}
}