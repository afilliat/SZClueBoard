package board;

public class RoomCell extends BoardCell {

	private Boolean isDoor = false;
	public enum DoorDirection {UP,DOWN,LEFT,RIGHT,NONE};
	DoorDirection dd;
	private char type;
	private String input;
	private int row;
	private int col;

	public RoomCell() {
		super();
	}

	public RoomCell (int row, int column) {
		this.row = row;
		this.col = column;
	}

	public RoomCell(BoardCell cell) {
		this.row = cell.getRow();
		this.col = cell.getCol();
		this.type = cell.getType();
		this.input = cell.getInput();
	}

	public RoomCell(int row, int col, String str) {
		this.row = row;
		this.col = col;
		this.input = str;
		this.type = str.charAt(0);
	}

	public Boolean isDoorway() {
		if (input.length() != 1) {
			if(input.charAt(1) == 'N'){
				return isDoor;
			} else{
				isDoor = true;
			}
		} 
		return isDoor;
	}

	public void setDoorDirection() {
		if (input.length() != 1) {
			if(input.charAt(1) == 'R')
				dd = DoorDirection.RIGHT;
			if (input.charAt(1) == 'L')
				dd = DoorDirection.LEFT;
			if (input.charAt(1) == 'D')
				dd = DoorDirection.DOWN;
			if (input.charAt(1) == 'U')
				dd = DoorDirection.UP;
			if (input.charAt(1) == 'N')
				dd = DoorDirection.NONE;
		}
	}

	public DoorDirection getDoorDirection() {
		setDoorDirection();
		if (isDoor == true) {
			return dd;
		}
		else {return null;}
	}

	public void setType(String temp) {
		input = temp;
		type = temp.charAt(0);
	}

	public char getType() {
		return type;
	}

	public String getInput() {
		return input;
	}

	@Override
	public String toString() {
		return "RoomCell [row=" + row + ", col=" + col + ", type=" + type
				+ "]";
	}

}
