package hackerrank;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Waiter {

	static int[] waiter(int[] stackA, int q) {
		List<Integer> outputList = new ArrayList<Integer>();

		List<Integer> primes = getNPrimeNumbers(1200);

		Stack<Integer> a = new Stack<Integer>();
		Stack<Integer> b = new Stack<Integer>();

		for (int i = 0; i < stackA.length; i++) {
			a.push(stackA[i]);
		}

		int iteration = 1;
		while (iteration <= q) {

			Stack<Integer> tempA = new Stack<Integer>();
			while (!a.isEmpty()) {
				int currElement = a.pop();
				if (currElement % primes.get(iteration-1) == 0) {
					b.push(currElement);
				} else {
					tempA.push(currElement);
				}
			}

			a = tempA;
			iteration++;
			flushStack(b, outputList);
		}

		flushStack(a, outputList);

		return getIntArrayFromList(outputList);
	}

	private static void flushStack(Stack<Integer> inputStack, List<Integer> outputList) {
		while (!inputStack.isEmpty()) {
			outputList.add(inputStack.pop());
		}
	}

	private static int[] getIntArrayFromList(List<Integer> intputArr) {
		int[] finalArr = new int[intputArr.size()];

		int index = 0;
		for (int i : intputArr) {
			finalArr[index++] = i;
		}

		return finalArr;
	}

	private static List<Integer> getNPrimeNumbers(int n) {
		List<Integer> primeNos = new ArrayList<Integer>();
		int start = 0, primeCheckStart = 2, countOfPrimes = 1;
		boolean calPrimeFl = true;

		// Calculate nth prime in chunks of 10000
		while (calPrimeFl) {
			int CHUNK_SIZE = 100000;
			boolean[] primes = new boolean[CHUNK_SIZE];

			for (int i = start; i < CHUNK_SIZE; i++) {
				primes[i] = true;
			}

			for (int i = primeCheckStart; i * i <= CHUNK_SIZE; i++) {
				if (primes[i] == true) {
					for (int j = i * i; j < CHUNK_SIZE; j += i) {
						primes[j] = false;
					}
				}
			}

			for (int i = primeCheckStart; i < CHUNK_SIZE; i++) {
				if (primes[i]) {
					if (countOfPrimes > n) {
						calPrimeFl = false;
						break;
					}
					primeNos.add((Integer) i);

					countOfPrimes++;
				}
			}

			start += CHUNK_SIZE;
			primeCheckStart = start;
		}

		return primeNos;
	}

	public static void main(String[] args) throws IOException {
		int[] stackA = new int[] { 3, 3, 4, 4, 9 };
		int numQueries = 2;

		System.out.println(Arrays.toString(waiter(stackA, numQueries)));
	}
}
