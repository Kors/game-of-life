package gol;

import static gol.CellState.ALIVE;
import static gol.CellState.DEAD;
import static gol.CellState.DIEING;

/**
 * @author K.ilya
 */
public class Matrix implements Cloneable {
	private CellState[][] gameField;
	private int rows;
	private int columns;

	Matrix(CellState[][] stateMatrix) {
		this.rows = stateMatrix.length;
		this.columns = stateMatrix[0].length;
		initField(stateMatrix);
	}

	public Matrix(int[][] ints) {
		initEmptyField();
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < columns; col++) {
				gameField[row + 1][col + 1] = CellState.valueOf(ints[row][col]);
			}
		}
	}

	private void initField(CellState[][] stateMatrix) {
		initEmptyField();
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < columns; col++) {
				gameField[row + 1][col + 1] = stateMatrix[row][col];
			}
		}
	}

	private void initEmptyField() {
		gameField = new CellState[rows + 2][columns + 2];
		for (int row = 0; row < rows + 2; row++) {
			for (int col = 0; col < columns + 2; col++) {
				gameField[row][col] = DEAD;
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

	public Matrix clone() throws CloneNotSupportedException {
		Matrix matrix = (Matrix) super.clone();
		matrix.gameField = gameField.clone();
		return matrix;
	}

	public boolean equals(Object o) {
		if (!(o instanceof Matrix))
			return false;
		Matrix m = (Matrix) o;
		return rows == m.rows &&
				columns == m.columns &&
				fieldEqual(m.gameField);
	}

	private boolean fieldEqual(CellState[][] field) {
		for (int row = 0; row < rows + 2; row++) {
			for (int col = 0; col < columns + 2; col++) {
				if (!gameField[row][col].equals(field[row][col])) {
					return false;
				}
			}
		}
		return true;
	}

	public int hashCode() {
		int hashCode = rows * 37 + columns;
		for (CellState[] line : gameField) {
			for (CellState cell : line) {
				hashCode *= 37;
				hashCode += cell.hashCode();
			}
		}
		return hashCode;
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}

	public CellState getCellState(int row, int col) {
		return gameField[row + 1][col + 1];
	}
}
