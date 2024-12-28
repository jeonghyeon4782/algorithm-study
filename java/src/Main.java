import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int n, r;
    static int[] input;
    static int[] nums;
    static int[] isSelected;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        nums = new int[n];
        isSelected = new int[n];
        r = Integer.parseInt(st.nextToken());
        input = new int[r];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        comb(0);
    }

    private static void comb(int cnt) {
        if (cnt == n) {
            System.out.println(Arrays.toString(isSelected));
            return;
        }

        isSelected[cnt] = 1;
        comb(cnt + 1);
        isSelected[cnt] = 2;
        comb(cnt + 1);
        isSelected[cnt] = 3;
        comb(cnt + 1);
        isSelected[cnt] = 4;
        comb(cnt + 1);
    }
}
