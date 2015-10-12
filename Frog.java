/* A frog tries to jump across a river.
   The frog is located at position 0 and want to
   go the the other side at position X. The longest
   distance the frog is able to jumpis D. If X > D,
   then the frog cannot jump to the other side. But
   every second there is a leaf falling on the river.
   The element A[i] in array A represents the position
   where a leaf will fall in the ith second.
   Worst case time complexity: O(n)
   Space complexity: O(X/D)
   This solution returns the first time when the frog
   can get to the other side of the river. If it cannot,
   return -1;
*/

public class Frog {
	
	public int jump(int[] A, int D, int X) {
		if (D >= X) {
			return 0;
		}
		if (A == null || A.length == 0) {
			return -1;
		}
		
		int n = (int) Math.ceil((double) X / D);
		Range[] gaps = new Range[n];
		gaps[0] = new Range(0, 0);
		System.out.println("range size: " + gaps.length);
		for (int i = 1; i < n - 1; i++) {
			gaps[i] = new Range();
		}
		gaps[n - 1] = new Range(X, X);
		
		int connectionCount = 0;
		for (int i = 0; i < A.length; i++) {
			int index = A[i] / D;
			Range curr = gaps[index];
			if (curr.isEmpty()) {
				curr.setMax(A[i]);
				curr.setMin(A[i]);
				if (index != 0) {
					Range prev = gaps[index - 1];
					if (!prev.isEmpty() && A[i] - prev.getMax() <= D) {
						prev.setNext();
						curr.setPrev();
						connectionCount++;
					}
				}
				if (index != n - 1) {
					Range next = gaps[index + 1];
					if (!next.isEmpty() && next.getMin() - A[i] <= D) {
						next.setPrev();
						curr.setNext();
						connectionCount++;
					}
				}
			} else {	// curr is not empty, need to be modified
				if (A[i] > curr.getMax()) {
					curr.setMax(A[i]);
					if (index != n - 1 && !curr.getNext()) {
						Range next = gaps[index + 1];
						if (!next.isEmpty() && next.getMin() - A[i] <= D) {
							next.setPrev();
							curr.setNext();
							connectionCount++;
						}
					}
				}
				if (A[i] < curr.getMin()) {
					curr.setMin(A[i]);
					if (index != 0 && !curr.getPrev()) {
						Range prev = gaps[index - 1];
						if (!prev.isEmpty() && A[i] - prev.getMax() <= D) {
							prev.setNext();
							curr.setPrev();
							connectionCount++;
						}
					}
				}
			}
			System.out.println(connectionCount);
			if (connectionCount >= n - 1) {
				return i;
			}	
		}
		return -1;
	}
	

	public static void main(String[] args) {
		int D = (int) (Math.random() * 100000);
		int X = D + (int) (Math.random() * 1000000);
		int n = (int) (Math.random() * 10000000); 
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = (int) (Math.random() * X);
		}
		Frog frog = new Frog();
		System.out.println(frog.jump(A, D, X));
	}
}

class Range {
	private int min;
	private int max;
	private boolean connectedWithPrev = false;
	private boolean connectedWithNext = false;
	public Range() {
		this.min = -1;
		this.max = -1;
	}
	public Range(int min, int max) {
		this.min = min;
		this.max = max;
	}
	public boolean isEmpty() {
		return this.min == -1 && this.max == -1;
	}
	public void setMin(int min) {
		this.min = min;
	}
	public void setMax(int max) {
		this.max = max;
	}
	public int getMin() {
		return this.min;
	}
	public int getMax() {
		return this.max;
	}
	public void setPrev() {
		this.connectedWithPrev = true;
	}
	public void setNext() {
		this.connectedWithNext = true;
	}
	public boolean getPrev() {
		return this.connectedWithPrev;
	}
	public boolean getNext() {
		return this.connectedWithNext;
	}
}
