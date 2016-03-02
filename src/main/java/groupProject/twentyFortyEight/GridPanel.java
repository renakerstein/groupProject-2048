package groupProject.twentyFortyEight;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GridPanel extends JPanel {

	private final JLabel[][] labels;
	private final Tile[][] tiles;

	public GridPanel() {

		setLayout(new GridLayout(4, 4));
		labels = new JLabel[4][4];
		tiles = new Tile[4][4];
		for (int i = 0; i < labels.length; i++) {
			for (int j = 0; j < labels[i].length; j++) {
				labels[i][j] = new JLabel();
				labels[i][j].setBorder((BorderFactory.createLineBorder(
						new Color(0xbbada0), 5)));
				add(labels[i][j]);
				tiles[i][j] = new Tile();
				labels[i][j].setBackground(new Color(0xcdc1b4));
				labels[i][j].setOpaque(true);
			}
		}

		this.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {

				case KeyEvent.VK_LEFT:
					moveLeft();
					break;

				case KeyEvent.VK_RIGHT:
					moveRight();
					break;
				case KeyEvent.VK_UP:
					moveUp();
					break;
				case KeyEvent.VK_DOWN:
					moveDown();
					break;
				}
			}
		});
	}

	private void moveDown() {

	}

	private void moveUp() {

	}

	private void moveRight() {

	}

	private void moveLeft() {
	}

	public void newGame() {

	}
}