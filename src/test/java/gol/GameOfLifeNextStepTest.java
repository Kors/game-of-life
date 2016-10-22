package gol;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static gol.CellState.*;
import static gol.GameOfLife.calcNextStep;
import static junit.framework.TestCase.assertEquals;

/**
 * @author K.ilya
 */
@RunWith(Parameterized.class)
public class GameOfLifeNextStepTest {
	Matrix stateBefore;
	Matrix stateAfter;

	public GameOfLifeNextStepTest(Matrix stateBefore, Matrix stateAfter) {
		this.stateBefore = stateBefore;
		this.stateAfter = stateAfter;
	}

	@Parameterized.Parameters
	public static Collection<Object[]> data() {
		CellState[][] before = {
				{ALIVE, DEAD, DEAD},
				{DEAD, ALIVE, ALIVE},
				{DIEING, DIEING, BEARING}};
		CellState[][] after = {
				{DIEING, BEARING, DEAD},
				{DEAD, DIEING, ALIVE},
				{ALIVE, ALIVE, ALIVE}};
		Matrix m1 = new Matrix(before);
		Matrix m2 = new Matrix(after);

		Object[][] params = new Object[][]{
				{m1, m2},
		};
		return Arrays.asList(params);
	}

	@Test
	public void neighborsTest() {
		assertEquals(stateAfter, calcNextStep(stateBefore));
	}
}
