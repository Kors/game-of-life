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
}
