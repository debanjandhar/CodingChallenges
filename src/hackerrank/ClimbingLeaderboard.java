package hackerrank;
import java.io.IOException;
import java.util.Arrays;

public class ClimbingLeaderboard {

	// Complete the climbingLeaderboard function below.
	static int[] climbingLeaderboard(int[] scores, int[] alice) {
		int[] ranks = new int[scores.length];
		int[] aliceRanks = new int[alice.length];

		int endIndex = -1;
		int currentElement = Integer.MIN_VALUE;

		for (int i = 0; i < ranks.length; i++) {
			if (currentElement != scores[i]) {
				ranks[++endIndex] = scores[i];
				currentElement = scores[i];
			}
		}

		int aliceEndIndex = -1;
		for (int i = 0; i < aliceRanks.length; i++) {
			int currScore = alice[i];
			
			int globalRankApprox = binarySearch(ranks, alice[i], 0, endIndex);
			
			if (currScore >= ranks[globalRankApprox]) {
				aliceRanks[++aliceEndIndex] = globalRankApprox+1;
			}else{
				aliceRanks[++aliceEndIndex] = globalRankApprox+1+1;
			}
		}

		return aliceRanks;
	}

	private static int binarySearch(int[] inputArr, int ele, int start, int end) {
		int mid = (start + end) / 2;
		if (inputArr[mid] == ele) {
			return mid;
		} else if (start == end) {
			// This means the number if not present.
			return start;
		} else if (ele > inputArr[mid]) {
			return binarySearch(inputArr, ele, start, mid);
		} else {
			return binarySearch(inputArr, ele, mid + 1, end);

		}
	}

	public static void main(String[] args) throws IOException {
		int[] result = climbingLeaderboard(new int[] { 100, 100, 50, 40, 40, 20, 10 }, new int[] { 5, 25, 50, 120 });

		System.out.println(Arrays.toString(result));
	}
}
