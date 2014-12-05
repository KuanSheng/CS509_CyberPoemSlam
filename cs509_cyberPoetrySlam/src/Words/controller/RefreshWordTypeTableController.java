package Words.controller;

import Words.view.WordTypeTable;

public class RefreshWordTypeTableController implements Listener {

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