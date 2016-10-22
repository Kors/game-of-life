package gol;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static gol.CellState.*;
import static gol.GameOfLife.getNextCellState;
import static junit.framework.TestCase.assertEquals;

/**
 * @author K.ilya
 */
@RunWith(Parameterized.class)
public class GameOfLifeNextStateTest {

	CellState state;
	int neighboursCount;
	CellState expectedState;

	public GameOfLifeNextStateTest(CellState state, int neighboursCount, CellState expectedState) {
		this.state = state;
		this.neighboursCount = neighboursCount;
		this.expectedState = expectedState;
	}

	@Parameterized.Parameters
	public static Collection<Object[]> data() {
		Object[][] data = new Object[][]{
				{ALIVE, 0, DIEING},
				{ALIVE, 1, DIEING},
				{ALIVE, 2, ALIVE},
				{ALIVE, 3, ALIVE},
				{ALIVE, 4, DIEING},
				{ALIVE, 8, DIEING},

				{DEAD, 0, DEAD},
				{DEAD, 1, DEAD},
				{DEAD, 2, DEAD},
				{DEAD, 3, BEARING},
				{DEAD, 4, DEAD},
				{DEAD, 8, DEAD},

				{BEARING, 0, DEAD},
				{BEARING, 1, DEAD},
				{BEARING, 2, ALIVE},
				{BEARING, 3, ALIVE},
				{BEARING, 4, DEAD},
				{BEARING, 8, DEAD},

				{DIEING, 0, DEAD},
				{DIEING, 1, DEAD},
				{DIEING, 2, ALIVE},
				{DIEING, 3, ALIVE},
				{DIEING, 4, DEAD},
				{DIEING, 8, DEAD},
		};
		return Arrays.asList(data);
	}

	@Test
	public void nextStateTest() {
		assertEquals(expectedState, getNextCellState(state, neighboursCount));
	}

}