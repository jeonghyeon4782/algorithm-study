import java.util.Arrays;

public class PG_92335 {
    public static void main(String[] args) {
        solution(110011, 10);
    }

    public static int solution(int n, int k) {
        int answer = -1;
        int cnt = 0;
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            sb.append(String.valueOf(n % k));
            n /= k;
        }
        sb.reverse();
        String[] s = sb.toString().split("0");
        for (String i : s) {
            if (i.equals("")) continue;
            int num = Integer.parseInt(i);
            if (solve(num)) cnt++;
        }
        answer = (cnt != 0) ? cnt : -1;
        System.out.println(answer);
        return answer;
    }

    private static boolean solve(int num) {
        if (num == 1) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}
