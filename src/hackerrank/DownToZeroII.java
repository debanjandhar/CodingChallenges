package hackerrank;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class DownToZeroII {

	/*
	 * Complete the downToZero function below.
	 */
	static int downToZero(int n) {
		Queue<Data> q = new LinkedList<Data>();
		q.add(new Data(n, 0));

		return makeZero(q);
	}

	private static int makeZero(Queue<Data> q) {
		Data currNum = q.remove();

		if (currNum.value == 0) {
			return currNum.distFromSource;
		}

		if (currNum.value <= 4) {
			if (currNum.value == 4)
				return currNum.distFromSource + 3;
			else
				return currNum.distFromSource + currNum.value;
		}

		q.add(new Data(currNum.value - 1, currNum.distFromSource + 1));

		for (int i = (int) Math.sqrt(currNum.value); i >= 2; i--) {
			if (currNum.value % i == 0) {
				q.add(new Data(currNum.value / i, currNum.distFromSource + 1));
			}
		}

		System.out.println("Output of queue is " + q);

		return makeZero(q);
	}

	public static void main(String[] args) throws IOException {

//		System.out.println(downToZero(966514));
//		System.out.println(downToZero(812849));
//		System.out.println(downToZero(808707));
//		System.out.println(downToZero(360422));
//		System.out.println(downToZero(691410));
//		System.out.println(downToZero(691343));
//		System.out.println(downToZero(94));
		System.out.println(downToZero(192658));
	}
}

class Data {
	int value;
	int distFromSource;

	public Data(int value, int distFromSource) {
		this.value = value;
		this.distFromSource = distFromSource;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[" + value + ", " + distFromSource + "]";
	}
}
