package ro.Personal.snake.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ro.Personal.snake.Snake;

public class SnakeTest {
	
	private Snake snake;
	
	@Before
	public void prepare() {
		this.snake = new Snake();
	}

	@Test
	public void checkInitialSnake() throws Exception {
		Assert.assertEquals("Snake length should be 0", 
				0, 
				snake.getLength());
	}
	
	@Test
	public void checkNoCollision() throws Exception {
		snake.moveRight();
		snake.moveRight();
		
		Assert.assertEquals("Collision should be false", 
				false,
				snake.checkCollision());
	}
}
