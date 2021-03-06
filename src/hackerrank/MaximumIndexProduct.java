package hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author debanjandhar
 * 
 *         Question :
 *         https://www.hackerrank.com/challenges/find-maximum-index-product/problem
 */
public class MaximumIndexProduct {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int numElements = Integer.parseInt(br.readLine());

		long startTime = System.currentTimeMillis();

		String[] nums = br.readLine().split(" ");
		long[] original = new long[numElements];
		int[] leftToRight = new int[numElements], rightToLeft = new int[numElements];

		for (int i = 0; i < numElements; i++) {
			original[i] = Long.parseLong(nums[i]);
			if (i == 0 || i == numElements - 1) {
				// First and last elements won't have left index and right index elements
				// respectively.
				leftToRight[i] = -1;
			} else if (original[i] < original[i - 1]) {
				leftToRight[i] = i - 1;
			} else if (original[i] == original[i - 1]) {
				leftToRight[i] = leftToRight[i - 1];
			} else {
				int prevLargeEleIndex = leftToRight[i - 1];
				while (prevLargeEleIndex != -1 && original[prevLargeEleIndex] <= original[i]) {
					prevLargeEleIndex = leftToRight[prevLargeEleIndex];
				}

				leftToRight[i] = prevLargeEleIndex;
			}
		}

		for (int i = numElements - 1; i >= 0; i--) {
			if (i == 0 || i == numElements - 1) {
				rightToLeft[i] = -1;
			} else if (original[i] < original[i + 1]) {
				rightToLeft[i] = i + 1;
			} else if (original[i] == original[i + 1]) {
				rightToLeft[i] = rightToLeft[i + 1];
			} else {
				int nextLargeEleIndex = rightToLeft[i + 1];
				while (nextLargeEleIndex != -1 && original[nextLargeEleIndex] <= original[i]) {
					nextLargeEleIndex = rightToLeft[nextLargeEleIndex];
				}

				rightToLeft[i] = nextLargeEleIndex;
			}
		}

		long max = Long.MIN_VALUE;
		for (int i = 0; i < numElements; i++) {
			long tmp = (leftToRight[i] + 1l) * (rightToLeft[i] + 1l);
			if (tmp > max) {
				max = tmp;
			}
		}

		System.out.println(max);
		System.out.println("Total time : " + (System.currentTimeMillis() - startTime));
	}
}
