/**
 * Given a integer, present it as a sum of some numbers 
 * in Fibonacci Sequence without the first two numbers (0, 1)
 * For example, the Fibonacci Sequence is 1, 2, 3, 5, 8, 13...
 * Now we have a number 10 which is equal to 8 + 2.
 * So we present it 10010 which means 8*1 + 5*0 + 3*0 + 2*1 + 1*0
 */

import java.util.ArrayList;
import java.util.List;

class FibonacciSum {
	public String FibonacciSum(int n) {
		if (n <= 0) {
			return null;
		}
		
		if (n == 1) {
			return "1";
		}
		
		if (n == 2) {
			return "10";
		}
		
		ArrayList<Integer> fib = buildF(n);
		
		List<Integer> rst = new ArrayList<>();
		
		int index = fib.size() - 1;
		while (n > fib.get(index)) {
			rst.add(fib.get(index));
			n = n - fib.get(index);
			index = search(fib, n, index);
		}
		rst.add(n);

		for (int i = 0; i < rst.size(); i++) {
			System.out.println(rst.get(i));
		}
		
		StringBuilder sb = new StringBuilder();
		index = 0;
		for (int j = fib.size() - 1; j >= 0; j--) {
			if (index < rst.size() && fib.get(j) == rst.get(index)) {
				sb.append(1);
				index++;
			} else {
				sb.append(0);
			}
		}
		
		return sb.toString();
	}
	
	private int search(ArrayList<Integer> fib, int target, int index) {
		int start = 0;
		int end = index;
		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			if (fib.get(mid) < target) {
				start = mid;
			} else {
				end = mid;
			}
		}
		if (fib.get(end) == target) {
			return end;
		} else {
			return end - 1;
		}
	}
	
	private ArrayList<Integer> buildF(int n) {
		ArrayList<Integer> fib = new ArrayList<>();
		int first = 0;
		int second = 1;
		int current = 0;
		while (current <= n) {
			current = first + second;
			if (current <= n) {
				fib.add(current);
			}
			first = second;
			second = current;
		}
		return fib;
	}
}