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
	Matrix startMatrix;

	@Before
	public void init() {
		gol = new GameOfLife();
		int[][] m = {
				{0, 1, 1, 0, 1, 1},
				{2, 1, 3, 3, 2, 0},
				{3, 1, 2, 0, 2, 1},
				{2, 0, 1, 1, 2, 0},
		};
		startMatrix = new Matrix(m);
		gol.initGameField(m);
	}

	@Test
	public void moveOnTest() {
		gol.moveOn();
		assertEquals(gol.matrix, calcNextStep(startMatrix));
	}
}
