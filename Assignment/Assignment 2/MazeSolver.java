// Name: An Le
// Account: masc0369
// Class: CS 310
// Assignment 2 - Maze Solver

import data_structures.*;

public class MazeSolver {
	private MazeGrid grid;
	private Queue<GridCell> queue;
	private Stack<GridCell> stack;
	private int dimension;
	
	public MazeSolver(int dimension){
		grid = new MazeGrid(this, dimension);
		queue = new Queue<GridCell>();
		stack = new Stack<GridCell>();
		this.dimension = dimension;
	}
	
//	Solving the puzzle is a two step process. First, you must flag each GridCell that is reachable with its distance from the origin. 
//	Then starting at the exit point and working back toward the origin, you select the available cell with the minimum distance. 
//	
//	For the first part, you will use a breadth first traveral algorithm to visit and mark each cell:
//		enqueue cell(0,0)
//		while( the queue is not empty ) {
//		    dequeue a GridCell from the queue.
//			must flag each GridCell that is reachable with its distance from the origin.
//		    mark each adjacent neighboring cell and enqueue it
//		    }
		
	public void mark(){
		//Enqueue the start cell
		GridCell originCell = grid.getCell(0,0);
		originCell.setDistance(0);
		queue.enqueue(originCell);
		grid.markDistance(originCell);
		
		while(!queue.isEmpty()){
			GridCell currentCell = queue.dequeue();
			int x = currentCell.getX();
			int y = currentCell.getY();
			int distance = currentCell.getDistance();
			
			//Enqueue neighboring cell
			GridCell nextCell = grid.getCell(x,y+1);
			if (grid.isValidMove(nextCell) && !nextCell.wasVisited()){
				nextCell.setDistance(distance + 1);
				grid.markDistance(nextCell);
				queue.enqueue(nextCell);
			}
				
			nextCell = grid.getCell(x+1,y);
			if (grid.isValidMove(nextCell) && !nextCell.wasVisited()){
				nextCell.setDistance(distance + 1);
				grid.markDistance(nextCell);
				queue.enqueue(nextCell);
			}
				
			nextCell = grid.getCell(x,y-1);
			if (grid.isValidMove(nextCell) && !nextCell.wasVisited()){
				nextCell.setDistance(distance + 1);
				grid.markDistance(nextCell);
				queue.enqueue(nextCell);
			}
				
			nextCell = grid.getCell(x-1,y);
			if (grid.isValidMove(nextCell) && !nextCell.wasVisited()){
				nextCell.setDistance(distance + 1);
				grid.markDistance(nextCell);
				queue.enqueue(nextCell);
			}
		}
	}
	
//	For the second part, begin at the green exit point, then check each adjacent neighboring cell, 
//	and push the one with minimum distance onto the stack. The stack then contains all of the cells in the shortest path:
//
//	distance = terminal_cell.getDistance();
//	if(distance == -1) return false;  // unreachable, puzzle has no solution
//	push terminal cell onto the stack
//	while(distance != 0) {
//	    get distance from each cell adjacent to the top of the stack
//	    select the cell with smallest distance and push on the stack
//	    }
//	while( stack is not empty ) {
//	    pop grid cell off the stack and mark it
//	    }
	
	public boolean move(){
		//Start from 0,0 so the last cell is d-1,d-1
		GridCell exitCell = grid.getCell(dimension - 1, dimension - 1);
		if (exitCell.getDistance() == -1){
			return false;
		}
		
		stack.push(exitCell);
		
		while(stack.peek().getDistance() != 0){
			GridCell currentCell = stack.peek();
			int x = currentCell.getX();
			int y = currentCell.getY();
			int distance = currentCell.getDistance();
			
			//Continue Keyword skips the current loop but does not end the loop
			GridCell nextCell = grid.getCell(x, y+1);
			if ((grid.isValidMove(nextCell)) && (nextCell.getDistance() < distance)){
				stack.push(nextCell);
				continue;
			}
			
			nextCell = grid.getCell(x+1, y);
			if ((grid.isValidMove(nextCell)) && (nextCell.getDistance() < distance)){
				stack.push(nextCell);
				continue;
			}
			
			nextCell = grid.getCell(x, y-1);
			if ((grid.isValidMove(nextCell)) && (nextCell.getDistance() < distance)){
				stack.push(nextCell);
				continue;
			}
			
			nextCell = grid.getCell(x-1, y);
			if ((grid.isValidMove(nextCell)) && (nextCell.getDistance() < distance)){
				stack.push(nextCell);
				continue;
			}
		}
		
		while(!stack.isEmpty()){
			grid.markMove(stack.pop());
		}
		
		return true;
	}	

	public void reset(){
		queue.makeEmpty();
		stack.makeEmpty();
	}

	public static void main(String[] args){
		new MazeSolver(50);
	}
}
