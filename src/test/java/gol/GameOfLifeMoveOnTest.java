package gol;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static gol.GameOfLife.calcNextStep;
import static junit.framework.TestCase.assertEquals;

/**
 * @author K.ilya
 */
@RunWith(Parameterized.class)
public class GameOfLifeMoveOnTest {

	GameOfLife gol;
	Matrix startMatrix;

	@Before
	public void init() {
		gol = new GameOfLife();
		startMatrix = new Matrix(new int[][]{
				{0, 1, 1, 0, 1, 1},
				{2, 1, 3, 3, 2, 0},
				{3, 1, 2, 0, 2, 1},
				{2, 0, 1, 1, 2, 0},
		});
		gol.initGameField();
	}

	@Test
	public void moveOnTest() {
		gol.moveOn();
		assertEquals(gol.matrix, calcNextStep(startMatrix));
	}
}
