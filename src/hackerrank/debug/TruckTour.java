package hackerrank.debug;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author debanjandhar
 * 
 *         Question : https://www.hackerrank.com/challenges/truck-tour/forum
 *         
 *         Incomplete, need to debug. 
 *
 */
public class TruckTour {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int numStops = Integer.parseInt(br.readLine());
		long[][] petrolPumpArr = new long[numStops][2];
		Queue<Stop> q = new LinkedList<>();

		for (int i = 0; i < numStops; i++) {
			String[] petrolPump = br.readLine().split(" ");
			petrolPumpArr[i][0] = Long.parseLong(petrolPump[0]);
			petrolPumpArr[i][1] = Long.parseLong(petrolPump[1]);
			q.add(new Stop(i, i == 0 ? numStops - 1 : i - 1));
		}

		System.out.println(getFirstStartPoint(q, petrolPumpArr));

	}

	static int getFirstStartPoint(Queue<Stop> q, long[][] petrolPumps) {
		if (q.isEmpty()) {
			return -1;
		}

		Stop currStop = q.remove();
		int nextStopId = (currStop.id + 1) % petrolPumps.length;

		if (currStop.id == currStop.destination) {
			return nextStopId;
		}

		Stop nextStop = new Stop(nextStopId, currStop.destination);
		if ((petrolPumps[currStop.id][0] >= petrolPumps[currStop.id][1])
				|| (petrolPumps[currStop.id][0] + currStop.petrolLft >= petrolPumps[currStop.id][1])) {
			nextStop.petrolLft = currStop.petrolLft + (petrolPumps[currStop.id][0] - petrolPumps[currStop.id][1]);
			q.add(nextStop);
		}

		return getFirstStartPoint(q, petrolPumps);

	}
}

class Stop {
	int id;
	long petrolLft;
	int destination;

	public Stop(int id, int destination) {
		this.id = id;
		this.petrolLft = 0;
		this.destination = destination;
	}

	@Override
	public String toString() {
		return id + "-" + petrolLft + "-" + destination;
	}
}