package hackerrank;
import java.io.IOException;

public class QueensAttack {

	// Complete the queensAttack function below.
	static int queensAttack(int n, int k, int r_q, int c_q, int[][] obstacles) {
		int count = 0;

		// Define next cell for each of queen's movement
		int[][] moves = { { 0, -1 }, { -1, 0 }, { 1, 0 }, { 0, 1 }, { 1, 1 }, { -1, -1 }, { 1, -1 }, { -1, 1 } };
		int numMoves = 8;
		int numObstacles = obstacles.length;

		for (int i = 0; i < numMoves; i++) {
			int distance = -1;
			int minDistance = Integer.MAX_VALUE;

			for (int j = 0; j < numObstacles; j++) {
				distance = getDistanceToObstacle(r_q, c_q, obstacles[j], moves[i]);

				// if there are multiple obstacles in single path, then consider the closest
				// one.
				if (distance != -1 && distance < minDistance) {
					minDistance = distance;
				}
			}

			if (minDistance == Integer.MAX_VALUE) {
				// There is no obstacle.
				count += getAllAttackingCells(n, r_q, c_q, moves[i]);
			} else {
				// Ignoring the cell where obstacle is present
				count += minDistance - 1;
			}
		}

		return count;
	}

	static void printChessBoard(int n, int k, int r_q, int c_q, int[][] obstacles) {
		String space = " ";

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (i == r_q && j == c_q) {
					// This is queen
					System.out.print("I" + space);
				} else if (isObstaclePoint(obstacles, i, j)) {
					System.out.print("O" + space);
				} else {
					System.out.print("." + space);
				}
			}
			System.out.println();
		}

	}

	private static boolean isObstaclePoint(int[][] obstacles, int x, int y) {
		for (int i = 0; i < obstacles.length; i++) {
			if (x == obstacles[i][0] && y == obstacles[i][1]) {
				return true;
			}
		}

		return false;
	}

	private static int getAllAttackingCells(int boardSize, int x1, int y1, int[] moves) {
		if (moves[0] == 0) {
			return getLength(boardSize, y1, moves[1]);
		} else if (moves[1] == 0) {
			return getLength(boardSize, x1, moves[0]);
		} else {
			// Calculate number of diagonal cells
			int xDiff = getLength(boardSize, x1, moves[0]);
			int yDiff = getLength(boardSize, y1, moves[1]);

			return Math.min(xDiff, yDiff);
		}

	}

	private static int getLength(int boardSize, int axisPoint, int plane) {
		int adjustment = 0;

		// Handling 0th index for negative planes in x and y axis.
		if (plane < 0) {
			adjustment++;
		}

		return (Math.abs((((1 + plane) / 2) * boardSize) - axisPoint) - adjustment);
	}

	// x defines row and y defines column.
	private static int getDistanceToObstacle(int x1, int y1, int[] x2y2, int[] moves) {
		int xDiff = x2y2[0] - x1;
		int yDiff = x2y2[1] - y1;

		boolean isDiagonalObstacle = Math.abs(xDiff) == Math.abs(yDiff);

		// Identify if current piece is an obstacle.
		if (xDiff != 0 && yDiff != 0 && !isDiagonalObstacle) {
			return -1;
		}

		if (moves[0] == 0 && xDiff == 0) {
			// Obstacle is in x-axis
			if ((moves[1] < 0 && yDiff < 0) || (moves[1] > 0 && yDiff > 0)) {
				return Math.abs(yDiff);
			}

		} else if (moves[1] == 0 && yDiff == 0) {
			// Obstacle is in y-axis
			if ((moves[0] < 0 && xDiff < 0) || (moves[0] > 0 && xDiff > 0)) {
				return Math.abs(xDiff);
			}
		} else if (isDiagonalObstacle) {
			// Now we know for sure that this is diagonal obstacle, as above 2 conditions
			// did not succeed.
			// If 2 cells are diagonally present in a matrix, then difference between
			// any one co-ordinate gives distance between 2 cells.
			if ( ((xDiff / Math.abs(xDiff)) == moves[0]) && ((yDiff / Math.abs(yDiff) == moves[1])) ) {
				return Math.abs(xDiff);
			}
		}

		return -1;

	}

	public static void main(String[] args) throws IOException {

		int boardSize = 100;
		int r_q = 54;
		int c_q = 30;
		int[][] obstacles = new int[][] { { 48, 36 }, { 38, 46 }, { 60, 24 }, { 70, 14 }, { 54, 36 }, { 54, 24 },
				{ 60, 30 }, { 48, 30 }, { 71, 50 }, { 14, 97 }, { 47, 31 }, { 29, 68 }, { 90, 10 }, { 36, 85 },
				{ 63, 24 }, { 32, 13 }, { 64, 57 }, { 45, 57 }, { 86, 19 }, { 43, 86 }, { 68, 72 }, { 29, 25 },
				{ 48, 59 }, { 38, 78 }, { 45, 16 }, { 40, 92 }, { 76, 85 }, { 40, 10 }, { 65, 16 }, { 71, 18 },
				{ 90, 40 }, { 65, 45 }, { 10, 37 }, { 19, 82 }, { 42, 56 }, { 46, 60 }, { 94, 14 }, { 34, 36 },
				{ 95, 49 }, { 78, 67 }, { 86, 23 }, { 28, 12 }, { 95, 57 }, { 38, 19 }, { 61, 49 }, { 67, 42 },
				{ 28, 25 }, { 38, 28 }, { 91, 20 }, { 90, 86 }, { 81, 19 }, { 18, 43 }, { 29, 69 }, { 36, 20 },
				{ 72, 75 }, { 39, 50 }, { 17, 92 }, { 48, 25 }, { 20, 79 }, { 82, 57 }, { 58, 50 }, { 94, 70 },
				{ 17, 19 }, { 73, 20 }, { 45, 12 }, { 19, 89 }, { 45, 12 }, { 59, 74 }, { 63, 71 }, { 32, 23 },
				{ 67, 85 }, { 24, 25 }, { 18, 61 }, { 97, 50 }, { 70, 37 }, { 30, 10 }, { 39, 90 }, { 75, 58 },
				{ 58, 34 }, { 47, 62 }, { 28, 28 }, { 79, 34 }, { 73, 80 }, { 93, 36 }, { 25, 45 }, { 48, 75 },
				{ 42, 13 }, { 18, 69 }, { 35, 21 }, { 18, 87 }, { 57, 19 }, { 26, 92 }, { 94, 34 }, { 84, 48 },
				{ 61, 95 }, { 62, 89 }, { 59, 74 }, { 50, 40 }, { 36, 37 }, { 95, 62 } };

//		int result = queensAttack(5, 3, 4, 3, new int[][] { { 5, 5 }, { 4, 2 }, { 2, 3 } });
//		printChessBoard(5, 3, 4, 3, new int[][] { { 5, 5 }, { 4, 2 }, { 2, 3 } });
		int result = queensAttack(boardSize, obstacles.length, r_q, c_q, obstacles);
//		printChessBoard(boardSize, obstacles.length, r_q, c_q, obstacles);

		System.out.println(result);
	}
}
