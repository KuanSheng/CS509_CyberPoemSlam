/**created and modified by KuanSheng
 * added redo() because I found that some redo operation are different from
 * executed first time **/
package Words.controller;

public abstract class Move {
	/** Execute given move. */
	public abstract boolean execute();
	
	/** Request undo. */
	public abstract boolean undo();
	
	/** Request redo**/
	public abstract boolean redo();
}
