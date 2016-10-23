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
	int stepNumber;

	void initGameField(int[][] m) {
		stepNumber = 0;
		oldGameField = null;
		gameField = new GameField(m);
	}

	void moveOn() {
		oldGameField = gameField;
		gameField = calcNextStep(gameField);
		stepNumber++;
		repaint();
	}

	private void repaint() {
		gameField.show();
		System.out.println();
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

	public static void main(String[] args) {
		GameOfLife gol = new GameOfLife();
		int[][] startPosition = gol.getStartField();
		gol.initGameField(startPosition);
		while (gol.gameField != null && !gol.gameField.equals(gol.oldGameField))
			gol.moveOn();
		System.out.println("The end. Last generation ¹" + gol.stepNumber);
	}

	private int[][] getStartField() {
		return new int[][]{
				{0, 1, 1, 0, 1, 1},
				{2, 1, 3, 3, 2, 0},
				{3, 1, 2, 0, 2, 1},
				{2, 0, 1, 1, 2, 0},
		};
	}
}
