import java.util.Arrays;
import java.util.Scanner;

public class BJ_10974 {
    static int[] input;
    static boolean[] isSelected;
    static int N;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        input = new int[N];
        isSelected = new boolean[N + 1];
        perm(0);
    }

    private static void perm(int cnt) {
        if (cnt == N) {
            Arrays.stream(input)
                    .forEach((x) -> System.out.print(x + " "));
            System.out.println();
            return;
        }

        for (int i = 1; i <= N; i++) {
            if (isSelected[i]) continue;
            input[cnt] = i;
            isSelected[i] = true;
            perm(cnt + 1);
            isSelected[i] = false;
        }
    }
}
