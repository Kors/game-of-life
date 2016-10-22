package gol;

import static gol.CellState.ALIVE;
import static gol.CellState.DIEING;

/**
 * @author K.ilya
 */
public class Matrix {
	private CellState[][] gameField;
	private int rows;
	private int columns;

	Matrix(CellState[][] stateMatrix) {
		this.rows = stateMatrix.length;
		this.columns = stateMatrix[0].length;
		initField(stateMatrix);
	}

	// TODO init border values!
	private void initField(CellState[][] stateMatrix) {
		gameField = new CellState[rows + 2][columns + 2];
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < columns; col++) {
				gameField[row + 1][col + 1] = stateMatrix[row][col];
			}
		}
	}

	int getNeighboursCount(int row, int col) {
		int sumNeighbours = 0;
		for (int rowShift = -1; rowShift <= 1; rowShift++) {
			for (int colShift = -1; colShift <= 1; colShift++) {
				sumNeighbours += isAlive(row + 1 + rowShift, col + 1 + colShift) ? 1 : 0;
			}
		}
		return sumNeighbours - (isAlive(row + 1, col + 1) ? 1 : 0);
	}

	// internal numbers
	private boolean isAlive(int row, int col) {
		return ALIVE.equals(gameField[row][col]) ||
				DIEING.equals(gameField[row][col]);
	}
}
