package board;

public class WalkwayCell extends BoardCell {

	private int row;
	private int col;
	private char type = 'W';
	private String input = "W";
	
	public WalkwayCell() {
		super();
	}

	public WalkwayCell(BoardCell cell) {
		this.row = cell.getRow();
		this.col = cell.getCol();
	}

	public WalkwayCell(int row, int col) {
		super();
		this.row = row;
		this.col = col;
	}
	
	public char getType(){
		return type;
	}
	
	public String getInput(){
		return input;
	}
	
	
	public Boolean isDoorway(){
		return false;
	}
	
	@Override
	public String toString() {
		return "WalkwayCell [row=" + row + ", col=" + col + ", type=" + type
				+ "]";
	}
	
	
}
