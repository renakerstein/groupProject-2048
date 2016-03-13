package groupProject.twentyFortyEight;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

public class GridPanel extends JPanel {

	private final JLabel[][] labels;
	private Tile[][] tiles;
	private final GameLogic gameLogic;
	private final Font largeFont;
	private final Font smallFont;
	private final Font smallestFont;
	private final JLabel scoreLabel;
	private BevelBorder bevel;

	public GridPanel(GameLogic logic, final JLabel score) {
		this.scoreLabel = score;
		 bevel = new BevelBorder(BevelBorder.RAISED);
		largeFont = new Font("Calibri", Font.PLAIN, 100);
		smallFont = new Font("Calibri", Font.PLAIN, 80);
		smallestFont = new Font("Calibri", Font.PLAIN, 60);
		setLayout(new GridLayout(4, 4));
		gameLogic = logic;
		labels = new JLabel[4][4];
		newGame();

		for (int i = 0; i < labels.length; i++) {
			for (int j = 0; j < labels[i].length; j++) {
				labels[i][j] = new JLabel();
				labels[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				labels[i][j].setBorder((BorderFactory.createLineBorder(new Color(0xbbada0), 5)));
				labels[i][j].setBorder(bevel);
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
				} else if(tiles[i][j].getValue() <1024){
					labels[i][j].setFont(smallFont);
				}
				else{
					labels[i][j].setFont(smallestFont);
				}
				if (tiles[i][j].getValue() != 0) {
					labels[i][j].setText(String.valueOf(tiles[i][j].getValue()));
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
		if (won){
			endGameMsg(g);
			/*JOptionPane.showMessageDialog(this, "You won-HAVE A GOOD DAY! \nTHANK YOU FOR PLAYING");
			gameLogic.newGame();
			newGame();
			repaint();**/
			repaint();
		}

		if (lost) {
			JOptionPane.showMessageDialog(this, "You lost-HAVE A GOOD DAY! \nTHANK YOU FOR PLAYING");
			gameLogic.newGame();
			newGame();
			repaint();
		}

	}
	
	public void endGameMsg(Graphics g2){
		Graphics2D g = (Graphics2D) g2;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
				RenderingHints.VALUE_STROKE_NORMALIZE);
			g.setColor(new Color(255, 255, 255, 30));
			g.fillRect(0, 0, getWidth(), getHeight());
			g.setColor(new Color(78, 139, 202));
			g.setFont(new Font("Arial", Font.BOLD, 48));
		
				g.drawString("You won!", 68, 150);
		
		/*	if (myLose) {
				g.drawString("Game over!", 50, 130);
				g.drawString("You lose!", 64, 200);
			}
			
				g.setFont(new Font(FONT_NAME, Font.PLAIN, 16));
				g.setColor(new Color(128, 128, 128, 128));
				g.drawString("Press ESC to play again", 80, getHeight() - 40);
			
		}
		g.setFont(new Font(FONT_NAME, Font.PLAIN, 18));
		g.drawString("Score: " + myScore, 200, 365);

	}**/
				
	}

	public void newGame() {
		gameLogic.newGame();
		gameLogic.addTile();
		gameLogic.addTile();
		tiles = gameLogic.getTiles();
		repaint();
	}
}