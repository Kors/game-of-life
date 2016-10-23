package gol;

import static gol.CellState.ALIVE;
import static gol.CellState.DEAD;
import static gol.CellState.DIEING;

/**
 * @author K.ilya
 */
public class GameField implements Cloneable {
	private CellState[][] matrix;
	private int rows;
	private int columns;

	GameField(CellState[][] stateMatrix) {
		this.rows = stateMatrix.length;
		this.columns = stateMatrix[0].length;  // TODO should be better ?!
		initField(stateMatrix);
	}

	public GameField(int[][] stateCodes) {
		this.rows = stateCodes.length;
		this.columns = stateCodes[0].length;  // TODO should be better ?!
		initEmptyField();
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < columns; col++) {
				matrix[row + 1][col + 1] = CellState.valueOf(stateCodes[row][col]);
			}
		}
	}

	private void initField(CellState[][] stateMatrix) {
		initEmptyField();
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < columns; col++) {
				matrix[row + 1][col + 1] = stateMatrix[row][col];
			}
		}
	}

	private void initEmptyField() {
		matrix = new CellState[rows + 2][columns + 2];
		for (int row = 0; row < rows + 2; row++) {
			for (int col = 0; col < columns + 2; col++) {
				matrix[row][col] = DEAD;
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
		return ALIVE.equals(matrix[row][col]) ||
				DIEING.equals(matrix[row][col]);
	}

	public GameField clone() throws CloneNotSupportedException {
		GameField gameField = (GameField) super.clone();
		gameField.matrix = this.matrix.clone();
		return gameField;
	}

	public boolean equals(Object o) {
		if (!(o instanceof GameField))
			return false;
		GameField m = (GameField) o;
		return rows == m.rows &&
				columns == m.columns &&
				fieldEqual(m.matrix);
	}

	private boolean fieldEqual(CellState[][] field) {
		for (int row = 0; row < rows + 2; row++) {
			for (int col = 0; col < columns + 2; col++) {
				if (!matrix[row][col].equals(field[row][col])) {
					return false;
				}
			}
		}
		return true;
	}

	public int hashCode() {
		int hashCode = rows * 37 + columns;
		for (CellState[] line : matrix) {
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
		return matrix[row + 1][col + 1];
	}

	public void show() {
		for (int row = 1; row < rows + 2; row++) {
			for (int col = 1; col < columns + 2; col++) {
				System.out.print(matrix[row][col] + "\t ");
			}
			System.out.println();
		}
	}
}
