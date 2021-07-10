package ro.Personal.snake;

import java.util.Random;

public class Apple extends SnakeMarble {

	public Apple(int row, int col) {
		super(row, col);
	}

	public void randomizePosition() {
		// place apple at new random coordinates
		Random random = new Random();
		
		this.setRow(random.nextInt(GameBoard.SIZE - 1));
		this.setCol(random.nextInt(GameBoard.SIZE - 1));
	}
}
