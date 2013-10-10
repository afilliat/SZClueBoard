package board;

public class RoomCell extends BoardCell {

	private Boolean isDoor = false;
	public enum DoorDirection {UP,DOWN,LEFT,RIGHT,NONE};
	DoorDirection dd;
	private String type;
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
	}

	public RoomCell(int row, int col, String str) {
		this.row = row;
		this.col = col;
		this.type = str;
	}

	public Boolean isDoorway() {
		if (type.length() != 1) {
			isDoor = true;
		} 
			return isDoor;
	}
	
	public void setDoorDirection() {
		if (type.length() != 1) {
			if(type.charAt(1) == 'R')
				dd = DoorDirection.RIGHT;
			if (type.charAt(1) == 'L')
				dd = DoorDirection.LEFT;
			if (type.charAt(1) == 'D')
				dd = DoorDirection.DOWN;
			if (type.charAt(1) == 'U')
				dd = DoorDirection.UP;
			if (type.charAt(1) == 'N')
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
		type = temp;
	}

	public String getType() {
		return type;
	}
	
	@Override
	public String toString() {
		return "RoomCell [row=" + row + ", col=" + col + ", type=" + type
				+ "]";
	}
	
}
