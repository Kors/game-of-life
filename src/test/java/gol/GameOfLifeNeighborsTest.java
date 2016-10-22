package gol;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static gol.CellState.ALIVE;
import static gol.CellState.DEAD;
import static gol.GameOfLife.getNeighboursCount;
import static junit.framework.TestCase.assertEquals;

/**
 * @author K.ilya
 */
@RunWith(Parameterized.class)
public class GameOfLifeNeighborsTest {
	Matrix stateBefore;
	int row;
	int col;
	int neighboursExpected;

	public GameOfLifeNeighborsTest(Matrix stateBefore, int row, int col, int neighboursExpected) {
		this.stateBefore = stateBefore;
		this.row = row;
		this.col = col;
		this.neighboursExpected = neighboursExpected;
	}

	@Parameterized.Parameters
	public static Collection<Object[]> data() {
		CellState[][] state = {
				{ALIVE, DEAD, DEAD},
				{DEAD, ALIVE, ALIVE},
				{DEAD, ALIVE, ALIVE}};
		Matrix m1 = new Matrix(state);

		Object[][] params = new Object[][]{
				{m1, 0, 0, 1},
				{m1, 0, 1, 3},
				{m1, 0, 2, 2},
				{m1, 1, 1, 4},
		};
		return Arrays.asList(params);
	}

	@Test
	public void neighborsTest() {
		assertEquals(1, getNeighboursCount(stateBefore.getGameField(), 0, 0));
		assertEquals(3, getNeighboursCount(stateBefore.getGameField(), 0, 1));
		assertEquals(2, getNeighboursCount(stateBefore.getGameField(), 0, 2));
		assertEquals(4, getNeighboursCount(stateBefore.getGameField(), 1, 1));
	}
}
