package gol;

import static gol.CellState.*;

/**
 * @author K.ilya
 */
public class GameOfLife {
	// transitional matrix
	private static final CellState[][] nextState = {
			{DEAD, DEAD, DEAD, BEARING, DEAD, DEAD, DEAD, DEAD, DEAD}, // in DEAD state
			{DIEING, DIEING, ALIVE, ALIVE, DIEING, DIEING, DIEING, DIEING, DIEING}, // in ALIVE state
			{DEAD, DEAD, ALIVE, ALIVE, DEAD, DEAD, DEAD, DEAD, DEAD}, // in DIEING state
			{DEAD, DEAD, ALIVE, ALIVE, DEAD, DEAD, DEAD, DEAD, DEAD}, // in BEARING state
	};

	static CellState getNextCellState(CellState state, int neighboursCount) {
		return nextState[state.ordinal()][neighboursCount];
	}

	static int getNeighboursCount(CellState[][] state, int row, int col) {
		int sumNeighbours = 0;
		for (int rowShift = -1; rowShift <= 1; rowShift++) {
			for (int colShift = -1; colShift <= 1; colShift++) {
				sumNeighbours += isAlive(state, row + rowShift, col + colShift);
			}
		}
		return sumNeighbours - isAlive(state, row, col);
	}

	private static int isAlive(CellState[][] state, int row, int col) {
		if (row < 0 || row >= state.length)
			return 0;
		if (col < 0 || col >= state[row].length)
			return 0;
		CellState cs = state[row][col];
		return ALIVE.equals(cs) ? 1 : 0;
	}
}
