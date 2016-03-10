package groupProject.twentyFortyEight;

import java.util.ArrayList;
import java.util.LinkedList;

public class GameLogic {

	private final Tile[][] gameTiles;
	private ArrayList<Tile> availableSlots;
	private Tile chosenSlot;
	private int currentVal, comparisonVal;
	private Tile current, comparison;
	private boolean merged;
	private int score;
	private final int bestScore;

	public GameLogic() {
		this.gameTiles = new Tile[4][4];
		for (int row = 0; row < gameTiles.length; row++) {
			for (int column = 0; column < gameTiles[row].length; column++) {
				gameTiles[row][column] = new Tile();
			}
		}
		this.availableSlots = new ArrayList<Tile>();
		this.merged = false;
		this.score = 0;
		this.bestScore = 0;
	}

	public Tile addTile() {
		availableSlots = emptySlots();
		if (!availableSlots.isEmpty()) {
			int index = (int) (Math.random() * availableSlots.size())
					% availableSlots.size();
			chosenSlot = availableSlots.get(index);
			chosenSlot.setValue(Math.random() < 0.9 ? 2 : 4);
		}
		return chosenSlot; // get row and column of this specific tile

	}

	public void moveLeft() {
		for (int row = 0; row < gameTiles.length; row++) {
			for (int col = 3; col >= 0; col--) {
				if (!gameTiles[row][col].isEmpty()) {
					current = gameTiles[row][col];
					currentVal = current.getValue();
					int counter = col;
					while (counter != 0) {
						comparison = gameTiles[row][counter - 1];
						comparisonVal = comparison.getValue();
						if ((col - counter) == 0) {
							merged = checkForEquality();
						} else {
							boolean space = true;
							for (int i = 0; i < (counter - col); i++) {
								if (gameTiles[col][i + 1].getValue() != 0) {
									space = false;
									break;
								}
							}
							if (space) {
								merged = checkForEquality();
							}
						}
						if (merged) {
							score += current.getValue();
							break;
						}
						counter--;
					}
				}
			}
		}

		for (int row = 0; row < gameTiles.length; row++) {
			LinkedList<Tile> list = new LinkedList<Tile>();
			for (int column = 0; column < gameTiles[row].length; column++) {
				list.add(gameTiles[row][column]);
			}

			for (int i = list.size() - 1; i >= 0; i--) {
				if (list.get(i).isEmpty()) {
					list.remove(list.get(i));
				}
			}
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
			for (int col = 0; col < gameTiles[row].length; col++) {
				if (!gameTiles[row][col].isEmpty()) {
					current = gameTiles[row][col];
					currentVal = current.getValue();
					int counter = col;
					while (counter != 3) {
						comparison = gameTiles[row][counter + 1];
						comparisonVal = comparison.getValue();
						if ((col - counter) == 0) {
							merged = checkForEquality();
						} else {
							boolean space = true;
							for (int i = 0; i < (counter - col); i++) {
								if (gameTiles[col][i + 1].getValue() != 0) {
									space = false;
									break;
								}
							}
							if (space) {
								merged = checkForEquality();
							}
						}
						if (merged) {
							score += current.getValue();
							break;
						}
						counter++;
					}
				}
			}
		}

		for (int row = 0; row < gameTiles.length; row++) {
			LinkedList<Tile> list = new LinkedList<Tile>();
			for (int column = 0; column < gameTiles[row].length; column++) {
				list.add(gameTiles[row][column]);
			}

			for (int i = list.size() - 1; i >= 0; i--) {
				if (list.get(i).isEmpty()) {
					list.remove(list.get(i));
				}
			}
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
			for (int col = 3; col >= 0; col--) {
				if (!gameTiles[col][row].isEmpty()) {
					current = gameTiles[col][row];
					currentVal = current.getValue();
					int counter = col;
					while (counter != 0) {
						comparison = gameTiles[counter - 1][row];
						comparisonVal = comparison.getValue();
						if ((counter - col) == 0) {
							merged = checkForEquality();
						} else {
							boolean space = true;
							for (int i = 0; i < (counter - col); i++) {
								if (gameTiles[i + 1][col].getValue() != 0) {
									space = false;
									break;
								}
							}
							if (space) {
								merged = checkForEquality();
							}
						}
						if (merged) {
							score += current.getValue();
							break;
						}
						counter--;
					}
				}
			}
		}

		for (int row = 0; row < gameTiles.length; row++) {
			LinkedList<Tile> list = new LinkedList<Tile>();
			for (int column = 0; column < gameTiles[row].length; column++) {
				list.add(gameTiles[column][row]);
			}

			for (int i = list.size() - 1; i >= 0; i--) {
				if (list.get(i).isEmpty()) {
					list.remove(list.get(i));
				}
			}
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
			for (int col = 0; col < gameTiles[row].length; col++) {
				if (!gameTiles[col][row].isEmpty()) {
					current = gameTiles[col][row];
					currentVal = current.getValue();
					int counter = col;
					while (counter != 3) {
						comparison = gameTiles[counter + 1][row];
						comparisonVal = comparison.getValue();
						if ((counter - col) == 0) {
							merged = checkForEquality();
						} else {
							boolean space = true;
							for (int i = 0; i < (counter - col); i++) {
								if (gameTiles[i + 1][col].getValue() != 0) {
									space = false;
									break;
								}
							}
							if (space) {
								merged = checkForEquality();
							}
						}
						if (merged) {
							score += current.getValue();
							break;
						}
						counter++;
					}
				}
			}
		}

		for (int row = 0; row < gameTiles.length; row++) {
			LinkedList<Tile> list = new LinkedList<Tile>();
			for (int column = 0; column < gameTiles[row].length; column++) {
				list.add(gameTiles[column][row]);
			}

			for (int i = list.size() - 1; i >= 0; i--) {
				if (list.get(i).isEmpty()) {
					list.remove(list.get(i));
				}
			}
			while (list.size() != 4) {
				list.addFirst(new Tile());
			}
			for (int i = 0; i < list.size(); i++) {
				gameTiles[i][row] = list.get(i);
			}
		}
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
				if ((j != 3)
						&& (gameTiles[i][j].getValue() == gameTiles[i][j + 1]
								.getValue())) {
					return false;
				}
				if ((i != 3)
						&& (gameTiles[i][j].getValue() == gameTiles[i + 1][j]
								.getValue())) {
					return false;
				}
			}

		}
		return true;
	}

	private boolean boardFull() {
		for (int i = 0; i < gameTiles.length; i++) {
			for (int j = 0; j < gameTiles[i].length; j++) {
				if (gameTiles[i][j].getValue() == 0) {
					return false;
				}
			}
		}
		return true;
	}
}