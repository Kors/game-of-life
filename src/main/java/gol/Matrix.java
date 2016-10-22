package gol;

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

	private void initField(CellState[][] stateMatrix) {
		gameField = new CellState[rows][columns];
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < columns; col++) {
				gameField[row][col] = stateMatrix[row][col];
			}
		}
	}

	public CellState[][] getGameField() {
		return gameField;
	}
}
