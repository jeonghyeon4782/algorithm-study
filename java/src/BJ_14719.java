import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BJ_14719 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int h = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        int answer = 0;
        int[] rains = new int[w];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < w; i++) {
            rains[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i < w - 1; i++) {
            int left = 0;
            int right = 0;
            int target = 0;
            for (int r = 0; r < i; r++) {
                right = Math.max(right, rains[r]);
            }
            for (int l = i; l < w; l++) {
                left = Math.max(left, rains[l]);
            }
            target = Math.min(right, left);

            if (rains[i] < target) {
                answer += target - rains[i];
            }
        }

        System.out.println(answer);
    }
}
