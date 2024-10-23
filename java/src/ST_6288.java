import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Metal implements Comparable<Metal> {
    int m; // 금속의 무게
    int p; // 무게 당 가격

    public Metal(int m, int p) {
        this.m = m;
        this.p = p;
    }

    public int compareTo(Metal o) {
        return o.p - this.p;
    }
}

public class ST_6288 {

    static int N, W;
    static Metal[] metals;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        int answer = 0;
        metals = new Metal[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            metals[i] = new Metal(m, p);
        }

        Arrays.sort(metals);

        for (Metal metal : metals) {
            if (W <= metal.m) {
                answer += W * metal.p;
                break;
            } else {
                W -= metal.m;
                answer += metal.m * metal.p;
            }
        }

        System.out.println(answer);
    }
}
