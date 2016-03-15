package groupProject.twentyFortyEight;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

public class GridPanel extends JPanel {

	private final JLabel[][] labels;
	private Tile[][] tiles;
	private final GameLogic gameLogic;
	private final Font largeFont, mediumFont, smallFont;
	private final JLabel scoreLabel, highScoreLabel;
	private boolean won;
	private boolean lost;

	public GridPanel(GameLogic logic, final JLabel score, JLabel highScoreLabel) {

		this.scoreLabel = score;
		this.highScoreLabel = highScoreLabel;
		this.won = false;
		this.lost = false;

		largeFont = new Font("Calibri", Font.PLAIN, 100);
		mediumFont = new Font("Calibri", Font.PLAIN, 80);
		smallFont = new Font("Calibri", Font.PLAIN, 60);

		setLayout(new GridLayout(4, 4));
		gameLogic = logic;
		labels = new JLabel[4][4];
		newGame();

		for (int i = 0; i < labels.length; i++) {
			for (int j = 0; j < labels[i].length; j++) {
				labels[i][j] = new JLabel();
				labels[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				add(labels[i][j]);
				labels[i][j].setOpaque(true);
			}
		}
		setFocusable(true);
		addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				boolean moved = false;
				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					moved = gameLogic.moveLeft();
					break;
				case KeyEvent.VK_RIGHT:
					moved = gameLogic.moveRight();
					break;
				case KeyEvent.VK_UP:
					moved = gameLogic.moveUp();
					break;
				case KeyEvent.VK_DOWN:
					moved = gameLogic.moveDown();
					break;
				case KeyEvent.VK_ESCAPE:
					newGame();
					return;
				}
				if (moved) {
					gameLogic.addTile();
				}
				repaint();
			}
		});
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawTiles(g);
	}

	private void drawTiles(Graphics g) {
		scoreLabel.setText(String.valueOf("<html>SCORE<br>"
				+ gameLogic.getScore() + "</html>"));
		highScoreLabel.setText(String.valueOf("<html>HIGH SCORE<br>"
				+ gameLogic.getHighScore() + "<html>"));
		for (int i = 0; i < labels.length; i++) {
			for (int j = 0; j < labels[i].length; j++) {
				labels[i][j].setBackground(tiles[i][j].getBackground());
				labels[i][j].setForeground(tiles[i][j].getForeground());
				labels[i][j].setBorder(BorderFactory.createCompoundBorder(
						BorderFactory.createLineBorder(new Color(0xbbada0), 5),
						BorderFactory.createEtchedBorder(EtchedBorder.LOWERED,
								Color.WHITE, Color.WHITE)));
				if (tiles[i][j].getValue() < 128) {
					labels[i][j].setFont(largeFont);
				} else if (tiles[i][j].getValue() < 1024) {
					labels[i][j].setFont(mediumFont);
				} else {
					labels[i][j].setFont(smallFont);
				}

				if (tiles[i][j].getValue() != 0) {
					labels[i][j]
							.setText(String.valueOf(tiles[i][j].getValue()));
				} else {
					labels[i][j].setText("");
				}
				if (tiles[i][j].getValue() == 2048) {
					won = true;
				}
				if (gameLogic.gameOver()) {
					lost = true;
				}
			}
		}
		if (won) {
			JOptionPane.showMessageDialog(this, "YOU WIN!", "2048",
					JOptionPane.PLAIN_MESSAGE, new ImageIcon("2048.png"));
			newGame();
		}
		if (lost) {

			JOptionPane.showMessageDialog(this, "GAME OVER!", "2048",
					JOptionPane.PLAIN_MESSAGE, new ImageIcon("2048.png"));
			newGame();
		}
		if (won || lost) {
			newGame();
		}
	}

	public void newGame() {
		lost = false;
		won = false;
		gameLogic.newGame();
		gameLogic.addTile();
		gameLogic.addTile();
		tiles = gameLogic.getTiles();
		repaint();
	}
}