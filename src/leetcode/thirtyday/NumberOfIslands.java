package leetcode.thirtyday;

import java.util.LinkedList;
import java.util.Queue;

public class NumberOfIslands {
	public int numIslands(char[][] grid) {
		int numIslands = 0;

		if (grid == null || grid.length == 0) {
			return numIslands;
		}

		boolean[][] isVisitedLand = new boolean[grid.length][grid[0].length];
		Queue<Grid_NI> q = new LinkedList<>();

		int[][] dirs = new int[][] { { 0, 1, }, { 1, 0 }, { -1, 0 }, { 0, -1 } };

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == '1' && !isVisitedLand[i][j]) {
					numIslands++;
					q.add(new Grid_NI(i, j));
					isVisitedLand[i][j] = true;
					while (!q.isEmpty()) {
						Grid_NI currCell = q.remove();
						int currX = currCell.x;
						int currY = currCell.y;

						System.out.println("Visiting island : [" + currX + "][" + currY + "]");

						for (int[] js : dirs) {
							int nextX = currX + js[0];
							int nextY = currY + js[1];
							if (isValid(nextX, nextY, grid.length, grid[0].length) && grid[nextX][nextY] == '1'
									&& !isVisitedLand[nextX][nextY]) {
								isVisitedLand[nextX][nextY] = true;
								q.add(new Grid_NI(nextX, nextY));
							}
						}
					}
				}
			}
		}

		return numIslands;
	}

	private boolean isValid(int x, int y, int rangeX, int rangeY) {
		return x >= 0 && y >= 0 && x < rangeX && y < rangeY;
	}

	public static void main(String[] args) {

//		char[][] grid = new char[][] { { '1', '1', '1', '1', '0' }, { '1', '1', '0', '1', '0' },
//				{ '1', '1', '0', '0', '0' }, { '0', '0', '0', '0', '0' } };
//		char[][] grid = new char[][] { { '1', '1', '0', '0', '0' }, { '1', '1', '0', '0', '0' },
//				{ '0', '0', '1', '0', '0' }, { '0', '0', '0', '1', '1' } };
//		char[][] grid = new char[][] {};
		char[][] grid = new char[][] {
				{ '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '0', '1', '1' },
				{ '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '0' },
				{ '1', '0', '1', '1', '1', '0', '0', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1' },
				{ '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1' },
				{ '1', '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1' },
				{ '1', '0', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '0', '1', '1', '1', '0', '1', '1', '1' },
				{ '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '0', '1', '1', '1', '1' },
				{ '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '0', '1', '1' },
				{ '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1' },
				{ '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1' },
				{ '0', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1' },
				{ '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1' },
				{ '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1' },
				{ '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1' },
				{ '1', '0', '1', '1', '1', '1', '1', '0', '1', '1', '1', '0', '1', '1', '1', '1', '0', '1', '1', '1' },
				{ '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '1', '0' },
				{ '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '0', '0' },
				{ '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1' },
				{ '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1' },
				{ '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1',
						'1' } };

		System.out.println(new NumberOfIslands().numIslands(grid));
	}
}

class Grid_NI {
	int x;
	int y;

	public Grid_NI(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "[" + x + "][" + y + "]";
	}
}