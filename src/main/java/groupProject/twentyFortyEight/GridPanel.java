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

public class GridPanel extends JPanel {

	private final JLabel[][] labels;
	private Tile[][] tiles;
	private GameLogic logic;
	private JLabel score, bestScore;

	public GridPanel(JLabel scoreLabel, JLabel bestScore) {

		setLayout(new GridLayout(4, 4));
		logic = new GameLogic();
		labels = new JLabel[4][4];
		newGame();

		this.score = scoreLabel;
		this.bestScore = bestScore;

		for (int i = 0; i < labels.length; i++) {
			for (int j = 0; j < labels[i].length; j++) {
				labels[i][j] = new JLabel();
				labels[i][j].setBorder((BorderFactory.createLineBorder(
						new Color(0xbbada0), 5)));
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
					logic.moveLeft();
					break;

				case KeyEvent.VK_RIGHT:
					logic.moveRight();
					break;
				case KeyEvent.VK_UP:
					logic.moveUp();
					break;
				case KeyEvent.VK_DOWN:
					logic.moveDown();
					break;
				}
				score.setText(String.valueOf(logic.getScore()));
				logic.addTile();
				repaint();
			}
		});
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		boolean won = false;
		boolean lost = false;
		for (int i = 0; i < labels.length; i++) {
			for (int j = 0; j < labels[i].length; j++) {
				labels[i][j].setBackground(tiles[i][j].getBackground());
				labels[i][j].setForeground(tiles[i][j].getForeground());
				labels[i][j].setFont(new Font("Calibri", Font.PLAIN, 100));
				if (tiles[i][j].getValue() != 0) {
					labels[i][j].setText(" " + tiles[i][j].getValue());
				} else {
					labels[i][j].setText("");
				}
				if (tiles[i][j].getValue() == 2048) {
					won = true;
					break;
				}
				if (logic.gameOver()) {
					lost = true;
					break;
				}
			}
		}
		if (won) {
			

			logic = new GameLogic();
			newGame();
			repaint();
			/**
			 * g.setColor(new Color(255, 255, 255, 30)); g.fillRect(0, 0,
			 * getWidth(), getHeight()); g.setColor(new Color(78, 139, 202));
			 * g.setFont(new Font("Calibri", Font.BOLD, 48));
			 * g.drawString("You won!", 68, 150);
			 */
		}
		
		if(lost){
		//	repaint();
			JOptionPane.showMessageDialog(this,
					"HAVE A GOOD DAY! \nTHANK YOU FOR PLAYING"
					);
		}
	}

	public void newGame() {
		logic.addTile();
		logic.addTile();
		tiles = logic.getTiles();
	}
}