package ro.Personal.snake;

import java.awt.Color;
import java.awt.Graphics2D;

public class SnakeMarble implements IPaintable {

	private int row;
	private int col;
	private boolean isHead;
	
	public SnakeMarble(int row, int col) {
		this(row, col, false);
	}
	
	public SnakeMarble(int row, int col, boolean isHead) {
		this.row = row;
		this.col = col;
		this.isHead = isHead;
	}
	
	@Override
	public void paint(Graphics2D graphics) {
		// This is where we will paint a marble
		Color marbleColor = isHead ? Color.RED : Color.GREEN;
		graphics.setColor(marbleColor);
		int x = this.col * GameBoard.CELL_SIZE_PX + GameBoard.OFFSET_LEFT_PX;
		int y = this.row * GameBoard.CELL_SIZE_PX + GameBoard.OFFSET_TOP_PX;
		graphics.fillOval(x, y, GameBoard.CELL_SIZE_PX, GameBoard.CELL_SIZE_PX);
		
		
	}
	
	public int getRow() {
		return this.row;
	}
	
	public void setRow(int row) {
		this.row = row;
	}
	
	public int getCol() {
		return this.col;
	}
	
	public void setCol(int col) {
		this.col = col;
	}
	
	public void decrementRow() {
		this.row = this.row - 1;
	}
	
	public void decrementCol() {
		this.col--;
	}
	
	public void incrementRow() {
		this.row ++;
	}
	
	public void incrementCol() {
		this.col++;
	}
}
