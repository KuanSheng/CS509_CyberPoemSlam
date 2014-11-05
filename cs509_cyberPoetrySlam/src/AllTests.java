import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import Words.controller.WordConnectionControllerTest;
import Words.controller.WordDisconnectionControllerTest;
import Words.controller.WordMoveControllerTest;
import Words.model.BoardTest;
import Words.model.ModelTest;
import Words.model.PoemTest;
import Words.model.RowTest;
import Words.model.WordTest;
import Words.view.ApplicationCanvasTest;
import Words.view.ApplicationTest;


@RunWith(Suite.class)
@SuiteClasses({WordTest.class, RowTest.class, PoemTest.class, ModelTest.class,  BoardTest.class, 
	ApplicationTest.class, ApplicationCanvasTest.class,
	 WordConnectionControllerTest.class, WordMoveControllerTest.class,WordDisconnectionControllerTest.class})
public class AllTests {

}
