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
		Matrix m0 = new Matrix(new CellState[][]{
				{ALIVE, DEAD, DEAD},
				{DEAD, ALIVE, ALIVE},
				{DIEING, DIEING, BEARING}});
		Matrix m1 = new Matrix(new CellState[][]{
				{DIEING, BEARING, DEAD},
				{DEAD, DIEING, ALIVE},
				{ALIVE, ALIVE, ALIVE}});
		Matrix m2 = new Matrix(new CellState[][]{
				{DEAD, ALIVE, DEAD},
				{DEAD, DEAD, ALIVE},
				{ALIVE, DIEING, ALIVE}});
		Matrix m3 = new Matrix(new CellState[][]{
				{DEAD, DIEING, DEAD},
				{BEARING, DEAD, ALIVE},
				{DIEING, ALIVE, ALIVE}});

		Object[][] params = new Object[][]{
				{m0, m1},
				{m1, m2},
				{m2, m3},
		};
		return Arrays.asList(params);
	}

	@Test
	public void neighborsTest() {
		assertEquals(stateAfter, calcNextStep(stateBefore));
	}
}
