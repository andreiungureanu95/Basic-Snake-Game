package ro.Personal.snake;

public interface IScoreCounter {

	/**
	 * Increase the no of points by 1
	 */
	public void increasePoints();
	
	/**
	 * Get the current score
	 * @return
	 */
	public int getPoints();
	
	/**
	 * Set the points back to 0
	 */
	public void resetPoints();
}
