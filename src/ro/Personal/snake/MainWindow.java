package ro.Personal.snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.WindowConstants;

public class MainWindow extends JFrame {

	private static final int WIDTH = 820;
	private static final int HEIGHT = 850;
	
	private GameBoard gameBoard;
	private Timer fpsTimer;
	
	public MainWindow() {
		
		this.gameBoard = new GameBoard();
		this.configure();
		this.buildContent();
		
		this.fpsTimer = new Timer(1000 / 30, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainWindow.this.repaint();
			}
		});
		this.fpsTimer.start();
	}
	
	private void configure() {
		this.setTitle("ITSnake");
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setSize(WIDTH, HEIGHT);
		this.getContentPane().setBackground(Color.BLACK);
		
//		this.gameBoard.addSnakeMovedListener(new SnakeMovedListener() {
//			@Override
//			public void snakeMoved() {
//				MainWindow.this.repaint();
//			}
//		});
		
		
		
		this.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
			}
			
			@Override
			public void keyPressed(KeyEvent keyEvent) {
				// This is what we need.
				MainWindow.this.gameBoard.keyPressed(keyEvent.getKeyCode());
			}
		});
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		
		Graphics2D g2d = (Graphics2D) g;
		this.gameBoard.paint(g2d);
	}
	
	private void buildContent() {
		
	}
	
	private void start() {
		
	}
	
	private void pause() {
		
	}
}
