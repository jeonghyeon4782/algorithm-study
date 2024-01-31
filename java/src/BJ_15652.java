import java.util.Scanner;

public class BJ_15652 {

    static int n, m;
    static int[] input;

    public static void comb(int cnt, int start) {
        if (cnt == m) {
            for (int num : input) {
                System.out.print(num + " ");
            }
            System.out.println();
            return;
        }

        for (int i = start; i < n + 1; i ++) {
            input[cnt] = i;
            comb(cnt + 1, i);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        input = new int[m];
        comb(0, 1);
    }
}
