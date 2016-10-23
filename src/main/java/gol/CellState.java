package gol;

/**
 * @author K.ilya
 */
public enum CellState {
	DEAD, ALIVE, DIEING, BEARING, UNKNOWN;

	public static CellState valueOf(int stateNum) {
		for (CellState val : CellState.values())
			if (val.ordinal() == stateNum)
				return val;
		return UNKNOWN;
	}
}
