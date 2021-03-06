package hackerrank;
import java.io.IOException;

/**
 * @author debanjandhar
 *
 *         Question :
 *         https://www.hackerrank.com/challenges/largest-rectangle/problem
 */
public class LargestRectangle {

	// Complete the largestRectangle function below.
	static long largestRectangle(int[] h) {
		long maxArea = Long.MIN_VALUE;

		int[] newH = new int[h.length];
		int numElements = h.length;

		// Here we're comparing each element with previous element. If current element
		// is first element, greater than or equal to previous element then inserting
		// the element in new array. If the element is smaller than the previous element
		// then we'll traverse backward until we find an element equal to or less than
		// the current element, updating each element with the value of current index
		// and calculating the max area in the process.
		for (int i = 0; i < numElements; i++) {
			if (i == 0) {
				newH[i] = h[i];
			} else if (h[i] >= h[i - 1]) {
				newH[i] = h[i];
			} else {
				// current index is smaller than previous index
				newH[i] = h[i];

				// traverse backwards
				int tmp = i - 1;
				// Indexing with 1, distance between current and previous element is 2.
				int tmpDist = 2;
				while (tmp >= 0 && h[i] < newH[tmp]) {
					long tmpMaxArea = Long.MIN_VALUE;

					if (newH[i] * tmpDist > newH[tmp] * (tmpDist - 1)) {
						tmpMaxArea = newH[i] * tmpDist;
					} else {
						tmpMaxArea = newH[tmp] * (tmpDist - 1);
					}

					if (tmpMaxArea > maxArea) {
						maxArea = tmpMaxArea;
					}

					newH[tmp] = h[i];

					tmpDist++;
					tmp--;
				}

			}
		}

		// new array now which has elements with increasing value.

		for (int i = h.length - 2; i >= 0; i--) {
			int dist = h.length - i;
			if (newH[i] * dist > maxArea) {
				maxArea = newH[i] * dist;
			}
		}

		return maxArea;

	}

	public static void main(String[] args) throws IOException {
//		int[] h = new int[] { 2, 4, 6, 8, 7, 3, 5, 2 };
//		int[] h = new int[] { 1, 2, 3, 4, 5 };
//		int[] h = new int[] { 10, 6320,6020,6098,1332,7263,672,9472,28338,3401,9494 };
		int[] h = new int[] { 11, 11, 10, 10, 10 };

		System.out.println(largestRectangle(h));
	}
}
