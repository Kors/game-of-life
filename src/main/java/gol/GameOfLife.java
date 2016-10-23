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

	GameField oldGameField;
	GameField gameField;

	void initGameField(int[][] m) {
		oldGameField = null;
		gameField = new GameField(m);
	}

	void moveOn() {
		oldGameField = gameField;
		gameField = calcNextStep(gameField);
		repaint();
	}

	private void repaint() {
		gameField.show();
	}

	static GameField calcNextStep(GameField before) {
		CellState[][] after = new CellState[before.getRows()][before.getColumns()];
		for (int row = 0; row < before.getRows(); row++) {
			for (int col = 0; col < before.getColumns(); col++) {
				after[row][col] = getNextCellState(before.getCellState(row, col), before.getNeighboursCount(row, col));
			}
		}
		return new GameField(after);
	}

	static CellState getNextCellState(CellState state, int neighboursCount) {
		return nextState[state.ordinal()][neighboursCount];
	}
}
