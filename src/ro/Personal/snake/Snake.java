package ro.Personal.snake;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class Snake implements IPaintable {

	private List<SnakeMarble> marbles;
	private SnakeDirection direction = SnakeDirection.RIGHT;
	private static final byte INITIAL_SIZE = 5;
	
	public Snake() {
		this.initSnakeMarbles();
	}
	
	private void initSnakeMarbles() {
		this.marbles = new ArrayList<SnakeMarble>();
		
		SnakeMarble head = new SnakeMarble(GameBoard.SIZE / 2, GameBoard.SIZE / 2, true);
		this.marbles.add(head);
		
		for (int index = 0; index < INITIAL_SIZE - 1; index++) {
			int col = (GameBoard.SIZE / 2) - (index + 1);
			SnakeMarble marble = new SnakeMarble(GameBoard.SIZE / 2, col);
			this.marbles.add(marble);
		}
	}
	
	@Override
	public void paint(Graphics2D graphics) {
		if (this.marbles == null) {
			return;
		}
		for(int index = 0; index < marbles.size(); index++) {
			SnakeMarble marble = this.marbles.get(index);
			
			marble.paint(graphics);
		}
	}
	
	public void changeDirection(SnakeDirection playerDirection) {
		if(this.direction == SnakeDirection.RIGHT && playerDirection == SnakeDirection.LEFT) {
			return;
		}
		if(this.direction == SnakeDirection.LEFT && playerDirection == SnakeDirection.RIGHT) {
			return;
		}
		if (this.direction == SnakeDirection.UP && playerDirection == SnakeDirection.DOWN) {
			return;
		}
		if (this.direction == SnakeDirection.DOWN && playerDirection == SnakeDirection.UP) {
			return;
		}
		
		this.direction = playerDirection;
	}
	
	public void moveSnake() {
		switch (this.direction) {
			case DOWN: this.moveDown(); break;
			case UP: this.moveUp(); break;
			case LEFT: this.moveLeft(); break;
			case RIGHT: this.moveRight(); break;
			default: this.moveRight();
		}
	}
	
	private void moveTail() {
		for(int index = this.marbles.size() - 1; index >= 1; index--) {
			SnakeMarble marble = this.marbles.get(index);
			SnakeMarble previousMarble = this.marbles.get(index - 1);
			
			marble.setCol(previousMarble.getCol());
			marble.setRow(previousMarble.getRow());
		}
	}
	
	public void moveUp() {
		this.moveTail();
		
		SnakeMarble head = this.marbles.get(0);
		if (head.getRow() == 0) {
			head.setRow(GameBoard.SIZE - 1);
		}else {
			head.decrementRow();
		}
	}
	
	public void moveDown() {
		this.moveTail();
		
		SnakeMarble head = this.marbles.get(0);
		if(head.getRow() == GameBoard.SIZE - 1) {
			head.setRow(0);
		}else {
			head.incrementRow();
		}
	}
	
	public void moveLeft() {
		this.moveTail();
		
		SnakeMarble head = this.marbles.get(0);
		if (head.getCol() == 0) {
			head.setCol(GameBoard.SIZE - 1);
		}else {
			head.decrementCol();
		}
	}
	
	public void moveRight() {
		this.moveTail();
		
		SnakeMarble head = this.marbles.get(0);
		if (head.getCol() == GameBoard.SIZE - 1) {
			head.setCol(0);
		} else {
			head.incrementCol();
		}
	}
	
	public void incrementSnake() {
		// increase snake marbles with 1
		SnakeMarble lastMarble = this.marbles.get(this.marbles.size() - 1);
		SnakeMarble newMarble = new SnakeMarble(lastMarble.getRow(), lastMarble.getCol());
		
		this.marbles.add(newMarble);
	}
	
	public int getLength() {
		return this.marbles.size() - INITIAL_SIZE;
	}
	
	public boolean checkCollision() {
		SnakeMarble head = this.getHead();
		
		for (int index = 1; index < this.marbles.size(); index++) {
			SnakeMarble marble = this.marbles.get(index);
			
			if (head.getCol() == marble.getCol() && head.getRow() == marble.getRow()) {
				return true;
			}
		}
		
		return false;
	}
	
	public SnakeMarble getHead() {
		return this.marbles.get(0);
	}
}
