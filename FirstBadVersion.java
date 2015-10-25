/**
 *  use binary search to find first bad version
 *  time complexity O(log n)
 */

public class FirstBadVersion {
    public int firstBadVersion(int n) {
        if (n <= 0) {
            return 0;
        }
        int start = 1;
        int end = n;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (isBadVersion(mid)) {
                end = mid;
            } else {
                start = mid;
            }
        }
        return isBadVersion(start) ? start : end;
    }
}
