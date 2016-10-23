package gol;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static gol.CellState.*;
import static junit.framework.TestCase.assertEquals;

/**
 * @author K.ilya
 */
@RunWith(Parameterized.class)
public class GameOfLifeNeighborsTest {
	GameField stateBefore;
	int row;
	int col;
	int neighboursExpected;

	public GameOfLifeNeighborsTest(GameField stateBefore, int row, int col, int neighboursExpected) {
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
				{DIEING, DIEING, BEARING}};
		GameField gf = new GameField(state);

		Object[][] params = new Object[][]{
				{gf, 0, 0, 1},
				{gf, 0, 1, 3},
				{gf, 0, 2, 2},
				{gf, 1, 1, 4},
		};
		return Arrays.asList(params);
	}

	@Test
	public void neighborsTest() {
		assertEquals(1, stateBefore.getNeighboursCount(0, 0));
		assertEquals(3, stateBefore.getNeighboursCount(0, 1));
		assertEquals(2, stateBefore.getNeighboursCount(0, 2));
		assertEquals(4, stateBefore.getNeighboursCount(1, 0));
		assertEquals(4, stateBefore.getNeighboursCount(1, 1));
		assertEquals(2, stateBefore.getNeighboursCount(1, 2));
		assertEquals(2, stateBefore.getNeighboursCount(2, 0));
		assertEquals(3, stateBefore.getNeighboursCount(2, 1));
		assertEquals(3, stateBefore.getNeighboursCount(2, 2));
	}
}
