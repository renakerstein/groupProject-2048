package groupProject.twentyFortyEight;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GridPanel extends JPanel {

	private final JLabel[][] labels;
	private Tile[][] tiles;
	private final GameLogic logic;

	public GridPanel() {

		setLayout(new GridLayout(4, 4));
		logic = new GameLogic();
		labels = new JLabel[4][4];
		newGame();

		for (int i = 0; i < labels.length; i++) {
			for (int j = 0; j < labels[i].length; j++) {
				labels[i][j] = new JLabel();
				labels[i][j].setBorder((BorderFactory.createLineBorder(new Color(0xbbada0), 5)));
				add(labels[i][j]);
				labels[i][j].setOpaque(true);
			}
		}

		this.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {

				case KeyEvent.VK_LEFT:
					logic.moveLeft();
					repaint();
					break;

				case KeyEvent.VK_RIGHT:
					logic.moveRight();
					repaint();
					break;
				case KeyEvent.VK_UP:
					logic.moveUp();
					repaint();
					break;
				case KeyEvent.VK_DOWN:
					logic.moveDown();
					repaint();
					break;
				}
			}
		});
	}

	@Override
	public void paint(Graphics arg0) {
		super.paint(arg0);
		for (int i = 0; i < labels.length; i++) {
			for (int j = 0; j < labels[i].length; j++) {
				labels[i][j].setBackground(tiles[i][j].getBackground());
				labels[i][j].setForeground(tiles[i][j].getForeground());
				labels[i][j].setFont(new Font("Calibri", Font.PLAIN, 150));
				if (tiles[i][j].getValue() != 0) {
					labels[i][j].setText(" " + tiles[i][j].getValue());
				}
			}
		}
	}

	public void newGame() {
		logic.addTile();
		logic.addTile();
		tiles = logic.getTiles();
	}
}