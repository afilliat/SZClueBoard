package board;

import board.RoomCell.DoorDirection;

//import board.RoomCell.DoorDirection;

public class BoardCell {

	private int row;
	private int col;
	private String type;
	
	
	public Boolean isDoorway() {
		RoomCell room =  new RoomCell(this);
		return room.isDoorway();
	}

	
	public DoorDirection getDoorDirection() {
		RoomCell room =  new RoomCell(this);
		return room.getDoorDirection();
	}
	
	
	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public String getType() {
		return type;
	}

	@Override
	public String toString() {
		return "BoardCell [row=" + row + ", col=" + col + ", type=" + type
				+ "]";
	}
	
	
	
	
}
