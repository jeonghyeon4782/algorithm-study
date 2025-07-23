import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA_촛불_이벤트 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int T = 1; T <= TC; T++) {
            long N = Long.parseLong(br.readLine());
            long answer = -1;
            long s = (long) 1;
            long e = (long) 2e9;
            while (s <= e) {
                long m = (s + e) / 2;
                long total = m * (m + 1) / 2;
                if (total == N) {
                    answer = m;
                    break;
                } else if (total < N) {
                    s = m + 1;
                } else {
                    e = m - 1;
                }
            }
            sb.append("#").append(T).append(" ").append(answer).append("\n");
        }
        System.out.print(sb);
    }
}
