import java.util.List;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

/**
* The triangle maximum sum path problem solution
* Use dynamic programming to solve the problem.
* space cost O(h) where h is the length of one side of the triangle
*
* @author  Yifei
* @since   2015-10-19
* 
*/
public class TriangleSolver {
	
	
	/**
	 * Return a list which is the split entire content of the file opened. 
	 * 
	 * @param   fileName   a String giving the name and path of the file
	 * @return  rst        a list contains int arrays which are split lines
	 */
	private List<List<Integer>> readFile(String fileName) {
		List<List<Integer>> rst = new ArrayList<>();

		try (BufferedReader br = 
				new BufferedReader(new FileReader(fileName))) {
			
			String line = br.readLine();
			while (line != null) {
				String[] tokens = line.split("\\s");
				List<Integer> nums = new ArrayList<>();
				for (String token : tokens) {
					nums.add(Integer.parseInt(token));
				}
				rst.add(nums);
				line = br.readLine();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return rst;
	}
	
	/**
	 * Return a integer which is the sum of the maximum path in the triangle 
	 * 
	 * @param   triangle   a list representing the triangle
	 * @return          	 a integer: the sum of the maximum
	 */
	private int maximumTotal(List<List<Integer>> triangle) {
		if (triangle == null || triangle.size() == 0 
			|| triangle.get(0) == null || triangle.get(0).size() == 0) {
			
			return 0;
		}

		int n = triangle.size() - 1;
		int[] state = new int[n + 1];
		for (int i = 0; i <= n; i++) {
			state[i] = triangle.get(n).get(i);
		}
				
		for (int i = n - 1; i >= 0; i--) {
			for (int j = 0; j <= i; j++) {
				int val = triangle.get(i).get(j);
				state[j] = Math.max(val + state[j], val + state[j + 1]);
			}
		}
		
		return state[0];
	}
	
	/**
	 * Main function 
	 * 
	 * @param   args   could be a string indicating the file name
	 */
	public static void main(String[] args) {
		String fileName = args.length > 0 ? args[0] : "triangle.txt";
		TriangleSolver solver = new TriangleSolver();
		List<List<Integer>> triangle = solver.readFile(fileName);
		System.out.println(solver.maximumTotal(triangle));
	}
}
