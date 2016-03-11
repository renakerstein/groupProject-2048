package groupProject.twentyFortyEight;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GridPanel extends JPanel {

	private final JLabel[][] labels;
	private Tile[][] tiles;
	private final GameLogic gameLogic;
	private final Font largeFont;
	private final Font smallFont;
	private final JLabel scoreLabel;

	public GridPanel(GameLogic logic, final JLabel score) {

		this.scoreLabel = score;

		largeFont = new Font("Calibri", Font.PLAIN, 100);
		smallFont = new Font("Calibri", Font.PLAIN, 80);
		setLayout(new GridLayout(4, 4));
		gameLogic = logic;
		labels = new JLabel[4][4];
		newGame();

		for (int i = 0; i < labels.length; i++) {
			for (int j = 0; j < labels[i].length; j++) {
				labels[i][j] = new JLabel();
				labels[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				labels[i][j].setBorder((BorderFactory.createLineBorder(new Color(0xbbada0), 5)));
				add(labels[i][j]);
				labels[i][j].setOpaque(true);
			}
		}
		setFocusable(true);
		addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {

				case KeyEvent.VK_LEFT:
					gameLogic.moveLeft();
					break;
				case KeyEvent.VK_RIGHT:
					gameLogic.moveRight();
					break;
				case KeyEvent.VK_UP:
					gameLogic.moveUp();
					break;
				case KeyEvent.VK_DOWN:
					gameLogic.moveDown();
					break;
				case KeyEvent.VK_ESCAPE:
					newGame();
					return;
				}
				gameLogic.addTile();

				repaint();
			}
		});
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		scoreLabel.setText(String.valueOf("<html>SCORE<br>" + gameLogic.getScore() + "</html>"));
		boolean won = false;
		boolean lost = false;
		for (int i = 0; i < labels.length; i++) {
			for (int j = 0; j < labels[i].length; j++) {
				labels[i][j].setBackground(tiles[i][j].getBackground());
				labels[i][j].setForeground(tiles[i][j].getForeground());
				if (tiles[i][j].getValue() < 128) {
					labels[i][j].setFont(largeFont);
				} else {
					labels[i][j].setFont(smallFont);
				}
				if (tiles[i][j].getValue() != 0) {
					labels[i][j].setText(String.valueOf(tiles[i][j].getValue()));
				} else {
					labels[i][j].setText("");
				}
				if (tiles[i][j].getValue() == 2048) {
					won = true;
					break;
				}
				if (gameLogic.gameOver()) {
					lost = true;
					break;
				}
			}
		}
		if (won) {
			gameLogic.newGame();
			newGame();
			repaint();
		}

		if (lost) {
			JOptionPane.showMessageDialog(this, "HAVE A GOOD DAY! \nTHANK YOU FOR PLAYING");
		}
	}

	public void newGame() {
		gameLogic.newGame();
		gameLogic.addTile();
		gameLogic.addTile();
		tiles = gameLogic.getTiles();
		repaint();
	}
}