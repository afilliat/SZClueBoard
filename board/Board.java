package board;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import board.RoomCell.DoorDirection;

public class Board {
	
	private Map<Integer, ArrayList<Integer>> adjs = new HashMap<Integer, ArrayList<Integer>>(); 
	private HashSet<BoardCell> moves = new HashSet<BoardCell>();
	private ArrayList<Integer> visited = new ArrayList<Integer>();
	private HashMap<Character, String> rooms = new HashMap<Character, String>();
	public ArrayList<BoardCell> cells = new ArrayList<BoardCell>();
	private String boardfile;
	private String roomsfile;
	
	public Board() {
		super();
		boardfile = "boardnoindex.csv";
		roomsfile = "legend.csv";
		loadConfigFiles();
		calcAdjacencies();
	}
	
	public Board(String file1, String file2) {
		boardfile = file1;
		roomsfile = file2;
		loadConfigFiles();
		calcAdjacencies();
	}
	
	public void loadConfigFiles() {
		loadRoomConfig(roomsfile);
		loadBoardConfig(boardfile);
	}
	
	public void loadRoomConfig(String fileName) {
		BufferedReader CSVFile;
		  
		try {
			CSVFile = new BufferedReader(new FileReader(fileName));
			rooms.clear();
			String dataRow = CSVFile.readLine(); // Read first line.
			Character initial;
			String room;

			while(dataRow != null){

				initial = dataRow.charAt(0);
				room = dataRow.substring(2);
				rooms.put(initial, room);
				dataRow = CSVFile.readLine();
				}
			  
			CSVFile.close(); // Close the file once all data has been read.
		} catch (Exception e) {
			System.out.println("EXCEPTION");
			System.out.println(e);
		}
	}
	
	public void loadBoardConfig(String fileName) {
		BufferedReader CSVFile;
		  
		try {
			CSVFile = new BufferedReader(new FileReader(fileName));		
			String dataRow = CSVFile.readLine(); // Read first line.
  
			  for(int row = 0; dataRow != null; row++) {
				  String[] thisRow = dataRow.split(",");
				  int col;
				  for(col = 0; col < thisRow.length; col++ ) {
					  if(!thisRow[col].equals("W")) {
						  RoomCell room = new RoomCell(row, col, thisRow[col]);
						  cells.add(room);
					  } else {
						  cells.add(new WalkwayCell(row, col));
					  }
				  }
				  //if (row != this.getNumRows()) {
					//  if (!(col == this.getNumColumns())) {
						  //System.out.println(this.getNumRows());
						  //System.out.println(row);
						  //System.out.println("cols " + col);
						  //System.out.println("columns " + this.getNumColumns());
						  //System.out.println("Check exception");
					//	  throw new BadConfigFormatException();
					 // }
				  //}
				  dataRow = CSVFile.readLine(); // Read next line of data.
			  }
			  CSVFile.close(); // Close the file once all data has been read.
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void calcAdjacencies() {
		int index;
		for (index = 0; index <= calcIndex(getNumRows(), getNumColumns()); index ++ ) {
			ArrayList<Integer> spots = new ArrayList<Integer>();
			if (cells.get(index).getType() == "W" || cells.get(index).isDoorway()) {
				if ((index+1) % (getNumColumns()) != 0) {
					if (cells.get(index + 1).getType() == "W" || ((cells.get(index + 1).isDoorway()) && cells.get(index + 1).getDoorDirection() == DoorDirection.LEFT )){
						spots.add(index + 1);
					}
				}
				if (index % getNumColumns() != 0){
					if (cells.get(index - 1).getType() == "W" || ((cells.get(index - 1).isDoorway()) && cells.get(index - 1).getDoorDirection() == DoorDirection.RIGHT )) {
						spots.add(index - 1);
					}
				}
				if ((index + this.getNumColumns()) <= calcIndex(this.getNumRows(), this.getNumColumns())){
					if (cells.get(index + this.getNumColumns()).getType() == "W" || ((cells.get(index + this.getNumColumns()).isDoorway()) && cells.get(index + this.getNumColumns()).getDoorDirection() == DoorDirection.UP )) {
						spots.add(index + this.getNumColumns());
					}
				}
				if (index - this.getNumColumns() >= 0){
					if (cells.get(index - this.getNumColumns()).getType() == "W" || ((cells.get(index - this.getNumColumns()).isDoorway()) && cells.get(index - this.getNumColumns()).getDoorDirection() == DoorDirection.DOWN )) {
						spots.add(index - this.getNumColumns());
					}
				}
			}	
			adjs.put(index, spots);
			//System.out.println("Cell indexed " + index + " " + adjs.get(index));
		}
	}
	
	public void startTargets(int row, int column, int numSteps){
		int index = calcIndex(row, column);
		visited.clear();
		visited.add(index);
		moves.clear();
		calcTargets(index, numSteps);
	}
	
	public void calcTargets(int index, int numSteps) {
		ArrayList<Integer> possibleSpots = new ArrayList<Integer>();
		for (Integer i : getAdjList(index)) {
			if (!(visited.contains(i))) {
				possibleSpots.add(i);
			}
		}
		
		for (Integer j: possibleSpots) {
			visited.add(j);
			if (this.getCellAt(j).isDoorway())
				moves.add(this.getCellAt(j));
			else if (numSteps == 1) {
				moves.add(this.getCellAt(j));
				visited.remove(j);
			}
			else {		
				calcTargets(j, numSteps - 1);
				visited.remove(j);
			}
		}
	}
		
	public HashSet<BoardCell> getTargets(){
		return moves;
	}
	
	public ArrayList<Integer> getAdjList(Integer index) {
		return adjs.get(index);
	}
	
	public int getNumRows() {
		  BufferedReader CSVboard;
		  int counterRows = 0;
		  
		try {
			CSVboard = new BufferedReader(new FileReader(boardfile));
			
			String dataRow = CSVboard.readLine(); // Read first line.

			  while (dataRow != null){
			      dataRow = CSVboard.readLine(); // Read next line of data.
			      counterRows++;				 // counts rows of the board
			  }
			  
			  CSVboard.close(); // Close the file once all data has been read.
			  return counterRows; //remove one to compensate for the row of numbers
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}	
	}
	
	public int getNumColumns() {
		  BufferedReader CSVboard;
		  int counterColumns = 0;
		  
		try {
			CSVboard = new BufferedReader(new FileReader(boardfile));
			
			String dataRow = CSVboard.readLine(); // Read first line.
			
			if (dataRow != null) {
				char[] firstRow = dataRow.toCharArray(); //converts line to character array.
				for (char c : firstRow)
					if (c == ',')
						counterColumns++;				//counts number of column separations
				counterColumns++;						//adds one more for the final column
			}
			CSVboard.close(); // Close the file once all data has been read.
			return counterColumns; // remove one to compensate for the column of numbers.
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}	  	
	}
	
	public RoomCell getRoomCellAt(int row, int column) {
		int index = calcIndex(row, column);
		RoomCell room = new RoomCell(cells.get(index));
		return room;
	}
	
	public WalkwayCell getWalkwayCellAt(int row, int column) {
		int index = calcIndex(row, column);
		WalkwayCell walk = new WalkwayCell(cells.get(index));
		return walk;
	}
	
	public BoardCell getCellAt(int index) {
		return cells.get(index);
	}
	
	public Map<Character, String> getRooms(String fileName) {	
		return rooms;
	}

	public int calcIndex(int row, int column) {
		if (row == 0)
			return column;
		else if (row == this.getNumRows() && column == this.getNumColumns())
			return calcIndex(getNumRows()-1, getNumColumns()-1);
		else 
			return (row*getNumColumns())+column;
	}
	
	public static void main(String args[]) {
		Board b = new Board();
		System.out.println(b.getAdjList(6));
		System.out.println(b.getAdjList(493));
		System.out.println(b.getAdjList(b.calcIndex(6,15)));
		System.out.println(b.getAdjList(b.calcIndex(4,9)));
		
	}
}
