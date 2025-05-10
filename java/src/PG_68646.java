import java.util.*;

class PG_68646 {
    public int solution(int[] a) {
        int answer = 0;
        int n = a.length;
        boolean[] left = new boolean[n];
        boolean[] right = new boolean[n];

        int min = a[0];

        for (int i = 1; i < n; i++) {
            if (a[i] > min) left[i] = true;
            if (a[i] < min) min = a[i];
        }

        min = a[n - 1];

        for (int i = n - 1; i >= 0; i--) {
            if (a[i] > min) right[i] = true;
            if (a[i] < min) min = a[i];
        }

        // System.out.println(Arrays.toString(left));
        // System.out.println(Arrays.toString(right));

        for (int i = 0; i < n; i++) {
            if (left[i] && right[i]) continue;
            answer++;
        }

        return answer;
    }
}