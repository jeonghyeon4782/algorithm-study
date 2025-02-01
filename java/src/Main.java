import java.util.Arrays;

public class Main {
    static int[] input = new int[10];
    static int N = 5;

    public static void main(String[] args) {
        comb(0, N);
    }

    private static void comb(int cnt, int arrow) {
        if (cnt == 9) {
            input[cnt] = arrow;
            System.out.println(Arrays.toString(input));
            return;
        }

        for (int i = 0; i <= arrow; i++) {
            input[cnt] = i;
            comb(cnt + 1, arrow - i);
        }
    }
}
