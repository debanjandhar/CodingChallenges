package hackerrank;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author debanjandhar
 * 
 *         Question :
 *         https://www.hackerrank.com/challenges/the-grid-search/problem
 *
 */
public class TheGridSearch {

	// Complete the gridSearch function below.
	static String gridSearch(String[] G, String[] P) {
		String yes = "YES";
		String no = "NO";

		int patternRowLength = P.length;

		for (int row = 0; row < G.length - patternRowLength + 1; row++) {
			String currRow = G[row];
			if (currRow.contains(P[0]) && matchPattern(P, G, row)) {
				return yes;
			}
		}

		return no;
	}

	private static boolean matchPattern(String[] pattern, String[] grid, int startRow) {
		List<Integer> patternAllIndexes = new ArrayList<Integer>();

		for (int i = 0; i < pattern.length; i++) {
			if (!grid[startRow].contains(pattern[i])) {
				return false;
			}

			List<Integer> currentPatternAllStartIndexes = getAlIndexes(grid[startRow], pattern[i]);

			if (patternAllIndexes.isEmpty()) {
				patternAllIndexes = currentPatternAllStartIndexes;
			} else {
				List<Integer> tempPatternAllStartIndexes = new ArrayList<Integer>();
				for (Integer index : currentPatternAllStartIndexes) {
					if (patternAllIndexes.contains(index)) {
						tempPatternAllStartIndexes.add(index);
					}
				}

				if (tempPatternAllStartIndexes.isEmpty()) {
					return false;
				} else {
					patternAllIndexes = tempPatternAllStartIndexes;
				}
			}

			startRow++;
		}

		return true;
	}

	private static List<Integer> getAlIndexes(String str, String pattern) {
		int endIndex = str.length() - pattern.length();
		List<Integer> allIndexes = new ArrayList<Integer>();
		int currIndex = 0;

		while (currIndex <= endIndex) {

			int tempIndex = str.indexOf(pattern, currIndex);
			if (tempIndex != -1) {
				allIndexes.add(tempIndex);
				currIndex = tempIndex;
			}

			currIndex++;
		}

		return allIndexes;
	}

	public static void main(String[] args) throws IOException {

//		String[] G = new String[] { "1234567890", "0987654321", "1111111111", "1111111111", "2222222222" };
//		String[] P = new String[] { "876543", "111111", "111111" };

//		String[] G = new String[] { "7283455864","6731158619","8988242643","3830589324","2229505813","5633845374","6473530293","7053106601","0834282956","4607924137" };
//		String[] P = new String[] { "9505","3845","3530" };

//		String[] G = new String[] { "400453592126560", "114213133098692", "474386082879648", "522356951189169",
//				"887109450487496", "252802633388782", "502771484966748", "075975207693780", "511799789562806",
//				"404007454272504", "549043809916080", "962410809534811", "445893523733475", "768705303214174",
//				"650629270887160" };
//		String[] P = new String[] { "99", "99" };

		String[] G = new String[] { "123412", "561212", "123612", "781234" };
		String[] P = new String[] { "12", "34" };

		System.out.println(gridSearch(G, P));
	}
}
