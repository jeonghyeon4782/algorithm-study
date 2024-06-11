import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BJ_20437 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            String str = br.readLine();
            int N = Integer.parseInt(br.readLine());

            if (N == 1) {
                System.out.println("1 1");
                continue;
            }

            ArrayList<Integer>[] positions = new ArrayList[26];
            for (int i = 0; i < 26; i++) {
                positions[i] = new ArrayList<>();
            }

            for (int i = 0; i < str.length(); i++) {
                positions[str.charAt(i) - 'a'].add(i);
            }

            int maxLength = 0;
            int minLength = Integer.MAX_VALUE;

            for (int i = 0; i < 26; i++) {
                if (positions[i].size() < N) continue;

                for (int j = 0; j <= positions[i].size() - N; j++) {
                    int length = positions[i].get(j + N - 1) - positions[i].get(j) + 1;
                    minLength = Math.min(minLength, length);
                    maxLength = Math.max(maxLength, length);
                }
            }

            if (minLength == Integer.MAX_VALUE) {
                System.out.println(-1);
            } else {
                System.out.println(minLength + " " + maxLength);
            }
        }
    }
}
