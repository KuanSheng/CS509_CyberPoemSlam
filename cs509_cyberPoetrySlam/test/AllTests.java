import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import Words.BrokerManagerTest;
import Words.controller.AddRandomWordForSwapTest;
import Words.controller.ConnectTwoPoemMoveTest;
import Words.controller.ConnectionMoveTest;
import Words.controller.ConnectionPoemMoveTest;
import Words.controller.DisconnectionMoveTest;
import Words.controller.DisconnectionPoemControllerTest;
import Words.controller.DisconnectionPoemMoveTest;
import Words.controller.MovePoemTest;
import Words.controller.MoveProtectTest;
import Words.controller.MoveWordTest;
import Words.controller.RedoControllerTest;
import Words.controller.ReleasePoemControllerTest;
import Words.controller.ReleasePoemMoveTest;
import Words.controller.ShiftRowControllerTest;
import Words.controller.ShiftRowMoveTest;
import Words.controller.SortWordsControllerTest;
import Words.controller.StoreStateControllerTest;
import Words.controller.SubmitPoemControllerTest;
import Words.controller.SwapRequestControllerTest;
import Words.controller.UndoControllerTest;
import Words.controller.WordConnectionControllerTest;
import Words.controller.WordDisconnectionControllerTest;
import Words.controller.WordMoveControllerTest;
import Words.model.BoardTest;
import Words.model.ModelTest;
import Words.model.OurSwapTest;
import Words.model.PoemTest;
import Words.model.RowTest;
import Words.model.SwapModelTest;
import Words.model.WordTest;
import Words.view.ApplicationCanvasTest;
import Words.view.ApplicationTest;
import Words.view.NewRequestTableTest;


@RunWith(Suite.class)
@SuiteClasses({WordTest.class, RowTest.class, PoemTest.class, ModelTest.class,  BoardTest.class, OurSwapTest.class,
	SwapModelTest.class,
	
	ApplicationTest.class, ApplicationCanvasTest.class,
	
	 WordConnectionControllerTest.class, WordMoveControllerTest.class,WordDisconnectionControllerTest.class,
	 UndoControllerTest.class, AddRandomWordForSwapTest.class,DisconnectionPoemControllerTest.class,
	 ReleasePoemControllerTest.class, ShiftRowControllerTest.class,SortWordsControllerTest.class, ReleasePoemMoveTest.class,
	 SwapRequestControllerTest.class,MoveProtectTest.class,
	 MovePoemTest.class, ConnectionPoemMoveTest.class,DisconnectionMoveTest.class, DisconnectionPoemMoveTest.class,
	 ShiftRowMoveTest.class, ConnectionMoveTest.class, SubmitPoemControllerTest.class, StoreStateControllerTest.class,
	 NewRequestTableTest.class,ConnectTwoPoemMoveTest.class, MoveWordTest.class, RedoControllerTest.class,
	 
     BrokerManagerTest.class})
public class AllTests {

}
