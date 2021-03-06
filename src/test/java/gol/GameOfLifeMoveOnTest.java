package gol;

import org.junit.Before;
import org.junit.Test;

import static gol.GameOfLife.calcNextStep;
import static junit.framework.TestCase.assertEquals;

/**
 * @author K.ilya
 */
public class GameOfLifeMoveOnTest {

	GameOfLife gol;
	GameField startGameField;

	@Before
	public void init() {
		gol = new GameOfLife();
		int[][] m = {
				{0, 1, 1, 0, 1, 1},
				{2, 1, 3, 3, 2, 0},
				{3, 1, 2, 0, 2, 1},
				{2, 0, 1, 1, 2, 0},
		};
		startGameField = new GameField(m);
		gol.initGameField(m);
	}

	@Test
	public void moveOnTest() {
		gol.moveOn();
		assertEquals(gol.gameField, calcNextStep(startGameField));
		assertEquals(gol.oldGameField, startGameField);
	}
}
