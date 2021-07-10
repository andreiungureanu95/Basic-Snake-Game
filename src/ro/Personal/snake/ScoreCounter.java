package ro.Personal.snake;

public class ScoreCounter implements IScoreCounter {

	private static final int MAX_SCORE = 200; 
	private int score;
	
	public ScoreCounter() {
		this.score = 0;
	}
	
	@Override
	public void increasePoints() {
		this.score++;
		
		if (this.score >= MAX_SCORE) {
			this.resetPoints();
		}
	}

	@Override
	public int getPoints() {
		return this.score ;
	}

	@Override
	public void resetPoints() {
		this.score = 0;
	}

}
