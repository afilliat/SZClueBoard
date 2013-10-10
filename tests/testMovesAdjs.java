package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import board.Board;
import board.BoardCell;

public class testMovesAdjs {

	private static Board board;
	
	@BeforeClass 
	public static void setUp() { //create board for use in the tests.
		board = new Board();
	}
	
	@Test
	public void testAdjWithAllWalkways() {
		int index = board.calcIndex(11, 16);
		ArrayList<Integer> testList = board.getAdjList(index);
		Assert.assertTrue(testList.contains(index + 1));
		Assert.assertTrue(testList.contains(index - 1));
		Assert.assertTrue(testList.contains(board.calcIndex(12, 16)));
		Assert.assertTrue(testList.contains(board.calcIndex(10, 16)));
		Assert.assertEquals(4, testList.size());
	}
	
	@Test
	public void testAdjOnBoardEdge() {
		int index = board.calcIndex(6, 0);
		ArrayList<Integer> testList = board.getAdjList(index);
		Assert.assertTrue(testList.contains(index + 1));
		Assert.assertTrue(testList.contains(board.calcIndex(5, 0)));
		Assert.assertEquals(2, testList.size());
		index = board.calcIndex(0, 7);
		testList = board.getAdjList(index);
		Assert.assertTrue(testList.contains(index - 1));
		Assert.assertTrue(testList.contains(board.calcIndex(1, 7)));
		Assert.assertEquals(2, testList.size());
		index = board.calcIndex(4, 22);
		testList = board.getAdjList(index);
		Assert.assertTrue(testList.contains(index - 1));
		Assert.assertTrue(testList.contains(board.calcIndex(5, 22)));
		Assert.assertEquals(2, testList.size());
		index = board.calcIndex(21, 12);
		testList = board.getAdjList(index);
		Assert.assertTrue(testList.contains(index + 1));
		Assert.assertTrue(testList.contains(index - 1));
		Assert.assertEquals(2, testList.size());
	}
	
	@Test
	public void testAdjBesideRooms() {
		int index = board.calcIndex(4, 9);
		ArrayList<Integer> testList = board.getAdjList(index);
		Assert.assertTrue(testList.contains(index - 1));
		Assert.assertTrue(testList.contains(board.calcIndex(5, 9)));
		Assert.assertEquals(2, testList.size());
		index = board.calcIndex(12, 18);
		testList = board.getAdjList(index);
		Assert.assertTrue(testList.contains(index - 1));
		Assert.assertTrue(testList.contains(index + 1));
		Assert.assertTrue(testList.contains(board.calcIndex(11, 18)));
		Assert.assertEquals(3, testList.size());
	}
	
	@Test
	public void testAdjBesideDoorWithNeededDirection() {
		int index = board.calcIndex(5, 15);
		ArrayList<Integer> testList = board.getAdjList(index);
		Assert.assertTrue(testList.contains(index - 1));
		Assert.assertTrue(testList.contains(index + 1));
		Assert.assertTrue(testList.contains(board.calcIndex(4, 15)));
		Assert.assertTrue(testList.contains(board.calcIndex(6, 15)));
		Assert.assertEquals(4, testList.size());
		index = board.calcIndex(12, 6);
		testList = board.getAdjList(index);
		Assert.assertTrue(testList.contains(index - 1));
		Assert.assertTrue(testList.contains(index + 1));
		Assert.assertTrue(testList.contains(board.calcIndex(11, 6)));
		Assert.assertTrue(testList.contains(board.calcIndex(13, 6)));
		Assert.assertEquals(4, testList.size());
	}
	
	@Test
	public void testAdjInDoorway() {
		int index = board.calcIndex(3, 18);
		ArrayList<Integer> testList = board.getAdjList(index);
		//Assert.assertTrue(testList.contains(index + 1));
		Assert.assertTrue(testList.contains(board.calcIndex(4, 18)));
		//Assert.assertTrue(testList.contains(index - 1));
		//Assert.assertTrue(testList.contains(board.calcIndex(2, 18)));
		Assert.assertEquals(1, testList.size());
		index = board.calcIndex(17, 8);
		testList = board.getAdjList(index);
		Assert.assertTrue(testList.contains(index - 1));
		//Assert.assertTrue(testList.contains(index + 1));
		//Assert.assertTrue(testList.contains(board.calcIndex(16, 8)));
		//Assert.assertTrue(testList.contains(board.calcIndex(18, 8)));
		//Assert.assertEquals(4, testList.size());
	}
	
	@Test
	public void testMovesOnWalkway() {
		//int index = board.calcIndex(6, 7);
		//board.calcTargets(index, 1);
		HashSet<BoardCell> testList = board.getTargets();
		
		board.startTargets(6,7,1);
		Assert.assertTrue(testList.contains(board.cells.get(board.calcIndex(6,8))));
		Assert.assertTrue(testList.contains(board.cells.get(board.calcIndex(5, 7))));
		Assert.assertTrue(testList.contains(board.cells.get(board.calcIndex(6, 6))));
		Assert.assertTrue(testList.contains(board.cells.get(board.calcIndex(7, 7))));
		Assert.assertEquals(4, testList.size());
		
		board.startTargets(4, 16, 2);
		Assert.assertTrue(testList.contains(board.cells.get(board.calcIndex(2, 16))));
		Assert.assertTrue(testList.contains(board.cells.get(board.calcIndex(3, 15))));
		Assert.assertTrue(testList.contains(board.cells.get(board.calcIndex(5, 15))));
		Assert.assertTrue(testList.contains(board.cells.get(board.calcIndex(6, 16))));
		Assert.assertTrue(testList.contains(board.cells.get(board.calcIndex(5, 17))));
		Assert.assertTrue(testList.contains(board.cells.get(board.calcIndex(4, 18))));
		Assert.assertEquals(6, testList.size());
		
		board.startTargets(16, 6, 2);
		Assert.assertTrue(testList.contains(board.cells.get(board.calcIndex(17, 5))));
		Assert.assertTrue(testList.contains(board.cells.get(board.calcIndex(18, 6))));
		Assert.assertTrue(testList.contains(board.cells.get(board.calcIndex(17, 7))));
		Assert.assertTrue(testList.contains(board.cells.get(board.calcIndex(15, 7))));
		Assert.assertTrue(testList.contains(board.cells.get(board.calcIndex(14, 6))));
		Assert.assertEquals(5, testList.size());
		
		board.startTargets(15, 15, 3);
		Assert.assertTrue(testList.contains(board.cells.get(board.calcIndex(12, 15))));
		Assert.assertTrue(testList.contains(board.cells.get(board.calcIndex(13, 16))));
		Assert.assertTrue(testList.contains(board.cells.get(board.calcIndex(14, 15))));
		Assert.assertTrue(testList.contains(board.cells.get(board.calcIndex(15, 16))));
		Assert.assertTrue(testList.contains(board.cells.get(board.calcIndex(16, 15))));
		Assert.assertTrue(testList.contains(board.cells.get(board.calcIndex(17, 16))));
		Assert.assertTrue(testList.contains(board.cells.get(board.calcIndex(18, 15))));
		Assert.assertTrue(testList.contains(board.cells.get(board.calcIndex(15, 14))));
		Assert.assertTrue(testList.contains(board.cells.get(board.calcIndex(14, 13))));
		Assert.assertTrue(testList.contains(board.cells.get(board.calcIndex(15, 12))));
		Assert.assertTrue(testList.contains(board.cells.get(board.calcIndex(14, 17))));
		Assert.assertEquals(11, testList.size());
		
	
	}
	
	@Test
	public void testMovesAllowRoomEntry() {
		HashSet<BoardCell> testList = board.getTargets();
		
		board.startTargets(18, 1, 3);
		Assert.assertTrue(testList.contains(board.cells.get(board.calcIndex(18, 0))));
		Assert.assertTrue(testList.contains(board.cells.get(board.calcIndex(17, 1))));
		Assert.assertTrue(testList.contains(board.cells.get(board.calcIndex(18, 2))));
		Assert.assertTrue(testList.contains(board.cells.get(board.calcIndex(17, 3))));
		Assert.assertTrue(testList.contains(board.cells.get(board.calcIndex(18, 4))));
		Assert.assertTrue(testList.contains(board.cells.get(board.calcIndex(16, 1))));
		Assert.assertEquals(6,  testList.size());
		
		board.startTargets(20, 15, 2);
		Assert.assertTrue(testList.contains(board.cells.get(board.calcIndex(18, 15))));
		Assert.assertTrue(testList.contains(board.cells.get(board.calcIndex(19, 16))));
		Assert.assertTrue(testList.contains(board.cells.get(board.calcIndex(21, 16))));
		Assert.assertTrue(testList.contains(board.cells.get(board.calcIndex(21, 14))));
		Assert.assertTrue(testList.contains(board.cells.get(board.calcIndex(20,14))));
		Assert.assertEquals(5,  testList.size());
		
	}
	
	@Test
	public void testMovesAllowRoomExit() {
		board.startTargets(19, 21, 2);
		HashSet<BoardCell> testList = board.getTargets();
		Assert.assertTrue(testList.contains(board.cells.get(board.calcIndex(18, 22))));
		Assert.assertTrue(testList.contains(board.cells.get(board.calcIndex(17, 21))));
		Assert.assertTrue(testList.contains(board.cells.get(board.calcIndex(18, 20))));
		Assert.assertEquals(3, testList.size());
		board.startTargets(6, 21, 3);
		testList = board.getTargets();
		Assert.assertTrue(testList.contains(board.cells.get(board.calcIndex(4, 22))));
		Assert.assertTrue(testList.contains(board.cells.get(board.calcIndex(4, 20))));
		Assert.assertTrue(testList.contains(board.cells.get(board.calcIndex(5, 19))));
		Assert.assertEquals(3, testList.size());
	}
}
