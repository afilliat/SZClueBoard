package tests;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import board.Board;

public class testBoard {

	private Board board;
	
	@Before 
	public void setUp() { //create board for use in the tests.
		board = new Board();
	}

	@Test
	public void testCalcIndex() {  //ensures the calcIndex function gets the correct cells
		long index = board.calcIndex(6, 3);
		long expected = 141;
		Assert.assertEquals(index, expected);
		index = board.calcIndex(18, 15);
		expected = 429;
		Assert.assertEquals(index, expected);
	}
	
	@Test
	public void testRoomLegendFormat() { //tests that the legend file is formatted properly
		  BufferedReader CSVLegend;
		  
		try {
			CSVLegend = new BufferedReader(new FileReader("legend.csv"));
			
			String dataRow = CSVLegend.readLine(); // Read first line.

			  while (dataRow != null){
				  Assert.assertEquals(',' , dataRow.charAt(1)); //Ensures only one character before the comma.
			      dataRow = CSVLegend.readLine(); // Read next line of data.
			  }
			  
			  CSVLegend.close(); // Close the file once all data has been read.
		} catch (Exception e) {
			fail("Exception.");
			System.out.println(e.getMessage());
		}	  
	}
	
	@Test
	public void testRoomLegendlength() { // tests for correct number of entries in the legend.
		  BufferedReader CSVLegend;
		  long counter = 0;
		  
		try {
			CSVLegend = new BufferedReader(new FileReader("legend.csv"));
			
			String dataRow = CSVLegend.readLine(); // Read first line.

			  while (dataRow != null){
			      dataRow = CSVLegend.readLine(); // Read next line of data.
			      counter++;
			  }
			  
			  CSVLegend.close(); // Close the file once all data has been read.
		} catch (Exception e) {
			fail("Exception.");
			System.out.println(e.getMessage());
		}	  
		Assert.assertEquals(11, counter);
	}
	
	@Test
	public void testBoardDimensions() { //ensures the board has reasonable dimensions.
		  BufferedReader CSVboard;
		  long counterRows = 0;
		  long counterColumns = 0;
		  
		try {
			CSVboard = new BufferedReader(new FileReader("board.csv"));
			
			String dataRow = CSVboard.readLine(); // Read first line.
			char[] firstRow = dataRow.toCharArray(); //converts line to character array.
		    for (char c : firstRow)
		    	if (c == ',')
		    		counterColumns++;				//counts number of column separations
		    counterColumns++;						//adds one more for the final column

			  while (dataRow != null){
			      dataRow = CSVboard.readLine(); // Read next line of data.
			      counterRows++;				 // counts rows of the board
			  }
			  
			  CSVboard.close(); // Close the file once all data has been read.
		} catch (Exception e) {
			fail("Exception.");
			System.out.println(e.getMessage());
		}	  
		
		if (counterRows > 30)
			fail("Too many rows.");
		
		if (counterRows < 10)
			fail("Too few rows.");
			
		if (counterColumns > 30) 
			fail("Too many columns.");
		
		if (counterColumns < 10)
			fail("Too few columns.");
	}
	
	@Test
	public void testRoomInitals() { //Tests to see if cell labels are read correctly.
		BufferedReader CSVFile;
		  
		try {
			CSVFile = new BufferedReader(new FileReader("board.csv"));
			int rowCounter = 0;
			
			String dataRow = CSVFile.readLine(); // Read first line.

			  while (dataRow != null){
				  if (rowCounter == 7)
					  break;
				  dataRow = CSVFile.readLine(); // Read next line of data.
				  rowCounter++;
			  }
			  
			  char[] stuff = dataRow.toCharArray();
			  Assert.assertEquals('L' , stuff[18+17+1]);
			  //18 for the desired cell 17 for commas 1 because it starts at zero
			  
			  while (dataRow != null){
				   if (rowCounter == 20)
					   break;
				   dataRow = CSVFile.readLine(); // Read next line of data.
				   rowCounter++;
				  }
				  
			  CSVFile.close(); // Close the file once all data has been read.
			  stuff = dataRow.toCharArray();
			  Assert.assertEquals('M' , stuff[11+10+1+1]);
			  //11 for the desired cell 10 for commas 1 because it starts at zero and 1 for the door in this row.
			  
		} catch (Exception e) {
			fail("Exception.");
			System.out.println(e.getMessage());
		}	
	}
	
	@Test
	public void testDoorLabelReading() { //Tests to make sure door labels are read correctly.
		BufferedReader CSVFile;
		  
		try {
			CSVFile = new BufferedReader(new FileReader("board.csv"));
			int rowCounter = 0;
			
			String dataRow = CSVFile.readLine(); // Read first line.

			  while (dataRow != null){
				  if (rowCounter == 6)
					  break;
				  dataRow = CSVFile.readLine(); // Read next line of data.
				  rowCounter++;
			  }
			  
			  char[] stuff = dataRow.toCharArray();
			  Assert.assertEquals('L' , stuff[21+20+1]);
			  //21 for the desired cell 20 for commas 1 because it starts at zero
			  Assert.assertEquals('U' , stuff[21+20+1+1]);
			  //21 for the desired cell 20 for commas 1 because it starts at zero 1 for the second half of the door
			  
			  while (dataRow != null){
				   if (rowCounter == 20)
					   break;
				   dataRow = CSVFile.readLine(); // Read next line of data.
				   rowCounter++;
				  }
				  
			  CSVFile.close(); // Close the file once all data has been read.
			  stuff = dataRow.toCharArray();
			  Assert.assertEquals('C' , stuff[5+4+1]);
			  //5 for the desired cell 4 for commas 1 because it starts at zero
			  Assert.assertEquals('R' , stuff[5+4+1+1]);
			  //5 for the desired cell 4 for commas 1 because it starts at zero 1 for the second half of the door
			  
		} catch (Exception e) {
			fail("Exception.");
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	 public void testCatchFileNotFoundException() { //Test to make sure an exception is thrown on a non-existent file.
		  BufferedReader CSVFile;
		  
		try {
			CSVFile = new BufferedReader(new FileReader("nofile.csv"));		
			String dataRow = CSVFile.readLine(); // Read first line.

			  while (dataRow != null){
			      dataRow = CSVFile.readLine(); // Read next line of data.
			  }
			  
			  CSVFile.close(); // Close the file once all data has been read.
			  fail("Did not catch file not found");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}	 
	 }
	
	@Test
	public void testArrayIndexOutOfBounds() { // Ensures an exception is thrown in an attempt to access non-existent items of an array.
		BufferedReader CSVFile;
		char[] testArray = null;
		int rowCounter = 0;
			
		try{
			 CSVFile = new BufferedReader (new FileReader("bad.csv"));
			 
			 String dataRow = CSVFile.readLine(); // Read first line.

			  while (dataRow != null){
				  if (rowCounter == 6)
					  break;
				  dataRow = CSVFile.readLine(); // Read next line of data.
				  rowCounter++;
			  }
			 
			  testArray = dataRow.toCharArray();
			 
		} catch (Exception e) {
			e.getMessage();	
		}
		
		try{
			
			System.out.println(testArray[20]);
			fail("Did not catch out of bounds");
			
		} catch (ArrayIndexOutOfBoundsException e){
			e.getMessage();
		}	
	}
}
