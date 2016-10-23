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
	GameField stateBefore;
	GameField stateAfter;

	public GameOfLifeNextStepTest(GameField stateBefore, GameField stateAfter) {
		this.stateBefore = stateBefore;
		this.stateAfter = stateAfter;
	}

	@Parameterized.Parameters
	public static Collection<Object[]> data() {
		GameField gf0 = new GameField(new CellState[][]{
				{ALIVE, DEAD, DEAD},
				{DEAD, ALIVE, ALIVE},
				{DIEING, DIEING, BEARING}});
		GameField gf1 = new GameField(new CellState[][]{
				{DIEING, BEARING, DEAD},
				{DEAD, DIEING, ALIVE},
				{ALIVE, ALIVE, ALIVE}});
		GameField gf2 = new GameField(new CellState[][]{
				{DEAD, ALIVE, DEAD},
				{DEAD, DEAD, ALIVE},
				{ALIVE, DIEING, ALIVE}});
		GameField gf3 = new GameField(new CellState[][]{
				{DEAD, DIEING, DEAD},
				{BEARING, DEAD, ALIVE},
				{DIEING, ALIVE, ALIVE}});

		Object[][] params = new Object[][]{
				{gf0, gf1},
				{gf1, gf2},
				{gf2, gf3},
		};
		return Arrays.asList(params);
	}

	@Test
	public void neighborsTest() {
		assertEquals(stateAfter, calcNextStep(stateBefore));
	}
}
