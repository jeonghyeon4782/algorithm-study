import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_K번째_접미어 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int test = Integer.parseInt(br.readLine());
        for (int T = 1; T <= test; T++) {
            int N = Integer.parseInt(br.readLine());
            String s = br.readLine();
            char[] sArr = s.toCharArray();
            String[] arr = new String[sArr.length];
            StringBuilder sb = new StringBuilder();
            int idx = 0;
            for (int i = sArr.length - 1; i >= 0; i--) {
                sb.insert(0, sArr[i]);
                arr[idx++] = sb.toString();
            }
            Arrays.sort(arr);
            System.out.printf("#%d %s\n", T, arr[N - 1]);
        }
    }
}
