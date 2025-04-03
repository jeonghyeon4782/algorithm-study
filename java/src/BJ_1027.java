import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1027 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] buildings = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            buildings[i] = Integer.parseInt(st.nextToken());
        }
        int answer = 0;
        for (int i = 0; i < N; i++) {
            int cnt = 0;
            double min = Double.POSITIVE_INFINITY;
            double max = Double.NEGATIVE_INFINITY;

            for (int j = i - 1; j >= 0; j--) {
                double t = (double) (buildings[i] - buildings[j]) / (double) (i - j);
                if (min > t) {
                    ++cnt;
                    min = t;
                }
            }

            for (int j = i + 1; j < N; j++) {
                double t = (double) (buildings[i] - buildings[j]) / (double) (i - j);
                if (max < t) {
                    ++cnt;
                    max = t;
                }
            }

            answer = Math.max(answer, cnt);
        }

        System.out.println(answer);
    }
}
