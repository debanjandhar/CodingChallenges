package hackerrank;

/**
 * @author debanjandhar
 * 
 * Question : https://www.hackerrank.com/challenges/kangaroo/problem?isFullScreen=true
 *
 */
import java.io.IOException;

public class Kangaroos {

	// Complete the kangaroo function below.
	static String kangaroo(int x1, int v1, int x2, int v2) {
		int behind, ahead, behindV, aheadV;
		String no = "NO", yes = "YES";

		if (x1 == x2) {
			// If both are at the same point, then they must have same velocity otherwise
			// one will always lead.
			if (v1 == v2) {
				return yes;
			}
			return no;
		}

		if (x1 > x2) {
			// Since x1 is already ahead and it's velocity is more than or equal to x2, then it'll
			// always lead.
			if (v1 >= v2) {
				return no;
			}

			behind = x2;
			behindV = v2;
			ahead = x1;
			aheadV = v1;

		} else {
			if (v2 >= v1) {
				return no;
			}
			behind = x1;
			behindV = v1;
			ahead = x2;
			aheadV = v2;
		}

		// If the distance becomes negative, it means that behind has catched up and
		// moved ahead. So it will always lead.
		while ((ahead - behind) >= 0) {
			behind += behindV;
			ahead += aheadV;

			if (ahead - behind == 0) {
				return yes;
			}
		}

		return no;
	}

	public static void main(String[] args) throws IOException {

//		String result = kangaroo(0, 3, 4, 2);
//		String result = kangaroo(21, 6, 47, 3);
		String result = kangaroo(43,2,70,2);
		System.out.println(result);
	}
}
