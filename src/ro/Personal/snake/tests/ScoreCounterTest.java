package ro.Personal.snake.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ro.Personal.snake.IScoreCounter;
import ro.Personal.snake.ScoreCounter;

public class ScoreCounterTest {
	
	private IScoreCounter scoreCounter;
	
	@Before
	public void setUp() {
		this.scoreCounter = new ScoreCounter();
	}
	
	@Test
	public void checkGetPoints() throws Exception {
		Assert.assertTrue(this.scoreCounter.getPoints() >= 0);
	}
	
	@Test
	public void checkIncreasingPoints() throws Exception {
		this.scoreCounter.increasePoints();
		
		Assert.assertTrue(this.scoreCounter.getPoints() == 1);
	}
	
	@Test 
	public void checkResetPoints() throws Exception {
		this.scoreCounter.increasePoints();
		
		this.scoreCounter.resetPoints();
		
		Assert.assertTrue(this.scoreCounter.getPoints() == 0);
	}
}
