import java.util.Arrays;
import java.util.Scanner;

public class BJ_6603 {
    static int[] input, nums;
    static int N;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            N = sc.nextInt();
            if (N == 0) return;
            input = new int[6];
            nums = new int[N];
            for (int i = 0; i < N; i++) {
                nums[i] = sc.nextInt();
            }
            comb(0, 0);
            System.out.println();
        }
    }

    private static void comb(int cnt, int start) {
        if (cnt == 6) {
            Arrays.stream(input)
                    .forEach((x) -> System.out.print(x + " "));
            System.out.println();
            return;
        }
        for (int i = start; i < N; i++) {
            input[cnt] = nums[i];
            comb(cnt + 1, i + 1);
        }
    }
}
