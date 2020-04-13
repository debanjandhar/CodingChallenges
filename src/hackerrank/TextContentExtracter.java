package hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class TextContentExtracter {

	private static void printTextContent(String line) {
		if (!line.startsWith("<")) {
			System.out.println("None");
			return;
		}

		Stack<String> st = new Stack<String>();
		boolean isPrintable = true, isCloseTag = false, withinOpenTag = false;
		String tagStr = "";
		StringBuilder outputStr = new StringBuilder();
		for (char c : line.toCharArray()) {
			switch (c) {
			case '<':
				if (isPrintable) {
					isPrintable = false;
				} else {
					System.out.println("None");
					return;
				}
				break;
			case '>':
				isPrintable = true;
				// Validations go here
				if (isCloseTag) {
					// Check if we are able to close the tag. If not, print NONE and exit.
					if (st.peek().equals(tagStr)) {
						st.pop();
						isCloseTag = false;
						withinOpenTag = false;
						// All open tags are closed, so start next string from new tag.
						if (st.isEmpty()) {
							outputStr.append("\n");
						}
					} else {
						System.out.println("None");
						return;
					}
				} else {
					withinOpenTag = true;
					st.push(tagStr);
				}
				tagStr = "";
				break;
			case '/':
				isCloseTag = true;
				break;
			default:
				if (isPrintable) {
					if (withinOpenTag) {
						outputStr.append(c);
					} else {
						System.out.println("None");
						return;
					}
				} else {
					tagStr += c;
				}
			}
		}

		// Removing last new line from the string.

		if (st.isEmpty()) {
			System.out.println(outputStr.toString().substring(0, outputStr.length() - 1));
		} else {
			System.out.println("None");
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		int numTestCases = Integer.parseInt(br.readLine());
//		for (int i = 0; i < numTestCases; i++) {
//			String input = br.readLine();
//			printTextContent(input);
//		}

//		printTextContent("<h1><h1>Sanjay has no watch</h1></h1><par>So wait for a while</par>");
//		printTextContent("<h1>Nayeem loves counseling</h1>");
//		printTextContent("<Amee>safat codes like a ninja</amee>");
//		printTextContent("<SA premium>Imtiaz has a secret crush</SA premium>");
//		printTextContent("<h1><a>contents</a>invalid</h1>");
//		printTextContent("<h1><h1></h1></h1>");
//		printTextContent("<par>So wait for a while<par>");
		printTextContent("<>hello</><h>dim</h>");
	}
}
