import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_12891 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
        char[] str = br.readLine().toCharArray();
        int[] target = new int[4];
        int ans = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            target[i] = Integer.parseInt(st.nextToken());
        }
        int[] now = new int[4];
        for (int i = 0; i < P; i++) {
            if (str[i] == 'A') now[0]++;
            if (str[i] == 'C') now[1]++;
            if (str[i] == 'G') now[2]++;
            if (str[i] == 'T') now[3]++;
        }
        if (check(target, now)) ans++;
        int idx = P;
        while (idx < str.length) {
            char first = str[idx - P];
            char last = str[idx];
            if (first == 'A') now[0]--;
            if (first == 'C') now[1]--;
            if (first == 'G') now[2]--;
            if (first == 'T') now[3]--;
            if (last == 'A') now[0]++;
            if (last == 'C') now[1]++;
            if (last == 'G') now[2]++;
            if (last == 'T') now[3]++;
            if (check(target, now)) ans++;
            ++idx;
        }
        System.out.println(ans);
    }

    private static boolean check(int[] target, int[] now) {
        for (int i = 0; i < 4; i++) {
            if (target[i] > now[i]) return false;
        }
        return true;
    }
}
