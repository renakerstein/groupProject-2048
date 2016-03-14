package groupProject.twentyFortyEight;

import java.util.ArrayList;
import java.util.LinkedList;

public class GameLogic {

	private final Tile[][] gameTiles;
	private ArrayList<Tile> availableSlots;
	private Tile chosenSlot;
	private int currentVal, comparisonVal;
	private Tile current, comparison;
	private int score = 0;
	private int bestScore = 0;
	boolean merged=false;

	public GameLogic() {
		this.gameTiles = new Tile[4][4];
		newGame();
	}

	public void newGame() {
		bestScore = score;
		score = 0;
		this.availableSlots = new ArrayList<Tile>();
		for (int row = 0; row < gameTiles.length; row++) {
			for (int column = 0; column < gameTiles[row].length; column++) {
				gameTiles[row][column] = new Tile();
			}
		}
	}

	public boolean isMerged() {
		return merged;
	}

	public void addTile() {
		availableSlots = emptySlots();
		if (!availableSlots.isEmpty()) {
			int index = (int) (Math.random() * availableSlots.size()) % availableSlots.size();
			chosenSlot = availableSlots.get(index);
			chosenSlot.setValue(Math.random() < 0.9 ? 2 : 4);
		}
	
	}

	public void moveLeft() {
		for (int row = 0; row < gameTiles.length; row++) {
			LinkedList<Tile> list = new LinkedList<Tile>();
			for (int column = 0; column < gameTiles[row].length; column++) {
				list.add(gameTiles[row][column]);
			}

			list = mergeLine(list);

			while (list.size() != 4) {
				list.add(new Tile());
			}
			for (int i = 0; i < list.size(); i++) {
				gameTiles[row][i] = list.get(i);
			}
		}
	}

	public void moveRight() {
		for (int row = 0; row < gameTiles.length; row++) {
			LinkedList<Tile> list = new LinkedList<Tile>();
			for (int column = 0; column < gameTiles[row].length; column++) {
				list.add(gameTiles[row][column]);
			}

			list = mergeLine(list);

			while (list.size() != 4) {
				list.addFirst(new Tile());
			}
			for (int i = list.size() - 1; i >= 0; i--) {
				gameTiles[row][i] = list.get(i);
			}
		}
	}

	public void moveUp() {
		for (int row = 0; row < gameTiles.length; row++) {
			LinkedList<Tile> list = new LinkedList<Tile>();
			for (int column = 0; column < gameTiles[row].length; column++) {
				list.add(gameTiles[column][row]);
			}

			list = mergeLine(list);

			while (list.size() != 4) {
				list.add(new Tile());
			}
			for (int i = 0; i < list.size(); i++) {
				gameTiles[i][row] = list.get(i);
			}
		}
	}

	public void moveDown() {
		for (int row = 0; row < gameTiles.length; row++) {
			LinkedList<Tile> list = new LinkedList<Tile>();
			for (int column = 0; column < gameTiles[row].length; column++) {
				list.add(gameTiles[column][row]);
			}
			list = mergeLine(list);

			while (list.size() != 4) {
				list.addFirst(new Tile());
			}
			for (int i = 0; i < list.size(); i++) {
				gameTiles[i][row] = list.get(i);
			}
		}
	}

	public LinkedList<Tile> mergeLine(LinkedList<Tile> list) {
		removeSpacesInList(list);
		for (int i = 0; i < (list.size() - 1); i++) {
			current = list.get(i);
			currentVal = current.getValue();
			comparison = list.get(i + 1);
			comparisonVal = comparison.getValue();
			 merged = checkForEquality();
			if (merged) {
				score += current.getValue();
			}
		}
		removeSpacesInList(list);
		return list;
	}

	public LinkedList<Tile> removeSpacesInList(LinkedList<Tile> list) {
		for (int i = list.size() - 1; i >= 0; i--) {
			if (list.get(i).isEmpty()) {
				list.remove(list.get(i));
			}
		}
		return list;
	}

	public ArrayList<Tile> emptySlots() {
		ArrayList<Tile> empty = new ArrayList<Tile>();
		for (Tile[] gameTile : gameTiles) {
			for (Tile element : gameTile) {
				if (element.isEmpty()) {
					empty.add(element);

				}
			}
		}
		return empty;
	}

	private boolean checkForEquality() {
		if (comparisonVal == currentVal) {
			comparison.setValue(0);
			current.setValue(currentVal + comparisonVal);
			return true;
		}
		return false;
	}

	public Tile[][] getTiles() {
		return gameTiles;
	}

	public int getScore() {
		return score;
	}

	public int getBestScore() {
		return bestScore;
	}

	public boolean gameOver() {
		if (!boardFull()) {
			return false;
		}

		for (int i = 0; i < gameTiles.length; i++) {
			for (int j = 0; j < gameTiles[i].length; j++) {
				if ((j != 3) && (gameTiles[i][j].getValue() == gameTiles[i][j + 1].getValue())) {
					return false;
				}
				if ((i != 3) && (gameTiles[i][j].getValue() == gameTiles[i + 1][j].getValue())) {
					return false;
				}
			}

		}
		return true;
	}

	private boolean boardFull() {
		for (Tile[] gameTile : gameTiles) {
			for (int j = 0; j < gameTile.length; j++) {
				if (gameTile[j].getValue() == 0) {
					return false;
				}
			}
		}
		return true;
	}
}