package ro.Personal.snake;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import javax.swing.JOptionPane;
import javax.swing.Timer;

public class GameBoard implements IPaintable{

	private class GameStepTask extends TimerTask {
		@Override
		public void run() {
			GameBoard.this.gameStep();
		}
	}
	
	public static final int SIZE = 20;
	public static final int CELL_SIZE_PX = 40; 
	public static final int OFFSET_TOP_PX = 38;
	public static final int OFFSET_LEFT_PX = 8;
	
	private Snake snake;
	private Apple apple;
	private Timer gameTimer;
	private int timerTick = 100;
	private boolean isPaused = false;
	
	private List<SnakeMovedListener> snakeMovedListeners;
	
	public GameBoard() {
		this.snake = new Snake();
		this.apple = new Apple(0, 0);
		this.apple.randomizePosition();
		this.snakeMovedListeners = new ArrayList<SnakeMovedListener>();
		this.gameTimer = new Timer(this.timerTick, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				GameBoard.this.gameStep();
			}
		});
		this.gameTimer.start();
//		this.gameTimer.scheduleAtFixedRate(new GameStepTask(), 1000, this.timerTick);
	}
	
	/**
	 * This gets called by gameTimer every X seconds.
	 * It should move the snake
	 */
//	@Override
//	public void run() {
////		if (!this.isPaused) {
//			this.gameStep();
////		}
//	}
	
	public void addSnakeMovedListener(SnakeMovedListener listener) {
		this.snakeMovedListeners.add(listener);
	}
	
	@Override
	public void paint(Graphics2D graphics) {
//		graphics.setColor(Color.BLUE);
//		for (int row = 0; row < SIZE; row++) {
//			for (int col = 0; col < SIZE; col++) {
//				int x = OFFSET_LEFT_PX + (col * CELL_SIZE_PX);
//				int y = OFFSET_TOP_PX + (row * CELL_SIZE_PX);
//				
//				graphics.drawRect(x, y, CELL_SIZE_PX, CELL_SIZE_PX);		
//			}
//		}
		
		this.snake.paint(graphics);
		this.apple.paint(graphics);
		
//		graphics.setColor(Color.RED);
//		graphics.fillOval(OFFSET_LEFT_PX, OFFSET_TOP_PX, CELL_SIZE_PX, CELL_SIZE_PX);
		
	}
	
	public void keyPressed(int keyCode) {
		switch(keyCode) {
		case KeyEvent.VK_UP: this.snake.changeDirection(SnakeDirection.UP); break;
		case KeyEvent.VK_DOWN: this.snake.changeDirection(SnakeDirection.DOWN); break;
		case KeyEvent.VK_RIGHT: this.snake.changeDirection(SnakeDirection.RIGHT); break;
		case KeyEvent.VK_LEFT: this.snake.changeDirection(SnakeDirection.LEFT); break;
		case KeyEvent.VK_SPACE: this.togglePause(); break;
		case KeyEvent.VK_R: this.reset();
		default: // do nothing 
		}
	}
	
	private void gameStep() {
		// called by gameTimer
		this.snake.moveSnake();
		if (this.hasEatenApple()) {
			this.snake.incrementSnake();
			this.apple.randomizePosition();
		}
		
		if (this.snake.checkCollision()) {
			this.gameOver();
		}
		
		// notify all the potential snakeMoved listeners
		for(SnakeMovedListener listener : this.snakeMovedListeners) {
			listener.snakeMoved();
		}
	}
	
	private void gameOver() {
		gameTimer.stop();
//		gameTimer = null;
		
		JOptionPane.showMessageDialog(null, "GAME OVER");
		this.reset();
	}
	
	private void reset() {
		this.snake = new Snake();
		this.apple = new Apple(0, 0);
		this.apple.randomizePosition();
		
		if (this.gameTimer != null) {
			this.gameTimer.stop();
		}
		
		this.gameTimer.restart();
//		this.gameTimer = new Timer("GameTimer");
//		this.gameTimer.scheduleAtFixedRate(new GameStepTask(), 0, this.timerTick);
	}
	
	private void togglePause() {
		if (this.isPaused) {
			// resume code
			this.gameTimer.restart();
//			this.gameTimer = new Timer("GameTimer");
//			this.gameTimer.scheduleAtFixedRate(new GameStepTask(), 0, this.timerTick);
		} else {
			// pause game
			this.gameTimer.stop();
//			gameTimer.cancel();
//			gameTimer = null;
		}
		
		this.isPaused = !this.isPaused;
	}
	
	private boolean hasEatenApple() {
		SnakeMarble head = this.snake.getHead();
		
		return head.getCol() == apple.getCol() && head.getRow() == apple.getRow();
	}
}
